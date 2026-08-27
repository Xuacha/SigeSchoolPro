package com.sigeschool.presentation.screens.curricular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Certificate
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.repository.CertificateRepository
import com.sigeschool.domain.repository.GradeRepository
import com.sigeschool.domain.repository.StudentRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class StudentRecordsUiState(
    val student: Student? = null,
    val grades: List<Grade> = emptyList(),
    val certificates: List<Certificate> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class StudentRecordsViewModel(
    private val studentId: String,
    private val studentRepository: StudentRepository,
    private val gradeRepository: GradeRepository,
    private val certificateRepository: CertificateRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentRecordsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            
            _uiState.update { it.copy(isLoading = true) }

            // Load Student
            studentRepository.getStudentById(studentId, institutionId).collect { res ->
                if (res is Resource.Success) {
                    _uiState.update { it.copy(student = res.data) }
                }
            }

            // Load Grades
            gradeRepository.getGradesByStudent(studentId, institutionId).collect { res ->
                if (res is Resource.Success) {
                    _uiState.update { it.copy(grades = res.data ?: emptyList()) }
                }
            }

            // Load Certificates
            certificateRepository.getCertificatesByStudent(studentId).collect { res ->
                if (res is Resource.Success) {
                    _uiState.update { it.copy(certificates = res.data ?: emptyList(), isLoading = false) }
                } else if (res is Resource.Error) {
                    _uiState.update { it.copy(isLoading = false, error = res.message) }
                }
            }
        }
    }

    fun generateCertificate(type: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = if (type == "STUDY") {
                certificateRepository.generateStudyCertificate(studentId).first()
            } else {
                certificateRepository.generateGradeCertificate(studentId, 2024).first()
            }
            
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                loadData()
            }
        }
    }
}
