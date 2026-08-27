package com.sigeschool.data.datasource

import com.sigeschool.domain.model.sie.*
import kotlinx.coroutines.flow.Flow

interface AcademicLocalDataSource {
    // Achievements
    fun getAchievements(subjectId: String, gradeId: String, period: Int): Flow<List<Achievement>>
    suspend fun saveAchievement(achievement: Achievement)

    // Academic Grades
    fun getGrade(studentId: String, subjectId: String, period: Int): Flow<AcademicGrade?>
    suspend fun saveGrade(grade: AcademicGrade)

    // Discipline
    fun getDisciplineRecords(studentId: String): Flow<List<DisciplineRecord>>
    fun getDisciplineRecordsForStudents(studentIds: List<String>): Flow<List<DisciplineRecord>>
    suspend fun saveDisciplineRecord(record: DisciplineRecord)
    fun getTotalDisciplineImpact(studentId: String): Flow<Double>

    // Study Plans
    fun getAllStudyPlans(): Flow<List<StudyPlan>>
    suspend fun saveStudyPlan(plan: StudyPlan)

    // Academic Structure
    fun getSedes(institutionId: String): Flow<List<Sede>>
    fun getJornadas(institutionId: String): Flow<List<Jornada>>
    fun getCursos(gradoId: String): Flow<List<Curso>>
}
