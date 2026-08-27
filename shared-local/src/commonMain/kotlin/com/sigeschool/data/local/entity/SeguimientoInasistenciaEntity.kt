package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "seguimiento_inasistencia",
    indices = [
        Index(value = ["alertaId", "institutionId"], name = "idx_seguimiento_alerta")
    ]
)
data class SeguimientoInasistenciaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val alertaId: Long,
    val usuarioId: String,
    val accion: String,
    val descripcion: String,
    val fechaSeguimiento: Long = 0,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
