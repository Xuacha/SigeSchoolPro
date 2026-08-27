package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "payments")
data class PaymentEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val amount: Double,
    val date: Long, // Adaptado a Long
    val concept: String,
    val paymentMethod: String,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
