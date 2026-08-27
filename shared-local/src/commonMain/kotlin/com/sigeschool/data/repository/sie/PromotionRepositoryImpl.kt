package com.sigeschool.data.repository.sie

import com.sigeschool.data.local.dao.sie.PromotionDao
import com.sigeschool.data.local.entity.sie.AutoevaluacionEntity
import com.sigeschool.data.local.entity.sie.PromotionConfigEntity
import com.sigeschool.domain.model.sie.Autoevaluacion
import com.sigeschool.domain.model.sie.PromotionConfig
import com.sigeschool.domain.repository.sie.PromotionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PromotionRepositoryImpl(
    private val promotionDao: PromotionDao
) : PromotionRepository {

    override suspend fun saveAutoevaluacion(autoevaluacion: Autoevaluacion): Result<Unit> = runCatching {
        promotionDao.insertAutoevaluacion(autoevaluacion.toEntity())
    }

    override fun getAutoevaluaciones(studentId: String, periodId: String): Flow<List<Autoevaluacion>> {
        return promotionDao.getAutoevaluaciones(studentId.toInt(), periodId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun hasSubmittedAutoevaluacion(studentId: String, subjectId: String, periodId: String): Boolean {
        return promotionDao.countAutoevaluacion(studentId.toInt(), subjectId, periodId) > 0
    }

    override fun getPromotionConfig(institutionId: String): Flow<PromotionConfig> {
        return promotionDao.getPromotionConfig(institutionId).map { entity ->
            entity?.toDomain() ?: PromotionConfig(institutionId = institutionId)
        }
    }

    override suspend fun updatePromotionConfig(config: PromotionConfig): Result<Unit> = runCatching {
        promotionDao.insertPromotionConfig(config.toEntity())
    }

    private fun Autoevaluacion.toEntity() = AutoevaluacionEntity(
        id = id,
        studentId = studentId.toInt(),
        subjectId = subjectId,
        periodId = periodId,
        score = score,
        registrationDate = registrationDate,
        metadata = metadata
    )

    private fun AutoevaluacionEntity.toDomain() = Autoevaluacion(
        id = id,
        studentId = studentId.toString(),
        subjectId = subjectId,
        periodId = periodId,
        score = score,
        registrationDate = registrationDate,
        metadata = metadata
    )

    private fun PromotionConfig.toEntity() = PromotionConfigEntity(
        id = id,
        institutionId = institutionId,
        maxFailedSubjects = maxFailedSubjects,
        maxInattendancePercentage = maxInattendancePercentage,
        minimumPassingScore = minimumPassingScore,
        autoevaluacionWeight = autoevaluacionWeight
    )

    private fun PromotionConfigEntity.toDomain() = PromotionConfig(
        id = id,
        institutionId = institutionId,
        maxFailedSubjects = maxFailedSubjects,
        maxInattendancePercentage = maxInattendancePercentage,
        minimumPassingScore = minimumPassingScore,
        autoevaluacionWeight = autoevaluacionWeight
    )
}
