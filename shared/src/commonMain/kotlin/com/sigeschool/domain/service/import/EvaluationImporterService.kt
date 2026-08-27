package com.sigeschool.domain.service.import

import com.sigeschool.domain.repository.GradeRepository
import com.sigeschool.domain.repository.StudentRepository
import com.sigeschool.domain.model.Grade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlin.random.Random

class EvaluationImporterService(
    private val studentRepository: StudentRepository,
    private val gradeRepository: GradeRepository,
    private val fileProcessor: FileProcessor
) {
    suspend fun importEvaluations(
        byteArray: ByteArray,
        fileName: String,
        institutionId: String,
        claseId: String,
        periodoId: String,
        corte: Int
    ): ContentImportResult = withContext(Dispatchers.Default) {
        val errors = mutableListOf<String>()
        var processed = 0
        try {
            val rows = fileProcessor.parseExcel(byteArray)
            
            rows.forEachIndexed { index, row ->
                try {
                    val studentIdentifier = row["Email"] ?: row["ID"] ?: row.values.elementAtOrNull(1) ?: ""
                    val scoreStr = row["Nota"] ?: row.values.elementAtOrNull(3) ?: "0"
                    val score = scoreStr.toDoubleOrNull() ?: 0.0

                    val searchResults = studentRepository.searchStudents(studentIdentifier)
                    val student = searchResults.firstOrNull { it.dni == studentIdentifier || it.email == studentIdentifier }

                    if (student != null) {
                        val grade = Grade(
                            id = "", // Will be generated
                            studentId = student.id,
                            institutionId = institutionId,
                            claseId = claseId,
                            periodId = periodoId,
                            score = score,
                            date = Clock.System.now().toString().split("T")[0],
                            observations = "Importado de $fileName - Corte $corte"
                        )
                        gradeRepository.saveGrade(grade, institutionId)
                        processed++
                    } else {
                        errors.add("Estudiante no encontrado: $studentIdentifier en fila ${index + 2}")
                    }
                } catch (e: Exception) {
                    errors.add("Error en fila ${index + 2}: ${e.message}")
                }
            }
        } catch (e: Exception) {
            errors.add("Error general: ${e.message}")
        }
        ContentImportResult(processed, errors)
    }
}
