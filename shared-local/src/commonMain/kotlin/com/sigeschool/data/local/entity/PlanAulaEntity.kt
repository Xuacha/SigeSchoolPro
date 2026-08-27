package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_planes_aula")
data class PlanAulaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val claseId: Long,
    val docenteId: String,
    val competencias: String? = null,
    val logros: String? = null,
    val indicadores: String? = null,
    val recursos: String? = null,
    val metodologia: String? = null,
    val evaluacion: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
