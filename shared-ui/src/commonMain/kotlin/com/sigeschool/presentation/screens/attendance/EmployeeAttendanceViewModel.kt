package com.sigeschool.presentation.screens.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AttendanceRepository
import com.sigeschool.data.repository.EmployeeRepository
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.EmployeeStatus
import com.sigeschool.domain.model.EmployeeAttendance
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class EmployeeAttendanceUiState(
    val date: String = "",
    val employees: List<Employee> = emptyList(),
    val attendance: List<EmployeeAttendance> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class EmployeeAttendanceViewModel(
    private val employeeRepository: EmployeeRepository,
    private val attendanceRepository: AttendanceRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmployeeAttendanceUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val now = Clock.System.now()
        val zone = TimeZone.currentSystemDefault()
        val today = now.toLocalDateTime(zone).date.toString()
        _uiState.update { it.copy(date = today) }
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val institutionId = authRepository.getCurrentInstitutionId() ?: ""
            
            combine(
                employeeRepository.getEmployees(institutionId).map { list ->
                    list.filter { it.status != EmployeeStatus.DESVINCULADO }
                },
                attendanceRepository.getEmployeeAttendanceByDate(_uiState.value.date, institutionId)
            ) { employees, attendanceRes ->
                _uiState.update { it.copy(
                    employees = employees,
                    attendance = attendanceRes.data ?: emptyList(),
                    isLoading = false
                ) }
            }.collect()
        }
    }

    fun approveOvertime(attendanceId: String) {
        viewModelScope.launch {
            val user = authRepository.getCurrentUser()
            val institutionId = authRepository.getCurrentInstitutionId() ?: ""
            val result = attendanceRepository.approveOvertime(attendanceId, user?.email ?: "Admin", institutionId)
            if (result is Resource.Success && result.data == true) {
                loadData()
            } else {
                _uiState.update { it.copy(error = (result as? Resource.Error)?.message ?: "No se pudo aprobar el tiempo extra") }
            }
        }
    }
    
    fun updateDate(date: String) {
        _uiState.update { it.copy(date = date) }
        loadData()
    }
}
