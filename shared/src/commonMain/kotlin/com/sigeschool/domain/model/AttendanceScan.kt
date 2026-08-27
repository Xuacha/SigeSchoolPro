package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AttendanceScan(
    val type: ScanType,
    val identifier: String, // DNI or ID
    val timestamp: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)

enum class ScanType {
    STUDENT_ENTRY,
    STUDENT_EXIT,
    EMPLOYEE_ENTRY,
    EMPLOYEE_EXIT
}
