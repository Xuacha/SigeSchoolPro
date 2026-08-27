package com.sigeschool.presentation.screens.grades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.sie.GradeCategory
import com.sigeschool.domain.repository.sie.SieRepository
import com.sigeschool.domain.service.sie.SieService
import com.sigeschool.data.repository.GradeRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.domain.usecase.sie.GetQualitativeGradeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class GradesUiState(
    val students: List<Student> = emptyList(),
    val selectedStudent: Student? = null,
    val grades: List<Grade> = emptyList(),
    val categories: List<GradeCategory> = emptyList(),
    val weightedAverage: Double = 0.0,
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentQualitativeGrade: String = "",
    val activeScaleId: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class GradesViewModel(
    private val gradeRepository: GradeRepository,
    private val studentRepository: StudentRepository,
    private val sieRepository: SieRepository,
    private val sieService: SieService,
    private val getQualitativeGradeUseCase: GetQualitativeGradeUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(GradesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        sessionManager.sessionState
            .onEach { if (it is SessionState.Loading) _uiState.update { s -> s.copy(isLoading = true) } }
            .flatMapLatest { state ->
                val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                if (institutionId != null) {
                    combine(
                        studentRepository.getAllStudents(institutionId),
                        sieRepository.getCategories(institutionId)
                    ) { studentsRes, categories ->
                        _uiState.update { it.copy(categories = categories) }
                        studentsRes
                    }
                } else {
                    flowOf(Resource.Success(emptyList()))
                }
            }
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, students = resource.data ?: it.students) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(isLoading = false, students = resource.data ?: emptyList(), error = null) }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = resource.message) }
                    }
                }
            }
            .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
            .launchIn(viewModelScope)
    }

    fun selectStudent(student: Student) {
        _uiState.update { it.copy(selectedStudent = student) }
        loadGrades(student.id)
    }

    private fun loadGrades(studentId: String) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            gradeRepository.getGradesByStudent(studentId, institutionId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, grades = resource.data ?: it.grades) }
                    }
                    is Resource.Success -> {
                        val newGrades = resource.data ?: emptyList()
                        val average = sieService.calculateWeightedAverage(
                            studentId = studentId,
                            periodId = _uiState.value.activeScaleId ?: "1", // Simplified for now
                            institutionId = institutionId,
                            subjectId = "", // Overall average
                            grades = newGrades,
                            categories = _uiState.value.categories,
                            redistributeMissingWeight = false
                        )
                        _uiState.update { it.copy(
                            isLoading = false, 
                            grades = newGrades, 
                            weightedAverage = average,
                            error = null
                        ) }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = resource.message) }
                    }
                }
            }
        }
    }

    fun saveGrade(grade: Grade) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isLoading = true) }
            val result = gradeRepository.saveGrade(grade, institutionId)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun deleteGrade(gradeId: String) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isLoading = true) }
            val result = gradeRepository.deleteGrade(gradeId, institutionId)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false) }
                // Re-load grades for the selected student
                _uiState.value.selectedStudent?.let { loadGrades(it.id) }
            }
        }
    }

    fun syncGrades() {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isLoading = true) }
            gradeRepository.syncWithCloud(institutionId)
            _uiState.value.selectedStudent?.let { loadGrades(it.id) }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onScoreChanged(score: String) {
        val scoreValue = score.toDoubleOrNull() ?: return
        viewModelScope.launch {
            val label = _uiState.value.activeScaleId?.let { scaleId ->
                getQualitativeGradeUseCase(scoreValue, scaleId)
            } ?: "Sin escala"
            _uiState.update { it.copy(currentQualitativeGrade = label) }
        }
    }
}
