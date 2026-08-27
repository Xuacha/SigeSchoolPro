package com.sigeschool.domain.repository.sie

import com.sigeschool.domain.model.sie.*
import kotlinx.coroutines.flow.Flow

interface SieRepository {
    // Escalas de calificación
    fun getGradingScales(institutionId: String): Flow<List<GradingScale>>
    suspend fun saveGradingScale(scale: GradingScale)
    suspend fun calculateEquivalence(score: Double, scaleId: String): String
    
    // Categorías y Pesos
    fun getCategories(institutionId: String): Flow<List<GradeCategory>>
    suspend fun saveCategory(category: GradeCategory)
    
    // Competencias y Logros
    fun getCompetencies(institutionId: String): Flow<List<Competency>>
    suspend fun saveCompetency(competency: Competency)
    
    // Rúbricas
    fun getRubrics(institutionId: String): Flow<List<Rubric>>
    suspend fun saveRubric(rubric: Rubric)
    suspend fun saveRubricEvaluation(evaluation: RubricEvaluation)
    fun getRubricEvaluation(gradeId: String): Flow<RubricEvaluation?>
}
