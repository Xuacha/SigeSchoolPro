package com.sigeschool.presentation.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.TaskRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.Task
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModel(
    private val taskRepository: TaskRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        sessionManager.sessionState
            .onEach { if (it is SessionState.Loading) _uiState.update { state -> state.copy(isLoading = true) } }
            .flatMapLatest { state ->
                val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                if (institutionId != null) {
                    taskRepository.getTasks(institutionId)
                } else {
                    flowOf(Resource.Success(emptyList()))
                }
            }
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true, tasks = resource.data ?: it.tasks) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(isLoading = false, tasks = resource.data ?: emptyList(), error = null) }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = resource.message) }
                    }
                }
            }
            .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
            .launchIn(viewModelScope)
    }

    fun addTask(task: Task, evidenceBytes: ByteArray? = null) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId()
            if (institutionId == null) {
                _uiState.update { it.copy(error = "No hay una sesión activa") }
                return@launch
            }
            
            _uiState.update { it.copy(isLoading = true) }
            val result = taskRepository.addTask(task.copy(institutionId = institutionId), evidenceBytes)
            if (result is Resource.Error) {
                _uiState.update { it.copy(isLoading = false, error = result.message) }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
