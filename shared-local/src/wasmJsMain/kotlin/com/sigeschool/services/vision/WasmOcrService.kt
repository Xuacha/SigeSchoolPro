package com.sigeschool.services.vision

class WasmOcrService : OcrService {
    override suspend fun recognizeText(imageBytes: ByteArray): Result<String> {
        return Result.success("OCR no soportado en Web por ahora")
    }
}
