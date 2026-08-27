package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val id: String,
    val name: String,
    val level: Int,
    val description: String?,
    val permissions: Map<String, String>, // resource to actions
    val isSystem: Boolean = false
)

@Serializable
data class Permission(
    val id: String,
    val name: String,
    val resource: String,
    val action: String,
    val description: String?
)
