package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.GradeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grade: GradeEntity)

    @Query("SELECT * FROM grades WHERE studentId = :studentId AND institutionId = :institutionId")
    fun getGradesByStudent(studentId: String, institutionId: String): Flow<List<GradeEntity>>

    @Query("SELECT * FROM grades WHERE institutionId = :institutionId")
    suspend fun getAllGradesSync(institutionId: String): List<GradeEntity>

    @Query("SELECT AVG(score) FROM grades WHERE studentId = :studentId AND institutionId = :institutionId")
    fun getStudentAverage(studentId: String, institutionId: String): Flow<Double?>

    @Query("SELECT * FROM grades WHERE syncStatus != 0 AND institutionId = :institutionId")
    suspend fun getPendingSyncGrades(institutionId: String): List<GradeEntity>

    @Query("UPDATE grades SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)

    @Query("DELETE FROM grades WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteGrade(id: String, institutionId: String)
}
