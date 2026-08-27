package com.sigeschool.domain.util

import com.sigeschool.domain.model.Institution
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SessionManager {
    private val _sessionState = MutableStateFlow<SessionState>(SessionState.Loading)
    val sessionState = _sessionState.asStateFlow()

    fun updateSession(user: UserInfo?, institutionId: String? = null) {
        if (user == null) {
            _sessionState.value = SessionState.LoggedOut
        } else {
            _sessionState.update { 
                SessionState.LoggedIn(user, institutionId)
            }
        }
    }

    fun startLoading() {
        _sessionState.value = SessionState.Loading
    }

    fun getCurrentInstitutionId(): String? {
        val state = _sessionState.value
        return if (state is SessionState.LoggedIn) state.institutionId else null
    }

    fun getCurrentUserId(): String? {
        val state = _sessionState.value
        return if (state is SessionState.LoggedIn) state.user.id else null
    }
}

sealed class SessionState {
    object Loading : SessionState()
    object LoggedOut : SessionState()
    data class LoggedIn(
        val user: UserInfo,
        val institutionId: String? = null
    ) : SessionState()
}
