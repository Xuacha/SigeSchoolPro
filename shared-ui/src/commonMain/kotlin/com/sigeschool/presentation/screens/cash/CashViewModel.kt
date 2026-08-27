package com.sigeschool.presentation.screens.cash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.UserRole
import com.sigeschool.domain.model.billing.CashArqueo
import com.sigeschool.domain.model.billing.CashTransaction
import com.sigeschool.domain.model.billing.CashTransactionType
import com.sigeschool.domain.repository.CashRepository
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class CashUiState(
    val transactions: List<CashTransaction> = emptyList(),
    val arqueo: CashArqueo? = null,
    val userRole: UserRole = UserRole.INVITADO,
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val dateRange: Pair<Long, Long>? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class CashViewModel(
    private val cashRepository: CashRepository,
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(CashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUserRole()
        setupObservation()
        loadTodayData()
    }

    private fun loadUserRole() {
        viewModelScope.launch {
            val user = authRepository.getCurrentUser()
            val role = user?.userMetadata?.get("role")?.toString()?.let { UserRole.fromString(it) } ?: UserRole.INVITADO
            _uiState.update { it.copy(userRole = role) }
        }
    }

    private fun setupObservation() {
        viewModelScope.launch {
            combine(
                sessionManager.sessionState,
                _uiState.map { it.dateRange }.distinctUntilChanged()
            ) { session, range ->
                session to range
            }.flatMapLatest { (session, range) ->
                val institutionId = (session as? SessionState.LoggedIn)?.institutionId
                if (institutionId != null && range != null) {
                    combine(
                        cashRepository.getTransactions(institutionId, range.first, range.second),
                        cashRepository.getArqueo(institutionId, range.first, range.second)
                    ) { transactions, arqueo ->
                        transactions to arqueo
                    }
                } else {
                    flowOf(emptyList<CashTransaction>() to null)
                }
            }.collect { (transactions, arqueo) ->
                _uiState.update { it.copy(
                    transactions = transactions,
                    arqueo = arqueo,
                    isLoading = false
                ) }
            }
        }
    }

    fun loadTodayData() {
        val now = Clock.System.now()
        val today = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val startOfDay = Clock.System.now().toEpochMilliseconds() - (today.hour * 3600 + today.minute * 60 + today.second) * 1000
        val endOfDay = startOfDay + 86400000 - 1
        
        loadRangeData(startOfDay, endOfDay)
    }

    fun loadRangeData(start: Long, end: Long) {
        _uiState.update { it.copy(dateRange = start to end, isLoading = true) }
    }

    fun registerTransaction(
        type: CashTransactionType,
        concept: String,
        category: String,
        amount: Double,
        paymentMethod: String,
        personName: String?,
        notes: String?
    ) {
        val role = _uiState.value.userRole
        val institutionId = sessionManager.getCurrentInstitutionId()
        
        if (institutionId == null) {
            _uiState.update { it.copy(error = "Institución no seleccionada") }
            return
        }

        if (role != UserRole.REPRESENTANTE_LEGAL && role != UserRole.RECTOR && role != UserRole.SECRETARIA) {
            _uiState.update { it.copy(error = "No tiene permisos para realizar esta operación") }
            return
        }

        viewModelScope.launch {
            try {
                val userId = authRepository.getCurrentUser()?.id ?: "unknown"
                val transaction = CashTransaction(
                    id = "cash_${Clock.System.now().toEpochMilliseconds()}",
                    institutionId = institutionId,
                    type = type,
                    concept = concept,
                    category = category,
                    amount = amount,
                    paymentMethod = paymentMethod,
                    personName = personName,
                    reference = null,
                    timestamp = Clock.System.now().toEpochMilliseconds(),
                    observations = notes,
                    registradoPorId = userId
                )
                cashRepository.registerTransaction(transaction)
                _uiState.update { it.copy(successMessage = "Transacción registrada con éxito") }
                loadTodayData()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Error al registrar: ${e.message}") }
            }
        }
    }

    fun clearMessages() {
        _uiState.update { it.copy(error = null, successMessage = null) }
    }
}
