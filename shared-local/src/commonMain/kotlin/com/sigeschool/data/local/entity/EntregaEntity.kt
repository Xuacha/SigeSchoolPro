package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "task_submissions",
    indices = [
        Index(value = ["tareaId"]),
        Index(value = ["estudianteId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = TareaEntity::class,
            parentColumns = ["id"],
            childColumns = ["tareaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["estudianteId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EntregaEntity(
    @PrimaryKey
    val id: String,
    val tareaId: String,
    val estudianteId: String,
    val status: String = "ENTREGADO",
    val submissionDate: Long = 0,
    val comment: String? = null,
    val grade: Double? = null,
    val feedback: String? = null,
    val lastModified: Long = 0,
    val syncStatus: Int = 0
)
