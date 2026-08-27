package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad para registrar los intentos de backup, restauración y rotación de llaves maestras.
 */
@Entity(tableName = "logs_backup_llaves")
data class KeyBackupLogEntity(
    @PrimaryKey val idLog: String,
    val accion: String, // BACKUP, RESTORE, ROTATE
    val fecha: Long,
    val usuarioId: String,
    val exito: Boolean,
    val mensajeError: String?,
    val metadata: String? // JSON con detalles adicionales
)
