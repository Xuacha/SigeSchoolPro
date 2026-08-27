package com.sigeschool.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "programa_oferta_mapping",
    indices = [
        Index(value = ["codigoFormulario", "institutionId"], name = "idx_prog_mapping_codigo")
    ]
)
data class ProgramaOfertaMappingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val codigoFormulario: String,
    val ofertaAcademicaId: Long,
    val gradoId: Long,
    @ColumnInfo(defaultValue = "1")
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
