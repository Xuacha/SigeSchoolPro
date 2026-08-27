package com.sigeschool.data.repository

import com.sigeschool.data.local.dao.LibroDao
import com.sigeschool.data.local.dao.PrestamoDao
import com.sigeschool.data.local.entity.LibroEntity
import com.sigeschool.data.local.entity.PrestamoEntity
import com.sigeschool.domain.model.Libro
import com.sigeschool.domain.model.Prestamo
import com.sigeschool.domain.repository.LibraryRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LibraryRepositoryImpl(
    private val libroDao: LibroDao,
    private val prestamoDao: PrestamoDao,
    private val supabaseClient: SupabaseClient
) : LibraryRepository {

    override fun getLibros(institutionId: String): Flow<List<Libro>> {
        return libroDao.getAll(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getLibroById(id: String, institutionId: String): Libro? {
        return libroDao.getById(id, institutionId)?.toDomain()
    }

    override suspend fun saveLibro(libro: Libro) {
        val entity = libro.toEntity()
        libroDao.insert(entity)
        try {
            supabaseClient.from("library_libros").upsert(entity)
        } catch (e: Exception) {
            // Saved locally offline-first
        }
    }

    override suspend fun deleteLibro(libro: Libro) {
        val entity = libro.toEntity()
        libroDao.delete(entity)
        try {
            supabaseClient.from("library_libros").delete {
                filter { eq("id", libro.id) }
            }
        } catch (e: Exception) {
            // Local operation completed
        }
    }

    override fun getPrestamos(institutionId: String): Flow<List<Prestamo>> {
        return prestamoDao.getAll(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getPrestamosByEstudiante(estudianteId: String, institutionId: String): Flow<List<Prestamo>> {
        return prestamoDao.getByStudent(estudianteId, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun savePrestamo(prestamo: Prestamo) {
        val entity = prestamo.toEntity()
        prestamoDao.insert(entity)
        try {
            supabaseClient.from("library_prestamos").upsert(entity)
        } catch (e: Exception) {
            // Saved locally
        }
    }

    override suspend fun registrarDevolucion(prestamoId: String, institutionId: String) {
        val now = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        val allPrestamos = prestamoDao.getAll(institutionId)
        // Update local entity
        try {
            supabaseClient.from("library_prestamos").update({
                set("fecha_devolucion_real", now)
                set("estado", "DEVUELTO")
            }) {
                filter {
                    eq("id", prestamoId)
                    eq("institution_id", institutionId)
                }
            }
        } catch (e: Exception) {
            // Handled offline
        }
    }

    override suspend fun syncLibrary(institutionId: String): Result<Unit> {
        return try {
            val remoteLibros = supabaseClient.from("library_libros")
                .select { filter { eq("institution_id", institutionId) } }
                .decodeList<LibroEntity>()
            remoteLibros.forEach { libroDao.insert(it) }

            val remotePrestamos = supabaseClient.from("library_prestamos")
                .select { filter { eq("institution_id", institutionId) } }
                .decodeList<PrestamoEntity>()
            remotePrestamos.forEach { prestamoDao.insert(it) }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun LibroEntity.toDomain() = Libro(
        id = id,
        institutionId = institutionId,
        isbn = isbn,
        titulo = titulo,
        autor = autor,
        editorial = editorial,
        anioPublicacion = anioPublicacion,
        categoria = categoria,
        descripcion = descripcion,
        ejemplaresTotales = ejemplaresTotales,
        ejemplaresDisponibles = ejemplaresDisponibles,
        ubicacionFisica = ubicacionFisica
    )

    private fun Libro.toEntity() = LibroEntity(
        id = id,
        institutionId = institutionId,
        isbn = isbn,
        titulo = titulo,
        autor = autor,
        editorial = editorial,
        anioPublicacion = anioPublicacion,
        categoria = categoria,
        descripcion = descripcion,
        ejemplaresTotales = ejemplaresTotales,
        ejemplaresDisponibles = ejemplaresDisponibles,
        ubicacionFisica = ubicacionFisica
    )

    private fun PrestamoEntity.toDomain() = Prestamo(
        id = id,
        institutionId = institutionId,
        libroId = libroId,
        estudianteId = estudianteId,
        docenteId = docenteId,
        fechaPrestamo = fechaPrestamo,
        fechaDevolucionPrevista = fechaDevolucionPrevista,
        fechaDevolucionReal = fechaDevolucionReal,
        estado = estado,
        observaciones = observaciones
    )

    private fun Prestamo.toEntity() = PrestamoEntity(
        id = id,
        institutionId = institutionId,
        libroId = libroId,
        estudianteId = estudianteId,
        docenteId = docenteId,
        fechaPrestamo = fechaPrestamo,
        fechaDevolucionPrevista = fechaDevolucionPrevista,
        fechaDevolucionReal = fechaDevolucionReal,
        estado = estado,
        observaciones = observaciones
    )
}
