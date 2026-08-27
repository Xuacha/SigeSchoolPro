package com.sigeschool.presentation.screens.exams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.ExamRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.Exam
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ExamUiState(
    val exams: List<Exam> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class ExamViewModel(
    private val examRepository: ExamRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExamUiState())
    val uiState: StateFlow<ExamUiState> = _uiState.asStateFlow()

    init {
        sessionManager.sessionState
            .onEach { if (it is SessionState.Loading) _uiState.update { state -> state.copy(isLoading = true) } }
            .flatMapLatest { state ->
                val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                if (institutionId != null) {
                    examRepository.getExams(institutionId)
                } else {
                    flowOf(Resource.Success(emptyList()))
                }
            }
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, exams = resource.data ?: it.exams) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(isLoading = false, exams = resource.data ?: emptyList(), error = null) }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = resource.message) }
                    }
                }
            }
            .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
            .launchIn(viewModelScope)
    }

    fun addExam(exam: Exam) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId()
            if (institutionId == null) {
                _uiState.update { it.copy(error = "No hay una sesión activa") }
                return@launch
            }
            _uiState.update { it.copy(isLoading = true) }
            val result = examRepository.addExam(exam.copy(institutionId = institutionId))
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun submitExamResult(exam: Exam, score: Double) {
        viewModelScope.launch {
            val session = sessionManager.sessionState.value
            val userId = (session as? SessionState.LoggedIn)?.user?.id
            if (userId == null) {
                _uiState.update { it.copy(error = "No se pudo identificar al usuario") }
                return@launch
            }
            _uiState.update { it.copy(isLoading = true) }
            val result = examRepository.saveExamResult(exam.id, score, userId)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
