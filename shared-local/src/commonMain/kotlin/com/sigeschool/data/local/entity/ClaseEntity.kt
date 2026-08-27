package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_clases")
data class ClaseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val ofertaAcademicaId: Long,
    val detalleOfertaId: Long,
    val nombre: String,
    val horario: String? = null,
    val capacidadMaxima: Int = 30,
    val estudiantesInscritos: Int = 0,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
