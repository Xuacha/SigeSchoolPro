package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "case_testimonies",
    foreignKeys = [
        ForeignKey(
            entity = ConvivenciaCaseEntity::class,
            parentColumns = ["id"],
            childColumns = ["caseId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TestimonyEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val caseId: String,
    val authorName: String,
    val authorRole: String,
    val content: String,
    val createdAt: Long = 0
)
