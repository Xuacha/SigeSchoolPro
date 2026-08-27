package com.sigeschool.services.ai

interface IAEngine {
    suspend fun classifyDocument(text: String): CurricularResponse
    suspend fun extractText(imageBytes: ByteArray): Result<String>
}
