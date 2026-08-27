package com.sigeschool.domain.service.sie

import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.sie.*
import com.sigeschool.domain.repository.sie.PromotionRepository
import kotlinx.coroutines.flow.firstOrNull

class SieService(
    private val promotionRepository: PromotionRepository? = null
) {

    /**
     * Calcula la nota definitiva ponderada de un estudiante para una asignatura y periodo.
     * @param redistributeMissingWeight Si es true, el peso de categorías sin notas se reparte entre las demás.
     *                                   Si es false (estándar Decreto 1290 estricto), la categoría cuenta como 0.0.
     */
    suspend fun calculateWeightedAverage(
        studentId: String,
        subjectId: String,
        periodId: String,
        institutionId: String,
        grades: List<Grade>,
        categories: List<GradeCategory>,
        redistributeMissingWeight: Boolean = false
    ): Double {
        if (grades.isEmpty()) return 0.0

        val config = promotionRepository?.getPromotionConfig(institutionId)?.firstOrNull() 
            ?: PromotionConfig(institutionId = institutionId)
        
        val autoevaluacion = promotionRepository?.getAutoevaluaciones(studentId, periodId)?.firstOrNull()
            ?.find { it.subjectId == subjectId }

        var totalWeightedScore = 0.0
        var totalWeightUsed = 0.0

        // Ajustamos los pesos de las categorías normales si hay autoevaluación
        val autoevaluacionWeight = config.autoevaluacionWeight / 100.0
        val remainingWeightFactor = 1.0 - autoevaluacionWeight

        categories.forEach { category ->
            val categoryGrades = grades.filter { it.categoryId == category.id }
            val adjustedWeight = (category.weightPercentage / 100.0) * remainingWeightFactor
            
            if (categoryGrades.isNotEmpty()) {
                val categoryAverage = categoryGrades.map { it.score }.average()
                totalWeightedScore += categoryAverage * adjustedWeight
                totalWeightUsed += adjustedWeight
            } else if (!redistributeMissingWeight) {
                totalWeightUsed += adjustedWeight
            }
        }

        // Sumamos la autoevaluación
        if (autoevaluacion != null) {
            totalWeightedScore += autoevaluacion.score * autoevaluacionWeight
            totalWeightUsed += autoevaluacionWeight
        }

        return if (totalWeightUsed > 0) {
            if (redistributeMissingWeight) {
                (totalWeightedScore / totalWeightUsed)
            } else {
                totalWeightedScore
            }
        } else {
            grades.map { it.score }.average()
        }
    }

    /**
     * Verifica el estado de promoción del estudiante basado en el Decreto 1290.
     */
    suspend fun checkPromotionStatus(
        studentId: String,
        institutionId: String,
        failedSubjectsCount: Int,
        totalClasses: Int,
        absences: Int
    ): PromotionStatus {
        val config = promotionRepository?.getPromotionConfig(institutionId)?.firstOrNull()
            ?: PromotionConfig(institutionId = institutionId)

        val reasons = mutableListOf<String>()

        if (failedSubjectsCount > config.maxFailedSubjects) {
            reasons.add("Reprobó $failedSubjectsCount asignaturas (Máximo permitido: ${config.maxFailedSubjects})")
        }

        val inattendancePercentage = if (totalClasses > 0) (absences.toDouble() / totalClasses) * 100 else 0.0
        if (inattendancePercentage > config.maxInattendancePercentage) {
            reasons.add("Inasistencia del ${inattendancePercentage.toInt()}% (Máximo permitido: ${config.maxInattendancePercentage}%)")
        }

        return if (reasons.isEmpty()) {
            PromotionStatus.Promoted
        } else {
            PromotionStatus.NotPromoted(reasons)
        }
    }

    /**
     * Determina el desempeño nacional (Decreto 1290) basado en la escala de la institución.
     */
    fun getPerformanceLevel(score: Double, scale: GradingScale): String {
        val range = scale.ranges.find { score >= it.minLimit && score <= it.maxLimit }
        return range?.name ?: "Sin Rango"
    }

    /**
     * Calcula el puntaje total de una rúbrica basado en las selecciones de criterios.
     */
    fun calculateRubricScore(evaluation: RubricEvaluation): Double {
        return evaluation.selections.sumOf { it.score }
    }

    /**
     * Aplica la lógica de nivelación (recuperación).
     * Según el Decreto 1290, las instituciones suelen permitir que la recuperación 
     * reemplace la nota original si es mayor, o tenga un tope máximo.
     */
    fun applyRecoveryLogic(originalGrade: Grade, recoveryGrade: Grade, maxRecoveryScore: Double = 3.0): Double {
        return if (recoveryGrade.score > originalGrade.score) {
            // Si la política es tope máximo, usamos minOf
            recoveryGrade.score.coerceAtMost(maxRecoveryScore)
        } else {
            originalGrade.score
        }
    }

    /**
     * Obtiene el indicador de logro correspondiente a un puntaje y una competencia.
     * Útil para la generación automática de boletines descriptivos.
     */
    fun getAchievementIndicator(score: Double, scale: GradingScale, competency: Competency): String? {
        val range = scale.ranges.find { score >= it.minLimit && score <= it.maxLimit } ?: return null
        return competency.indicators.find { it.rangeId == range.id }?.description
    }
}
