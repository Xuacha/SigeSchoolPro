package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "alertas_tempranas")
data class AlertaTempranaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val studentId: String,
    val tipo: String,
    val nivel: String,
    val descripcion: String,
    val fechaDeteccion: Long = 0,
    val estado: String = "ACTIVA",
    val atendidaPor: String? = null,
    val fechaAtencion: Long? = null,
    val observaciones: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
