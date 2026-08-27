package com.sigeschool.presentation.viewmodel.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.security.KeyBackupService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BackupSecurityViewModel(
    private val keyBackupService: KeyBackupService
) : ViewModel() {

    private val _uiState = MutableStateFlow(BackupSecurityUiState())
    val uiState: StateFlow<BackupSecurityUiState> = _uiState.asStateFlow()

    init {
        checkBackupStatus()
    }

    fun checkBackupStatus() {
        viewModelScope.launch {
            val hasBackup = keyBackupService.hasBackup()
            _uiState.update { it.copy(hasRemoteBackup = hasBackup) }
        }
    }

    fun onPinChanged(pin: String) {
        _uiState.update { it.copy(pinInput = pin) }
    }

    fun createBackup() {
        val pin = _uiState.value.pinInput
        if (pin.length < 4) {
            _uiState.update { it.copy(error = "El PIN debe tener al menos 4 caracteres") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }
            keyBackupService.backupKey(pin)
                .onSuccess {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            successMessage = "Respaldo creado exitosamente en la nube",
                            hasRemoteBackup = true,
                            pinInput = ""
                        ) 
                    }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, error = "Error: ${e.message}") }
                }
        }
    }

    fun restoreBackup() {
        val pin = _uiState.value.pinInput
        if (pin.isEmpty()) {
            _uiState.update { it.copy(error = "Ingrese el PIN de respaldo") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }
            keyBackupService.restoreKey(pin)
                .onSuccess {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            successMessage = "Clave maestra restaurada. Reinicie la aplicación para aplicar cambios.",
                            pinInput = ""
                        ) 
                    }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, error = "PIN incorrecto o error de red: ${e.message}") }
                }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(error = null, successMessage = null) }
    }
}

data class BackupSecurityUiState(
    val isLoading: Boolean = false,
    val hasRemoteBackup: Boolean = false,
    val pinInput: String = "",
    val error: String? = null,
    val successMessage: String? = null
)
