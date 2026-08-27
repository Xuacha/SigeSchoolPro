package com.sigeschool.data.repository.sie

import com.sigeschool.data.datasource.sie.SieLocalDataSource
import com.sigeschool.domain.model.sie.*
import com.sigeschool.domain.repository.sie.SieRepository
import kotlinx.coroutines.flow.Flow

class SieRepositoryImpl(private val localDataSource: SieLocalDataSource) : SieRepository {

    override fun getGradingScales(institutionId: String): Flow<List<GradingScale>> {
        return localDataSource.getGradingScales(institutionId)
    }

    override suspend fun saveGradingScale(scale: GradingScale) {
        localDataSource.saveGradingScale(scale)
    }

    override suspend fun calculateEquivalence(score: Double, scaleId: String): String {
        return localDataSource.calculateEquivalence(score, scaleId)
    }

    override fun getCategories(institutionId: String): Flow<List<GradeCategory>> {
        return localDataSource.getCategories(institutionId)
    }

    override suspend fun saveCategory(category: GradeCategory) {
        localDataSource.saveCategory(category)
    }

    override fun getCompetencies(institutionId: String): Flow<List<Competency>> {
        return localDataSource.getCompetencies(institutionId)
    }

    override suspend fun saveCompetency(competency: Competency) {
        localDataSource.saveCompetency(competency)
    }

    override fun getRubrics(institutionId: String): Flow<List<Rubric>> {
        return localDataSource.getRubrics(institutionId)
    }

    override suspend fun saveRubric(rubric: Rubric) {
        localDataSource.saveRubric(rubric)
    }

    override suspend fun saveRubricEvaluation(evaluation: RubricEvaluation) {
        localDataSource.saveRubricEvaluation(evaluation)
    }

    override fun getRubricEvaluation(gradeId: String): Flow<RubricEvaluation?> {
        return localDataSource.getRubricEvaluation(gradeId)
    }
}
