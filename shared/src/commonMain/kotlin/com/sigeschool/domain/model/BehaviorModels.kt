package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ConvivenciaCase(
    val id: String,
    val institutionId: String,
    val studentId: String,
    val title: String,
    val description: String,
    val faultType: String = "LEVE", // LEVE, GRAVE, GRAVISIMA
    val status: String = "ABIERTO", // ABIERTO, EN_PROCESO, CERRADO
    val openingDate: Long = 0,
    val resolution: String? = null
)

@Serializable
data class FamilyAttendance(
    val id: String,
    val institutionId: String,
    val studentId: String,
    val citationDate: Long,
    val reason: String,
    val attended: Boolean = false,
    val commitments: String? = null
)

@Serializable
data class BehavioralScore(
    val id: String,
    val institutionId: String,
    val studentId: String,
    val period: Int,
    val score: Double,
    val observations: String? = null
)
