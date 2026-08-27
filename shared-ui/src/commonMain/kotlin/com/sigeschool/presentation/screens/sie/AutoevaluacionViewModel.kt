package com.sigeschool.presentation.screens.sie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.sie.Autoevaluacion
import com.sigeschool.domain.repository.sie.PromotionRepository
import com.sigeschool.domain.service.sie.SieService
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

data class AutoevaluacionUiState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
    val autoevaluaciones: List<AutoevaluacionItemState> = emptyList(),
    val error: String? = null
)

data class AutoevaluacionItemState(
    val subjectId: String,
    val subjectName: String,
    val teacherName: String,
    val score: Double,
    val isSubmitted: Boolean
)

class AutoevaluacionViewModel(
    private val promotionRepository: PromotionRepository,
    private val sieService: SieService,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AutoevaluacionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            val session = sessionManager.sessionState.value
            if (session !is SessionState.LoggedIn) {
                _uiState.update { it.copy(isLoading = false, error = "Sesión no iniciada") }
                return@launch
            }

            val studentId = session.user.id
            // En un escenario real, obtendríamos las asignaturas inscritas del estudiante.
            // Para este ejemplo, simularemos la carga o usaremos datos existentes si los hay.
            
            // Suponiendo que el periodo actual es "PERIODO_1"
            val periodId = "PERIODO_1"
            
            promotionRepository.getAutoevaluaciones(studentId, periodId)
                .onEach { existing ->
                    // Mapear a items. Aquí faltaría la lista de asignaturas inscritas.
                    // Por simplicidad, si no hay asignaturas cargadas previamente, 
                    // se asume que se obtienen de otro repositorio (ej. Curricular o Academic).
                }
                .launchIn(viewModelScope)
            
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun updateScore(subjectId: String, score: Double) {
        _uiState.update { state ->
            val newList = state.autoevaluaciones.map { 
                if (it.subjectId == subjectId) it.copy(score = score) else it 
            }
            state.copy(autoevaluaciones = newList)
        }
    }

    fun saveAll() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            val session = sessionManager.sessionState.value as? SessionState.LoggedIn ?: return@launch
            val studentId = session.user.id
            val periodId = "PERIODO_1"

            try {
                _uiState.value.autoevaluaciones.forEach { item ->
                    val auto = Autoevaluacion(
                        id = "${studentId}_${item.subjectId}_$periodId",
                        studentId = studentId,
                        subjectId = item.subjectId,
                        periodId = periodId,
                        score = item.score,
                        registrationDate = Clock.System.now().toEpochMilliseconds()
                    )
                    promotionRepository.saveAutoevaluacion(auto)
                }
                _uiState.update { it.copy(isSaving = false, isSaved = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false, error = e.message) }
            }
        }
    }
}
