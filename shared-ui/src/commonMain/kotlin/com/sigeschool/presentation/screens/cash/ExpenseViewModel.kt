package com.sigeschool.presentation.screens.cash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.repository.ExpenseRepository
import com.sigeschool.domain.model.billing.Expense
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.random.Random

class ExpenseViewModel(
    private val expenseRepository: ExpenseRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<ExpenseUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.EnteredAmount -> {
                _uiState.update { it.copy(amount = event.value) }
            }
            is ExpenseEvent.EnteredDescription -> {
                _uiState.update { it.copy(description = event.value) }
            }
            is ExpenseEvent.EnteredCategory -> {
                _uiState.update { it.copy(category = event.value) }
            }
            is ExpenseEvent.SaveExpense -> {
                saveExpense()
            }
        }
    }

    private fun saveExpense() {
        val currentState = _uiState.value
        if (currentState.amount.isEmpty() || currentState.description.isEmpty()) {
            viewModelScope.launch {
                _eventFlow.emit(ExpenseUiEvent.ShowSnackbar("Por favor, complete los campos obligatorios"))
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val institutionId = sessionManager.getCurrentInstitutionId()
                    ?: throw Exception("Sesión no iniciada")

                val result = expenseRepository.saveExpense(
                    Expense(
                        id = Random.nextInt().toString(),
                        institutionId = institutionId,
                        amount = currentState.amount.toDoubleOrNull() ?: 0.0,
                        description = currentState.description,
                        category = currentState.category,
                        date = Clock.System.now().toEpochMilliseconds()
                    )
                )
                
                if (result is Resource.Success) {
                    _eventFlow.emit(ExpenseUiEvent.ExpenseSaved)
                    _uiState.update { ExpenseUiState() } // Reset
                } else {
                    _eventFlow.emit(ExpenseUiEvent.ShowSnackbar("Error al guardar el gasto"))
                }
            } catch (e: Exception) {
                _eventFlow.emit(ExpenseUiEvent.ShowSnackbar("Error: ${e.message}"))
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    data class ExpenseUiState(
        val amount: String = "",
        val description: String = "",
        val category: String = "General",
        val isLoading: Boolean = false
    )

    sealed class ExpenseEvent {
        data class EnteredAmount(val value: String) : ExpenseEvent()
        data class EnteredDescription(val value: String) : ExpenseEvent()
        data class EnteredCategory(val value: String) : ExpenseEvent()
        object SaveExpense : ExpenseEvent()
    }

    sealed class ExpenseUiEvent {
        data class ShowSnackbar(val message: String) : ExpenseUiEvent()
        object ExpenseSaved : ExpenseUiEvent()
    }
}
