package com.sigeschool.domain.repository.sie

import com.sigeschool.domain.model.sie.Autoevaluacion
import com.sigeschool.domain.model.sie.PromotionConfig
import kotlinx.coroutines.flow.Flow

interface PromotionRepository {
    // Autoevaluacion
    suspend fun saveAutoevaluacion(autoevaluacion: Autoevaluacion): Result<Unit>
    fun getAutoevaluaciones(studentId: String, periodId: String): Flow<List<Autoevaluacion>>
    suspend fun hasSubmittedAutoevaluacion(studentId: String, subjectId: String, periodId: String): Boolean

    // Config
    fun getPromotionConfig(institutionId: String): Flow<PromotionConfig>
    suspend fun updatePromotionConfig(config: PromotionConfig): Result<Unit>
}
