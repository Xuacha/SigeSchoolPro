package com.sigeschool.domain.model.sie

import com.sigeschool.domain.model.Student
import kotlinx.serialization.Serializable

@Serializable
data class AcademicReport(
    val student: Student,
    val period: Int,
    val grades: List<SubjectGrade>,
    val conductScore: ConductScore,
    val familySupportScore: FamilySupportScore,
    val overallAverage: Double,
    val generalObservations: String,
    val signatures: ReportSignatures
)

@Serializable
data class SubjectGrade(
    val subject: String,
    val teacher: String,
    val periods: Map<Int, Double>,
    val finalGrade: Double,
    val performanceLevel: PerformanceLevel,
    val indicators: List<String> = emptyList(),
    val observations: String
)

@Serializable
enum class PerformanceLevel {
    SUPERIOR, ALTO, BASICO, BAJO
}

@Serializable
data class ConductScore(
    val score: Double,
    val positiveNotes: List<String>,
    val negativeNotes: List<String>,
    val overallComment: String
)

@Serializable
data class FamilySupportScore(
    val score: Double,
    val citationsAttended: Int,
    val citationsMissed: Int,
    val comments: String
)

@Serializable
data class ReportSignatures(
    val rectorName: String,
    val rectorSignatureUrl: String?,
    val date: String
)
