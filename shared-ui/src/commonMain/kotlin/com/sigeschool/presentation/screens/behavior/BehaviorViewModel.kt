package com.sigeschool.presentation.screens.behavior

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.ConvivenciaCase
import com.sigeschool.domain.model.FamilyAttendance
import com.sigeschool.domain.repository.BehaviorRepository
import com.sigeschool.domain.repository.SessionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface BehaviorUiState {
    data object Loading : BehaviorUiState
    data class Success(
        val cases: List<ConvivenciaCase>,
        val citations: List<FamilyAttendance>
    ) : BehaviorUiState
    data class Error(val message: String) : BehaviorUiState
}

class BehaviorViewModel(
    private val repository: BehaviorRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BehaviorUiState>(BehaviorUiState.Loading)
    val uiState: StateFlow<BehaviorUiState> = _uiState.asStateFlow()

    fun loadStudentBehavior(studentId: String) {
        viewModelScope.launch {
            _uiState.value = BehaviorUiState.Loading
            val institutionId = sessionRepository.getInstitutionId() ?: ""

            combine(
                repository.getCasesByStudent(institutionId, studentId),
                repository.getFamilyAttendanceByStudent(institutionId, studentId)
            ) { cases, citations ->
                BehaviorUiState.Success(cases = cases, citations = citations)
            }.catch { e ->
                _uiState.value = BehaviorUiState.Error(e.message ?: "Error al cargar comportamiento")
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun createCitation(studentId: String, reason: String) {
        viewModelScope.launch {
            val instId = sessionRepository.getInstitutionId() ?: ""
            val newCitation = FamilyAttendance(
                id = "CIT-${System.currentTimeMillis()}",
                institutionId = instId,
                studentId = studentId,
                citationDate = kotlinx.datetime.Clock.System.now().toEpochMilliseconds(),
                reason = reason,
                attended = false
            )
            repository.saveFamilyAttendance(newCitation)
            loadStudentBehavior(studentId)
        }
    }
}
