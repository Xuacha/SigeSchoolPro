package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classes")
data class ClassEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val level: String,
    val institutionId: String,
    val teacherId: String?,
    val createdAt: String
)
