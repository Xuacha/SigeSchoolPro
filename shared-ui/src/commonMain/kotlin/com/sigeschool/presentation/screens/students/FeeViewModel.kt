package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.FeeRepository
import com.sigeschool.domain.model.FeePayment
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class FeeViewModel(
    private val feeRepository: FeeRepository
) : ViewModel() {

    private val _paymentsState = MutableStateFlow<Resource<List<FeePayment>>>(Resource.Loading())
    val paymentsState: StateFlow<Resource<List<FeePayment>>> = _paymentsState.asStateFlow()

    private val _operationStatus = MutableStateFlow<Resource<Boolean>?>(null)
    val operationStatus: StateFlow<Resource<Boolean>?> = _operationStatus.asStateFlow()

    fun loadPayments(studentId: String) {
        viewModelScope.launch {
            feeRepository.getPaymentsByStudent(studentId).collect {
                _paymentsState.value = it
            }
        }
    }

    fun registerPayment(
        studentId: String,
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
        }
    }
    
    fun clearOperationStatus() {
        _operationStatus.value = null
    }
}
