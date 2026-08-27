package com.sigeschool.presentation.screens.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.billing.PaymentRequest
import com.sigeschool.domain.model.billing.PaymentResponse
import com.sigeschool.services.billing.FacturacionService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class PaymentUiState {
    object Idle : PaymentUiState()
    object Loading : PaymentUiState()
    data class Success(val redirectUrl: String) : PaymentUiState()
    data class Error(val message: String) : PaymentUiState()
}

class PaymentPortalViewModel(
    private val facturacionService: FacturacionService
) : ViewModel() {

    private val _uiState = MutableStateFlow<PaymentUiState>(PaymentUiState.Idle)
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    fun processPayment(amount: Double, description: String, studentId: String, conceptId: String, method: String) {
        viewModelScope.launch {
            _uiState.value = PaymentUiState.Loading
            val request = PaymentRequest(
                amount = amount,
                description = description,
                studentId = studentId,
                conceptId = conceptId,
                paymentMethod = method
            )
            
            facturacionService.iniciarPagoEnLinea(request)
                .onSuccess { response ->
                    val url = response.redirectUrl
                    if (response.success && url != null) {
                        _uiState.value = PaymentUiState.Success(url)
                    } else {
                        _uiState.value = PaymentUiState.Error(response.error ?: "Error desconocido al procesar el pago")
                    }
                }
                .onFailure { error ->
                    _uiState.value = PaymentUiState.Error(error.message ?: "Error de conexión")
                }
        }
    }

    fun resetState() {
        _uiState.value = PaymentUiState.Idle
    }
}
