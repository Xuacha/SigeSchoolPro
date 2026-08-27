package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val dueDate: Long,
    val classId: String,
    val subjectId: String,
    val institutionId: String,
    val sincronizado: Boolean = false
)
