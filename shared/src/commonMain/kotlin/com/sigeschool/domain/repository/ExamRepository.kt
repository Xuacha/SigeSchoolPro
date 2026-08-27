package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Exam
import kotlinx.coroutines.flow.Flow

interface ExamRepository {
    fun getExams(institutionId: String): Flow<List<Exam>>
    suspend fun saveExam(exam: Exam)
    suspend fun syncWithCloud()
}
