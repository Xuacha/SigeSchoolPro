package com.sigeschool.domain.model.sie

import kotlinx.serialization.Serializable

@Serializable
data class Autoevaluacion(
    val id: String,
    val studentId: String,
    val subjectId: String,
    val periodId: String,
    val score: Double,
    val registrationDate: Long,
    val metadata: String? = null
)

@Serializable
sealed class PromotionStatus {
    @Serializable
    object Promoted : PromotionStatus()
    
    @Serializable
    data class NotPromoted(val reasons: List<String>) : PromotionStatus()
    
    @Serializable
    object Pending : PromotionStatus()
}

@Serializable
data class PromotionConfig(
    val id: String = "default_config",
    val institutionId: String,
    val maxFailedSubjects: Int = 3,
    val maxInattendancePercentage: Double = 25.0,
    val minimumPassingScore: Double = 3.0,
    val autoevaluacionWeight: Double = 5.0
)
