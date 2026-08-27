package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "notificaciones_calificaciones")
data class NotificacionCalificacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val calificacionId: String,
    val institutionId: String,
    val enviadoEstudiante: Boolean = false,
    val enviadoAcudiente: Boolean = false,
    val enviadoDocente: Boolean = false,
    val enviadoCoordinador: Boolean = false,
    val fechaEnvioEstudiante: Long? = null,
    val fechaEnvioAcudiente: Long? = null,
    val fechaEnvioDocente: Long? = null,
    val fechaEnvioCoordinador: Long? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
