package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val institutionId: String,
    val username: String,
    val role: UserRole,
    val fullName: String,
    val email: String? = null,
    val profilePictureUri: String? = null
)
