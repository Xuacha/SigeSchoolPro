package com.sigeschool.presentation.screens.laboral

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.VacationRequest
import com.sigeschool.domain.model.LiquidationCalculation
import com.sigeschool.domain.model.AdvanceRequest
import com.sigeschool.domain.repository.LaboralRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.util.LaboralCalculator
import com.sigeschool.domain.util.Resource
import com.sigeschool.util.PdfPlatformGenerator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class LaboralUiState(
    val vacationRequests: List<VacationRequest> = emptyList(),
    val advanceRequests: List<AdvanceRequest> = emptyList(),
    val payrollCalculation: PayrollCalculation? = null,
    val liquidationEstimate: LiquidationCalculation? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

class LaboralViewModel(
    private val laboralRepository: LaboralRepository,
    private val authRepository: AuthRepository,
    private val pdfGenerator: PdfPlatformGenerator
) : ViewModel() {

    private val _uiState = MutableStateFlow(LaboralUiState())
    val uiState: StateFlow<LaboralUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val employeeId = authRepository.getCurrentUser()?.id ?: ""
            loadVacationRequests(employeeId)
            loadAdvanceRequests(employeeId)
        }
    }

    private fun loadVacationRequests(employeeId: String) {
        viewModelScope.launch {
            laboralRepository.getVacationRequests(employeeId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _uiState.update { it.copy(isLoading = false, vacationRequests = resource.data ?: emptyList()) }
                    is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = resource.message) }
                }
            }
        }
    }

    private fun loadAdvanceRequests(employeeId: String) {
        viewModelScope.launch {
            laboralRepository.getAdvanceRequests(employeeId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _uiState.update { it.copy(isLoading = false, advanceRequests = resource.data ?: emptyList()) }
                    is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = resource.message) }
                }
            }
        }
    }

    fun submitAdvanceRequest(amount: Double, reason: String) {
        viewModelScope.launch {
            val employeeId = authRepository.getCurrentUser()?.id ?: ""
            val request = AdvanceRequest(
                employeeId = employeeId,
                amountRequested = amount,
                reason = reason,
                requestDate = 0 // En prod usar timestamp real
            )
            _uiState.update { it.copy(isLoading = true) }
            val result = laboralRepository.submitAdvanceRequest(request)
            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, successMessage = "Adelanto solicitado") }
                    loadAdvanceRequests(employeeId)
                }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }

    fun calculatePayroll(basicSalary: Double, daysWorked: Int, advances: Double = 0.0) {
        val calculation = LaboralCalculator.calculatePayroll(basicSalary, daysWorked, advances)
        _uiState.update { it.copy(payrollCalculation = calculation) }
    }

    fun estimateLiquidation(lastSalary: Double, startDate: Long, endDate: Long) {
        val estimate = LaboralCalculator.calculateLiquidation(lastSalary, startDate, endDate)
        _uiState.update { it.copy(liquidationEstimate = estimate) }
    }

    fun submitVacationRequest(startDate: Long, endDate: Long, days: Int, observations: String) {
        viewModelScope.launch {
            val employeeId = authRepository.getCurrentUser()?.id ?: ""
            val request = VacationRequest(
                employeeId = employeeId,
                startDate = startDate,
                endDate = endDate,
                days = days,
                observations = observations
            )
            _uiState.update { it.copy(isLoading = true) }
            val result = laboralRepository.submitVacationRequest(request)
            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, successMessage = "Solicitud enviada con éxito") }
                    loadVacationRequests(employeeId)
                }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }
    
    fun clearMessages() {
        _uiState.update { it.copy(error = null, successMessage = null) }
    }
}
