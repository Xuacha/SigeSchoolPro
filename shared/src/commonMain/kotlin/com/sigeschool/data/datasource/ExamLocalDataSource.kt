package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Exam
import kotlinx.coroutines.flow.Flow

interface ExamLocalDataSource {
    fun getExams(institutionId: String): Flow<List<Exam>>
    fun getExamsByClass(classId: String): Flow<List<Exam>>
    suspend fun insertExam(exam: Exam)
    suspend fun deleteExam(exam: Exam)
    suspend fun getUnsyncedExams(): List<Exam>
}
