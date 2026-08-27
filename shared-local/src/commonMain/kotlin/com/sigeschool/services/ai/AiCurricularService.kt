package com.sigeschool.services.ai

interface AiCurricularService {
    suspend fun analyzeText(text: String): AiResult<CurricularResponse>
}
