package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "convivencia_cases",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ConvivenciaCaseEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val teacherId: String,
    val createdByUserId: String,
    val openingDate: Long = 0,
    val status: String,
    val description: String,
    val resolution: String? = null,
    val resolutionDate: Long? = null,
    val syncStatus: Int = 1, // PENDING_INSERT
    val lastModified: Long = 0
)
