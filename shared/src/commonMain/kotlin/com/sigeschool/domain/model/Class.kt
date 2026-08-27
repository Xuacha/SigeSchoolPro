package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Class(
    val id: String = "",
    val name: String = "",           // Ej: "1ro A"
    val level: String = "",          // Ej: "Primaria"
    @SerialName("institution_id")
    val institutionId: String = "",
    @SerialName("teacher_id")
    val teacherId: String? = null,
    @SerialName("created_at")
    val createdAt: String = ""
)
