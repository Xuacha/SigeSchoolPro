package com.sigeschool.core.ai

actual class DocumentClassifier {
    actual fun classify(text: String): String {
        // Stub for iOS
        return when {
            text.contains("médico", ignoreCase = true) -> "medical_info"
            text.contains("nota", ignoreCase = true) -> "academic_record"
            else -> "observation"
        }
    }
    
    actual fun close() {}
}
