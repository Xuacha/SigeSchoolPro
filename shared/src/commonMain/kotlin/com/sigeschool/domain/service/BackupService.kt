package com.sigeschool.domain.service

import kotlinx.coroutines.flow.Flow

interface BackupService {
    suspend fun createBackup(manual: Boolean = false): Result<BackupResult>
    fun getBackupLogs(): Flow<List<BackupInfo>>
    suspend fun restoreBackup(backupId: String): Result<Unit>
    suspend fun listRemoteBackups(): Result<List<RemoteBackupInfo>>
}

data class BackupResult(
    val id: String,
    val success: Boolean,
    val fileSize: Long,
    val storageUrl: String?,
    val error: String? = null
)

data class BackupInfo(
    val id: String,
    val fecha: Long,
    val estado: String,
    val tamanio: Long,
    val esManual: Boolean,
    val error: String? = null
)

data class RemoteBackupInfo(
    val id: String,
    val name: String,
    val size: Long,
    val createdAt: Long,
    val url: String
)
