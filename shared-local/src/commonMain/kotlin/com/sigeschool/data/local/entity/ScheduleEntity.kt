package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val classroomId: String,
    val subjectId: String,
    val teacherId: String,
    val dayOfWeek: Int,
    val startTime: String,
    val endTime: String,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
