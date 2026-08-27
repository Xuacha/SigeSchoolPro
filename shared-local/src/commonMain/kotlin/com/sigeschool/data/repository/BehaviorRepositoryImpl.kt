package com.sigeschool.data.repository

import com.sigeschool.data.local.dao.ConvivenciaDao
import com.sigeschool.data.local.entity.BehavioralScoreEntity
import com.sigeschool.data.local.entity.ConvivenciaCaseEntity
import com.sigeschool.data.local.entity.FamilyAttendanceEntity
import com.sigeschool.domain.model.BehavioralScore
import com.sigeschool.domain.model.ConvivenciaCase
import com.sigeschool.domain.model.FamilyAttendance
import com.sigeschool.domain.repository.BehaviorRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BehaviorRepositoryImpl(
    private val convivenciaDao: ConvivenciaDao,
    private val supabaseClient: SupabaseClient
) : BehaviorRepository {

    override fun getCasesByStudent(institutionId: String, studentId: String): Flow<List<ConvivenciaCase>> {
        return convivenciaDao.getCasesByStudent(institutionId, studentId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveCase(case: ConvivenciaCase) {
        val entity = case.toEntity()
        convivenciaDao.insertCase(entity)
        try {
            supabaseClient.from("convivencia_cases").upsert(entity)
        } catch (e: Exception) {
            // Local offline-first save
        }
    }

    override suspend fun deleteCase(caseId: String, institutionId: String) {
        convivenciaDao.deleteCaseById(caseId, institutionId)
        try {
            supabaseClient.from("convivencia_cases").delete {
                filter {
                    eq("id", caseId)
                    eq("institution_id", institutionId)
                }
            }
        } catch (e: Exception) {
            // Deleted locally
        }
    }

    override fun getFamilyAttendanceByStudent(institutionId: String, studentId: String): Flow<List<FamilyAttendance>> {
        return convivenciaDao.getFamilyAttendanceByStudent(studentId, institutionId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveFamilyAttendance(attendance: FamilyAttendance) {
        val entity = attendance.toEntity()
        convivenciaDao.insertFamilyAttendance(entity)
        try {
            supabaseClient.from("family_attendance").upsert(entity)
        } catch (e: Exception) {
            // Local save
        }
    }

    override fun getBehavioralScores(institutionId: String, studentId: String): Flow<List<BehavioralScore>> {
        return convivenciaDao.getScoresByStudent(studentId, institutionId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveBehavioralScore(score: BehavioralScore) {
        val entity = score.toEntity()
        convivenciaDao.insertBehavioralScore(entity)
        try {
            supabaseClient.from("behavioral_scores").upsert(entity)
        } catch (e: Exception) {
            // Local save
        }
    }

    override suspend fun syncBehavior(institutionId: String): Result<Unit> {
        return try {
            val remoteCases = supabaseClient.from("convivencia_cases")
                .select { filter { eq("institution_id", institutionId) } }
                .decodeList<ConvivenciaCaseEntity>()
            remoteCases.forEach { convivenciaDao.insertCase(it) }

            val remoteAtt = supabaseClient.from("family_attendance")
                .select { filter { eq("institution_id", institutionId) } }
                .decodeList<FamilyAttendanceEntity>()
            remoteAtt.forEach { convivenciaDao.insertFamilyAttendance(it) }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun ConvivenciaCaseEntity.toDomain() = ConvivenciaCase(
        id = id,
        institutionId = institutionId,
        studentId = studentId,
        title = "Caso ${id.take(8)}",
        description = description,
        status = status,
        openingDate = openingDate,
        resolution = resolution
    )

    private fun ConvivenciaCase.toEntity() = ConvivenciaCaseEntity(
        id = id,
        institutionId = institutionId,
        studentId = studentId,
        teacherId = "DOC_SYSTEM",
        createdByUserId = "USER_SYSTEM",
        openingDate = openingDate,
        status = status,
        description = description,
        resolution = resolution
    )

    private fun FamilyAttendanceEntity.toDomain() = FamilyAttendance(
        id = id,
        institutionId = institutionId,
        studentId = studentId,
        citationDate = citationDate,
        reason = meetingNotes ?: "Citación a acudiente",
        attended = status == "ATENDIDO",
        commitments = behavioralImpact
    )

    private fun FamilyAttendance.toEntity() = FamilyAttendanceEntity(
        id = id,
        institutionId = institutionId,
        studentId = studentId,
        parentName = "Acudiente",
        citationDate = citationDate,
        attendanceDate = if (attended) kotlinx.datetime.Clock.System.now().toEpochMilliseconds() else null,
        status = if (attended) "ATENDIDO" else "PENDIENTE",
        meetingNotes = reason,
        behavioralImpact = commitments
    )

    private fun BehavioralScoreEntity.toDomain() = BehavioralScore(
        id = id,
        institutionId = institutionId,
        studentId = studentId,
        period = periodId.toIntOrNull() ?: 1,
        score = scoreType.toDoubleOrNull() ?: 5.0,
        observations = feedback
    )

    private fun BehavioralScore.toEntity() = BehavioralScoreEntity(
        id = id,
        institutionId = institutionId,
        studentId = studentId,
        competencyId = "COMP_GENERAL",
        periodId = period.toString(),
        scoreType = score.toString(),
        feedback = observations,
        evaluationDate = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
    )
}
