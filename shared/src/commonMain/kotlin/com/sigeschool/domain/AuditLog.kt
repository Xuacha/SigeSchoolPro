package com.sigeschool.domain

import kotlinx.serialization.Serializable

@Serializable
data class AuditLog(
    val id: String,
    val institutionId: String,
    val userId: String,
    val userName: String,
    val userRole: String,
    val action: String,
    val entityName: String,
    val entityId: String,
    val details: String,
    val timestamp: Long
)
