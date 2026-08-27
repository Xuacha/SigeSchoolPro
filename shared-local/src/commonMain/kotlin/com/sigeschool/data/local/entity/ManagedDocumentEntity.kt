package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "managed_documents")
data class ManagedDocumentEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val type: String, // Normalizado a String
    val content: String,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
