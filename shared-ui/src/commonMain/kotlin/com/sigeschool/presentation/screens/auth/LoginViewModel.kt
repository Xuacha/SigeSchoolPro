package com.sigeschool.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val institutionName: String = "",
    val whatsappNumber: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false,
    val isSignUpMode: Boolean = false,
    val institutionId: String? = null,
    val userEmail: String? = null,
    val selectedEducationalModels: Set<String> = emptySet(),
    val hasInstitution: Boolean = true,
    val firstLoginRequiredUserId: String? = null
)

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeSession()
        checkSession()
        checkInstitutionStatus()
    }

    private fun checkInstitutionStatus() {
        viewModelScope.launch {
            try {
                // En KMP, esto podría depender del repositorio
                val count = authRepository.getInstitutionCount()
                _uiState.update { it.copy(hasInstitution = count > 0) }
            } catch (e: Exception) {
                _uiState.update { it.copy(hasInstitution = false) }
            }
        }
    }

    private fun observeSession() {
        viewModelScope.launch {
            authRepository.sessionState.collect { state ->
                when (state) {
                    is SessionState.LoggedIn -> {
                        _uiState.update { it.copy(
                            isLoggedIn = true,
                            institutionId = state.institutionId,
                            userEmail = state.user.email,
                            isLoading = false
                        ) }
                    }
                    is SessionState.LoggedOut -> {
                        _uiState.update { it.copy(
                            isLoggedIn = false,
                            institutionId = null,
                            userEmail = null,
                            isLoading = false
                        ) }
                    }
                    is SessionState.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun checkSession() {
        viewModelScope.launch {
            authRepository.checkSession()
        }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onInstitutionNameChange(name: String) {
        _uiState.update { it.copy(institutionName = name) }
    }

    fun onWhatsappNumberChange(number: String) {
        _uiState.update { it.copy(whatsappNumber = number) }
    }

    fun toggleEducationalModel(model: String) {
        _uiState.update { state ->
            val current = state.selectedEducationalModels
            val newModels = if (current.contains(model)) {
                current - model
            } else {
                current + model
            }
            state.copy(selectedEducationalModels = newModels)
        }
    }

    fun toggleMode() {
        _uiState.update { it.copy(isSignUpMode = !it.isSignUpMode, error = null) }
    }

    fun performAuthAction() {
        val email = _uiState.value.email
        val password = _uiState.value.password
        val institutionName = _uiState.value.institutionName
        val whatsappNumber = _uiState.value.whatsappNumber
        val isSignUp = _uiState.value.isSignUpMode
        val educationalModels = _uiState.value.selectedEducationalModels.toList()

        if (email.isBlank() || password.isBlank() || (isSignUp && institutionName.isBlank())) {
            _uiState.update { it.copy(error = "Por favor, completa todos los campos") }
            return
        }

        if (isSignUp && educationalModels.isEmpty()) {
            _uiState.update { it.copy(error = "Selecciona al menos un modelo educativo") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                if (isSignUp) {
                    val success = authRepository.registerWithInstitution(
                        email = email,
                        password = password,
                        institutionName = institutionName,
                        whatsappNumber = whatsappNumber,
                        educationalModels = educationalModels
                    )
                    if (!success) {
                        _uiState.update { it.copy(isLoading = false, error = "Error al crear la institución.") }
                    }
                } else {
                    authRepository.login(email, password)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMessage = when {
                    e.message?.contains("confirmation email", ignoreCase = true) == true ->
                        "Error al enviar el correo de confirmación. Verifica tu correo o contacta a soporte."
                    e.message?.contains("invalid login credentials", ignoreCase = true) == true ->
                        "Credenciales inválidas. Verifica tu correo y contraseña."
                    else -> "Error: ${e.message ?: "Ocurrió un error inesperado"}"
                }
                _uiState.update { it.copy(isLoading = false, error = errorMessage) }
            }
        }
    }

    fun login() {
        performAuthAction()
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
