package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.UserRole
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.util.SmartImportUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

data class StudentUiState(
    val students: List<Student> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val availableGrades: List<String> = emptyList(),
    val currentUserRole: UserRole = UserRole.INVITADO
)

class StudentViewModel(
    private val repository: StudentRepository,
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeStudents()
        loadInstitutionInfo()
        loadUserRole()
    }

    private fun observeStudents() {
        viewModelScope.launch {
            sessionManager.sessionState
                .flatMapLatest { state ->
                    val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                    if (institutionId != null) {
                        repository.getAllStudents(institutionId)
                    } else {
                        flowOf(Resource.Success(emptyList()))
                    }
                }
                .collect { resource ->
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

    private fun loadUserRole() {
        viewModelScope.launch {
            val user = authRepository.getCurrentUser()
            val role = user?.userMetadata?.get("role")?.toString()?.let { UserRole.fromString(it) } ?: UserRole.RECTOR // Default for testing
            _uiState.update { it.copy(currentUserRole = role) }
        }
    }

    private fun loadInstitutionInfo() {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            val institution = authRepository.getCurrentInstitution() // Todavía necesario para metadata específica
            institution?.let { inst ->
                val grades = com.sigeschool.domain.model.EducationalGrades.getGradesForLevels(inst.educationalModels)
                _uiState.update { it.copy(availableGrades = grades) }
            }
        }
    }

    fun addStudent(student: Student, consent: com.sigeschool.domain.model.Consent? = null) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.addStudent(student.copy(institutionId = institutionId), consent)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = null) }
            }
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.updateStudent(student)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = null) }
            }
        }
    }

    fun deleteStudent(id: String) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.deleteStudent(id, institutionId)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = null) }
            }
        }
    }

    fun searchStudents(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun syncStudent(id: String) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isLoading = true) }
            repository.syncWithSupabase(institutionId)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun importStudents(rows: List<List<String>>) {
        viewModelScope.launch {
            if (rows.isEmpty()) return@launch

            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            
            val headers = rows.first()
            val mapping = SmartImportUtils.mapHeaders(headers, "student")
            val dataRows = rows.drop(1)

            val newStudents = dataRows.map { row ->
                val data = SmartImportUtils.extractData(row, mapping)
                Student(
                    nombre = data["nombre"] ?: "",
                    apellido = data["apellido"] ?: "",
                    dni = data["dni"] ?: "",
                    grado = data["grado"] ?: "",
                    seccion = data["seccion"] ?: "",
                    institutionId = institutionId,
                    activo = true
                )
            }.filter { it.nombre.isNotBlank() && it.dni.isNotBlank() }

            newStudents.forEach { repository.addStudent(it, null) }
        }
    }
}
