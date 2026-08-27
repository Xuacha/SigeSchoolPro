package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_periodos")
data class PeriodoAcademicoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val nombre: String,
    val tipo: String,
    val fechaInicio: Long,
    val fechaFin: Long,
    val duracionMeses: Int = 0,
    val numeroCortes: Int = 0,
    val esActivo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
