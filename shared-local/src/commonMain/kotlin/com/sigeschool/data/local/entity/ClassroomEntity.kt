package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "classrooms")
data class ClassroomEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val name: String,
    val capacity: Int,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
