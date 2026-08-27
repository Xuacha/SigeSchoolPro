package com.sigeschool.presentation.screens.grades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.data.repository.GradeRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class GradesUiState(
    val students: List<Student> = emptyList(),
    val selectedStudent: Student? = null,
    val grades: List<Grade> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class GradesViewModel(
    private val gradeRepository: GradeRepository,
    private val studentRepository: StudentRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GradesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadStudents()
    }

    // FIX: se agrega institutionId (antes traía estudiantes de
    // cualquier institución guardada en el dispositivo).
    private fun loadStudents() {
        viewModelScope.launch {
            val institutionId = authRepository.getCurrentInstitutionId()
            if (institutionId.isNullOrEmpty()) {
                _uiState.update { it.copy(isLoading = false, error = "No se encontró la institución activa") }
                return@launch
            }
            studentRepository.getAllStudents(institutionId).collect { resource ->
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
        }
    }

    fun selectStudent(student: Student) {
        _uiState.update { it.copy(selectedStudent = student) }
        loadGrades(student.id)
    }

    private fun loadGrades(studentId: Long) {
        viewModelScope.launch {
            gradeRepository.getGradesByStudent(studentId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, grades = resource.data ?: it.grades) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(isLoading = false, grades = resource.data ?: emptyList(), error = null) }
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
            _uiState.update { it.copy(isLoading = true) }
            val result = gradeRepository.saveGrade(grade)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun deleteGrade(gradeId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = gradeRepository.deleteGrade(gradeId)
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
            gradeRepository.syncWithCloud()
        }
    }
}
