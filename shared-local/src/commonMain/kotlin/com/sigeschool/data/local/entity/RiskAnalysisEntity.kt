package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "risk_analysis")
data class RiskAnalysisEntity(
    @PrimaryKey val studentId: String,
    val institutionId: String,
    val riskLevel: String,
    val riskScore: Double,
    val factors: String,
    val lastModified: Long = 0,
    val syncStatus: Int = 0
)
