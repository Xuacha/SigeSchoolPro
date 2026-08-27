package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_horarios")
data class HorarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val claseId: Long,
    val diaSemana: Int,
    val horaInicio: String,
    val horaFin: String,
    val aulaId: Long? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
