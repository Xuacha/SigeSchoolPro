package com.sigeschool.core.ai

actual class AcademicStructureAnalyzer {
    actual suspend fun analyzeDocument(text: String): AcademicStructure {
        return AcademicStructure(planEstudioName = "Desktop Analyzed")
    }
}
