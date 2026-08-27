package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey val id: String,
    val authUserId: String?,
    val institutionId: String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val email: String,
    val createdAt: Long,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
