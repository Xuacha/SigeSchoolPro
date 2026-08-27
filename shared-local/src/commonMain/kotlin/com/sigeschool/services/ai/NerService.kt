package com.sigeschool.services.ai

import com.sigeschool.domain.model.CvData
import com.sigeschool.domain.model.sie.Rubric
import com.sigeschool.domain.model.sie.AchievementIndicator

expect class NerService {
    constructor()
    suspend fun extractCvFields(text: String): CvData
    suspend fun extractRubric(text: String): Rubric?
    suspend fun extractIndicators(text: String): List<AchievementIndicator>
}
