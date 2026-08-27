package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Student(
    val id: String = "", // UUID
    val nombre: String = "",
    val apellido: String = "",
    @SerialName("fecha_nacimiento")
    val fechaNacimiento: String = "",
    val grado: String = "",
    val seccion: String = "",
    val dni: String = "",
    val telefono: String = "",
    val email: String = "",
    val direccion: String = "",
    val nombreAcudiente: String? = null,
    val documentoAcudiente: String? = null,
    val telefonoAcudiente: String? = null,
    val emailAcudiente: String? = null,
    @SerialName("fechaRegistro")
    val fechaRegistro: String = "",
    val institutionId: String = "",
    val photoUrl: String? = null,
    val estadoMatricula: StudentStatus = StudentStatus.MATRICULADO,
    val estadoAcademico: AcademicStatus = AcademicStatus.CURSANDO,
    val activo: Boolean = true,
    val sincronizado: Boolean = false
) {
    val nombreCompleto: String get() = "$nombre $apellido"
}

enum class StudentStatus {
    MATRICULADO, RETIRADO, SUSPENDIDO, ASPIRANTE
}

enum class AcademicStatus {
    CURSANDO, PROMOVIDO, GRADUADO, REPROBADO
}
