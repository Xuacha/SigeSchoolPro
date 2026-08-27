package com.sigeschool.domain.model.sie

import kotlinx.serialization.Serializable

@Serializable
data class GradingScale(
    val id: String,
    val institutionId: String,
    val name: String, // Ej: "Escala Nacional Decreto 1290"
    val minScore: Double,
    val maxScore: Double,
    val isDefault: Boolean = false,
    val ranges: List<ScaleRange>
)

@Serializable
data class ScaleRange(
    val id: String,
    val gradingScaleId: String,
    val name: String, // Ej: "Superior", "Alto", "Básico", "Bajo"
    val minLimit: Double,
    val maxLimit: Double,
    val description: String? = null,
    val color: String? = null // Hex code para UI
)

@Serializable
data class GradeCategory(
    val id: String,
    val institutionId: String,
    val name: String, // Ej: "Exámenes", "Tareas", "Ser/Actitudinal"
    val weightPercentage: Double, // Ej: 40.0
    val periodId: String? = null // Opcional si aplica a periodos específicos
)

@Serializable
data class Rubric(
    val id: String,
    val title: String,
    val description: String? = null,
    val criteria: List<RubricCriterion> = emptyList()
)

@Serializable
data class RubricCriterion(
    val id: String,
    val name: String, // Ej: "Ortografía", "Contenido"
    val weight: Double,
    val levels: List<CriterionLevel>
)

@Serializable
data class CriterionLevel(
    val id: String,
    val name: String, // Ej: "Excelente", "Bueno"
    val score: Double,
    val description: String? = null
)

@Serializable
data class RubricEvaluation(
    val id: String,
    val gradeId: String,
    val rubricId: String,
    val selections: List<CriterionSelection>
)

@Serializable
data class CriterionSelection(
    val criterionId: String,
    val levelId: String,
    val score: Double // El puntaje obtenido en este criterio
)

@Serializable
data class Competency(
    val id: String,
    val institutionId: String,
    val code: String, // Ej: "MAT-01"
    val description: String,
    val area: String, // Ej: "Matemáticas"
    val indicators: List<AchievementIndicator> = emptyList()
)

@Serializable
data class AchievementIndicator(
    val id: String,
    val competencyId: String,
    val rangeId: String, // Vínculo con ScaleRange (Superior, Alto, etc.)
    val description: String // Texto pedagógico: "Demuestra excelencia en..."
)

@Serializable
data class StudentGrade(
    val id: String,
    val studentId: String,
    val subjectId: String,
    val periodId: String,
    val categoryId: String? = null,
    val score: Double,
    val qualitativeNote: String? = null,
    val gradingScaleId: String,
    val isRecovery: Boolean = false,
    val originalGradeId: String? = null,
    val competencyId: String? = null
)
