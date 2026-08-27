package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class InstitutionMember(
    val id: String? = null,
    @SerialName("user_id")
    val userId: String,
    @SerialName("institution_id")
    val institutionId: String,
    val role: String = "DOCENTE", // ADMIN, RECTOR, SECRETARIA, DOCENTE
    @SerialName("created_at")
    val createdAt: String? = null
)
