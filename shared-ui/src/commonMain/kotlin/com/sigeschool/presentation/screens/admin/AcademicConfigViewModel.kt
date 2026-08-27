package com.sigeschool.presentation.screens.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.repository.sie.AcademicRepository
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AcademicConfigUiState(
    val sedes: List<Any> = emptyList(), // Usando Any por falta de definición exacta, pero AcademicRepository los usa
    val jornadas: List<Any> = emptyList(),
    val cursos: List<Any> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class AcademicConfigViewModel(
    private val academicRepository: AcademicRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AcademicConfigUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadConfig()
    }

    fun loadConfig() {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                academicRepository.getSedes(institutionId).collect { list ->
                    _uiState.update { it.copy(sedes = list, isLoading = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
