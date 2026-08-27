package com.sigeschool.services.ai

class WasmNoOpAiService : AiCurricularService {
    override suspend fun analyzeText(text: String): AiResult<CurricularResponse> {
        return AiResult.Success(CurricularResponse(emptyList()))
    }
}
