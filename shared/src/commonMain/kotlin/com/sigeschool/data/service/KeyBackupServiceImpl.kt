package com.sigeschool.data.service

import com.sigeschool.domain.repository.KeyBackupRepository
import com.sigeschool.domain.security.KeyBackupService
import com.sigeschool.domain.util.CryptoManager
import com.sigeschool.domain.util.SessionManager
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class KeyBackupServiceImpl(
    private val supabaseClient: SupabaseClient,
    private val sessionManager: SessionManager,
    private val backupRepository: KeyBackupRepository
) : KeyBackupService {

    override suspend fun backupKey(pin: String): Result<Unit> = withContext(Dispatchers.Default) {
        val institutionId = sessionManager.getCurrentInstitutionId() ?: return@withContext Result.failure(Exception("No institution session"))
        val userId = sessionManager.getCurrentUserId() ?: "unknown"
        val logId = Uuid.random().toString()
        
        try {
            val masterKey = CryptoManager.getMasterKeyBytes()
            if (masterKey.isEmpty()) return@withContext Result.failure(Exception("Could not extract master key"))
            
            val encryptedBackup = CryptoManager.encryptWithPin(masterKey, pin)
            
            val timestamp = Clock.System.now().toEpochMilliseconds()
            val fileName = "backups/$institutionId/$timestamp.key"
            val bucket = supabaseClient.storage.from("key_backups")
            
            bucket.upload(fileName, encryptedBackup) {
                upsert = true
            }
            
            backupRepository.insertKeyBackupLog(
                idLog = logId,
                accion = "BACKUP",
                fecha = timestamp,
                usuarioId = userId,
                exito = true,
                mensajeError = null,
                metadata = buildJsonObject {
                    put("fileName", fileName)
                    put("institutionId", institutionId)
                }.toString()
            )
            
            Result.success(Unit)
        } catch (e: Exception) {
            backupRepository.insertKeyBackupLog(
                idLog = logId,
                accion = "BACKUP",
                fecha = Clock.System.now().toEpochMilliseconds(),
                usuarioId = userId,
                exito = false,
                mensajeError = e.message,
                metadata = null
            )
            Result.failure(e)
        }
    }

    override suspend fun restoreKey(pin: String): Result<ByteArray> = withContext(Dispatchers.Default) {
        val institutionId = sessionManager.getCurrentInstitutionId() ?: return@withContext Result.failure(Exception("No institution session"))
        val userId = sessionManager.getCurrentUserId() ?: "unknown"
        val logId = Uuid.random().toString()

        try {
            val bucket = supabaseClient.storage.from("key_backups")
            val files = bucket.list("backups/$institutionId")
            val latestFile = files.maxByOrNull { it.name } ?: return@withContext Result.failure(Exception("No backup found"))
            
            val encryptedBytes = bucket.downloadPublic("backups/$institutionId/${latestFile.name}")
            val masterKey = CryptoManager.decryptWithPin(encryptedBytes, pin)
            
            CryptoManager.importMasterKey(masterKey)
            
            backupRepository.insertKeyBackupLog(
                idLog = logId,
                accion = "RESTORE",
                fecha = Clock.System.now().toEpochMilliseconds(),
                usuarioId = userId,
                exito = true,
                mensajeError = null,
                metadata = buildJsonObject {
                    put("fileName", latestFile.name)
                }.toString()
            )
            
            Result.success(masterKey)
        } catch (e: Exception) {
            backupRepository.insertKeyBackupLog(
                idLog = logId,
                accion = "RESTORE",
                fecha = Clock.System.now().toEpochMilliseconds(),
                usuarioId = userId,
                exito = false,
                mensajeError = e.message,
                metadata = null
            )
            Result.failure(e)
        }
    }

    override suspend fun rotateKey(oldPin: String, newPin: String): Result<Unit> = withContext(Dispatchers.Default) {
        // Implementation for key rotation if needed
        Result.success(Unit) 
    }

    override suspend fun hasBackup(): Boolean {
        val institutionId = sessionManager.getCurrentInstitutionId() ?: return false
        return try {
            val bucket = supabaseClient.storage.from("key_backups")
            val files = bucket.list("backups/$institutionId")
            files.isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }
}
