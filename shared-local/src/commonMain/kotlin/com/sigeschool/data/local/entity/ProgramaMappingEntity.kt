package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "programa_mapping")
data class ProgramaMappingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val codigoFormulario: String,
    val ofertaAcademicaId: Long,
    val gradoId: Long,
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
