package com.sigeschool.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.Institution
import com.sigeschool.util.PdfPlatformGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingsUiState(
    val institution: Institution = Institution(),
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val message: String? = null,
    val error: String? = null
)

class SettingsViewModel(
    private val authRepository: AuthRepository,
    private val pdfGenerator: PdfPlatformGenerator
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadInstitution()
    }

    private fun loadInstitution() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val inst = authRepository.getCurrentInstitution()
            if (inst != null) {
                _uiState.update { it.copy(institution = inst, isLoading = false) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "No se pudo cargar la información de la institución") }
            }
        }
    }

    fun onInstitutionChange(updated: Institution) {
        _uiState.update { it.copy(institution = updated) }
    }

    fun saveChanges() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true, message = null, error = null) }
            val success = authRepository.updateInstitution(_uiState.value.institution)
            if (success) {
                _uiState.update { it.copy(isSaving = false, message = "Cambios guardados con éxito") }
            } else {
                _uiState.update { it.copy(isSaving = false, error = "Error al guardar los cambios") }
            }
        }
    }
    
    fun clearMessage() {
        _uiState.update { it.copy(message = null, error = null) }
    }
}
