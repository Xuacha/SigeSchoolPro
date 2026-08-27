package com.sigeschool.services

actual suspend fun extractTextFromPlatform(fileBytes: ByteArray, fileName: String): String {
    // En Desktop real usaríamos Apache PDFBox o POI
    return "Texto extraído en Desktop de $fileName (Simulado con estructura real)"
}
