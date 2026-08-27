package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "library_libros")
data class LibroEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val isbn: String? = null,
    val titulo: String,
    val autor: String,
    val editorial: String? = null,
    val anioPublicacion: Int? = null,
    val categoria: String? = null,
    val descripcion: String? = null,
    val ejemplaresTotales: Int = 1,
    val ejemplaresDisponibles: Int = 1,
    val ubicacionFisica: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
