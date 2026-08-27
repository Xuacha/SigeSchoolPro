package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "preferencias_notificaciones")
data class PreferenciaNotificacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val usuarioId: String,
    val tipoEvento: String,
    val push: Boolean = true,
    val inApp: Boolean = true,
    val email: Boolean = true,
    val sms: Boolean = false,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
