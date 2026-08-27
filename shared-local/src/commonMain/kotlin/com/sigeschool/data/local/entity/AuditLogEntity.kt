package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "audit_logs")
data class AuditLogEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val userId: String,
    val userName: String,
    val userRole: String,
    val action: String,
    val entityName: String,
    val entityId: String,
    val details: String,
    val timestamp: Long = 0
)
