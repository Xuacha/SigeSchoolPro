package com.sigeschool.data.datasource

import com.sigeschool.domain.service.BackupInfo
import kotlinx.coroutines.flow.Flow

interface BackupLocalDataSource {
    fun getBackupLogs(): Flow<List<BackupInfo>>
    suspend fun insertLog(idLog: String, fechaInicio: Long, esManual: Boolean)
    suspend fun updateLogSuccess(idLog: String, fechaFin: Long, tamanioBytes: Long, rutaArchivo: String, metadata: String?)
    suspend fun updateLogFailure(idLog: String, fechaFin: Long, errorMensaje: String)
}
