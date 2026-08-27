package com.sigeschool.services.ai

class NoOpAiService : AiCurricularService {
    override suspend fun analyzeText(text: String): AiResult<CurricularResponse> {
        return AiResult.Success(CurricularResponse(emptyList()))
    }
}
