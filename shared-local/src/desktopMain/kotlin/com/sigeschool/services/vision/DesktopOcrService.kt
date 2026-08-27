package com.sigeschool.services.vision

class DesktopOcrService : OcrService {
    override suspend fun recognizeText(imageBytes: ByteArray): Result<String> {
        return Result.success("OCR no soportado en Desktop por ahora")
    }
}
