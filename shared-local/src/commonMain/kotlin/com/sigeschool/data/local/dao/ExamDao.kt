package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ExamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamDao {
    @Query("SELECT * FROM exams WHERE institutionId = :institutionId")
    fun getExams(institutionId: String): Flow<List<ExamEntity>>

    @Query("SELECT * FROM exams WHERE classId = :classId")
    fun getExamsByClass(classId: String): Flow<List<ExamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: ExamEntity)

    @Delete
    suspend fun deleteExam(exam: ExamEntity)

    @Query("SELECT * FROM exams WHERE sincronizado = 0")
    suspend fun getUnsyncedExams(): List<ExamEntity>
}
