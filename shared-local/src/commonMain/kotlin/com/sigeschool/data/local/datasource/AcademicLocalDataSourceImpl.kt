package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.AcademicLocalDataSource
import com.sigeschool.data.local.dao.sie.AcademicDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.sie.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class AcademicLocalDataSourceImpl(
    private val academicDao: AcademicDao
) : AcademicLocalDataSource {

    override fun getAchievements(subjectId: String, gradeId: String, period: Int): Flow<List<Achievement>> {
        return academicDao.getAchievements(subjectId, gradeId, period).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveAchievement(achievement: Achievement) {
        academicDao.insertAchievement(achievement.toEntity())
    }

    override fun getGrade(studentId: String, subjectId: String, period: Int): Flow<AcademicGrade?> {
        return academicDao.getGrade(studentId, subjectId, period).map { it?.toDomain() }
    }

    override suspend fun saveGrade(grade: AcademicGrade) {
        academicDao.insertGrade(grade.toEntity())
    }

    override fun getDisciplineRecords(studentId: String): Flow<List<DisciplineRecord>> {
        return academicDao.getDisciplineRecords(studentId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getDisciplineRecordsForStudents(studentIds: List<String>): Flow<List<DisciplineRecord>> {
        return academicDao.getDisciplineRecordsForStudents(studentIds).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveDisciplineRecord(record: DisciplineRecord) {
        academicDao.insertDisciplineRecord(record.toEntity())
    }

    override fun getTotalDisciplineImpact(studentId: String): Flow<Double> {
        return academicDao.getTotalDisciplineImpact(studentId).map { it ?: 0.0 }
    }

    override fun getAllStudyPlans(): Flow<List<StudyPlan>> {
        return academicDao.getAllStudyPlans().map { entities ->
            entities.map { planEntity ->
                // This is a bit simplified, ideally we'd use a join or get areas separately
                planEntity.toDomain(emptyList()) 
            }
        }
    }

    override suspend fun saveStudyPlan(plan: StudyPlan) {
        academicDao.insertStudyPlan(plan.toEntity())
        plan.areas.forEach { area ->
            academicDao.insertAreaPlan(area.toEntity(plan.id))
        }
    }

    // Academic Structure
    override fun getSedes(institutionId: String): Flow<List<Sede>> {
        return academicDao.getSedes(institutionId).map { entities -> entities.map { it.toDomain() } }
    }

    override fun getJornadas(institutionId: String): Flow<List<Jornada>> {
        return academicDao.getJornadas(institutionId).map { entities -> entities.map { it.toDomain() } }
    }

    override fun getCursos(gradoId: String): Flow<List<Curso>> {
        return academicDao.getCursos(gradoId).map { entities -> entities.map { it.toDomain() } }
    }
}
