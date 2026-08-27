package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StudentListViewModel(
    private val repository: StudentRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(StudentListState())
    val state: StateFlow<StudentListState> = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadStudents()
    }

    fun loadStudents() {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            repository.getAllStudents(institutionId)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _state.update { it.copy(isLoading = true) }
                        }
                        is Resource.Success -> {
                            _state.update { it.copy(isLoading = false, students = resource.data ?: emptyList()) }
                        }
                        is Resource.Error -> {
                            _state.update { it.copy(isLoading = false) }
                            _eventFlow.emit(UiEvent.ShowSnackbar(resource.message ?: "Error al cargar estudiantes"))
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun onStudentClick(student: Student) {
        if (!student.sincronizado) {
            // En este proyecto usamos sincronizado en lugar de SyncStatus.CONFLICT
            // Pero si detectamos algo podríamos mostrar el diálogo.
            // Por ahora, navegación directa.
            viewModelScope.launch {
                _eventFlow.emit(UiEvent.NavigateToDetail(student.id))
            }
        } else {
            viewModelScope.launch {
                _eventFlow.emit(UiEvent.NavigateToDetail(student.id))
            }
        }
    }

    fun updateStudentStatus(studentId: String, newStatus: StudentStatus, reason: String? = null) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val student = _state.value.students.find { it.id == studentId }
            if (student != null) {
                val updatedStudent = student.copy(estadoMatricula = newStatus)
                val result = repository.updateStudent(updatedStudent)
                _state.update { it.copy(isLoading = false) }
                
                when (result) {
                    is Resource.Success -> {
                        _eventFlow.emit(UiEvent.ShowSnackbar("Estado actualizado"))
                    }
                    is Resource.Error -> {
                        _eventFlow.emit(UiEvent.ShowSnackbar(result.message ?: "Error al actualizar"))
                    }
                    else -> Unit
                }
            }
        }
    }

    fun deleteStudent(id: String) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _state.update { it.copy(isLoading = true) }
            val result = repository.deleteStudent(id, institutionId)
            _state.update { it.copy(isLoading = false) }
            
            if (result is Resource.Error) {
                _eventFlow.emit(UiEvent.ShowSnackbar(result.message ?: "Error al eliminar"))
            } else {
                _eventFlow.emit(UiEvent.ShowSnackbar("Estudiante eliminado"))
            }
        }
    }

    fun dismissConflictDialog() {
        _state.update { it.copy(conflictStudent = null) }
    }

    fun resolveConflict(useRemote: Boolean) {
        val student = _state.value.conflictStudent ?: return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, conflictStudent = null) }
            // En un proyecto real, aquí llamaríamos a repository.resolveConflict
            // Por ahora simulamos éxito
            _state.update { it.copy(isLoading = false) }
            _eventFlow.emit(UiEvent.ShowSnackbar("Conflicto resuelto (Simulado)"))
        }
    }

    data class StudentListState(

        val students: List<Student> = emptyList(),
        val isLoading: Boolean = false,
        val conflictStudent: Student? = null
    )

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data class NavigateToDetail(val studentId: String) : UiEvent()
    }
}
