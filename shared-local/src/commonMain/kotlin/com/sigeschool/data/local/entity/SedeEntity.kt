package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_sedes")
data class SedeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val nombre: String,
    val direccion: String? = null,
    val telefono: String? = null,
    val activa: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
