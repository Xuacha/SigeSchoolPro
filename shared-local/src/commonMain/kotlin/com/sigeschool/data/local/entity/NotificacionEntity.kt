package com.sigeschool.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "notificaciones",
    indices = [
        Index(value = ["usuarioId", "institutionId"], name = "idx_notificaciones_usuario"),
        Index(value = ["leida", "institutionId"], name = "idx_notificaciones_leida")
    ]
)
data class NotificacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val usuarioId: String,
    val tipo: String,
    val titulo: String,
    val mensaje: String,
    val canal: String,
    @ColumnInfo(defaultValue = "0")
    val leida: Boolean = false,
    val fecha: Long = 0,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
