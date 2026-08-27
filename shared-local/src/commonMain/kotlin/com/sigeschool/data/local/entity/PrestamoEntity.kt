package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "library_prestamos")
data class PrestamoEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val libroId: String,
    val estudianteId: String? = null,
    val docenteId: String? = null,
    val fechaPrestamo: Long = 0,
    val fechaDevolucionPrevista: Long,
    val fechaDevolucionReal: Long? = null,
    val estado: String = "ACTIVO",
    val observaciones: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
