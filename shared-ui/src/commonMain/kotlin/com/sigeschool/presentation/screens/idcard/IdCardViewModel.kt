package com.sigeschool.presentation.screens.idcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.IdCard
import com.sigeschool.domain.model.Student
import com.sigeschool.util.PdfPlatformGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.sigeschool.data.repository.EmployeeRepository
import com.sigeschool.domain.model.UserRole

data class IdCardUiState(
    val isLoading: Boolean = false,
    val allCards: List<IdCard> = emptyList(),
    val filteredCards: List<IdCard> = emptyList(),
    val error: String? = null,
    val pdfData: ByteArray? = null,
    val selectedRole: UserRole? = null,
    val selectedGrade: String? = null,
    val grades: List<String> = emptyList()
)

class IdCardViewModel(
    private val studentRepository: StudentRepository,
    private val employeeRepository: EmployeeRepository,
    private val authRepository: AuthRepository,
    private val pdfGenerator: PdfPlatformGenerator,
    private val sessionManager: com.sigeschool.domain.util.SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(IdCardUiState())
    val uiState = _uiState.asStateFlow()

    fun loadAllCards() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val institutionId = sessionManager.getCurrentInstitutionId() ?: ""
                val institution = authRepository.getCurrentInstitution()
                val institutionName = institution?.name ?: "SigeSchool"

                studentRepository.getAllStudents(institutionId).collect { studentsRes ->
                    val students = studentsRes.data ?: emptyList()
                    val studentCards = students.map { student ->
                        IdCard(
                            id = student.id.toString(),
                            ownerName = student.nombreCompleto,
                            ownerRole = UserRole.ESTUDIANTE.name,
                            identifier = student.dni.ifBlank { student.id.toString() },
                            institutionName = institutionName,
                            barcodeType = "CODE_128",
                            grade = student.grado
                        )
                    }

                    val distinctGrades = students.map { it.grado }.filter { it.isNotBlank() }.distinct().sorted()

                    employeeRepository.getEmployees(institutionId).collect { employees ->
                        val employeeCards = employees.map { employee ->
                            IdCard(
                                id = employee.id,
                                ownerName = employee.fullName,
                                ownerRole = employee.role.name,
                                identifier = employee.dni.ifBlank { employee.id },
                                institutionName = institutionName,
                                barcodeType = "CODE_128"
                            )
                        }

                        val allCards = studentCards + employeeCards
                        _uiState.update { it.copy(
                            allCards = allCards,
                            filteredCards = allCards,
                            grades = distinctGrades,
                            isLoading = false 
                        ) }
                        applyFilters()
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun onRoleSelected(role: UserRole?) {
        _uiState.update { it.copy(selectedRole = role) }
        applyFilters()
    }

    fun onGradeSelected(grade: String?) {
        _uiState.update { it.copy(selectedGrade = grade) }
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        val filtered = state.allCards.filter { card ->
            val matchesRole = state.selectedRole == null || card.ownerRole == state.selectedRole.name
            val matchesGrade = state.selectedGrade == null || card.grade == state.selectedGrade
            matchesRole && matchesGrade
        }
        _uiState.update { it.copy(filteredCards = filtered) }
    }

    fun generatePdf() {
        val currentCards = _uiState.value.filteredCards
        if (currentCards.isEmpty()) return

        viewModelScope.launch {
            try {
                val pdf = PdfPlatformGenerator.generateIdCards(currentCards)
                _uiState.update { it.copy(pdfData = pdf) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Error al generar PDF: ${e.message}") }
            }
        }
    }
    
    fun clearPdfData() {
        _uiState.update { it.copy(pdfData = null) }
    }
}
