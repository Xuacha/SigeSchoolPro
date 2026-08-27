package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_asignaturas")
data class AsignaturaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val areaConocimientoId: Long,
    val nombre: String,
    val codigo: String? = null,
    val descripcion: String? = null,
    val intensidadHoraria: Int = 0,
    val esElectiva: Boolean = false,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
