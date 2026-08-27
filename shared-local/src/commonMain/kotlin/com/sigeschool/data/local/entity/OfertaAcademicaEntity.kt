package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_ofertas")
data class OfertaAcademicaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val gradoId: Long,
    val periodoAcademicoId: Long,
    val nombre: String,
    val fechaInicio: Long,
    val fechaFin: Long,
    val estado: String = "BORRADOR",
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
