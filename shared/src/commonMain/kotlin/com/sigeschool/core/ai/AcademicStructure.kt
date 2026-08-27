package com.sigeschool.core.ai

import kotlinx.serialization.Serializable

@Serializable
data class AcademicStructure(
    val areas: List<AnalyzedArea> = emptyList(),
    val planEstudioName: String = "Nuevo Plan de Estudios",
    val description: String? = null
)

@Serializable
data class AnalyzedArea(
    val name: String,
    val description: String? = null,
    val subjects: List<AnalyzedSubject> = emptyList()
)

@Serializable
data class AnalyzedSubject(
    val name: String,
    val code: String? = null,
    val description: String? = null,
    val weeklyHours: Int = 0,
    val competencies: List<String> = emptyList()
)
