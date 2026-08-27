package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AttendanceEntity
import com.sigeschool.data.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

data class AttendanceWithStudent(
    @Embedded val attendance: AttendanceEntity,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "id"
    )
    val student: StudentEntity
)

@Serializable
data class GradeAttendance(
    val gradoNombre: String,
    val totalEstudiantes: Int,
    val totalPresentes: Int
)

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendance WHERE institutionId = :institutionId ORDER BY timestamp DESC")
    fun getAllAttendance(institutionId: String): Flow<List<AttendanceEntity>>

    @Transaction
    @Query("SELECT * FROM attendance WHERE institutionId = :institutionId ORDER BY timestamp DESC LIMIT :limit")
    fun getLatestMovements(institutionId: String, limit: Int = 20): Flow<List<AttendanceWithStudent>>

    @Transaction
    @Query("""
        SELECT * FROM attendance 
        WHERE institutionId = :institutionId 
        AND timestamp >= :startOfDay 
        AND timestamp < :nextDayStart 
        ORDER BY timestamp DESC
    """)
    fun getAttendanceByRange(institutionId: String, startOfDay: Long, nextDayStart: Long): Flow<List<AttendanceWithStudent>>

    @Query("""
        SELECT COUNT(DISTINCT studentId) FROM attendance 
        WHERE institutionId = :institutionId 
        AND type = 'ENTRY' 
        AND timestamp >= :startOfDay
        AND timestamp < :nextDayStart
    """)
    fun getPresentCount(institutionId: String, startOfDay: Long, nextDayStart: Long): Flow<Int>

    @Query("""
        SELECT COUNT(*) FROM students s
        WHERE s.institutionId = :institutionId 
        AND NOT EXISTS (
            SELECT 1 FROM attendance a 
            WHERE a.studentId = s.id 
            AND a.institutionId = :institutionId 
            AND a.type = 'ENTRY' 
            AND a.timestamp >= :startOfDay
            AND a.timestamp < :nextDayStart
        )
    """)
    fun getAbsentCount(institutionId: String, startOfDay: Long, nextDayStart: Long): Flow<Int>

    @Query("""
        SELECT 
            g.nombre as gradoNombre, 
            COUNT(DISTINCT m.estudianteId) as totalEstudiantes,
            COUNT(DISTINCT a.studentId) as totalPresentes
        FROM academic_grados g
        INNER JOIN academic_ofertas o ON g.id = o.gradoId AND o.institutionId = :institutionId
        INNER JOIN academic_clases c ON o.id = c.ofertaAcademicaId AND c.institutionId = :institutionId
        INNER JOIN academic_matriculas m ON c.id = m.claseId AND m.institutionId = :institutionId
        LEFT JOIN attendance a ON m.estudianteId = a.studentId 
            AND a.institutionId = :institutionId
            AND a.type = 'ENTRY' 
            AND a.timestamp >= :startOfDay
            AND a.timestamp < :nextDayStart
        WHERE g.institutionId = :institutionId
        GROUP BY g.id
    """)
    fun getAttendanceByGrade(institutionId: String, startOfDay: Long, nextDayStart: Long): Flow<List<GradeAttendance>>

    @Query("""
        SELECT COUNT(*) FROM (
            SELECT a1.studentId
            FROM attendance a1
            WHERE a1.institutionId = :institutionId
              AND a1.timestamp = (
                  SELECT MAX(a2.timestamp) 
                  FROM attendance a2 
                  WHERE a2.studentId = a1.studentId 
                    AND a2.institutionId = :institutionId
                    AND a2.timestamp >= :startOfDay
                    AND a2.timestamp < :nextDayStart
              )
              AND a1.type = 'ENTRY'
        )
    """)
    fun getOnSiteCount(institutionId: String, startOfDay: Long, nextDayStart: Long): Flow<Int>

    @Query("SELECT * FROM attendance WHERE institutionId = :institutionId")
    suspend fun getAllAttendanceSync(institutionId: String): List<AttendanceEntity>

    @Query("SELECT * FROM attendance WHERE studentId = :studentId AND institutionId = :institutionId ORDER BY timestamp DESC")
    fun getByEstudiante(studentId: String, institutionId: String): Flow<List<AttendanceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: AttendanceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: List<AttendanceEntity>)

    @Query("SELECT * FROM attendance WHERE studentId = :studentId AND institutionId = :institutionId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastAttendanceForStudent(studentId: String, institutionId: String): AttendanceEntity?

    @Query("SELECT * FROM attendance WHERE institutionId = :institutionId AND syncStatus = 5")
    suspend fun getSyncingRecords(institutionId: String): List<AttendanceEntity>

    @Query("UPDATE attendance SET syncStatus = 1 WHERE id IN (:ids) AND institutionId = :institutionId")
    suspend fun markAsPending(ids: List<String>, institutionId: String)

    @Query("""
        SELECT * FROM attendance 
        WHERE syncStatus != 0
        AND institutionId = :institutionId
    """)
    suspend fun getPendingSyncAttendance(institutionId: String): List<AttendanceEntity>

    @Query("UPDATE attendance SET syncStatus = 5 WHERE id IN (:ids) AND institutionId = :institutionId")
    suspend fun markAsSyncing(ids: List<String>, institutionId: String)

    @Query("UPDATE attendance SET syncStatus = 0 WHERE id IN (:ids) AND institutionId = :institutionId")
    suspend fun markAsSynced(ids: List<String>, institutionId: String)

    @Query("UPDATE attendance SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)

    @Query("DELETE FROM attendance WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteAttendance(id: String, institutionId: String)

    @Query("UPDATE attendance SET studentId = :targetStudentId, syncStatus = 2 WHERE studentId = :sourceStudentId AND institutionId = :institutionId")
    suspend fun migrateStudentAttendance(sourceStudentId: String, targetStudentId: String, institutionId: String)
}
