package com.sigeschool.domain.usecase.sie

import com.sigeschool.domain.model.sie.StudentGrade
import com.sigeschool.domain.model.sie.GradeCategory

/**
 * Caso de uso para calcular la nota final ponderada de un estudiante
 * basándose en las categorías y pesos definidos por la institución.
 */
class CalculateFinalGradeUseCase {
    operator fun invoke(grades: List<StudentGrade>, categories: List<GradeCategory>): Double {
        if (grades.isEmpty()) return 0.0
        
        var totalWeightedScore = 0.0
        var totalWeightUsed = 0.0

        categories.forEach { category ->
            val categoryGrades = grades.filter { it.categoryId == category.id }
            if (categoryGrades.isNotEmpty()) {
                val categoryAverage = categoryGrades.map { it.score }.average()
                totalWeightedScore += categoryAverage * (category.weightPercentage / 100.0)
                totalWeightUsed += category.weightPercentage
            }
        }

        // Si no se han usado todas las categorías (ej: faltan notas), 
        // normalizamos el resultado sobre lo evaluado hasta el momento.
        return if (totalWeightUsed > 0) {
            (totalWeightedScore / (totalWeightUsed / 100.0))
        } else {
            0.0
        }
    }
}
