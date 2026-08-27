package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "tareas",
    indices = [
        Index(value = ["institutionId"]),
        Index(value = ["claseId"]),
        Index(value = ["createdBy"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = ClaseEntity::class,
            parentColumns = ["id"],
            childColumns = ["claseId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TareaEntity(
    @PrimaryKey
    val id: String,
    val institutionId: String,
    val claseId: Long,
    val title: String,
    val description: String,
    val deadline: Long,
    val createdBy: String,
    val createdAt: Long = 0,
    val lastModified: Long = 0,
    val syncStatus: Int = 0
)
