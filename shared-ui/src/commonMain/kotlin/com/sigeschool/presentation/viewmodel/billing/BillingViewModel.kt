package com.sigeschool.presentation.viewmodel.billing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.billing.Invoice
import com.sigeschool.domain.repository.billing.BillingRepository
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class BillingUiState(
    val invoices: List<Invoice> = emptyList(),
    val students: List<com.sigeschool.domain.model.Student> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedInvoice: Invoice? = null,
    val isMassiveGenerationLoading: Boolean = false,
    val generatedPdf: ByteArray? = null
)

sealed class BillingEvent {
    object LoadInvoices : BillingEvent()
    data class SelectInvoice(val invoice: Invoice) : BillingEvent()
    data class GenerateInvoicePdf(val invoice: Invoice) : BillingEvent()
    data class GenerateMassive(
        val studentIds: List<String>,
        val concept: String,
        val amount: Double,
        val month: Int
    ) : BillingEvent()
}

@OptIn(ExperimentalCoroutinesApi::class)
class BillingViewModel(
    private val repository: BillingRepository,
    private val studentRepository: com.sigeschool.domain.repository.StudentRepository,
    private val pdfGenerator: com.sigeschool.util.ReceiptGenerator,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(BillingUiState())
    val uiState: StateFlow<BillingUiState> = _uiState.asStateFlow()

    init {
        observeInvoices()
        loadStudents()
    }

    private fun loadStudents() {
        viewModelScope.launch {
            studentRepository.getActiveStudents()
                .catch { e -> _uiState.update { it.copy(error = e.message) } }
                .collect { students ->
                    _uiState.update { it.copy(students = students) }
                }
        }
    }

    private fun observeInvoices() {
        viewModelScope.launch {
            sessionManager.sessionState
                .flatMapLatest { state ->
                    val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                    if (institutionId != null) {
                        repository.getInvoices(institutionId)
                    } else {
                        flowOf(emptyList())
                    }
                }
                .catch { e -> _uiState.update { it.copy(error = e.message) } }
                .collect { invoices ->
                    _uiState.update { it.copy(invoices = invoices, isLoading = false) }
                }
        }
    }

    fun onEvent(event: BillingEvent) {
        when (event) {
            is BillingEvent.LoadInvoices -> { /* Already observed */ }
            is BillingEvent.SelectInvoice -> _uiState.update { it.copy(selectedInvoice = event.invoice) }
            is BillingEvent.GenerateInvoicePdf -> generatePdf(event.invoice)
            is BillingEvent.GenerateMassive -> generateMassive(
                event.studentIds,
                event.concept,
                event.amount,
                event.month
            )
        }
    }

    private fun generatePdf(invoice: Invoice) {
        viewModelScope.launch {
            try {
                val pdf = pdfGenerator.generateInvoicePdf(invoice)
                _uiState.update { it.copy(generatedPdf = pdf) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Error al generar PDF: ${e.message}") }
            }
        }
    }

    private fun generateMassive(
        studentIds: List<String>,
        concept: String,
        amount: Double,
        month: Int
    ) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isMassiveGenerationLoading = true) }
            try {
                repository.generateMassiveInvoices(institutionId, studentIds, concept, amount, month)
                _uiState.update { it.copy(isMassiveGenerationLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isMassiveGenerationLoading = false, error = e.message) }
            }
        }
    }
}
