package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Transaction
import com.sigeschool.data.local.entity.StudentEntity
import com.sigeschool.data.local.entity.StudentWithPrograms
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Transaction
    @Query("SELECT * FROM students WHERE institutionId = :institutionId AND deletedAt IS NULL AND isDuplicate = 0")
    fun getAllStudents(institutionId: String): Flow<List<StudentEntity>>

    @Transaction
    @Query("SELECT * FROM students WHERE institutionId = :institutionId AND deletedAt IS NULL AND isDuplicate = 0")
    fun getStudentsWithPrograms(institutionId: String): Flow<List<StudentWithPrograms>>

    @Transaction
    @Query("SELECT * FROM students WHERE id = :id AND institutionId = :institutionId")
    suspend fun getStudentWithProgramsById(id: String, institutionId: String): StudentWithPrograms?

    @Query("SELECT * FROM students WHERE institutionId = :institutionId AND deletedAt IS NULL AND isDuplicate = 0")
    suspend fun getAllStudentsSync(institutionId: String): List<StudentEntity>

    @Query("SELECT * FROM students WHERE id = :id AND institutionId = :institutionId")
    suspend fun getStudentById(id: String, institutionId: String): StudentEntity?

    @Query("SELECT * FROM students WHERE institutionId = :institutionId AND deletedAt IS NULL AND status != 'WITHDRAWN' AND isDuplicate = 0")
    fun getActiveStudents(institutionId: String): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE institutionId = :institutionId AND deletedAt IS NULL AND status != 'WITHDRAWN' AND isDuplicate = 0")
    suspend fun getActiveStudentsSync(institutionId: String): List<StudentEntity>

    @Query("SELECT * FROM students WHERE institutionId = :institutionId AND status = :status AND deletedAt IS NULL AND isDuplicate = 0")
    fun getStudentsByStatus(institutionId: String, status: String): Flow<List<StudentEntity>>

    @Query("UPDATE students SET status = :newStatus, withdrawalReason = :reason, withdrawalDate = :date, statusUpdatedAt = :updatedAt, syncStatus = 2 WHERE id = :studentId AND institutionId = :institutionId")
    suspend fun updateStudentStatus(studentId: String, institutionId: String, newStatus: String, reason: String?, date: Long?, updatedAt: Long)

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Query("SELECT * FROM students WHERE documentId = :documentId AND institutionId = :institutionId AND deletedAt IS NULL")
    suspend fun getStudentByDocumentId(documentId: String, institutionId: String): StudentEntity?

    @Query("SELECT * FROM students WHERE documentId = :documentId AND institutionId = :institutionId LIMIT 1")
    suspend fun findByDocument(institutionId: String, documentId: String): StudentEntity?

    @Query("""
        SELECT s.* FROM students s
        INNER JOIN users u ON s.userId = u.id
        WHERE u.username = :email AND s.institutionId = :institutionId
    """)
    suspend fun getStudentByEmail(email: String, institutionId: String): StudentEntity?

    @Query("SELECT * FROM students WHERE qrCode = :qrCode AND institutionId = :institutionId")
    suspend fun getStudentByQrCode(qrCode: String, institutionId: String): StudentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Query("SELECT * FROM students WHERE syncStatus != 0 AND institutionId = :institutionId")
    suspend fun getPendingSyncStudents(institutionId: String): List<StudentEntity>

    @Query("UPDATE students SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)

    @Query("UPDATE students SET deletedAt = :deletedAt, deletedReason = :reason, deletedByUserId = :userId, syncStatus = 2 WHERE id = :id AND institutionId = :institutionId")
    suspend fun softDeleteStudent(id: String, institutionId: String, deletedAt: Long, reason: String, userId: String)

    @Query("UPDATE students SET isDuplicate = 1, mergedIntoId = :targetId, syncStatus = 2 WHERE id = :sourceId AND institutionId = :institutionId")
    suspend fun markAsDuplicate(sourceId: String, targetId: String, institutionId: String)

    @Query("SELECT * FROM students WHERE (firstName LIKE '%' || :query || '%' OR lastName LIKE '%' || :query || '%' OR documentId LIKE '%' || :query || '%') AND institutionId = :institutionId AND deletedAt IS NULL")
    suspend fun searchStudents(query: String, institutionId: String): List<StudentEntity>

    @Query("DELETE FROM students WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteStudent(id: String, institutionId: String)

    @Query("UPDATE students SET diasInasistenciaConsecutiva = :days WHERE id = :id AND institutionId = :instId")
    suspend fun updateConsecutiveDays(id: String, days: Int, instId: String)

    @Query("UPDATE students SET diasInasistenciaConsecutiva = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun resetConsecutiveDays(id: String, instId: String)

    @Query("SELECT * FROM students WHERE institutionId = :instId AND estadoMatricula = 'RETIRADO_DESERCION'")
    fun getDesertores(instId: String): Flow<List<StudentEntity>>

    @Query("""
        SELECT p.name 
        FROM programs p
        JOIN student_programs sp ON p.id = sp.programId
        WHERE sp.studentId = :studentId AND p.institutionId = :instId AND p.activo = 1
    """)
    suspend fun getProgramsByStudent(studentId: String, instId: String): List<String>

    @Query("""
        SELECT s.* 
        FROM students s
        JOIN student_programs sp ON s.id = sp.studentId
        WHERE sp.programId = :programId AND s.institutionId = :instId AND s.deletedAt IS NULL AND s.status != 'WITHDRAWN'
    """)
    fun getStudentsByProgram(programId: String, instId: String): Flow<List<StudentEntity>>
}
