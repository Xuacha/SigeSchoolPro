package com.sigeschool.presentation.screens.sie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.sie.GradingScale
import com.sigeschool.domain.model.sie.GradeCategory
import com.sigeschool.domain.model.sie.Competency
import com.sigeschool.domain.repository.sie.SieRepository
import com.sigeschool.domain.service.sie.SieService
import com.sigeschool.services.ai.NerService
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SieUiState(
    val scales: List<GradingScale> = emptyList(),
    val categories: List<GradeCategory> = emptyList(),
    val competencies: List<Competency> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class SieViewModel(
    private val sieRepository: SieRepository,
    private val sieService: SieService,
    private val nerService: NerService,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SieUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadSieConfig()
    }

    private fun loadSieConfig() {
        sessionManager.sessionState
            .filterIsInstance<SessionState.LoggedIn>()
            .mapNotNull { it.institutionId }
            .flatMapLatest { institutionId ->
                combine(
                    sieRepository.getGradingScales(institutionId),
                    sieRepository.getCategories(institutionId),
                    sieRepository.getCompetencies(institutionId)
                ) { scales, categories, competencies ->
                    _uiState.update { it.copy(
                        scales = scales,
                        categories = categories,
                        competencies = competencies,
                        isLoading = false
                    ) }
                }
            }
            .onStart { _uiState.update { it.copy(isLoading = true) } }
            .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
            .launchIn(viewModelScope)
    }

    fun saveCategory(category: GradeCategory) {
        viewModelScope.launch {
            try {
                sieRepository.saveCategory(category)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun generateRubricFromText(text: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val rubric = nerService.extractRubric(text)
                if (rubric != null) {
                    // Aquí se podría abrir un diálogo para confirmar y guardar
                    sieRepository.saveRubric(rubric)
                    _uiState.update { it.copy(isLoading = false) }
                } else {
                    _uiState.update { it.copy(isLoading = false, error = "No se pudo extraer una rúbrica válida del texto.") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
