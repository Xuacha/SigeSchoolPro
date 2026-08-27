package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "parents_guardians")
data class ParentGuardianEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val userId: String? = null,
    val fullName: String,
    val documentId: String,
    val phoneNumber: String,
    val email: String? = null,
    val relationToStudent: String,
    val lastModified: Long = 0
)
