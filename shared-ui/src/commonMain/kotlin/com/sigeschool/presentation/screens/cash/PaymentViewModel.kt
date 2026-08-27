package com.sigeschool.presentation.screens.cash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.repository.PaymentRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.billing.Payment
import com.sigeschool.domain.model.billing.PaymentMethod
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.random.Random

class PaymentViewModel(
    private val paymentRepository: PaymentRepository,
    private val studentRepository: StudentRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<PaymentUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: PaymentEvent) {
        when (event) {
            is PaymentEvent.EnteredStudentId -> {
                _uiState.update { it.copy(studentDocumentId = event.value) }
                searchStudent(event.value)
            }
            is PaymentEvent.EnteredAmount -> {
                _uiState.update { it.copy(amount = event.value) }
            }
            is PaymentEvent.EnteredConcept -> {
                _uiState.update { it.copy(concept = event.value) }
            }
            is PaymentEvent.ChangedPaymentMethod -> {
                _uiState.update { it.copy(paymentMethod = event.value) }
            }
            is PaymentEvent.ProcessPayment -> {
                handlePaymentSubmission()
            }
        }
    }

    private fun searchStudent(documentId: String) {
        viewModelScope.launch {
            val instId = sessionManager.getCurrentInstitutionId() ?: ""
            val students = studentRepository.search(documentId, instId)
            val student = students.find { it.dni == documentId }
            _uiState.update { 
                it.copy(
                    studentName = student?.nombreCompleto ?: "",
                    studentId = student?.id
                )
            }
        }
    }

    private fun handlePaymentSubmission() {
        val currentState = _uiState.value
        val institutionId = sessionManager.getCurrentInstitutionId()
        
        if (institutionId == null) {
            viewModelScope.launch {
                _eventFlow.emit(PaymentUiEvent.ShowSnackbar("Sesión no válida"))
            }
            return
        }

        if (currentState.studentId == null || currentState.amount.isEmpty() || currentState.concept.isEmpty()) {
            viewModelScope.launch {
                _eventFlow.emit(PaymentUiEvent.ShowSnackbar("Por favor, complete todos los campos"))
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            val payment = Payment(
                id = Random.nextInt().toString(),
                institutionId = institutionId,
                studentId = currentState.studentId,
                amount = currentState.amount.toDoubleOrNull() ?: 0.0,
                concept = currentState.concept,
                paymentMethod = currentState.paymentMethod,
                date = Clock.System.now().toEpochMilliseconds()
            )

            if (currentState.paymentMethod == PaymentMethod.WOMPI || currentState.paymentMethod == PaymentMethod.STRIPE) {
                when (val result = paymentRepository.processGatewayPayment(payment)) {
                    is Resource.Success -> {
                        _eventFlow.emit(PaymentUiEvent.InitiateGatewayPayment(result.data ?: "", currentState.paymentMethod))
                    }
                    is Resource.Error -> {
                        _eventFlow.emit(PaymentUiEvent.ShowSnackbar(result.message ?: "Error en pasarela"))
                    }
                    is Resource.Loading -> {}
                }
            } else {
                saveLocalPayment(payment)
            }
            
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun saveLocalPayment(payment: Payment) {
        val result = paymentRepository.savePayment(payment)
        if (result is Resource.Success) {
            _eventFlow.emit(PaymentUiEvent.PaymentSaved)
            _uiState.update { PaymentUiState() } // Reset state
        } else {
            _eventFlow.emit(PaymentUiEvent.ShowSnackbar("Error al guardar el pago"))
        }
    }

    data class PaymentUiState(
        val studentDocumentId: String = "",
        val studentId: String? = null,
        val studentName: String = "",
        val amount: String = "",
        val concept: String = "",
        val paymentMethod: PaymentMethod = PaymentMethod.CASH,
        val isLoading: Boolean = false
    )

    sealed class PaymentEvent {
        data class EnteredStudentId(val value: String) : PaymentEvent()
        data class EnteredAmount(val value: String) : PaymentEvent()
        data class EnteredConcept(val value: String) : PaymentEvent()
        data class ChangedPaymentMethod(val value: PaymentMethod) : PaymentEvent()
        object ProcessPayment : PaymentEvent()
    }

    sealed class PaymentUiEvent {
        data class ShowSnackbar(val message: String) : PaymentUiEvent()
        object PaymentSaved : PaymentUiEvent()
        data class InitiateGatewayPayment(val transactionToken: String, val method: PaymentMethod) : PaymentUiEvent()
    }
}
