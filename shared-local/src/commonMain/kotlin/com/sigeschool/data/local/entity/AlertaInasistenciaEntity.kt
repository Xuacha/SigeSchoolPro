package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "alertas_inasistencia",
    indices = [
        Index(value = ["estudianteId", "institutionId"], name = "idx_alertas_estudiante"),
        Index(value = ["estado", "institutionId"], name = "idx_alertas_estado")
    ]
)
data class AlertaInasistenciaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val estudianteId: String,
    val acudienteId: String,
    val directorCursoId: String? = null,
    val jefeAreaId: String? = null,
    val coordinadorId: String? = null,
    val inasistenciasConsecutivas: Int,
    val diasSemana: Int,
    val semanaInicio: Long,
    val semanaFin: Long,
    val nivelAlerta: Int,
    val estado: String,
    val fechaAlerta: Long = 0,
    val fechaResolucion: Long? = null,
    val observaciones: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
