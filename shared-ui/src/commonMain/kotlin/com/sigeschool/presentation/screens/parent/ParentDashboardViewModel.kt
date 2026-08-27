package com.sigeschool.presentation.screens.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.Notification
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.repository.NotificationRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ParentDashboardUiState(
    val students: List<Student> = emptyList(),
    val notifications: List<Notification> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class ParentDashboardViewModel(
    private val studentRepository: StudentRepository,
    private val notificationRepository: NotificationRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(ParentDashboardUiState())
    val uiState: StateFlow<ParentDashboardUiState> = _uiState.asStateFlow()

    init {
        observeData()
    }

    private fun observeData() {
        sessionManager.sessionState
            .onEach { if (it is SessionState.Loading) _uiState.update { s -> s.copy(isLoading = true) } }
            .flatMapLatest { state ->
                val loggedIn = state as? SessionState.LoggedIn
                val institutionId = loggedIn?.institutionId
                val userId = loggedIn?.user?.id

                if (institutionId != null && userId != null) {
                    combine(
                        studentRepository.getAllStudents(institutionId),
                        notificationRepository.getNotificationsByAcudiente(userId)
                    ) { studentsRes, notifications ->
                        val students = (studentsRes as? Resource.Success)?.data ?: emptyList()
                        _uiState.update { 
                            it.copy(
                                students = students,
                                notifications = notifications,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                } else {
                    _uiState.update { it.copy(isLoading = false, students = emptyList(), notifications = emptyList()) }
                    flowOf(Unit)
                }
            }
            .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
            .launchIn(viewModelScope)
    }
}
