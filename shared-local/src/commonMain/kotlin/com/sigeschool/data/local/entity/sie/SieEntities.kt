package com.sigeschool.data.local.entity.sie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grading_scales")
data class GradingScaleEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val name: String,
    val minScore: Double,
    val maxScore: Double,
    val isDefault: Boolean
)

@Entity(tableName = "scale_ranges")
data class ScaleRangeEntity(
    @PrimaryKey val id: String,
    val gradingScaleId: String,
    val name: String,
    val minLimit: Double,
    val maxLimit: Double,
    val description: String?,
    val color: String?
)

@Entity(tableName = "grade_categories")
data class GradeCategoryEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val name: String,
    val weightPercentage: Double,
    val periodId: String?
)

@Entity(tableName = "rubrics")
data class RubricEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val title: String,
    val description: String?
)

@Entity(tableName = "rubric_criteria")
data class RubricCriterionEntity(
    @PrimaryKey val id: String,
    val rubricId: String,
    val name: String,
    val weight: Double
)

@Entity(tableName = "criterion_levels")
data class CriterionLevelEntity(
    @PrimaryKey val id: String,
    val criterionId: String,
    val name: String,
    val score: Double,
    val description: String?
)

@Entity(tableName = "competencies")
data class CompetencyEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val code: String,
    val description: String,
    val area: String
)

@Entity(tableName = "achievement_indicators")
data class AchievementIndicatorEntity(
    @PrimaryKey val id: String,
    val competencyId: String,
    val rangeId: String,
    val description: String
)

@Entity(tableName = "rubric_evaluations")
data class RubricEvaluationEntity(
    @PrimaryKey val id: String,
    val gradeId: String,
    val rubricId: String
)

@Entity(tableName = "rubric_selections")
data class CriterionSelectionEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val evaluationId: String,
    val criterionId: String,
    val levelId: String,
    val score: Double
)
