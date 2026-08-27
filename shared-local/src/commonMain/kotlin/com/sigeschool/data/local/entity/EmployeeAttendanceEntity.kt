package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sigeschool.domain.model.EmployeeAttendance

@Entity(tableName = "employee_attendance")
data class EmployeeAttendanceEntity(
    @PrimaryKey
    val id: String, // UUID
    val employeeId: String,
    val institutionId: String,
    val checkIn: String?,
    val checkOut: String?,
    val totalHours: Double,
    val extraHours: Double,
    val isExtraApproved: Boolean,
    val approvedBy: String?,
    val date: String,
    val status: String,
    val sincronizado: Boolean = false
) {
    fun toDomain() = EmployeeAttendance(
        id = id,
        employeeId = employeeId,
        institutionId = institutionId,
        checkIn = checkIn,
        checkOut = checkOut,
        totalHours = totalHours,
        extraHours = extraHours,
        isExtraApproved = isExtraApproved,
        approvedBy = approvedBy,
        date = date,
        status = status,
        sincronizado = sincronizado
    )
}

fun EmployeeAttendance.toEntity() = EmployeeAttendanceEntity(
    id = id,
    employeeId = employeeId,
    institutionId = institutionId,
    checkIn = checkIn,
    checkOut = checkOut,
    totalHours = totalHours,
    extraHours = extraHours,
    isExtraApproved = isExtraApproved,
    approvedBy = approvedBy,
    date = date,
    status = status,
    sincronizado = sincronizado
)
