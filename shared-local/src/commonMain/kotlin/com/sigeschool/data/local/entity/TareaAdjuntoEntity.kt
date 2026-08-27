package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "task_attachments",
    indices = [
        Index(value = ["parentId"])
    ]
)
data class TareaAdjuntoEntity(
    @PrimaryKey
    val id: String,
    val parentId: String,
    val fileName: String,
    val fileUrl: String,
    val fileType: String,
    val lastModified: Long = 0,
    val syncStatus: Int = 0
)
