package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "behavioral_scores",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = BehavioralCompetencyEntity::class,
            parentColumns = ["id"],
            childColumns = ["competencyId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BehavioralScoreEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val competencyId: String,
    val periodId: String,
    val scoreType: String,
    val feedback: String? = null,
    val evaluationDate: Long = 0
)
