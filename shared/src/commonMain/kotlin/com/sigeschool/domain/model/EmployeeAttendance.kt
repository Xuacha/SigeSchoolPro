package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class EmployeeAttendance(
    val id: String = "", // UUID
    @SerialName("employee_id")
    val employeeId: String,
    val institutionId: String,
    @SerialName("check_in")
    val checkIn: String? = null, // ISO Timestamp
    @SerialName("check_out")
    val checkOut: String? = null, // ISO Timestamp
    @SerialName("total_hours")
    val totalHours: Double = 0.0,
    @SerialName("extra_hours")
    val extraHours: Double = 0.0,
    @SerialName("is_extra_approved")
    val isExtraApproved: Boolean = false,
    @SerialName("approved_by")
    val approvedBy: String? = null,
    val date: String, // YYYY-MM-DD
    val status: String = "REGULAR", // REGULAR, HOLIDAY, ABSENT
    val sincronizado: Boolean = false
)
