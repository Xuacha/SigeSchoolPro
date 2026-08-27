package com.sigeschool.domain.model.sie

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class Achievement(
    val id: String,
    val subjectId: String,
    val gradeId: String,
    val period: Int,
    val description: String,
    val type: AchievementType = AchievementType.COGNITIVO
)

@Serializable
enum class AchievementType {
    COGNITIVO, PROCEDIMENTAL, ACTITUDINAL
}

@Serializable
data class AcademicGrade(
    val id: String,
    val studentId: String,
    val subjectId: String,
    val period: Int,
    val value: Double,
    val achievementIds: List<String> = emptyList(),
    val observations: String? = null,
    val updatedAt: Instant
)

@Serializable
data class DisciplineRecord(
    val id: String,
    val studentId: String,
    val type: DisciplineType,
    val description: String,
    val date: Instant,
    val teacherId: String,
    val impactOnGrade: Double = 0.0, // Afectación a la nota de conducta
    val parentNotified: Boolean = false,
    val parentAttended: Boolean? = null // null: no citado, true: asistió, false: no asistió
)

@Serializable
enum class DisciplineType {
    POSITIVA, NEGATIVA, CITACION_ACUDIENTE, SEGUIMIENTO_PSICOLOGICO
}

@Serializable
data class StudyPlan(
    val id: String,
    val title: String,
    val areas: List<AreaPlan>,
    val version: String,
    val lastUpdated: Instant
)

@Serializable
data class AreaPlan(
    val id: String,
    val name: String,
    val intensity: Int, // Horas semanales
    val subjects: List<String> // IDs de asignaturas
)

@Serializable
data class Sede(val id: String, val nombre: String)

@Serializable
data class Jornada(val id: String, val nombre: String)

@Serializable
data class Curso(val id: String, val nombre: String, val gradoId: String)

