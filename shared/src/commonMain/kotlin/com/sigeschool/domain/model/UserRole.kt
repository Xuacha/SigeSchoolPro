package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class UserRole(val level: Int) {
    REPRESENTANTE_LEGAL(5),
    RECTOR(4),
    DIRECTOR(4),
    COORDINADOR_ACADEMICO(3),
    CONTADOR(3),
    SECRETARIA(2),
    DOCENTE(2),
    PERSONAL_ADMINISTRATIVO(2),
    PADRE_FAMILIA(1),
    ESTUDIANTE(1),
    INVITADO(0);

    companion object {
        fun fromString(value: String?): UserRole {
            return entries.find { it.name.equals(value, ignoreCase = true) } ?: DOCENTE
        }
    }

    fun canManagePEI(): Boolean = level >= RECTOR.level
    fun canViewPEI(): Boolean = level >= DOCENTE.level

    fun canManagePlanEstudios(): Boolean = level >= RECTOR.level
    fun canViewPlanEstudios(): Boolean = level >= DOCENTE.level

    fun canManagePlanAula(): Boolean = level >= RECTOR.level || this == DOCENTE
    fun canViewPlanAula(): Boolean = level >= RECTOR.level || this == DOCENTE

    fun canEnrollStudent(): Boolean = level >= RECTOR.level || this == SECRETARIA
}

@Serializable
data class UserPermission(
    val role: UserRole,
    val permissions: List<String>
)

val permissionsByRole = mapOf(
    UserRole.REPRESENTANTE_LEGAL to listOf("all", "financial_config"),
    UserRole.RECTOR to listOf("all"),
    UserRole.DIRECTOR to listOf("all_minus_deep_finance"),
    UserRole.COORDINADOR_ACADEMICO to listOf("academic_management", "manage_teachers", "manage_students"),
    UserRole.CONTADOR to listOf("financial_module", "payroll", "economic_reports"),
    UserRole.SECRETARIA to listOf("enrollment", "id_cards", "documents"),
    UserRole.DOCENTE to listOf("attendance", "grades", "tasks"),
    UserRole.PERSONAL_ADMINISTRATIVO to listOf("personal_attendance"),
    UserRole.PADRE_FAMILIA to listOf(
        "view_own_student_reports",
        "view_attendance",
        "view_grades",
        "view_tasks",
        "receive_notifications"
    ),
    UserRole.ESTUDIANTE to listOf("view_own_info", "view_announcements", "view_assigned_tasks"),
    UserRole.INVITADO to emptyList()
)

@Serializable
data class ParentStudentRelation(
    val parentId: String = "",
    val studentId: String = "",
    val relationship: String = "PADRE" // PADRE, MADRE, TUTOR
)
