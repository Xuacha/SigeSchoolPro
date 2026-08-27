package com.sigeschool.services

actual suspend fun extractTextFromPlatform(fileBytes: ByteArray, fileName: String): String {
    // En Web real usaríamos pdf.js via JSInterop
    return "Texto extraído en Web de $fileName (Simulado con estructura real)"
}
