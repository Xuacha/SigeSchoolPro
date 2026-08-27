package com.sigeschool.presentation.screens.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.data.repository.GradeRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.Student
import com.sigeschool.util.PdfPlatformGenerator
import com.sigeschool.util.ReportGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class StudentRanking(
    val student: Student,
    val average: Double
)

data class ReportsState(
    val isLoading: Boolean = false,
    val topStudents: List<StudentRanking> = emptyList(),
    val message: String? = null
)

class ReportsViewModel(
    private val studentRepository: StudentRepository,
    private val gradeRepository: GradeRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ReportsState())
    val state: StateFlow<ReportsState> = _state.asStateFlow()

    init {
        loadRanking()
    }

    // FIX: `studentRepository.getAllStudents()` devuelve un
    // Flow<Resource<List<Student>>>, no un Flow<List<Student>>. El
    // código original hacía `.first()` y luego `.map { ... }`
    // directamente sobre ese `Resource`, lo cual NO COMPILA (Resource
    // no tiene `map`). Se corrige leyendo `.data` del Resource, y
    // además ahora se filtra por institución (antes traía estudiantes
    // de cualquier institución).
    private suspend fun currentStudents(): List<Student> {
        val institutionId = authRepository.getCurrentInstitutionId() ?: return emptyList()
        if (institutionId.isEmpty()) return emptyList()
        return studentRepository.getAllStudents(institutionId).first().data ?: emptyList()
    }

    fun loadRanking() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val students = currentStudents()
                val ranking = withContext(Dispatchers.Default) {
                    students.map { student ->
                        async {
                            val grades = gradeRepository.getGradesByStudent(student.id).first().data ?: emptyList()
                            val avg = ReportGenerator.calculateAverage(grades)
                            StudentRanking(student, avg)
                        }
                    }.awaitAll()
                }.sortedByDescending { it.average }.take(10)

                _state.value = _state.value.copy(
                    isLoading = false,
                    topStudents = ranking
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    message = "Error al cargar ranking: ${e.message}"
                )
            }
        }
    }

    fun generateBulkReports(grado: String, seccion: String, onComplete: (ByteArray) -> Unit) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val combined = withContext(Dispatchers.Default) {
                    val students = currentStudents()
                        .filter { it.grado == grado && it.seccion == seccion }

                    val bulkData = students.map { student ->
                        async {
                            val grades = gradeRepository.getGradesByStudent(student.id).first().data ?: emptyList()
                            student to grades
                        }
                    }.awaitAll()

                    PdfPlatformGenerator.generateBulkReport(bulkData)
                }

                onComplete(combined)
                _state.value = _state.value.copy(isLoading = false, message = "Reportes generados con éxito")
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    message = "Error en generación masiva: ${e.message}"
                )
            }
        }
    }
}
