package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_jornadas")
data class JornadaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val nombre: String,
    val horaInicio: String? = null,
    val horaFin: String? = null,
    val activa: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
