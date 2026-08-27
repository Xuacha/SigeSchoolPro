package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.StudentEntity
import com.sigeschool.domain.model.Student

import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.domain.model.AcademicStatus
import com.sigeschool.domain.util.CryptoManager

fun StudentEntity.toDomain(): Student {
    return Student(
        id = id,
        institutionId = institutionId,
        nombre = nombre,
        apellido = apellido,
        fechaNacimiento = fechaNacimiento,
        grado = grado,
        seccion = seccion,
        dni = CryptoManager.decrypt(dni),
        telefono = CryptoManager.decrypt(telefono),
        email = CryptoManager.decrypt(email),
        direccion = CryptoManager.decrypt(direccion),
        nombreAcudiente = nombreAcudiente,
        documentoAcudiente = documentoAcudiente?.let { CryptoManager.decrypt(it) },
        telefonoAcudiente = telefonoAcudiente?.let { CryptoManager.decrypt(it) },
        emailAcudiente = emailAcudiente?.let { CryptoManager.decrypt(it) },
        fechaRegistro = fechaRegistro,
        estadoMatricula = StudentStatus.valueOf(estadoMatricula),
        estadoAcademico = AcademicStatus.valueOf(estadoAcademico),
        activo = activo,
        sincronizado = sincronizado
    )
}

fun Student.toEntity(): StudentEntity {
    return StudentEntity(
        id = id,
        institutionId = institutionId,
        nombre = nombre,
        apellido = apellido,
        fechaNacimiento = fechaNacimiento,
        grado = grado,
        seccion = seccion,
        dni = CryptoManager.encrypt(dni),
        telefono = CryptoManager.encrypt(telefono),
        email = CryptoManager.encrypt(email),
        direccion = CryptoManager.encrypt(direccion),
        nombreAcudiente = nombreAcudiente,
        documentoAcudiente = documentoAcudiente?.let { CryptoManager.encrypt(it) },
        telefonoAcudiente = telefonoAcudiente?.let { CryptoManager.encrypt(it) },
        emailAcudiente = emailAcudiente?.let { CryptoManager.encrypt(it) },
        fechaRegistro = fechaRegistro,
        estadoMatricula = estadoMatricula.name,
        estadoAcademico = estadoAcademico.name,
        activo = activo,
        sincronizado = sincronizado
    )
}
