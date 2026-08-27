package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_records")
data class AcademicRecordEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val year: Int,
    val grade: String,
    val gpa: Double,
    val status: String,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
