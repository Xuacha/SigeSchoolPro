package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.FeeRepository
import com.sigeschool.domain.model.FeePayment
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class FeeViewModel(
    private val feeRepository: FeeRepository
) : ViewModel() {

    private val _paymentsState = MutableStateFlow<Resource<List<FeePayment>>>(Resource.Loading())
    val paymentsState: StateFlow<Resource<List<FeePayment>>> = _paymentsState.asStateFlow()

    // FIX: la pantalla (StudentDetailScreen) usaba `feeViewModel.payments`
    // como si fuera una List<FeePayment> directa, pero esa propiedad no
    // existía (solo estaba `paymentsState`, que es un
    // Resource<List<FeePayment>>). Esto no compilaba. Se agrega este
    // StateFlow derivado ya desempaquetado para que la UI no tenga que
    // lidiar con Resource.
    private val _payments = MutableStateFlow<List<FeePayment>>(emptyList())
    val payments: StateFlow<List<FeePayment>> = _payments.asStateFlow()

    private val _operationStatus = MutableStateFlow<Resource<Boolean>?>(null)
    val operationStatus: StateFlow<Resource<Boolean>?> = _operationStatus.asStateFlow()

    // FIX: URL firmada resuelta bajo demanda para ver un recibo (ya no
    // se abre directamente una URL pública guardada en el modelo).
    private val _receiptUrlToOpen = MutableStateFlow<String?>(null)
    val receiptUrlToOpen: StateFlow<String?> = _receiptUrlToOpen.asStateFlow()

    fun loadPayments(studentId: Long) {
        viewModelScope.launch {
            feeRepository.getPaymentsByStudent(studentId).collect { resource ->
                _paymentsState.value = resource
                resource.data?.let { _payments.value = it }
            }
        }
    }

    fun viewReceipt(receiptPath: String) {
        viewModelScope.launch {
            _receiptUrlToOpen.value = feeRepository.getReceiptUrl(receiptPath)
        }
    }

    fun consumeReceiptUrl() {
        _receiptUrlToOpen.value = null
    }

    fun registerPayment(
        studentId: Long,
        institutionId: String,
        amount: Double,
        concept: String,
        user: String,
        method: String = "EFECTIVO"
    ) {
        viewModelScope.launch {
            _operationStatus.value = Resource.Loading()
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val payment = FeePayment(
                id = Clock.System.now().toEpochMilliseconds().toString(),
                studentId = studentId,
                institutionId = institutionId,
                monto = amount,
                concepto = concept,
                fecha = now.toString(),
                usuarioRecibe = user,
                metodoPago = method
            )
            _operationStatus.value = feeRepository.registerPayment(payment)
            loadPayments(studentId)
        }
    }

    fun clearOperationStatus() {
        _operationStatus.value = null
    }
}
