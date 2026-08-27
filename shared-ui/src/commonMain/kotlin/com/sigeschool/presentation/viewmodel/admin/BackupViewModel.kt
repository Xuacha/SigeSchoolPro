package com.sigeschool.presentation.viewmodel.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.service.BackupService
import com.sigeschool.domain.service.BackupInfo
import com.sigeschool.domain.service.RemoteBackupInfo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BackupViewModel(private val backupService: BackupService) : ViewModel() {

    private val _uiState = MutableStateFlow(BackupUiState())
    val uiState: StateFlow<BackupUiState> = _uiState.asStateFlow()

    init {
        loadLogs()
        refreshRemoteBackups()
    }

    private fun loadLogs() {
        backupService.getBackupLogs()
            .onEach { logs ->
                _uiState.update { it.copy(logs = logs) }
            }
            .launchIn(viewModelScope)
    }

    fun refreshRemoteBackups() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            backupService.listRemoteBackups()
                .onSuccess { remoteBackups ->
                    _uiState.update { it.copy(remoteBackups = remoteBackups, isLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(error = error.message, isLoading = false) }
                }
        }
    }

    fun createBackup() {
        viewModelScope.launch {
            _uiState.update { it.copy(isCreating = true) }
            backupService.createBackup(manual = true)
                .onSuccess {
                    _uiState.update { it.copy(isCreating = false) }
                    refreshRemoteBackups()
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isCreating = false, error = error.message) }
                }
        }
    }

    fun restoreBackup(id: String) {
        // Implementación de restauración (pendientes confirmaciones de UI)
    }
}

data class BackupUiState(
    val logs: List<BackupInfo> = emptyList(),
    val remoteBackups: List<RemoteBackupInfo> = emptyList(),
    val isLoading: Boolean = false,
    val isCreating: Boolean = false,
    val error: String? = null
)
