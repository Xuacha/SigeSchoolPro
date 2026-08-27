package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class TaskStatus { PENDING, SUBMITTED, GRADED }

@Serializable
data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    @SerialName("due_date")
    val dueDate: String = "", // Cambiado a String para consistencia con tu nueva propuesta
    @SerialName("class_id")
    val classId: String = "",
    @SerialName("subject_id")
    val subjectId: String = "",
    @SerialName("teacher_id")
    val teacherId: String = "",
    @SerialName("institution_id")
    val institutionId: String = "",
    val status: TaskStatus = TaskStatus.PENDING,
    val sincronizado: Boolean = false
)

@Serializable
data class TaskSubmission(
    val id: String = "",
    @SerialName("task_id")
    val taskId: String = "",
    @SerialName("student_id")
    val studentId: String = "",
    @SerialName("submission_date")
    val submissionDate: String = "",
    @SerialName("evidence_url")
    val evidenceUrl: String? = null,
    val score: Double? = null,
    val sincronizado: Boolean = false
)
