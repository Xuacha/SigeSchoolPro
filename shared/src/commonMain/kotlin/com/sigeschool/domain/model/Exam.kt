package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Exam(
    val id: String = "",
    val title: String = "",
    val date: Long = 0,
    @SerialName("class_id")
    val classId: String = "",
    @SerialName("subject_id")
    val subjectId: String = "",
    @SerialName("max_score")
    val maxScore: Double = 20.0,
    @SerialName("institution_id")
    val institutionId: String = "",
    val durationMinutes: Int = 60,
    val questions: List<Question> = emptyList(),
    val sincronizado: Boolean = false
)

@Serializable
data class Question(
    val id: String = "",
    val text: String = "",
    val options: List<String> = emptyList(),
    val correctOptionIndex: Int = 0,
    val points: Double = 1.0
)
