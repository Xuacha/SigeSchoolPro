package com.sigeschool.core.ai

expect class AcademicStructureAnalyzer {
    suspend fun analyzeDocument(text: String): AcademicStructure
}
