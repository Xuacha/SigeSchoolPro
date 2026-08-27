package com.sigeschool.presentation.screens.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AttendanceRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.Attendance
import com.sigeschool.domain.model.AttendanceScan
import com.sigeschool.domain.model.AttendanceStatus
import com.sigeschool.domain.model.ScanType
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock as kotlinxClock
import kotlinx.datetime.Instant as kotlinxInstant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

data class AttendanceUiState(
    val date: String = "", // Formato YYYY-MM-DD
    val students: List<Student> = emptyList(),
    val attendanceMap: Map<String, AttendanceStatus> = emptyMap(),
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val isSyncing: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class AttendanceViewModel(
    private val studentRepository: StudentRepository,
    private val attendanceRepository: AttendanceRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AttendanceUiState(date = getCurrentDate()))
    val uiState = _uiState.asStateFlow()

    init {
        observeData()
    }

    private fun observeData() {
        viewModelScope.launch {
            combine(
                sessionManager.sessionState,
                uiState.map { it.date }.distinctUntilChanged()
            ) { state, date ->
                val instId = (state as? SessionState.LoggedIn)?.institutionId
                instId to date
            }.flatMapLatest { (instId, date) ->
                if (instId != null) {
                    combine(
                        studentRepository.getAllStudents(instId),
                        attendanceRepository.getAttendanceByDate(date, instId)
                    ) { studentsRes, attendanceRes ->
                        studentsRes to attendanceRes
                    }
                } else {
                    flowOf(Resource.Success(emptyList<Student>()) to Resource.Success(emptyList<Attendance>()))
                }
            }.collect { (studentsRes, attendanceRes) ->
                handleResourceUpdate(studentsRes, attendanceRes)
            }
        }
    }

    private fun handleResourceUpdate(
        studentsRes: Resource<List<Student>>,
        attendanceRes: Resource<List<Attendance>>
    ) {
        when {
            studentsRes is Resource.Loading || attendanceRes is Resource.Loading -> {
                _uiState.update { it.copy(isLoading = true) }
            }
            studentsRes is Resource.Error -> {
                _uiState.update { it.copy(isLoading = false, error = studentsRes.message) }
            }
            attendanceRes is Resource.Error -> {
                _uiState.update { it.copy(isLoading = false, error = attendanceRes.message) }
            }
            studentsRes is Resource.Success && attendanceRes is Resource.Success -> {
                val students = studentsRes.data ?: emptyList()
                val attendanceList = attendanceRes.data ?: emptyList()
                val map = attendanceList.associate { it.studentId to it.estado }
                _uiState.update { 
                    it.copy(
                        students = students,
                        attendanceMap = map,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    fun updateDate(millis: Long) {
        val instant = kotlinxInstant.fromEpochMilliseconds(millis)
        val zone = TimeZone.currentSystemDefault()
        val dateString = instant.toLocalDateTime(zone).date.toString()
        _uiState.update { it.copy(date = dateString) }
    }

    fun updateStatus(studentId: String, status: AttendanceStatus) {
        val currentMap = _uiState.value.attendanceMap.toMutableMap()
        currentMap[studentId] = status
        _uiState.update { it.copy(attendanceMap = currentMap) }
    }

    fun saveAttendance() {
        val state = _uiState.value
        val institutionId = sessionManager.getCurrentInstitutionId() ?: return
        val attendanceList = state.students.map { student ->
            Attendance(
                studentId = student.id,
                fecha = state.date,
                estado = state.attendanceMap[student.id] ?: AttendanceStatus.ABSENT,
                institutionId = institutionId
            )
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            val result = attendanceRepository.saveAttendance(attendanceList, institutionId)
            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isSaving = false, error = "Asistencia guardada correctamente") }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isSaving = false, error = result.message ?: "Error al guardar asistencia") }
                }
                is Resource.Loading -> { /* Handled by isSaving */ }
            }
        }
    }

    fun syncWithCloud() {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            _uiState.update { it.copy(isSyncing = true) }
            try {
                attendanceRepository.syncWithCloud(institutionId)
                _uiState.update { it.copy(isSyncing = false, error = "Sincronización exitosa") }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSyncing = false, error = "Error en la sincronización remota: ${e.message}") }
            }
        }
    }

    fun onScanResult(barcode: String, scanType: ScanType) {
        viewModelScope.launch {
            val institutionId = sessionManager.getCurrentInstitutionId() ?: return@launch
            val now = kotlinxClock.System.now().toString()
            val scan = AttendanceScan(
                type = scanType,
                identifier = barcode,
                timestamp = now
            )
            val result = attendanceRepository.registerScan(scan, institutionId)
            when (result) {
                is Resource.Success -> {
                    if (scanType == ScanType.STUDENT_ENTRY || scanType == ScanType.STUDENT_EXIT) {
                        observeData()
                    }
                    _uiState.update { it.copy(error = "Escaneo exitoso: $barcode") }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "Error al procesar escaneo") }
                }
                is Resource.Loading -> { /* Optional: show loading for scan */ }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun getCurrentDate(): String {
        return try {
            val now = kotlinxClock.System.now()
            val zone = TimeZone.currentSystemDefault()
            now.toLocalDateTime(zone).date.toString()
        } catch (e: Exception) {
            "2024-05-20"
        }
    }
}
