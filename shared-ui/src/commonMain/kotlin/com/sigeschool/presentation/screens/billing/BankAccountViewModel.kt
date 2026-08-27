package com.sigeschool.presentation.screens.billing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.billing.AccountStatus
import com.sigeschool.domain.model.billing.AccountType
import com.sigeschool.domain.model.billing.BankAccount
import com.sigeschool.domain.repository.billing.BankAccountRepository
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface BankAccountUiState {
    object Idle : BankAccountUiState
    object Loading : BankAccountUiState
    data class Success(val account: BankAccount?) : BankAccountUiState
    data class Error(val message: String) : BankAccountUiState
}

class BankAccountViewModel(
    private val repository: BankAccountRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<BankAccountUiState>(BankAccountUiState.Idle)
    val uiState: StateFlow<BankAccountUiState> = _uiState.asStateFlow()

    // Cache del institutionId actual para operaciones de guardado
    private var currentInstitutionId: String? = null

    init {
        // Hallazgo A.1 - MEDIO: Hacer reactivo el institutionId
        viewModelScope.launch {
            sessionManager.sessionState
                .map { state ->
                    if (state is SessionState.LoggedIn) state.institutionId else null
                }
                .distinctUntilChanged()
                .collect { institutionId ->
                    currentInstitutionId = institutionId
                    if (institutionId != null) {
                        loadAccount(institutionId)
                    } else if (sessionManager.sessionState.value !is SessionState.Loading) {
                        _uiState.value = BankAccountUiState.Error("Sesión de institución no válida")
                    }
                }
        }
    }

    private fun loadAccount(institutionId: String) {
        viewModelScope.launch {
            _uiState.value = BankAccountUiState.Loading
            repository.getAccountByInstitution(institutionId)
                .catch { e -> _uiState.value = BankAccountUiState.Error(e.message ?: "Error desconocido") }
                .collect { account ->
                    _uiState.value = BankAccountUiState.Success(account)
                }
        }
    }

    fun saveOrUpdateAccount(
        bankName: String,
        accountType: AccountType,
        accountNumber: String,
        holderName: String,
        holderDni: String,
        email: String?
    ) {
        val institutionId = currentInstitutionId
        if (institutionId == null) {
            _uiState.value = BankAccountUiState.Error("No hay una institución activa")
            return
        }

        viewModelScope.launch {
            _uiState.value = BankAccountUiState.Loading
            val currentAccount = (uiState.value as? BankAccountUiState.Success)?.account
            
            val account = BankAccount(
                id = currentAccount?.id ?: "",
                institutionId = institutionId,
                bankName = bankName,
                accountType = accountType,
                accountNumber = accountNumber,
                holderName = holderName,
                holderDni = holderDni,
                notificationEmail = email,
                status = currentAccount?.status ?: AccountStatus.ACTIVA
            )

            val result = if (currentAccount == null) {
                repository.saveAccount(account)
            } else {
                repository.updateAccount(account)
            }

            result.onSuccess {
                loadAccount(institutionId)
            }.onFailure { e ->
                _uiState.value = BankAccountUiState.Error(e.message ?: "Error al guardar")
            }
        }
    }
}
