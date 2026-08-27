package com.sigeschool.presentation.screens.puc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.PucRepository
import com.sigeschool.domain.model.PucAccount
import com.sigeschool.domain.model.AccountingEntry
import com.sigeschool.domain.model.EntryDetail
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.util.FinancialStatementGenerator
import com.sigeschool.util.PdfPlatformGenerator
import com.sigeschool.util.SmartImportUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class PucUiState(
    val accounts: List<PucAccount> = emptyList(),
    val entries: List<AccountingEntry> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val pdfReport: Pair<ByteArray, String>? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class PucViewModel(
    private val pucRepository: PucRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(PucUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeAccounts()
        observeEntries()
    }

    private fun observeAccounts() {
        viewModelScope.launch {
            sessionManager.sessionState
                .flatMapLatest { state ->
                    val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                    if (institutionId != null) {
                        pucRepository.getAccounts(institutionId)
                    } else {
                        flowOf(emptyList())
                    }
                }
                .catch { e -> _uiState.update { it.copy(error = e.message) } }
                .collect { accounts ->
                    if (accounts.isEmpty()) {
                        sessionManager.getCurrentInstitutionId()?.let {
                            pucRepository.seedInitialPuc(it)
                        }
                    } else {
                        _uiState.update { it.copy(accounts = accounts, isLoading = false) }
                    }
                }
        }
    }

    private fun observeEntries() {
        viewModelScope.launch {
            sessionManager.sessionState
                .flatMapLatest { state ->
                    val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                    if (institutionId != null) {
                        pucRepository.getEntries(institutionId)
                    } else {
                        flowOf(emptyList())
                    }
                }
                .collect { entries ->
                    _uiState.update { it.copy(entries = entries) }
                }
        }
    }

    fun saveEntry(
        description: String,
        details: List<EntryDetail>,
        date: String? = null
    ) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            val totalDebit = details.sumOf { it.debit }
            val totalCredit = details.sumOf { it.credit }

            if (totalDebit != totalCredit) {
                _uiState.update { it.copy(error = "La partida no está balanceada (Débito: $totalDebit, Crédito: $totalCredit)") }
                return@launch
            }

            val finalDate = date ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
            val entry = AccountingEntry(
                date = finalDate,
                description = description,
                institutionId = institutionId,
                entries = details,
                totalDebit = totalDebit,
                totalCredit = totalCredit
            )

            val success = pucRepository.saveEntry(entry)
            if (success) {
                _uiState.update { it.copy(error = null) }
            } else {
                _uiState.update { it.copy(error = "Error al guardar el asiento contable") }
            }
        }
    }

    fun generateFinancialStatement(type: String, institutionName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val reportBytes = withContext(Dispatchers.Default) {
                    val statement = if (type == "BALANCE") {
                        FinancialStatementGenerator.generateBalanceGeneral(
                            institutionName, uiState.value.accounts, uiState.value.entries
                        )
                    } else {
                        FinancialStatementGenerator.generateEstadoResultados(
                            institutionName, uiState.value.accounts, uiState.value.entries
                        )
                    }
                    PdfPlatformGenerator.generateFinancialReport(statement)
                }
                _uiState.update { it.copy(isLoading = false, pdfReport = reportBytes to "${type}_${institutionName}.pdf") }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al generar reporte: ${e.message}") }
            }
        }
    }

    fun clearPdfReport() {
        _uiState.update { it.copy(pdfReport = null) }
    }

    fun importEntries(rows: List<List<String>>) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            if (rows.isEmpty()) return@launch
            val headers = rows.first()
            val mapping = SmartImportUtils.mapHeaders(headers, "finance")
            val dataRows = rows.drop(1)

            dataRows.forEach { row ->
                val data = SmartImportUtils.extractData(row, mapping)
                val code = data["codigo"] ?: ""
                val debit = data["debito"]?.toDoubleOrNull() ?: 0.0
                val credit = data["credito"]?.toDoubleOrNull() ?: 0.0
                
                if (code.isNotBlank() && (debit > 0 || credit > 0)) {
                    val account = pucRepository.getAccountByCode(code, institutionId)
                    if (account != null) {
                        val entry = AccountingEntry(
                            date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString(),
                            description = data["descripcion"] ?: "Importación: ${account.name}",
                            institutionId = institutionId,
                            entries = listOf(EntryDetail(
                                accountCode = account.code,
                                accountName = account.name,
                                debit = debit,
                                credit = credit
                            )),
                            totalDebit = debit,
                            totalCredit = credit
                        )
                        pucRepository.saveEntry(entry)
                    }
                }
            }
        }
    }
}
