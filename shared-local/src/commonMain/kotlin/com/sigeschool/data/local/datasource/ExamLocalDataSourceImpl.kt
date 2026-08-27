package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.ExamLocalDataSource
import com.sigeschool.data.local.dao.ExamDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.Exam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExamLocalDataSourceImpl(
    private val examDao: ExamDao
) : ExamLocalDataSource {
    override fun getExams(institutionId: String): Flow<List<Exam>> {
        return examDao.getExams(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getExamsByClass(classId: String): Flow<List<Exam>> {
        return examDao.getExamsByClass(classId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertExam(exam: Exam) {
        examDao.insertExam(exam.toEntity())
    }

    override suspend fun deleteExam(exam: Exam) {
        examDao.deleteExam(exam.toEntity())
    }

    override suspend fun getUnsyncedExams(): List<Exam> {
        return examDao.getUnsyncedExams().map { it.toDomain() }
    }
}
