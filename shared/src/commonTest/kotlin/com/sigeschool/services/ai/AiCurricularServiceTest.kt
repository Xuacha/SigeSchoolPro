package com.sigeschool.services.ai

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class AiCurricularServiceTest {

    @Test
    fun testLocalAnalysisDetection() = runTest {
        val service = object : AiCurricularService {
            override suspend fun analyzeText(text: String): AiResult<CurricularResponse> {
                // Simulación mínima para test de lógica común
                val containsMision = text.contains("Misión", ignoreCase = true)
                return AiResult.Success(CurricularResponse(
                    blocks = if (containsMision) listOf(CurricularBlockProposal("Misión", text, CurricularBlockType.MISIÓN)) else emptyList()
                ))
            }
        }

        val result = service.analyzeText("Nuestra Misión es educar con calidad.")
        assertTrue(result is AiResult.Success)
        val data = (result as AiResult.Success).data
        assertTrue(data.blocks.any { it.type == CurricularBlockType.MISIÓN })
    }
}
