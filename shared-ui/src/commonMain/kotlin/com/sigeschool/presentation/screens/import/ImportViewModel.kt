package com.sigeschool.presentation.screens.import

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.service.import.ImportService
import com.sigeschool.domain.service.user.UserCreationService
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ImportUiState(
    val tipoImportacion: String = "ACUDIENTES",
    val isLoading: Boolean = false,
    val previewData: List<Map<String, String>> = emptyList(),
    val totalRegistros: Int = 0,
    val nuevosEstudiantes: Int = 0,
    val nuevosAcudientes: Int = 0,
    val usuariosACrear: Int = 0,
    val usuariosCreados: Int = 0,
    val error: String? = null,
    val progreso: Float = 0f,
    val successMessage: String? = null
)

class ImportViewModel(
    private val importService: ImportService,
    private val userCreationService: UserCreationService,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(ImportUiState())
    val uiState: StateFlow<ImportUiState> = _uiState.asStateFlow()

    fun setTipo(tipo: String) {
        _uiState.update { it.copy(tipoImportacion = tipo) }
    }

    fun processCsvImport(csvContent: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }
            try {
                val result = importService.importAcudientesFromCsv(csvContent)
                result.onSuccess { count ->
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            successMessage = "Importación completada: $count acudientes procesados.",
                            nuevosAcudientes = count
                        ) 
                    }
                }.onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, error = "Error: ${e.message}") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(error = null, successMessage = null) }
    }
}
