package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.CitaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CitaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cita: CitaEntity): Long

    @Update
    suspend fun update(cita: CitaEntity)

    @Query("SELECT * FROM citas WHERE institutionId = :instId AND id = :id LIMIT 1")
    suspend fun getById(id: Long, instId: String): CitaEntity?

    @Query("SELECT * FROM citas WHERE docenteId = :docenteId AND institutionId = :instId ORDER BY fechaCita DESC")
    fun getByDocente(docenteId: String, instId: String): Flow<List<CitaEntity>>

    @Query("SELECT * FROM citas WHERE acudienteId = :acudienteId AND institutionId = :instId ORDER BY fechaCita DESC")
    fun getByAcudiente(acudienteId: String, instId: String): Flow<List<CitaEntity>>

    @Query("SELECT * FROM citas WHERE institutionId = :instId ORDER BY fechaCita DESC")
    fun getAllByInstitution(instId: String): Flow<List<CitaEntity>>
}
