package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnrollmentViewModel(
    private val repository: StudentRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(EnrollmentState())
    val state: StateFlow<EnrollmentState> = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: EnrollmentEvent) {
        when (event) {
            is EnrollmentEvent.EnteredFirstName -> {
                _state.update { it.copy(firstName = event.value) }
            }
            is EnrollmentEvent.EnteredLastName -> {
                _state.update { it.copy(lastName = event.value) }
            }
            is EnrollmentEvent.EnteredDocumentId -> {
                _state.update { it.copy(documentId = event.value) }
            }
            is EnrollmentEvent.PhotoCaptured -> {
                _state.update { it.copy(photoBytes = event.bytes) }
            }
            is EnrollmentEvent.SaveStudent -> {
                saveStudent()
            }
        }
    }

    private fun saveStudent() {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            val currentState = _state.value
            
            if (currentState.firstName.isBlank() || currentState.lastName.isBlank() || currentState.documentId.isBlank()) {
                _eventFlow.emit(UiEvent.ShowSnackbar("Por favor complete todos los campos"))
                return@launch
            }

            val student = Student(
                nombre = currentState.firstName,
                apellido = currentState.lastName,
                dni = currentState.documentId,
                institutionId = institutionId,
                activo = true
            )

            val result = repository.addStudent(student, null)
            when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(UiEvent.SaveSuccess(student.id))
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowSnackbar(result.message ?: "Error al guardar"))
                }
                else -> Unit
            }
        }
    }

    data class EnrollmentState(
        val firstName: String = "",
        val lastName: String = "",
        val documentId: String = "",
        val photoBytes: ByteArray? = null,
        val isLoading: Boolean = false
    )

    sealed class EnrollmentEvent {
        data class EnteredFirstName(val value: String) : EnrollmentEvent()
        data class EnteredLastName(val value: String) : EnrollmentEvent()
        data class EnteredDocumentId(val value: String) : EnrollmentEvent()
        data class PhotoCaptured(val bytes: ByteArray) : EnrollmentEvent()
        object SaveStudent : EnrollmentEvent()
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data class SaveSuccess(val studentId: String) : UiEvent()
    }
}
