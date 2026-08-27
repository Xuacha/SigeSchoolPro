package com.sigeschool.presentation.screens.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.GradeRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.sie.*
import com.sigeschool.domain.repository.sie.AcademicRepository
import com.sigeschool.domain.repository.sie.SieRepository
import com.sigeschool.domain.service.sie.SieService
import com.sigeschool.domain.usecase.sie.CalculateConductScoreUseCase
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.services.pdf.PdfGenerator
import com.sigeschool.util.ReportGenerator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class StudentRanking(
    val student: Student,
    val average: Double
)

data class ReportsState(
    val isLoading: Boolean = false,
    val topStudents: List<StudentRanking> = emptyList(),
    val message: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class ReportsViewModel(
    private val studentRepository: StudentRepository,
    private val gradeRepository: GradeRepository,
    private val academicRepository: AcademicRepository,
    private val sieRepository: SieRepository,
    private val sieService: SieService,
    private val calculateConductScoreUseCase: CalculateConductScoreUseCase,
    private val pdfGenerator: PdfGenerator,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _state = MutableStateFlow(ReportsState())
    val state: StateFlow<ReportsState> = _state.asStateFlow()

    init {
        observeSession()
    }

    private fun observeSession() {
        sessionManager.sessionState
            .map { (it as? SessionState.LoggedIn)?.institutionId }
            .distinctUntilChanged()
            .onEach { institutionId ->
                if (institutionId != null) {
                    loadRanking(institutionId)
                } else {
                    _state.update { it.copy(topStudents = emptyList(), message = null) }
                }
            }
            .launchIn(viewModelScope)
    }

    fun loadRanking(institutionId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                // SEC-05: Filtrado explícito por institución en el ranking
                val studentsRes = studentRepository.getAllStudents(institutionId)
                    .filter { it !is Resource.Loading }
                    .first()
                
                val students = studentsRes.data ?: emptyList()
                val studentIds = students.map { it.id }

                val allGradesRes = gradeRepository.getGradesByStudentList(studentIds, institutionId)
                    .filter { it !is Resource.Loading }
                    .first()
                val allGrades = allGradesRes.data ?: emptyList()
                val gradesByStudent = allGrades.groupBy { it.studentId }

                val ranking = withContext(Dispatchers.Default) {
                    students.map { student ->
                        val grades = gradesByStudent[student.id] ?: emptyList()
                        val avg = ReportGenerator.calculateAverage(grades)
                        StudentRanking(student, avg)
                    }
                }.sortedByDescending { it.average }.take(10)
                
                _state.update { it.copy(
                    isLoading = false,
                    topStudents = ranking,
                    message = null
                ) }
            } catch (e: Exception) {
                _state.update { it.copy(
                    isLoading = false,
                    message = "Error al cargar ranking: ${e.message}"
                ) }
            }
        }
    }

    fun generateBulkReports(grado: String, seccion: String, period: Int, onComplete: (ByteArray) -> Unit) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _state.update { it.copy(isLoading = true) }
            try {
                // SEC-05: Filtrado obligatorio por institución en generación masiva
                val studentsRes = studentRepository.getAllStudents(institutionId)
                    .filter { it !is Resource.Loading }
                    .first()
                val students = (studentsRes.data ?: emptyList())
                    .filter { it.grado == grado && it.seccion == seccion }
                val studentIds = students.map { it.id }

                // Cargar configuración de SIE para la institución
                val categories = sieRepository.getCategories(institutionId).first()
                val scales = sieRepository.getGradingScales(institutionId).first()
                val competencies = sieRepository.getCompetencies(institutionId).first()
                val activeScale = scales.find { it.isDefault } ?: scales.firstOrNull()

                // Carga masiva de notas y disciplina para evitar N+1
                val allGradesRes = gradeRepository.getGradesByStudentList(studentIds, institutionId)
                    .filter { it !is Resource.Loading }
                    .first()
                val allGrades = allGradesRes.data ?: emptyList()
                val gradesByStudent = allGrades.groupBy { it.studentId }

                val allDisciplineRecords = academicRepository.getDisciplineRecordsForStudents(studentIds).first()

                val reports = withContext(Dispatchers.Default) {
                    students.map { student ->
                        val rawGrades = gradesByStudent[student.id] ?: emptyList()
                        
                        val subjectGrades = rawGrades.groupBy { it.subjectId }.map { (subject, grades) ->
                            val periodsMap = grades.associate { (it.periodId.toIntOrNull() ?: 0) to it.score }
                            
                            // Cambio a promedio ponderado según SIE
                            val weightedAvg = sieService.calculateWeightedAverage(
                                "", "", "", "", grades, categories, false
                            )
                            
                            // Buscar indicador de logro basado en competencia
                            val subjectCompetency = competencies.find { it.area.equals(subject, ignoreCase = true) }
                            val indicator = if (subjectCompetency != null && activeScale != null) {
                                sieService.getAchievementIndicator(weightedAvg, activeScale, subjectCompetency)
                            } else null

                            SubjectGrade(
                                subject = subject,
                                teacher = "Docente",
                                periods = periodsMap,
                                finalGrade = weightedAvg,
                                performanceLevel = activeScale?.let { 
                                    mapToPerformanceLevel(sieService.getPerformanceLevel(weightedAvg, it))
                                } ?: PerformanceLevel.BASICO,
                                indicators = listOfNotNull(indicator),
                                observations = grades.lastOrNull()?.observations ?: ""
                            )
                        }

                        val disciplineRecords = allDisciplineRecords[student.id] ?: emptyList()
                        val conductScoreValue = calculateConductScoreUseCase(disciplineRecords)

                        val overallAvg = if (subjectGrades.isNotEmpty()) subjectGrades.map { it.finalGrade }.average() else 0.0

                        AcademicReport(
                            student = student,
                            period = period,
                            grades = subjectGrades,
                            conductScore = ConductScore(conductScoreValue, emptyList(), emptyList(), ""),
                            familySupportScore = FamilySupportScore(5.0, 0, 0, ""),
                            overallAverage = overallAvg,
                            generalObservations = "Desempeño General: ${activeScale?.let { sieService.getPerformanceLevel(overallAvg, it) } ?: ""}",
                            signatures = ReportSignatures("RECTOR DEMO", null, "2024-11-20")
                        )
                    }
                }

                // Generar PDFs en lote
                if (reports.isNotEmpty()) {
                    val combinedPdf = pdfGenerator.generateBulkAcademicReports(reports)
                    onComplete(combinedPdf)
                }
                
                _state.update { it.copy(isLoading = false, message = "Reportes generados con éxito") }
            } catch (e: Exception) {
                _state.update { it.copy(
                    isLoading = false, 
                    message = "Error en generación masiva: ${e.message}"
                ) }
            }
        }
    }

    private fun mapToPerformanceLevel(level: String): PerformanceLevel {
        return when (level.uppercase().replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")) {
            "SUPERIOR" -> PerformanceLevel.SUPERIOR
            "ALTO" -> PerformanceLevel.ALTO
            "BASICO" -> PerformanceLevel.BASICO
            "BAJO" -> PerformanceLevel.BAJO
            else -> PerformanceLevel.BASICO
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
