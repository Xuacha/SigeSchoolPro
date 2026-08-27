package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class EmployeeStatus {
    ACTIVO, INACTIVO, DESVINCULADO
}

@Serializable
data class Employee(
    val id: String = "",
    @SerialName("institution_id")
    val institutionId: String = "",
    @SerialName("first_name")    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    val dni: String = "",
    val role: UserRole = UserRole.DOCENTE,
    val qualification: String = "", // TÉCNICO, PROFESIONAL, ESPECIALISTA, etc.
    val specialization: String = "", // Área de experticia
    val department: String = "", // ACADÉMICO, FINANCIERO, ADMINISTRATIVO, OPERATIVO
    val email: String = "",
    val phone: String = "",
    @SerialName("hire_date")
    val hireDate: Long = 0,
    val status: EmployeeStatus = EmployeeStatus.ACTIVO
) {
    val fullName: String get() = "$firstName $lastName"
    val isActive: Boolean get() = status == EmployeeStatus.ACTIVO
}
