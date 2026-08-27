package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sigeschool.domain.model.Question

@Entity(tableName = "exams")
data class ExamEntity(
    @PrimaryKey val id: String,
    val title: String,
    val date: Long,
    val classId: String,
    val subjectId: String,
    val maxScore: Double,
    val institutionId: String,
    val durationMinutes: Int = 60,
    val questions: List<Question> = emptyList(),
    val sincronizado: Boolean = false
)
