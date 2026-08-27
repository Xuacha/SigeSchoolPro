package com.sigeschool.presentation.screens.sie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.sie.PromotionConfig
import com.sigeschool.domain.repository.sie.PromotionRepository
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ConfigPromocionUiState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val config: PromotionConfig? = null,
    val error: String? = null,
    val saveSuccess: Boolean = false
)

class ConfiguracionPromocionViewModel(
    private val repository: PromotionRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConfigPromocionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadConfig()
    }

    private fun loadConfig() {
        val institutionId = sessionManager.getCurrentInstitutionId() ?: run {
            _uiState.update { it.copy(isLoading = false, error = "Sesión inválida: sin institución activa") }
            return
        }
        repository.getPromotionConfig(institutionId)
            .onStart { _uiState.update { it.copy(isLoading = true) } }
            .onEach { config ->
                _uiState.update { it.copy(isLoading = false, config = config) }
            }
            .catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
            .launchIn(viewModelScope)
    }

    fun updateConfig(config: PromotionConfig) {
        _uiState.update { it.copy(config = config, saveSuccess = false) }
    }

    fun saveConfig() {
        val config = _uiState.value.config ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            repository.updatePromotionConfig(config)
                .onSuccess {
                    _uiState.update { it.copy(isSaving = false, saveSuccess = true) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isSaving = false, error = e.message) }
                }
        }
    }
}
