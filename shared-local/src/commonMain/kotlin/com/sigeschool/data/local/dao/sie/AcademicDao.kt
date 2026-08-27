package com.sigeschool.data.local.dao.sie

import androidx.room.*
import com.sigeschool.data.local.entity.sie.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AcademicDao {
    // Achievements
    @Query("SELECT * FROM achievements WHERE subjectId = :subjectId AND gradeId = :gradeId AND period = :period")
    fun getAchievements(subjectId: String, gradeId: String, period: Int): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievement(achievement: AchievementEntity)

    // Academic Grades
    @Query("SELECT * FROM academic_grades WHERE studentId = :studentId AND subjectId = :subjectId AND period = :period")
    fun getGrade(studentId: String, subjectId: String, period: Int): Flow<AcademicGradeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grade: AcademicGradeEntity)

    // Discipline
    @Query("SELECT * FROM discipline_records WHERE studentId = :studentId ORDER BY date DESC")
    fun getDisciplineRecords(studentId: String): Flow<List<DisciplineRecordEntity>>

    @Query("SELECT * FROM discipline_records WHERE studentId IN (:studentIds) ORDER BY studentId, date DESC")
    fun getDisciplineRecordsForStudents(studentIds: List<String>): Flow<List<DisciplineRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisciplineRecord(record: DisciplineRecordEntity)

    @Query("SELECT SUM(impactOnGrade) FROM discipline_records WHERE studentId = :studentId")
    fun getTotalDisciplineImpact(studentId: String): Flow<Double?>

    // Study Plans
    @Query("SELECT * FROM study_plans")
    fun getAllStudyPlans(): Flow<List<StudyPlanEntity>>

    @Transaction
    @Query("SELECT * FROM area_plans WHERE studyPlanId = :planId")
    fun getAreasForPlan(planId: String): Flow<List<AreaPlanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudyPlan(plan: StudyPlanEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreaPlan(area: AreaPlanEntity)
}
