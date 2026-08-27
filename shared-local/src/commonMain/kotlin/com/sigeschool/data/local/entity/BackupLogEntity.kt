package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs_backup")
data class BackupLogEntity(
    @PrimaryKey val idLog: String,
    val fechaInicio: Long,
    val fechaFin: Long?,
    val estado: String, // EXITOSO, FALLIDO, EN_PROGRESO
    val tamanioBytes: Long,
    val rutaArchivo: String?,
    val errorMensaje: String?,
    val esManual: Boolean,
    val metadata: String?
)
