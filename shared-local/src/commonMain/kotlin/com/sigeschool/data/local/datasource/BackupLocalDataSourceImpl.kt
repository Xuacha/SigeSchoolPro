package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.BackupLocalDataSource
import com.sigeschool.data.local.dao.BackupDao
import com.sigeschool.data.local.entity.BackupLogEntity
import com.sigeschool.domain.service.BackupInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BackupLocalDataSourceImpl(
    private val backupDao: BackupDao
) : BackupLocalDataSource {
    override fun getBackupLogs(): Flow<List<BackupInfo>> {
        return backupDao.getAllLogs().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertLog(idLog: String, fechaInicio: Long, esManual: Boolean) {
        backupDao.insertLog(BackupLogEntity(
            idLog = idLog,
            fechaInicio = fechaInicio,
            fechaFin = null,
            estado = "EN_PROGRESO",
            tamanioBytes = 0,
            rutaArchivo = null,
            errorMensaje = null,
            esManual = esManual,
            metadata = null
        ))
    }

    override suspend fun updateLogSuccess(idLog: String, fechaFin: Long, tamanioBytes: Long, rutaArchivo: String, metadata: String?) {
        val current = backupDao.getLogById(idLog)
        if (current != null) {
            backupDao.updateLog(current.copy(
                fechaFin = fechaFin,
                estado = "EXITOSO",
                tamanioBytes = tamanioBytes,
                rutaArchivo = rutaArchivo,
                metadata = metadata
            ))
        }
    }

    override suspend fun updateLogFailure(idLog: String, fechaFin: Long, errorMensaje: String) {
        val current = backupDao.getLogById(idLog)
        if (current != null) {
            backupDao.updateLog(current.copy(
                fechaFin = fechaFin,
                estado = "FALLIDO",
                errorMensaje = errorMensaje
            ))
        }
    }

    private fun BackupLogEntity.toDomain(): BackupInfo {
        return BackupInfo(
            id = idLog,
            fecha = fechaInicio,
            estado = estado,
            tamanio = tamanioBytes,
            esManual = esManual,
            error = errorMensaje
        )
    }
}
