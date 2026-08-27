package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Grade(
    val id: String = "", // UUID
    @SerialName("student_id")
    val studentId: String,
    val institutionId: String,
    val subjectId: String = "",          // ID de la asignatura (reemplaza String plano)
    val periodId: String = "",           // ID del periodo (reemplaza String plano)
    val claseId: String = "",            // ID de la clase
    val categoryId: String? = null,      // Relación con GradeCategory (Exámenes, Tareas, etc.)
    val score: Double = 0.0,
    val observations: String = "",
    val date: String = "",               // YYYY-MM-DD
    val isRecovery: Boolean = false,     // Para procesos de nivelación
    val originalGradeId: String? = null, // Referencia a la nota original si es recuperación
    val competencyId: String? = null,    // Logro/Competencia evaluada
    val sincronizado: Boolean = false
) {
    /**
     * Retorna la escala de valoración nacional según Decreto 1290
     * Nota: En el futuro esto debería consultar la GradingScale de la institución.
     */
    fun getDesempenoNacional(): String {
        return when {
            score >= 4.6 -> "Superior"
            score >= 4.0 -> "Alto"
            score >= 3.0 -> "Básico"
            else -> "Bajo"
        }
    }
}
