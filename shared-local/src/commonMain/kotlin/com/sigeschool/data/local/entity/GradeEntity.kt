package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "grades")
data class GradeEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val courseId: String,
    val subjectId: String,
    val score: Double,
    val date: Long,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
