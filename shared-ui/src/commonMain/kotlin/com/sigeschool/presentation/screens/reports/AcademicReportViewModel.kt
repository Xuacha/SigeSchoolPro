package com.sigeschool.presentation.screens.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.GradeRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.sie.*
import com.sigeschool.domain.repository.sie.AcademicRepository
import com.sigeschool.domain.usecase.sie.CalculateConductScoreUseCase
import com.sigeschool.services.pdf.PdfGenerator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.Grade

data class AcademicReportState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val pdfData: ByteArray? = null
)

class AcademicReportViewModel(
    private val studentRepository: StudentRepository,
    private val gradeRepository: GradeRepository,
    private val academicRepository: AcademicRepository,
    private val calculateConductScoreUseCase: CalculateConductScoreUseCase,
    private val pdfGenerator: PdfGenerator,
    private val sessionManager: com.sigeschool.domain.util.SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(AcademicReportState())
    val state = _state.asStateFlow()

    fun generateReport(studentId: String, period: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val institutionId = sessionManager.getCurrentInstitutionId() ?: throw Exception("Sesión no válida")
                val student = studentRepository.getStudentById(studentId, institutionId) 
                    ?: throw Exception("Estudiante no encontrado")

                val gradesResource = gradeRepository.getGradesByStudent(studentId, institutionId).first()
                val rawGrades = gradesResource.data ?: emptyList()
                
                // Agrupar por asignatura para el reporte académico
                val subjectGrades = rawGrades.groupBy { it.subjectId }.map { (subjectId, grades) ->
                    val periodsMap = grades.associate { (it.periodId.toIntOrNull() ?: 0) to it.score }
                    val avg = if (grades.isNotEmpty()) grades.map { it.score }.average() else 0.0
                    
                    SubjectGrade(
                        subject = subjectId,
                        teacher = "Docente", // Debería venir del modelo Grade o Subject
                        periods = periodsMap,
                        finalGrade = avg,
                        performanceLevel = getPerformanceLevel(avg),
                        observations = grades.lastOrNull()?.observations ?: ""
                    )
                }

                val disciplineRecords = academicRepository.getDisciplineRecords(studentId).first()
                val conductScoreValue = calculateConductScoreUseCase(disciplineRecords)

                val positiveNotes = disciplineRecords.filter { it.type == DisciplineType.POSITIVA }.map { it.description }
                val negativeNotes = disciplineRecords.filter { it.type != DisciplineType.POSITIVA }.map { it.description }

                // Cálculo de apoyo familiar basado en citaciones
                val citations = disciplineRecords.filter { it.type == DisciplineType.CITACION_ACUDIENTE }
                val attended = citations.count { it.parentAttended == true }
                val missed = citations.count { it.parentAttended == false }
                val familyScoreValue = if (citations.isEmpty()) 5.0 else {
                    val base = 5.0
                    (base - (missed * 0.5)).coerceIn(1.0, 5.0)
                }

                val report = AcademicReport(
                    student = student,
                    period = period,
                    grades = subjectGrades,
                    conductScore = ConductScore(
                        score = conductScoreValue,
                        positiveNotes = positiveNotes,
                        negativeNotes = negativeNotes,
                        overallComment = if (conductScoreValue >= 4.0) "Excelente comportamiento" else "Debe mejorar"
                    ),
                    familySupportScore = FamilySupportScore(
                        score = familyScoreValue,
                        citationsAttended = attended,
                        citationsMissed = missed,
                        comments = "Seguimiento institucional"
                    ),
                    overallAverage = if (subjectGrades.isNotEmpty()) subjectGrades.map { it.finalGrade }.average() else 0.0,
                    generalObservations = "Proceso satisfactorio",
                    signatures = ReportSignatures("RECTOR DEMO", null, "2024-11-20")
                )

                val pdf = pdfGenerator.generateAcademicReport(report)
                _state.update { it.copy(isLoading = false, pdfData = pdf) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    private fun getPerformanceLevel(score: Double): PerformanceLevel {
        return when {
            score >= 4.6 -> PerformanceLevel.SUPERIOR
            score >= 4.0 -> PerformanceLevel.ALTO
            score >= 3.0 -> PerformanceLevel.BASICO
            else -> PerformanceLevel.BAJO
        }
    }
}
