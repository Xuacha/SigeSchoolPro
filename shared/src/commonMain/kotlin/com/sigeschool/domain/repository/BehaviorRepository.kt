package com.sigeschool.domain.repository

import com.sigeschool.domain.model.BehavioralScore
import com.sigeschool.domain.model.ConvivenciaCase
import com.sigeschool.domain.model.FamilyAttendance
import kotlinx.coroutines.flow.Flow

interface BehaviorRepository {
    fun getCasesByStudent(institutionId: String, studentId: String): Flow<List<ConvivenciaCase>>
    suspend fun saveCase(case: ConvivenciaCase)
    suspend fun deleteCase(caseId: String, institutionId: String)

    fun getFamilyAttendanceByStudent(institutionId: String, studentId: String): Flow<List<FamilyAttendance>>
    suspend fun saveFamilyAttendance(attendance: FamilyAttendance)

    fun getBehavioralScores(institutionId: String, studentId: String): Flow<List<BehavioralScore>>
    suspend fun saveBehavioralScore(score: BehavioralScore)

    suspend fun syncBehavior(institutionId: String): Result<Unit>
}
