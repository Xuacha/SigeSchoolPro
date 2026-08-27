package com.sigeschool.data.repository.sie

import com.sigeschool.data.datasource.AcademicLocalDataSource
import com.sigeschool.domain.model.sie.*
import com.sigeschool.domain.repository.sie.AcademicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AcademicRepositoryImpl(
    private val localDataSource: AcademicLocalDataSource
) : AcademicRepository {

    override fun getAchievements(subjectId: String, gradeId: String, period: Int): Flow<List<Achievement>> {
        return localDataSource.getAchievements(subjectId, gradeId, period)
    }

    override suspend fun saveAchievement(achievement: Achievement) {
        localDataSource.saveAchievement(achievement)
    }

    override fun getGrade(studentId: String, subjectId: String, period: Int): Flow<AcademicGrade?> {
        return localDataSource.getGrade(studentId, subjectId, period)
    }

    override suspend fun saveGrade(grade: AcademicGrade) {
        localDataSource.saveGrade(grade)
    }

    override fun getDisciplineRecords(studentId: String): Flow<List<DisciplineRecord>> {
        return localDataSource.getDisciplineRecords(studentId)
    }

    override fun getDisciplineRecordsForStudents(studentIds: List<String>): Flow<Map<String, List<DisciplineRecord>>> {
        return localDataSource.getDisciplineRecordsForStudents(studentIds).map { records ->
            records.groupBy { it.studentId }
        }
    }

    override suspend fun saveDisciplineRecord(record: DisciplineRecord) {
        localDataSource.saveDisciplineRecord(record)
    }

    override fun getConductScore(studentId: String, baseScore: Double): Flow<Double> {
        return localDataSource.getTotalDisciplineImpact(studentId).map { totalImpact ->
            (baseScore + totalImpact).coerceIn(0.0, 5.0)
        }
    }

    override fun getStudyPlans(): Flow<List<StudyPlan>> {
        return localDataSource.getAllStudyPlans()
    }

    override suspend fun saveStudyPlan(plan: StudyPlan) {
        localDataSource.saveStudyPlan(plan)
    }

    override suspend fun importPlanFromDocument(documentId: String): Result<StudyPlan> {
        return try {
            val doc = localDataSource.getCurricularDocument(documentId)
            val plan = StudyPlan(
                id = "PLAN-${System.currentTimeMillis()}",
                institutionId = doc?.institutionId ?: "",
                gradeId = "GRADE_DEFAULT",
                areaId = "AREA_DEFAULT",
                name = doc?.title ?: "Plan Importado",
                year = 2024
            )
            localDataSource.saveStudyPlan(plan)
            Result.success(plan)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Academic Structure
    override fun getSedes(institutionId: String): Flow<List<Sede>> {
        return localDataSource.getSedes(institutionId)
    }

    override fun getJornadas(institutionId: String): Flow<List<Jornada>> {
        return localDataSource.getJornadas(institutionId)
    }

    override fun getCursos(gradoId: String): Flow<List<Curso>> {
        return localDataSource.getCursos(gradoId)
    }
}
