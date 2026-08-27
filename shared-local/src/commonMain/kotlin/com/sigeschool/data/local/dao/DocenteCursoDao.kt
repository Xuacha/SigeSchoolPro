package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.DocenteCursoEntity
import com.sigeschool.data.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocenteCursoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(docenteCurso: DocenteCursoEntity): Long

    @Update
    suspend fun update(docenteCurso: DocenteCursoEntity)

    @Query("SELECT * FROM employee_docente_cursos WHERE docenteId = :docenteId AND institutionId = :instId AND activo = 1")
    fun getCursosByDocente(docenteId: Long, instId: String): Flow<List<DocenteCursoEntity>>

    @Query("SELECT * FROM employee_docente_cursos WHERE cursoId = :cursoId AND institutionId = :instId AND activo = 1")
    fun getDocentesByCurso(cursoId: Long, instId: String): Flow<List<DocenteCursoEntity>>

    @Query("DELETE FROM employee_docente_cursos WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("DELETE FROM employee_docente_cursos WHERE docenteId = :docenteId AND cursoId = :cursoId AND asignaturaId = :asignaturaId")
    suspend fun removeAsignacion(docenteId: Long, cursoId: Long, asignaturaId: Long)

    @Query("""
        SELECT DISTINCT s.* FROM students s
        INNER JOIN academic_matriculas m ON s.id = m.estudianteId
        INNER JOIN academic_clases c ON m.claseId = c.id
        INNER JOIN academic_detalles_oferta det ON c.detalleOfertaId = det.id
        WHERE s.institutionId = :instId AND det.docenteId = :userId
    """)
    fun getStudentsByDocente(userId: String, instId: String): Flow<List<StudentEntity>>
}
