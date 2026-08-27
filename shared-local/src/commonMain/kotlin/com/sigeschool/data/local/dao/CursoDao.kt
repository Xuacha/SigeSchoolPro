package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.CursoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CursoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(curso: CursoEntity): Long

    @Update
    suspend fun update(curso: CursoEntity)

    @Query("SELECT * FROM academic_cursos WHERE institutionId = :instId AND activo = 1 ORDER BY gradoId, nombre")
    fun getAll(instId: String): Flow<List<CursoEntity>>

    @Query("SELECT * FROM academic_cursos WHERE gradoId = :gradoId AND institutionId = :instId AND activo = 1")
    fun getByGrado(gradoId: Long, instId: String): Flow<List<CursoEntity>>

    @Query("SELECT * FROM academic_cursos WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): CursoEntity?

    @Query("UPDATE academic_cursos SET estudiantesInscritos = estudiantesInscritos + 1 WHERE id = :cursoId AND institutionId = :instId")
    suspend fun incrementarInscritos(cursoId: Long, instId: String)

    @Query("UPDATE academic_cursos SET estudiantesInscritos = estudiantesInscritos - 1 WHERE id = :cursoId AND institutionId = :instId AND estudiantesInscritos > 0")
    suspend fun decrementarInscritos(cursoId: Long, instId: String)

    @Query("DELETE FROM academic_cursos WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)
}
