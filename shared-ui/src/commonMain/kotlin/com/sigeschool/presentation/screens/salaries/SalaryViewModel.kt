package com.sigeschool.presentation.screens.salaries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.SalaryRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.data.repository.EmployeeRepository
import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.EmployeeStatus
import com.sigeschool.domain.model.SalaryRecord
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SalaryUiState(
    val salaryRecords: List<SalaryRecord> = emptyList(),
    val employees: List<Employee> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class SalaryViewModel(
    private val salaryRepository: SalaryRepository,
    private val authRepository: AuthRepository,
    private val employeeRepository: EmployeeRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SalaryUiState())
    val uiState: StateFlow<SalaryUiState> = _uiState.asStateFlow()

    init {
        observeSalaries()
        observeEmployees()
    }

    private fun observeEmployees() {
        viewModelScope.launch {
            sessionManager.sessionState
                .flatMapLatest { state ->
                    val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                    if (institutionId != null) {
                        employeeRepository.getActiveEmployees(institutionId)
                    } else {
                        flowOf(emptyList())
                    }
                }
                .collect { employees ->
                    _uiState.update { it.copy(employees = employees) }
                }
        }
    }

    fun addSalaryRecord(record: SalaryRecord) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            salaryRepository.addSalaryRecord(record.copy(institutionId = institutionId))
        }
    }

    private fun observeSalaries() {
        viewModelScope.launch {
            sessionManager.sessionState
                .flatMapLatest { state ->
                    val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                    if (institutionId != null) {
                        salaryRepository.getSalaryRecords(institutionId)
                    } else {
                        flowOf(Resource.Success(emptyList()))
                    }
                }
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = true, salaryRecords = resource.data ?: it.salaryRecords) }
                        }
                        is Resource.Success -> {
                            _uiState.update { it.copy(isLoading = false, salaryRecords = resource.data ?: emptyList(), error = null) }
                        }
                        is Resource.Error -> {
                            _uiState.update { it.copy(isLoading = false, error = resource.message) }
                        }
                    }
                }
        }
    }
}
