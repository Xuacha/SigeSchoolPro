package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "salaries")
data class SalaryEntity(
    @PrimaryKey val id: String,
    val employeeId: String,
    val institutionId: String,
    val amount: Double,
    val concept: String,
    val paymentDate: Long,
    val periodMonth: Int,
    val periodYear: Int,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
