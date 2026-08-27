package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_detalles_oferta")
data class DetalleOfertaAcademicaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val ofertaAcademicaId: Long,
    val asignaturaId: Long,
    val docenteId: String? = null,
    val intensidadHoraria: Int = 0,
    val aula: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
