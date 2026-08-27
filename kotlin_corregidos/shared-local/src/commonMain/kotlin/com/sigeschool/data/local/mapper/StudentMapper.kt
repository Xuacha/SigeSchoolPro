package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.StudentEntity
import com.sigeschool.domain.model.Student

import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.domain.model.AcademicStatus

fun StudentEntity.toDomain(): Student {
    return Student(
        id = id,
        nombre = nombre,
        apellido = apellido,
        fechaNacimiento = fechaNacimiento,
        grado = grado,
        seccion = seccion,
        dni = dni,
        telefono = telefono,
        email = email,
        direccion = direccion,
        fechaRegistro = fechaRegistro,
        institutionId = institutionId,
        estadoMatricula = StudentStatus.valueOf(estadoMatricula),
        estadoAcademico = AcademicStatus.valueOf(estadoAcademico),
        activo = activo,
        sincronizado = sincronizado
    )
}

fun Student.toEntity(): StudentEntity {
    return StudentEntity(
        id = id,
        nombre = nombre,
        apellido = apellido,
        fechaNacimiento = fechaNacimiento,
        grado = grado,
        seccion = seccion,
        dni = dni,
        telefono = telefono,
        email = email,
        direccion = direccion,
        fechaRegistro = fechaRegistro,
        institutionId = institutionId,
        estadoMatricula = estadoMatricula.name,
        estadoAcademico = estadoAcademico.name,
        activo = activo,
        sincronizado = sincronizado
    )
}
