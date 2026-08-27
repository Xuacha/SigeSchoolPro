package com.sigeschool.data.service

import com.sigeschool.domain.repository.BackupRepository
import com.sigeschool.domain.repository.AuthRepository
import com.sigeschool.domain.service.BackupService
import com.sigeschool.domain.service.BackupResult
import com.sigeschool.domain.service.BackupInfo
import com.sigeschool.domain.service.RemoteBackupInfo
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

expect class PlatformBackupHelper {
    suspend fun getDatabaseFile(): ByteArray?
    suspend fun restoreDatabase(bytes: ByteArray): Boolean
}

class BackupServiceImpl(
    private val repository: BackupRepository,
    private val authRepository: AuthRepository,
    private val supabaseClient: SupabaseClient,
    private val platformHelper: PlatformBackupHelper
) : BackupService {

    override suspend fun createBackup(manual: Boolean): Result<BackupResult> {
        val id = "BK_" + Clock.System.now().toEpochMilliseconds()
        val startTime = Clock.System.now().toEpochMilliseconds()
        
        repository.insertLog(
            idLog = id,
            fechaInicio = startTime,
            esManual = manual
        )

        return try {
            val dbBytes = platformHelper.getDatabaseFile() 
                ?: throw Exception("No se pudo obtener el archivo de base de datos")

            val institutionId = authRepository.getCurrentInstitutionId() ?: "INST_UNKNOWN"
            val fileName = "backup_${institutionId}_${startTime}.db"
            
            val bucket = supabaseClient.storage.from("backups")
            bucket.upload(fileName, dbBytes)
            
            val publicUrl = try { bucket.publicUrl(fileName) } catch (e: Exception) { null }

            repository.updateLogSuccess(
                idLog = id,
                fechaFin = Clock.System.now().toEpochMilliseconds(),
                tamanioBytes = dbBytes.size.toLong(),
                rutaArchivo = fileName,
                metadata = "{\"url\":\"$publicUrl\"}"
            )

            Result.success(BackupResult(id, true, dbBytes.size.toLong(), publicUrl))
        } catch (e: Exception) {
            repository.updateLogFailure(
                idLog = id,
                fechaFin = Clock.System.now().toEpochMilliseconds(),
                errorMensaje = e.message ?: "Error desconocido"
            )
            Result.failure(e)
        }
    }

    override fun getBackupLogs(): Flow<List<BackupInfo>> {
        return repository.getBackupLogs()
    }

    override suspend fun restoreBackup(backupId: String): Result<Unit> {
        // Lógica de restauración: descargar de Supabase y pasar al helper de plataforma
        return Result.failure(Exception("No implementado"))
    }

    override suspend fun listRemoteBackups(): Result<List<RemoteBackupInfo>> {
        return try {
            val institutionId = authRepository.getCurrentInstitutionId() ?: return Result.failure(Exception("No autenticado"))
            val bucket = supabaseClient.storage.from("backups")
            val files = bucket.list()
            val filtered = files.filter { it.name.contains(institutionId) }.map {
                RemoteBackupInfo(
                    id = it.id ?: "",
                    name = it.name,
                    size = 0, // Tamaño no disponible directamente en esta versión del SDK sin parsear metadata
                    createdAt = it.createdAt?.toEpochMilliseconds() ?: 0L,
                    url = bucket.publicUrl(it.name)
                )
            }
            Result.success(filtered)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
