package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "servicios")
data class ServicioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val nombre: String,
    val descripcion: String? = null,
    val tipo: String,
    val responsable: String? = null,
    val ubicacion: String? = null,
    val horario: String? = null,
    val notificaAcudiente: Boolean = true,
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
