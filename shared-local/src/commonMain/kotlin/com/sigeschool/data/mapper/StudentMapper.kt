package com.sigeschool.data.mapper

import com.sigeschool.data.local.entity.StudentEntity
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus

fun StudentEntity.toDomain() = Student(
    id = id,
    nombre = firstName,
    apellido = lastName,
    dni = documentId,
    institutionId = institutionId,
    photoUrl = photoPath,
    email = email ?: "",
    telefono = phone ?: "",
    direccion = address ?: "",
    estadoMatricula = try { StudentStatus.valueOf(estadoMatricula) } catch(e: Exception) { StudentStatus.MATRICULADO },
    sincronizado = syncStatus == 1
)

fun Student.toEntity() = StudentEntity(
    id = id,
    institutionId = institutionId,
    firstName = nombre,
    lastName = apellido,
    documentId = dni,
    photoPath = photoUrl,
    qrCode = "", // Default value
    email = email,
    phone = telefono,
    address = direccion,
    estadoMatricula = estadoMatricula.name,
    syncStatus = if (sincronizado) 1 else 0
)
