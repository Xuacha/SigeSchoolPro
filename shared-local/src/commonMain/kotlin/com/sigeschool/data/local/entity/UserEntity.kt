package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val username: String,
    val password: String,
    val role: String,
    val fullName: String,
    val email: String? = null,
    val profilePictureUri: String? = null,
    val fcmToken: String? = null,
    val isFirstLogin: Boolean = true,
    val isActive: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
