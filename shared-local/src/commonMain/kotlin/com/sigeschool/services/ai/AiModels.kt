package com.sigeschool.services.ai

import kotlinx.serialization.Serializable

@Serializable
enum class CurricularBlockType {
    MISIÓN, VISIÓN, OBJETIVO, CONTENIDO, COMPETENCIA, OTRO
}

@Serializable
data class AiMetadata(
    val modelName: String = "",
    val processingTimeMs: Long = 0,
    val tokensUsed: Int = 0
)

@Serializable
data class CurricularBlockProposal(
    val title: String,
    val content: String,
    val type: CurricularBlockType,
    val confidence: Double = 1.0,
    val tags: List<String> = emptyList()
)

@Serializable
data class CurricularResponse(
    val blocks: List<CurricularBlockProposal>,
    val metadata: AiMetadata = AiMetadata()
)

sealed class AiResult<out T> {
    data class Success<T>(val data: T) : AiResult<T>()
    data class RetryableError(val message: String, val exception: Throwable? = null) : AiResult<Nothing>()
    data class FatalError(val message: String, val exception: Throwable? = null) : AiResult<Nothing>()
}
