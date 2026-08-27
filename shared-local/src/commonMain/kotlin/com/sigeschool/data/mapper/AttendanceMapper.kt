package com.sigeschool.data.mapper

import com.sigeschool.data.local.entity.AttendanceEntity
import com.sigeschool.domain.model.Attendance
import com.sigeschool.domain.model.AttendanceStatus

fun AttendanceEntity.toDomain() = Attendance(
    id = id,
    studentId = studentId,
    institutionId = institutionId,
    fecha = "", // Adaptar timestamp a ISO String si es necesario
    estado = AttendanceStatus.PRESENTE, // Adaptar type a status
    observaciones = observacion ?: "",
    sincronizado = syncStatus == 1
)

fun Attendance.toEntity() = AttendanceEntity(
    id = id,
    institutionId = institutionId,
    studentId = studentId,
    timestamp = 0, // Adaptar fecha a Long
    type = "ENTRY", // Valor por defecto para cumplir con el constructor
    observacion = observaciones,
    syncStatus = if (sincronizado) 1 else 0
)
