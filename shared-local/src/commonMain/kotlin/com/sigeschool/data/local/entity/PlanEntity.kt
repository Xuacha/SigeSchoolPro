package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "planes",
    indices = [
        Index(value = ["nombre"], name = "idx_planes_nombre")
    ]
)
data class PlanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val descripcion: String? = null,
    val limiteEstudiantes: Int = 0,
    val precioMensual: Double,
    val precioAnual: Double,
    val incluyeNomina: Boolean = false,
    val incluyeCarnets: Boolean = false,
    val incluyeBI: Boolean = false,
    val incluyeSoportePrioritario: Boolean = false,
    val incluyeAPI: Boolean = false,
    val incluyeGestorDedicado: Boolean = false,
    val incluyeImplementacionGuiada: Boolean = false,
    val capacitaciones: Int = 0,
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
