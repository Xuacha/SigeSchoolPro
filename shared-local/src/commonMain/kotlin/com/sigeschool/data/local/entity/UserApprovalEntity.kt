package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user_approvals")
data class UserApprovalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val userId: String,
    val status: String,
    val requestedAt: Long = 0,
    val approvedAt: Long? = null,
    val approvedByUserId: String? = null,
    val rejectedReason: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
