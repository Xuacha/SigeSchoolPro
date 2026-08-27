package com.sigeschool.util

import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object ReportGenerator {

    /**
     * Mapea una nota numérica a la escala cualitativa del Decreto 1290.
     */
    fun getDesempeno(score: Double): String {
        return when {
            score >= 4.6 -> "SUPERIOR"
            score >= 4.0 -> "ALTO"
            score >= 3.0 -> "BÁSICO"
            else -> "BAJO"
        }
    }

    /**
     * Genera descriptores automáticos basados en el desempeño.
     */
    private fun getDescriptor(subject: String, score: Double): String {
        return when (getDesempeno(score)) {
            "SUPERIOR" -> "Demuestra un dominio excepcional de los conceptos en $subject, superando los objetivos propuestos."
            "ALTO" -> "Alcanza satisfactoriamente los niveles de competencia en $subject con buen desempeño académico."
            "BÁSICO" -> "Cumple con los requisitos mínimos para el área de $subject, se recomienda fortalecer procesos."
            "BAJO" -> "No alcanza las competencias mínimas en $subject. Requiere plan de apoyo y nivelación inmediata."
            else -> ""
        }
    }

    /**
     * Genera un reporte estructurado bajo normativa Decreto 1290 (1.0 - 5.0).
     */
    suspend fun generateBoletin(
        student: Student,
        grades: List<Grade>
    ): ByteArray {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val dateStr = "${now.dayOfMonth}/${now.monthNumber}/${now.year}"
        val average = if (grades.isEmpty()) 0.0 else grades.map { it.score }.average()

        val sb = StringBuilder()
        sb.append("==========================================\n")
        sb.append("         SIGESCHOOL PRO - BOLETÍN        \n")
        sb.append("      NORMATIVA SIE (DECRETO 1290)      \n")
        sb.append("==========================================\n")
        sb.append("ESTUDIANTE: ${student.nombreCompleto}\n")
        sb.append("GRADO: ${student.grado} | SECCIÓN: ${student.seccion}\n")
        sb.append("DNI: ${student.dni}\n")
        sb.append("FECHA DE EMISIÓN: $dateStr\n")
        sb.append("==========================================\n\n")

        sb.append("CALIFICACIONES Y DESEMPEÑOS:\n")
        sb.append("------------------------------------------\n")
        
        grades.groupBy { it.subjectId }.forEach { (subject, subjectGrades) ->
            val subjAvg = subjectGrades.map { it.score }.average()
            val desempeno = getDesempeno(subjAvg)
            
            sb.append("${subject.uppercase().padEnd(25)} | NOTA: ${subjAvg.toString().take(4)} | $desempeno\n")
            sb.append("DESCRIPTOR: ${getDescriptor(subject, subjAvg)}\n")
            
            subjectGrades.forEach { g ->
                sb.append("  - Período ${g.periodId}: ${g.score} (${getDesempeno(g.score)})\n")
            }
            sb.append("\n")
        }

        sb.append("==========================================\n")
        sb.append("PROMEDIO GENERAL FINAL: ${average.toString().take(5)}\n")
        sb.append("DESEMPEÑO GLOBAL: ${getDesempeno(average)}\n")
        sb.append("ESTADO: ${if (average >= 3.0) "APROBADO" else "REPROBADO"}\n")
        sb.append("==========================================\n")

        return sb.toString().encodeToByteArray()
    }

    fun calculateAverage(grades: List<Grade>): Double {
        return if (grades.isEmpty()) 0.0 else grades.map { it.score }.average()
    }
}
