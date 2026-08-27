package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Libro(
    val id: String,
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
    val ubicacionFisica: String? = null
)

@Serializable
data class Prestamo(
    val id: String,
    val institutionId: String,
    val libroId: String,
    val estudianteId: String? = null,
    val docenteId: String? = null,
    val fechaPrestamo: Long = 0,
    val fechaDevolucionPrevista: Long,
    val fechaDevolucionReal: Long? = null,
    val estado: String = "ACTIVO", // ACTIVO, DEVUELTO, ATRASADO
    val observaciones: String? = null
)
