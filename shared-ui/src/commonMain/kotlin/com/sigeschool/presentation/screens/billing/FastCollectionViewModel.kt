package com.sigeschool.presentation.screens.billing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.billing.*
import com.sigeschool.domain.usecase.billing.SearchStudentUseCase
import com.sigeschool.domain.usecase.billing.ProcessPaymentUseCase
import com.sigeschool.domain.repository.billing.BillingRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

data class FastCollectionState(
    val searchQuery: String = "",
    val searchResults: List<Student> = emptyList(),
    val selectedStudent: Student? = null,
    val cartItems: List<InvoiceItem> = emptyList(),
    val availableCategories: List<FeeCategory> = emptyList(),
    val selectedPaymentMethod: PaymentMethod = PaymentMethod.EFECTIVO,
    val amountReceived: Double = 0.0,
    val isProcessing: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
) {
    val subtotal: Double = cartItems.sumOf { it.total }
    val total: Double = subtotal // Por ahora sin descuentos globales
    val change: Double = if (amountReceived > total) amountReceived - total else 0.0
}

class FastCollectionViewModel(
    private val searchStudentUseCase: SearchStudentUseCase,
    private val processPaymentUseCase: ProcessPaymentUseCase,
    private val billingRepository: BillingRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FastCollectionState())
    val state = _state.asStateFlow()

    init {
        loadFeeCategories()
    }

    private fun loadFeeCategories() {
        viewModelScope.launch {
            billingRepository.getFeeCategories().collect { categories ->
                _state.update { it.copy(availableCategories = categories) }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _state.update { it.copy(searchQuery = query) }
        viewModelScope.launch {
            searchStudentUseCase(query).collect { results ->
                _state.update { it.copy(searchResults = results) }
            }
        }
    }

    fun selectStudent(student: Student) {
        _state.update { it.copy(selectedStudent = student, searchResults = emptyList(), searchQuery = "") }
    }

    fun clearStudent() {
        _state.update { it.copy(selectedStudent = null, cartItems = emptyList()) }
    }

    fun addItemToCart(category: FeeCategory) {
        val newItem = InvoiceItem(
            id = "item_${Clock.System.now().toEpochMilliseconds()}",
            categoryId = category.id,
            description = category.name,
            quantity = 1,
            unitPrice = category.basePrice,
            total = category.basePrice
        )
        _state.update { it.copy(cartItems = it.cartItems + newItem) }
    }

    fun removeItemFromCart(itemId: String) {
        _state.update { it.copy(cartItems = it.cartItems.filter { item -> item.id != itemId }) }
    }

    fun setPaymentMethod(method: PaymentMethod) {
        _state.update { it.copy(selectedPaymentMethod = method) }
    }

    fun setAmountReceived(amount: Double) {
        _state.update { it.copy(amountReceived = amount) }
    }

    fun processPayment() {
        val currentState = _state.value
        val student = currentState.selectedStudent ?: return
        if (currentState.cartItems.isEmpty()) return

        viewModelScope.launch {
            _state.update { it.copy(isProcessing = true, error = null) }
            
            val invoiceId = "INV_${Clock.System.now().toEpochMilliseconds()}"
            val pagoId = "PAY_${Clock.System.now().toEpochMilliseconds()}"
            
            val invoice = Invoice(
                id = invoiceId,
                pagoId = pagoId,
                number = "REC-${Clock.System.now().toEpochMilliseconds()}",
                studentId = student.id,
                studentName = student.nombreCompleto,
                parentName = student.nombreAcudiente ?: "N/A",
                parentId = student.documentoAcudiente ?: "UNKNOWN",
                grade = student.grado,
                institutionId = student.institutionId,
                date = Clock.System.now(),
                dueDate = Clock.System.now(),
                status = InvoiceStatus.ACCEPTED,
                type = DocumentType.INVOICE_INTERNAL,
                items = currentState.cartItems,
                totalAmount = currentState.total,
                paidAmount = currentState.total,
                balance = 0.0,
                concept = "Cobro Rápido - Ventanilla"
            )

            val result = processPaymentUseCase(
                invoice = invoice,
                paymentMethod = currentState.selectedPaymentMethod,
                amount = currentState.total,
                registrarId = "CAJERO_LOCAL"
            )

            if (result.isSuccess) {
                _state.update { 
                    it.copy(
                        isProcessing = false,
                        successMessage = "Pago procesado con éxito",
                        selectedStudent = null,
                        cartItems = emptyList(),
                        amountReceived = 0.0
                    ) 
                }
            } else {
                _state.update { 
                    it.copy(
                        isProcessing = false,
                        error = "Error al procesar el pago: ${result.exceptionOrNull()?.message}"
                    ) 
                }
            }
        }
    }
    
    fun clearMessages() {
        _state.update { it.copy(error = null, successMessage = null) }
    }
}
