package com.sigeschool.data.local.repository

import com.sigeschool.data.local.dao.BackupDao
import com.sigeschool.data.local.entity.KeyBackupLogEntity
import com.sigeschool.domain.repository.KeyBackupRepository

class KeyBackupRepositoryImpl(
    private val backupDao: BackupDao
) : KeyBackupRepository {
    override suspend fun insertKeyBackupLog(
        idLog: String,
        accion: String,
        fecha: Long,
        usuarioId: String,
        exito: Boolean,
        mensajeError: String?,
        metadata: String?
    ) {
        backupDao.insertKeyBackupLog(
            KeyBackupLogEntity(
                idLog = idLog,
                accion = accion,
                fecha = fecha,
                usuarioId = usuarioId,
                exito = exito,
                mensajeError = mensajeError,
                metadata = metadata
            )
        )
    }
}
