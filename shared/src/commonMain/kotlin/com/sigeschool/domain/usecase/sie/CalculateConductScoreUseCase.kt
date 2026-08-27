package com.sigeschool.domain.usecase.sie

import com.sigeschool.domain.model.sie.DisciplineRecord
import com.sigeschool.domain.model.sie.DisciplineType
import kotlin.math.max
import kotlin.math.min

class CalculateConductScoreUseCase {
    operator fun invoke(records: List<DisciplineRecord>): Double {
        val baseScore = 4.0
        
        val positiveAdjustment = records.filter { it.type == DisciplineType.POSITIVA }
            .size * 0.2
        val cappedPositive = min(1.0, positiveAdjustment)
        
        val negativeAdjustment = records.filter { it.type != DisciplineType.POSITIVA }
            .sumOf { 
                when(it.type) {
                    DisciplineType.NEGATIVA -> 0.3
                    DisciplineType.CITACION_ACUDIENTE -> 0.5
                    DisciplineType.SEGUIMIENTO_PSICOLOGICO -> 0.1
                    else -> 0.0
                }
            }

        return max(1.0, min(5.0, baseScore + cappedPositive - negativeAdjustment))
    }
}
