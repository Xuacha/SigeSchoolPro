package com.sigeschool.data.local.dao.sie

import androidx.room.*
import com.sigeschool.data.local.entity.sie.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SieDao {
    @Query("SELECT * FROM grading_scales WHERE institutionId = :institutionId")
    fun getGradingScales(institutionId: String): Flow<List<GradingScaleEntity>>

    @Query("SELECT * FROM grading_scales WHERE id = :id")
    suspend fun getGradingScaleById(id: String): GradingScaleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGradingScale(scale: GradingScaleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScaleRanges(ranges: List<ScaleRangeEntity>)

    @Query("SELECT * FROM scale_ranges WHERE gradingScaleId = :scaleId ORDER BY minLimit ASC")
    fun getRangesForScale(scaleId: String): Flow<List<ScaleRangeEntity>>

    @Transaction
    suspend fun saveFullScale(scale: GradingScaleEntity, ranges: List<ScaleRangeEntity>) {
        insertGradingScale(scale)
        insertScaleRanges(ranges)
    }

    // Categorías
    @Query("SELECT * FROM grade_categories WHERE institutionId = :institutionId")
    fun getGradeCategories(institutionId: String): Flow<List<GradeCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGradeCategory(category: GradeCategoryEntity)

    // Competencias
    @Query("SELECT * FROM competencies WHERE institutionId = :institutionId")
    fun getCompetencies(institutionId: String): Flow<List<CompetencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetency(competency: CompetencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievementIndicators(indicators: List<AchievementIndicatorEntity>)

    @Query("SELECT * FROM achievement_indicators WHERE competencyId = :competencyId")
    fun getIndicatorsForCompetency(competencyId: String): Flow<List<AchievementIndicatorEntity>>

    // Rúbricas
    @Query("SELECT * FROM rubrics WHERE institutionId = :institutionId")
    fun getRubrics(institutionId: String): Flow<List<RubricEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRubric(rubric: RubricEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRubricCriteria(criteria: List<RubricCriterionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCriterionLevels(levels: List<CriterionLevelEntity>)

    @Query("SELECT * FROM rubric_criteria WHERE rubricId = :rubricId")
    fun getCriteriaForRubric(rubricId: String): Flow<List<RubricCriterionEntity>>

    @Query("SELECT * FROM criterion_levels WHERE criterionId = :criterionId")
    fun getLevelsForCriterion(criterionId: String): Flow<List<CriterionLevelEntity>>

    // Evaluaciones de Rúbricas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRubricEvaluation(evaluation: RubricEvaluationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCriterionSelections(selections: List<CriterionSelectionEntity>)

    @Transaction
    suspend fun saveFullRubricEvaluation(evaluation: RubricEvaluationEntity, selections: List<CriterionSelectionEntity>) {
        insertRubricEvaluation(evaluation)
        insertCriterionSelections(selections)
    }

    @Query("SELECT * FROM rubric_evaluations WHERE gradeId = :gradeId")
    fun getRubricEvaluationByGrade(gradeId: String): Flow<RubricEvaluationEntity?>

    @Query("SELECT * FROM rubric_selections WHERE evaluationId = :evaluationId")
    fun getSelectionsForEvaluation(evaluationId: String): Flow<List<CriterionSelectionEntity>>
}
