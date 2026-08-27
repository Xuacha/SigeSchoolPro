package com.sigeschool.presentation.screens.announcements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AnnouncementRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.Announcement
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AnnouncementUiState(
    val announcements: List<Announcement> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class AnnouncementViewModel(
    private val announcementRepository: AnnouncementRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnnouncementUiState())
    val uiState: StateFlow<AnnouncementUiState> = _uiState.asStateFlow()

    init {
        sessionManager.sessionState
            .onEach { _uiState.update { it.copy(isLoading = true) } }
            .flatMapLatest { state ->
                val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                if (institutionId != null) {
                    announcementRepository.getAnnouncements(institutionId)
                } else {
                    flowOf(emptyList())
                }
            }
            .onEach { announcements ->
                _uiState.update { it.copy(announcements = announcements, isLoading = false) }
            }
            .catch { e -> _uiState.update { it.copy(error = e.message, isLoading = false) } }
            .launchIn(viewModelScope)
    }

    fun addAnnouncement(announcement: Announcement) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: ""
            if (institutionId.isNotEmpty()) {
                announcementRepository.addAnnouncement(announcement.copy(institutionId = institutionId))
            }
        }
    }
}
