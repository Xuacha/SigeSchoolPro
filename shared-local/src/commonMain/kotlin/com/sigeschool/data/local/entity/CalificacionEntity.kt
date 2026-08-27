package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_calificaciones")
data class CalificacionEntity(
    @PrimaryKey
    val id: String,
    val institutionId: String,
    val estudianteId: String,
    val claseId: Long,
    val periodoAcademicoId: Long,
    val corte: Int = 1,
    val nota: Double,
    val observacion: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
