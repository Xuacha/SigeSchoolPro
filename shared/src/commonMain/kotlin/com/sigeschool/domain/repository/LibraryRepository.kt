package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Libro
import com.sigeschool.domain.model.Prestamo
import kotlinx.coroutines.flow.Flow

interface LibraryRepository {
    fun getLibros(institutionId: String): Flow<List<Libro>>
    suspend fun getLibroById(id: String, institutionId: String): Libro?
    suspend fun saveLibro(libro: Libro)
    suspend fun deleteLibro(libro: Libro)

    fun getPrestamos(institutionId: String): Flow<List<Prestamo>>
    fun getPrestamosByEstudiante(estudianteId: String, institutionId: String): Flow<List<Prestamo>>
    suspend fun savePrestamo(prestamo: Prestamo)
    suspend fun registrarDevolucion(prestamoId: String, institutionId: String)
    suspend fun syncLibrary(institutionId: String): Result<Unit>
}
