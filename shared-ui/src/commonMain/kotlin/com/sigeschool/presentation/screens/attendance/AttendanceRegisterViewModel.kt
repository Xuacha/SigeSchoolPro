package com.sigeschool.presentation.screens.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AttendanceRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.model.Attendance
import com.sigeschool.domain.model.AttendanceStatus
import com.sigeschool.domain.model.Class
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AttendanceRegisterViewModel(
    private val attendanceRepository: AttendanceRepository,
    private val studentRepository: StudentRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _attendanceMap = MutableStateFlow<Map<String, AttendanceStatus>>(emptyMap())
    val attendanceMap: StateFlow<Map<String, AttendanceStatus>> = _attendanceMap.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadStudents(classItem: Class) {
        viewModelScope.launch {
            _isLoading.value = true
            val instId = sessionManager.getCurrentInstitutionId() ?: ""
            studentRepository.getAllStudents(instId).collect { resource ->
                if (resource is Resource.Success) {
                    val allStudents = resource.data ?: emptyList()
                    // Filtrar por grado y sección que coincidan con el nombre de la clase
                    val filtered = allStudents.filter { student ->
                        val className = classItem.name.uppercase()
                        student.grado.uppercase() in className && student.seccion.uppercase() in className
                    }
                    _students.value = filtered
                    _attendanceMap.value = filtered.associate { it.id to AttendanceStatus.PRESENT }
                }
                _isLoading.value = false
            }
        }
    }

    fun updateStatus(studentId: String, status: AttendanceStatus) {
        val current = _attendanceMap.value.toMutableMap()
        current[studentId] = status
        _attendanceMap.value = current
    }

    fun saveAttendance(classId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val instId = sessionManager.getCurrentInstitutionId() ?: ""
            val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
            
            val list = _attendanceMap.value.map { (studentId, status) ->
                Attendance(
                    studentId = studentId,
                    fecha = today,
                    estado = status,
                    institutionId = instId
                )
            }
            
            attendanceRepository.saveAttendance(list, instId)
            _isLoading.value = false
        }
    }
}
