package com.sigeschool.domain.repository

interface KeyBackupRepository {
    suspend fun insertKeyBackupLog(
        idLog: String,
        accion: String,
        fecha: Long,
        usuarioId: String,
        exito: Boolean,
        mensajeError: String?,
        metadata: String?
    )
}
