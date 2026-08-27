package com.sigeschool.services.ai

class WasmAiCurricularService : AiCurricularService {
    override suspend fun analyzeText(text: String): AiResult<CurricularResponse> {
        return AiResult.Success(
            CurricularResponse(
                blocks = listOf(
                    CurricularBlockProposal(
                        "Web AI",
                        "Análisis no disponible en Web aún",
                        CurricularBlockType.OTRO
                    )
                )
            )
        )
    }
}
