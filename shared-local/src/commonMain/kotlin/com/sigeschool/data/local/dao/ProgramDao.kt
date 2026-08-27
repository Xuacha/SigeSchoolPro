package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ProgramEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgram(program: ProgramEntity): Long

    @Update
    suspend fun updateProgram(program: ProgramEntity)

    @Query("SELECT * FROM programs WHERE institutionId = :institutionId AND activo = 1")
    fun getProgramsByInstitution(institutionId: String): Flow<List<ProgramEntity>>

    @Query("SELECT * FROM programs WHERE id = :id AND institutionId = :institutionId LIMIT 1")
    suspend fun getProgramById(id: String, institutionId: String): ProgramEntity?

    @Query("SELECT * FROM programs WHERE codigo = :codigo AND institutionId = :institutionId LIMIT 1")
    suspend fun getByCodigo(codigo: String, institutionId: String): ProgramEntity?

    @Query("SELECT * FROM programs WHERE gradoId = :gradoId AND institutionId = :institutionId LIMIT 1")
    suspend fun getByGrado(gradoId: Long, institutionId: String): ProgramEntity?

    @Delete
    suspend fun deleteProgram(program: ProgramEntity)
}
