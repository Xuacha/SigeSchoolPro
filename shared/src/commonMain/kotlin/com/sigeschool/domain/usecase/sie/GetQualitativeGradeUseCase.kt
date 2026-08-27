package com.sigeschool.domain.usecase.sie

import com.sigeschool.domain.repository.sie.SieRepository

class GetQualitativeGradeUseCase(private val repository: SieRepository) {
    suspend operator fun invoke(score: Double, scaleId: String): String {
        return repository.calculateEquivalence(score, scaleId)
    }
}
