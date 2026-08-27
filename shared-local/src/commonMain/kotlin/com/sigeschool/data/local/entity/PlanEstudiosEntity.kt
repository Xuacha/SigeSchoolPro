package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_planes_estudios")
data class PlanEstudiosEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val nombre: String,
    val descripcion: String? = null,
    val version: String = "1.0",
    val vigente: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
