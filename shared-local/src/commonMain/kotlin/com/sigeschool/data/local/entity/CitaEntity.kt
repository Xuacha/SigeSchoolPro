package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "citas")
data class CitaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val docenteId: String,
    val acudienteId: String,
    val estudianteId: String,
    val fechaCita: Long,
    val estado: String,
    val motivo: String? = null,
    val observaciones: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
