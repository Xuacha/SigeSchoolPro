package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.datetime.Clock

@Serializable
data class Attendance(
    val id: String = "", // UUID
    @SerialName("studentId")
    val studentId: String,
    val institutionId: String,
    val fecha: String, // ISO Format YYYY-MM-DD
    val estado: AttendanceStatus,
    val observaciones: String = "",
    val sincronizado: Boolean = false
) {
    fun test() = kotlinx.datetime.Clock.System.now()
}

enum class AttendanceStatus {
    PRESENT, ABSENT, LATE, JUSTIFIED,
    // Keep Spanish for backward compatibility if needed, or migration
    PRESENTE, FALTA, TARDE, JUSTIFICADO
}
