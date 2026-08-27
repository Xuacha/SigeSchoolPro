package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.PrestamoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamo: PrestamoEntity)

    @Update
    suspend fun update(prestamo: PrestamoEntity)

    @Query("SELECT * FROM library_prestamos WHERE institutionId = :instId")
    fun getAll(instId: String): Flow<List<PrestamoEntity>>

    @Query("SELECT * FROM library_prestamos WHERE estudianteId = :studentId AND institutionId = :instId")
    fun getByStudent(studentId: String, instId: String): Flow<List<PrestamoEntity>>

    @Query("SELECT * FROM library_prestamos WHERE estado = 'ACTIVO' AND fechaDevolucionPrevista < :currentTime AND institutionId = :instId")
    fun getAtrasados(currentTime: Long, instId: String): Flow<List<PrestamoEntity>>

    @Delete
    suspend fun delete(prestamo: PrestamoEntity)
}
