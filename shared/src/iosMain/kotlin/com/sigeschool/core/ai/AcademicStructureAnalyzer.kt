package com.sigeschool.core.ai

actual class AcademicStructureAnalyzer {
    actual suspend fun analyzeDocument(text: String): AcademicStructure {
        // Basic rule-based implementation for iOS
        val lines = text.lines().map { it.trim() }.filter { it.isNotEmpty() }
        return AcademicStructure(
            areas = emptyList(), // Stub areas
            planEstudioName = "Extracción básica iOS: " + (if (lines.isNotEmpty()) lines[0].take(10) else "")
        )
    }
}
