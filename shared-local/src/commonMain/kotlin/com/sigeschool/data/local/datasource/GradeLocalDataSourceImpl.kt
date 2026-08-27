package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.GradeLocalDataSource
import com.sigeschool.data.local.dao.GradeDao
import com.sigeschool.data.local.entity.GradeEntity
import com.sigeschool.domain.model.Grade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GradeLocalDataSourceImpl(private val gradeDao: GradeDao) : GradeLocalDataSource {

    override fun getGradesByStudent(studentId: String, institutionId: String): Flow<List<Grade>> {
        return gradeDao.getGradesByStudent(studentId, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getGradesByStudentList(studentIds: List<String>, institutionId: String): Flow<List<Grade>> {
        return gradeDao.getGradesByStudentList(studentIds, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getGradesByInstitution(institutionId: String, periodId: String?): Flow<List<Grade>> {
        return gradeDao.getGradesByInstitution(institutionId, periodId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveGrade(grade: Grade) {
        gradeDao.insert(grade.toEntity())
    }

    override suspend fun deleteGrade(gradeId: String, institutionId: String) {
        gradeDao.deleteById(gradeId, institutionId)
    }

    override suspend fun getUnsyncedGrades(institutionId: String): List<Grade> {
        return gradeDao.getUnsyncedGrades(institutionId).map { it.toDomain() }
    }

    private fun GradeEntity.toDomain(): Grade = Grade(
        id = id,
        studentId = studentId,
        institutionId = institutionId,
        subjectId = subjectId,
        score = score,
        periodId = periodId,
        observations = observations,
        date = date,
        sincronizado = sincronizado
    )

    private fun Grade.toEntity(): GradeEntity = GradeEntity(
        id = id,
        studentId = studentId,
        institutionId = institutionId,
        subjectId = subjectId,
        score = score,
        periodId = periodId,
        observations = observations,
        date = date,
        sincronizado = sincronizado
    )
}
