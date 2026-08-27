package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.MatriculaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatriculaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MatriculaEntity): Long

    @Update
    suspend fun update(entity: MatriculaEntity)

    @Query("SELECT * FROM academic_matriculas WHERE institutionId = :instId AND estudianteId = :estudianteId")
    fun getByEstudiante(instId: String, estudianteId: String): Flow<List<MatriculaEntity>>

    @Query("SELECT * FROM academic_matriculas WHERE institutionId = :instId AND estudianteId = :estudianteId")
    suspend fun getByEstudianteSync(estudianteId: String, instId: String): List<MatriculaEntity>

    @Query("SELECT * FROM academic_matriculas WHERE institutionId = :instId AND claseId = :claseId")
    fun getByClase(instId: String, claseId: Long): Flow<List<MatriculaEntity>>

    @Query("SELECT * FROM academic_matriculas WHERE institutionId = :instId AND claseId = :claseId")
    suspend fun getByClaseSync(claseId: Long, instId: String): List<MatriculaEntity>

    @Query("SELECT * FROM academic_matriculas WHERE institutionId = :instId AND claseId = :claseId AND estudianteId = :estudianteId")
    suspend fun getByClaseAndEstudiante(instId: String, claseId: Long, estudianteId: String): MatriculaEntity?

    @Query("DELETE FROM academic_matriculas WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("DELETE FROM academic_matriculas WHERE claseId = :claseId AND institutionId = :instId")
    suspend fun deleteByClase(claseId: Long, instId: String)

    @Query("SELECT * FROM academic_matriculas WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<MatriculaEntity>

    @Query("SELECT * FROM academic_matriculas WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<MatriculaEntity>

    @Query("UPDATE academic_matriculas SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)

    @Query("UPDATE academic_matriculas SET estudianteId = :targetStudentId, syncStatus = 2 WHERE estudianteId = :sourceStudentId AND institutionId = :instId")
    suspend fun migrateStudentMatriculas(sourceStudentId: String, targetStudentId: String, instId: String)
}
