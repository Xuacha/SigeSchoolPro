package com.sigeschool.data.local.entity.sie

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import com.sigeschool.data.local.entity.StudentEntity

@Entity(
    tableName = "autoevaluaciones",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AutoevaluacionEntity(
    @PrimaryKey val id: String,
    val studentId: String, // Cambiado de Int a String para coincidir con StudentEntity
    val subjectId: String,
    val periodId: String,
    val score: Double,
    val registrationDate: Long,
    val metadata: String?
)

@Entity(tableName = "configuracion_promocion")
data class PromotionConfigEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val maxFailedSubjects: Int,
    val maxInattendancePercentage: Double,
    val minimumPassingScore: Double,
    val autoevaluacionWeight: Double
)
