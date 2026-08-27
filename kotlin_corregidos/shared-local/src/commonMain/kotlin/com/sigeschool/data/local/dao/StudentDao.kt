package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(students: List<StudentEntity>)

    // FIX: antes devolvía TODOS los estudiantes de TODAS las instituciones
    // guardadas en el dispositivo. Ahora se filtra por institución.
    @Query("SELECT * FROM students WHERE institutionId = :institutionId ORDER BY apellido ASC")
    fun getAllStudents(institutionId: String): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE id = :id")
    suspend fun getStudentById(id: Long): StudentEntity?

    @Query("SELECT * FROM students WHERE dni = :dni LIMIT 1")
    suspend fun getStudentByDni(dni: String): StudentEntity?

    @Update
    suspend fun update(student: StudentEntity)

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE students SET activo = 0 WHERE id = :id")
    suspend fun softDeleteById(id: Long)

    // FIX: la búsqueda tampoco filtraba por institución.
    @Query("SELECT * FROM students WHERE institutionId = :institutionId AND (nombre LIKE '%' || :query || '%' OR apellido LIKE '%' || :query || '%' OR dni LIKE '%' || :query || '%')")
    fun searchStudents(institutionId: String, query: String): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE sincronizado = 0")
    suspend fun getUnsyncedStudents(): List<StudentEntity>
}
