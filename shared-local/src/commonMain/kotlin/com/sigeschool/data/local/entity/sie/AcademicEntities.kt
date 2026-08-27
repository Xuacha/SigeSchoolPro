package com.sigeschool.data.local.entity.sie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: String,
    val subjectId: String,
    val gradeId: String,
    val period: Int,
    val description: String,
    val type: String // COGNITIVO, PROCEDIMENTAL, ACTITUDINAL
)

@Entity(tableName = "academic_grades")
data class AcademicGradeEntity(
    @PrimaryKey val id: String,
    val studentId: String,
    val subjectId: String,
    val period: Int,
    val value: Double,
    val achievementIds: String, // Comma separated IDs or JSON
    val observations: String?,
    val updatedAt: Long
)

@Entity(tableName = "discipline_records")
data class DisciplineRecordEntity(
    @PrimaryKey val id: String,
    val studentId: String,
    val type: String, // POSITIVA, NEGATIVA, CITACION_ACUDIENTE, SEGUIMIENTO_PSICOLOGICO
    val description: String,
    val date: Long,
    val teacherId: String,
    val impactOnGrade: Double,
    val parentNotified: Boolean,
    val parentAttended: Boolean? // null: no citado, true: asistió, false: no asistió
)

@Entity(tableName = "study_plans")
data class StudyPlanEntity(
    @PrimaryKey val id: String,
    val title: String,
    val version: String,
    val lastUpdated: Long
)

@Entity(tableName = "area_plans")
data class AreaPlanEntity(
    @PrimaryKey val id: String,
    val studyPlanId: String,
    val name: String,
    val intensity: Int,
    val subjectIds: String // Comma separated IDs
)
