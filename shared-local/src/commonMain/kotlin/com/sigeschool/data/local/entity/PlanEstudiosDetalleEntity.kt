package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_planes_estudios_detalle")
data class PlanEstudiosDetalleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val planEstudiosId: Long,
    val gradoId: Long,
    val asignaturaId: Long,
    val intensidadHorariaMinima: Int = 0,
    val esObligatoria: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
