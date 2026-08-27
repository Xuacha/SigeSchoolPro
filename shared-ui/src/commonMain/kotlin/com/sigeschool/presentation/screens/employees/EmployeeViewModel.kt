package com.sigeschool.presentation.screens.employees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.EmployeeRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.EmployeeStatus
import com.sigeschool.domain.model.UserRole
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.util.SmartImportUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class EmployeeUiState(
    val employees: List<Employee> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeViewModel(
    private val employeeRepository: EmployeeRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmployeeUiState())
    val uiState: StateFlow<EmployeeUiState> = _uiState.asStateFlow()

    init {
        sessionManager.sessionState
            .onEach { _uiState.update { it.copy(isLoading = true) } }
            .flatMapLatest { state ->
                val institutionId = (state as? SessionState.LoggedIn)?.institutionId
                if (institutionId != null) {
                    employeeRepository.getEmployees(institutionId)
                } else {
                    flowOf(emptyList())
                }
            }
            .onEach { employees ->
                _uiState.update { it.copy(employees = employees, isLoading = false) }
            }
            .catch { e -> _uiState.update { it.copy(error = e.message, isLoading = false) } }
            .launchIn(viewModelScope)
    }


    fun addEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val institutionId = sessionManager.getCurrentInstitutionId() ?: throw Exception("No institution selected")
                employeeRepository.addEmployee(employee.copy(institutionId = institutionId))
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun importEmployees(rows: List<List<String>>) {
        viewModelScope.launch {
            if (rows.isEmpty()) return@launch

            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            
            val headers = rows.first()
            val mapping = SmartImportUtils.mapHeaders(headers, "employee")
            val dataRows = rows.drop(1)

            val newEmployees = dataRows.map { row ->
                val data = SmartImportUtils.extractData(row, mapping)
                Employee(
                    firstName = data["nombre"] ?: "",
                    lastName = data["apellido"] ?: "",
                    dni = data["dni"] ?: "",
                    role = UserRole.fromString(data["rol"]),
                    qualification = data["formacion"] ?: "",
                    specialization = data["especialidad"] ?: "",
                    phone = data["telefono"] ?: "",
                    email = data["email"] ?: "",
                    institutionId = institutionId,
                    status = EmployeeStatus.ACTIVO
                )
            }.filter { it.firstName.isNotBlank() && it.dni.isNotBlank() }

            newEmployees.forEach { employee ->
                launch { employeeRepository.addEmployee(employee) }
            }
        }
    }
}
