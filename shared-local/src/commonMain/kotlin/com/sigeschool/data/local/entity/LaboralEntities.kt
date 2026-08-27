package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacation_requests")
data class VacationRequestEntity(
    @PrimaryKey val id: String,
    val employeeId: String,
    val startDate: Long,
    val endDate: Long,
    val days: Int,
    val status: String,
    val observations: String,
    val sincronizado: Boolean = false
)

@Entity(tableName = "advance_requests")
data class AdvanceRequestEntity(
    @PrimaryKey val id: String,
    val employeeId: String,
    val amountRequested: Double,
    val reason: String,
    val status: String,
    val requestDate: Long,
    val maxAllowed: Double,
    val sincronizado: Boolean = false
)

@Entity(tableName = "payroll_calculations")
data class PayrollCalculationEntity(
    @PrimaryKey val id: String,
    val employeeId: String,
    val basicSalary: String, // Encrypted
    val daysWorked: Int,
    val transportAllowance: String, // Encrypted
    val healthDeduction: String, // Encrypted
    val pensionDeduction: String, // Encrypted
    val advances: String, // Encrypted
    val extraHours: String = "0.0", // Encrypted
    val totalDevengado: String, // Encrypted
    val totalDeducciones: String, // Encrypted
    val netPay: String, // Encrypted
    val date: Long,
    val sincronizado: Boolean = false
)
