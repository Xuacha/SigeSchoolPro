package com.sigeschool.services.vision

interface OcrService {
    suspend fun recognizeText(imageBytes: ByteArray): Result<String>
}
