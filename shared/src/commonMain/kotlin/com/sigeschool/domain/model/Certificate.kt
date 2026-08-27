package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Certificate(
    val id: String = "",
    @SerialName("student_id")
    val studentId: String = "",
    val type: CertificateType = CertificateType.CONSTANCIA_ESTUDIO,
    @SerialName("issue_date")
    val issueDate: String = "",
    @SerialName("template_id")
    val templateId: String = "",
    @SerialName("institution_id")
    val institutionId: String = ""
)

enum class CertificateType {
    CONSTANCIA_ESTUDIO, // Estado activo actual
    CERTIFICADO_ESTUDIO, // Validación de aprobación de grado/semestre
    GRADUACION,
    ASISTENCIA
}
