package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Subject(
    val id: String = "",
    val name: String = "",           // Ej: "Matemáticas", "Lenguaje"
    @SerialName("class_id")
    val classId: String = "",
    @SerialName("teacher_id")
    val teacherId: String? = null
)
