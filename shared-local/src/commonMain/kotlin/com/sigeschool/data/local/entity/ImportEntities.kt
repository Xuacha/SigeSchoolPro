package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "importaciones")
data class ImportEntity(
    @PrimaryKey val idImportacion: String,
    val tipo: String,
    val nombreArchivo: String,
    val fechaImportacion: Long,
    val idUsuarioImporto: String,
    val totalRegistros: Int,
    val registrosCreados: Int,
    val registrosActualizados: Int,
    val errores: Int,
    val duplicados: Int,
    val usuariosCreados: Int,
    val notificacionesEnviadas: Int,
    val estado: String = "PROCESANDO",
    val detalleJson: String?
)

@Entity(
    tableName = "importaciones_detalle",
    foreignKeys = [
        ForeignKey(
            entity = ImportEntity::class,
            parentColumns = ["idImportacion"],
            childColumns = ["idImportacion"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ImportDetailEntity(
    @PrimaryKey val idDetalle: String,
    val idImportacion: String,
    val fila: Int,
    val documento: String?,
    val accion: String,
    val mensaje: String?
)
