package com.sigeschool.data.local.datasource.sie

import com.sigeschool.data.datasource.sie.SieLocalDataSource
import com.sigeschool.data.local.dao.sie.SieDao
import com.sigeschool.data.local.entity.sie.*
import com.sigeschool.domain.model.sie.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SieLocalDataSourceImpl(private val sieDao: SieDao) : SieLocalDataSource {

    override fun getGradingScales(institutionId: String): Flow<List<GradingScale>> {
        return sieDao.getGradingScales(institutionId).map { entities ->
            entities.map { entity ->
                val ranges = sieDao.getRangesForScale(entity.id).first()
                entity.toDomain(ranges.map { it.toDomain() })
            }
        }
    }

    override suspend fun saveGradingScale(scale: GradingScale) {
        val entity = scale.toEntity()
        val rangeEntities = scale.ranges.map { it.toEntity() }
        sieDao.saveFullScale(entity, rangeEntities)
    }

    override suspend fun getGradingScaleById(id: String): GradingScale? {
        val entity = sieDao.getGradingScaleById(id)
        return if (entity != null) {
            val ranges = sieDao.getRangesForScale(entity.id).first()
            entity.toDomain(ranges.map { it.toDomain() })
        } else null
    }

    override suspend fun calculateEquivalence(score: Double, scaleId: String): String {
        val ranges = sieDao.getRangesForScale(scaleId).first()
        return ranges.find { score >= it.minLimit && score <= it.maxLimit }?.name ?: "Sin escala"
    }

    override fun getCategories(institutionId: String): Flow<List<GradeCategory>> {
        return sieDao.getGradeCategories(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveCategory(category: GradeCategory) {
        sieDao.insertGradeCategory(category.toEntity())
    }

    override fun getCompetencies(institutionId: String): Flow<List<Competency>> {
        return sieDao.getCompetencies(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveCompetency(competency: Competency) {
        sieDao.insertCompetency(competency.toEntity())
    }

    override fun getRubrics(institutionId: String): Flow<List<Rubric>> {
        return sieDao.getRubrics(institutionId).map { entities ->
            entities.map { entity ->
                val criteria = sieDao.getCriteriaForRubric(entity.id).first().map { criterionEntity ->
                    val levels = sieDao.getLevelsForCriterion(criterionEntity.id).first().map { levelEntity ->
                        CriterionLevel(levelEntity.id, levelEntity.name, levelEntity.score, levelEntity.description)
                    }
                    RubricCriterion(criterionEntity.id, criterionEntity.name, criterionEntity.weight, levels)
                }
                Rubric(entity.id, entity.title, entity.description, criteria)
            }
        }
    }

    override suspend fun saveRubric(rubric: Rubric) {
        sieDao.insertRubric(RubricEntity(rubric.id, "INST_TODO", rubric.title, rubric.description))
        val criteriaEntities = rubric.criteria.map { 
            RubricCriterionEntity(it.id, rubric.id, it.name, it.weight)
        }
        sieDao.insertRubricCriteria(criteriaEntities)
        
        rubric.criteria.forEach { criterion ->
            val levelEntities = criterion.levels.map { 
                CriterionLevelEntity(it.id, criterion.id, it.name, it.score, it.description)
            }
            sieDao.insertCriterionLevels(levelEntities)
        }
    }

    override suspend fun saveRubricEvaluation(evaluation: RubricEvaluation) {
        val entity = RubricEvaluationEntity(evaluation.id, evaluation.gradeId, evaluation.rubricId)
        val selectionEntities = evaluation.selections.map { 
            CriterionSelectionEntity(evaluationId = evaluation.id, criterionId = it.criterionId, levelId = it.levelId, score = it.score)
        }
        sieDao.saveFullRubricEvaluation(entity, selectionEntities)
    }

    override fun getRubricEvaluation(gradeId: String): Flow<RubricEvaluation?> {
        return sieDao.getRubricEvaluationByGrade(gradeId).map { entity ->
            entity?.let {
                val selections = sieDao.getSelectionsForEvaluation(it.id).first().map { sel ->
                    CriterionSelection(sel.criterionId, sel.levelId, sel.score)
                }
                RubricEvaluation(it.id, it.gradeId, it.rubricId, selections)
            }
        }
    }

    private fun GradeCategoryEntity.toDomain() = GradeCategory(
        id = this.id,
        institutionId = this.institutionId,
        name = this.name,
        weightPercentage = this.weightPercentage,
        periodId = this.periodId
    )

    private fun GradeCategory.toEntity() = GradeCategoryEntity(
        id = this.id,
        institutionId = this.institutionId,
        name = this.name,
        weightPercentage = this.weightPercentage,
        periodId = this.periodId
    )

    private fun CompetencyEntity.toDomain() = Competency(
        id = this.id,
        institutionId = this.institutionId,
        code = this.code,
        description = this.description,
        area = this.area
    )

    private fun Competency.toEntity() = CompetencyEntity(
        id = this.id,
        institutionId = this.institutionId,
        code = this.code,
        description = this.description,
        area = this.area
    )

    private fun GradingScaleEntity.toDomain(ranges: List<ScaleRange>) = GradingScale(
        id = this.id,
        institutionId = this.institutionId,
        name = this.name,
        minScore = this.minScore,
        maxScore = this.maxScore,
        isDefault = this.isDefault,
        ranges = ranges
    )

    private fun ScaleRangeEntity.toDomain() = ScaleRange(
        id = this.id,
        gradingScaleId = this.gradingScaleId,
        name = this.name,
        minLimit = this.minLimit,
        maxLimit = this.maxLimit,
        description = this.description,
        color = this.color
    )

    private fun GradingScale.toEntity() = GradingScaleEntity(
        id = this.id,
        institutionId = this.institutionId,
        name = this.name,
        minScore = this.minScore,
        maxScore = this.maxScore,
        isDefault = this.isDefault
    )

    private fun ScaleRange.toEntity() = ScaleRangeEntity(
        id = this.id,
        gradingScaleId = this.gradingScaleId,
        name = this.name,
        minLimit = this.minLimit,
        maxLimit = this.maxLimit,
        description = this.description,
        color = this.color
    )
}
