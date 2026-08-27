package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_niveles_educativos")
data class NivelEducativoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val nombre: String,
    val descripcion: String? = null,
    val orden: Int = 0,
    val icono: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
