package com.sigeschool.domain.repository

import com.sigeschool.domain.service.BackupInfo
import kotlinx.coroutines.flow.Flow

interface BackupRepository {
    suspend fun insertLog(idLog: String, fechaInicio: Long, esManual: Boolean)
    suspend fun updateLogSuccess(idLog: String, fechaFin: Long, tamanioBytes: Long, rutaArchivo: String, metadata: String)
    suspend fun updateLogFailure(idLog: String, fechaFin: Long, errorMensaje: String)
    fun getBackupLogs(): Flow<List<BackupInfo>>
}
