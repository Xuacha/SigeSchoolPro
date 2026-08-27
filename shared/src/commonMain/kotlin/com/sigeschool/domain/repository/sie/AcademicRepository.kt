package com.sigeschool.domain.repository.sie

import com.sigeschool.domain.model.sie.*
import kotlinx.coroutines.flow.Flow

interface AcademicRepository {
    // Achievements
    fun getAchievements(subjectId: String, gradeId: String, period: Int): Flow<List<Achievement>>
    suspend fun saveAchievement(achievement: Achievement)

    // Academic Grades
    fun getGrade(studentId: String, subjectId: String, period: Int): Flow<AcademicGrade?>
    suspend fun saveGrade(grade: AcademicGrade)

    // Discipline & Conduct
    fun getDisciplineRecords(studentId: String): Flow<List<DisciplineRecord>>
    fun getDisciplineRecordsForStudents(studentIds: List<String>): Flow<Map<String, List<DisciplineRecord>>>
    suspend fun saveDisciplineRecord(record: DisciplineRecord)
    fun getConductScore(studentId: String, baseScore: Double = 5.0): Flow<Double>

    // Study Plans
    fun getStudyPlans(): Flow<List<StudyPlan>>
    suspend fun saveStudyPlan(plan: StudyPlan)
    suspend fun importPlanFromDocument(documentId: String): Result<StudyPlan>
    
    // Academic Structure
    fun getSedes(institutionId: String): Flow<List<com.sigeschool.domain.model.sie.Sede>>
    fun getJornadas(institutionId: String): Flow<List<com.sigeschool.domain.model.sie.Jornada>>
    fun getCursos(gradoId: String): Flow<List<com.sigeschool.domain.model.sie.Curso>>
}
