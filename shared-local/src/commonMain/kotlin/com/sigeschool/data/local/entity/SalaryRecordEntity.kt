package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salary_records")
data class SalaryRecordEntity(
    @PrimaryKey val id: String,
    val employeeId: String,
    val institutionId: String,
    val amount: Double,
    val date: Long,
    val type: String,
    val status: String,
    val observation: String,
    val sincronizado: Boolean = false
)
