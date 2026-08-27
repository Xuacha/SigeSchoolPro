package com.sigeschool.services

actual suspend fun extractTextFromPlatform(fileBytes: ByteArray, fileName: String): String {
    // En Android real usaríamos PDFBox-Android o ML Kit Entity Extraction
    return "Texto extraído en Android de $fileName (Simulado con estructura real)"
}
