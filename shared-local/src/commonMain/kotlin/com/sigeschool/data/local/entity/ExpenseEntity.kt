package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val amount: Double,
    val date: Long, // Adaptado a Long
    val description: String,
    val category: String,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
