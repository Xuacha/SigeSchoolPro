package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "risk_summary")
data class RiskSummaryEntity(
    @PrimaryKey val institutionId: String,
    val totalStudents: Int,
    val critical: Int,
    val high: Int,
    val medium: Int,
    val low: Int,
    val averageRisk: Double,
    val lastModified: Long = 0,
    val syncStatus: Int = 0
)
