package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ConvivenciaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCase(case: ConvivenciaCaseEntity)

    @Update
    suspend fun updateCase(case: ConvivenciaCaseEntity)

    @Query("DELETE FROM convivencia_cases WHERE id = :caseId AND institutionId = :institutionId")
    suspend fun deleteCaseById(caseId: String, institutionId: String)

    @Query("SELECT * FROM convivencia_cases WHERE institutionId = :institutionId AND studentId = :studentId ORDER BY openingDate DESC")
    fun getCasesByStudent(institutionId: String, studentId: String): Flow<List<ConvivenciaCaseEntity>>

    @Query("SELECT * FROM convivencia_cases WHERE id = :caseId AND institutionId = :institutionId")
    suspend fun getCaseById(caseId: String, institutionId: String): ConvivenciaCaseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestimony(testimony: TestimonyEntity)

    @Query("SELECT * FROM case_testimonies WHERE caseId = :caseId AND institutionId = :institutionId ORDER BY createdAt ASC")
    fun getTestimoniesByCase(caseId: String, institutionId: String): Flow<List<TestimonyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetency(competency: BehavioralCompetencyEntity)

    @Query("SELECT * FROM behavioral_competencies WHERE institutionId = :institutionId")
    fun getAllCompetencies(institutionId: String): Flow<List<BehavioralCompetencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBehavioralScore(score: BehavioralScoreEntity)

    @Query("SELECT * FROM behavioral_scores WHERE studentId = :studentId AND institutionId = :institutionId")
    fun getScoresByStudent(studentId: String, institutionId: String): Flow<List<BehavioralScoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFamilyAttendance(attendance: FamilyAttendanceEntity)

    @Query("SELECT * FROM family_attendance WHERE studentId = :studentId AND institutionId = :institutionId ORDER BY citationDate DESC")
    fun getFamilyAttendanceByStudent(studentId: String, institutionId: String): Flow<List<FamilyAttendanceEntity>>
}
