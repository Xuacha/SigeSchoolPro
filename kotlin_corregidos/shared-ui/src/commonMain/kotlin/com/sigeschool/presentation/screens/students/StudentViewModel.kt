package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Student
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.util.SmartImportUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StudentUiState(
    val students: List<Student> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val availableGrades: List<String> = emptyList()
)

class StudentViewModel(
    private val repository: StudentRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentUiState())
    val uiState = _uiState.asStateFlow()

    // FIX: se cachea aquí para no repetir la consulta de sesión en cada
    // acción (sync manual, etc.) y para poder validar que exista antes
    // de llamar al repositorio.
    private var currentInstitutionId: String? = null

    init {
        loadInstitutionInfo()
        loadStudents()
    }

    private fun loadInstitutionInfo() {
        viewModelScope.launch {
            val institution = authRepository.getCurrentInstitution()
            institution?.let { inst ->
                val grades = com.sigeschool.domain.model.EducationalGrades.getGradesForLevels(inst.educationalModels)
                _uiState.update { it.copy(availableGrades = grades) }
            }
        }
    }

    // FIX CRÍTICO: antes llamaba a repository.getAllStudents() sin
    // institución, trayendo estudiantes de todas las instituciones
    // guardadas en el dispositivo. Ahora primero resuelve la
    // institución activa y solo entonces se suscribe al flujo.
    private fun loadStudents() {
        viewModelScope.launch {
            val institutionId = authRepository.getCurrentInstitutionId()
            if (institutionId.isNullOrEmpty()) {
                _uiState.update { it.copy(isLoading = false, error = "No se encontró la institución activa") }
                return@launch
            }
            currentInstitutionId = institutionId
            repository.getAllStudents(institutionId).collect { resource ->
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

    fun addStudent(student: Student) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.addStudent(student)
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

    fun deleteStudent(id: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.deleteStudent(id)
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

    fun syncStudent(id: Long) {
        viewModelScope.launch {
            val institutionId = currentInstitutionId ?: authRepository.getCurrentInstitutionId()
            if (institutionId.isNullOrEmpty()) {
                _uiState.update { it.copy(error = "No se encontró la institución activa") }
                return@launch
            }
            _uiState.update { it.copy(isLoading = true) }
            repository.syncWithSupabase(institutionId)
            // loadStudents() no es necesario porque getAllStudents() es un Flow que ya está observando
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun importStudents(rows: List<List<String>>) {
        viewModelScope.launch {
            if (rows.isEmpty()) return@launch

            val institutionId = authRepository.getCurrentInstitutionId() ?: ""
            if (institutionId.isEmpty()) return@launch
            
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

            newStudents.forEach { repository.addStudent(it) }
        }
    }
}
