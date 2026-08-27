package com.sigeschool.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.*
import com.sigeschool.domain.model.AttendanceStatus
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock as kotlinxClock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

import com.sigeschool.domain.model.UserRole

data class DashboardUiState(
    val role: UserRole = UserRole.INVITADO,
    val userName: String = "",
    val totalStudents: Int = 0,
    val todayAttendancePercentage: Int = 0,
    val generalAverageGrade: Double = 0.0,
    val gradeDistribution: Map<String, Int> = emptyMap(),
    val subjectAverages: Map<String, Double> = emptyMap(),
    val periodAverages: Map<String, Double> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class DashboardViewModel(
    private val studentRepository: StudentRepository,
    private val attendanceRepository: AttendanceRepository,
    private val gradeRepository: GradeRepository,
    private val authRepository: AuthRepository,
    private val taskRepository: TaskRepository,
    private val examRepository: ExamRepository,
    private val announcementRepository: AnnouncementRepository,
    private val salaryRepository: SalaryRepository,
    private val pucRepository: PucRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing = _isSyncing.asStateFlow()

    init {
        loadDashboardData()
    }

    fun syncData() {
        viewModelScope.launch {
            _isSyncing.value = true
            try {
                val institutionId = authRepository.getCurrentInstitutionId()
                if (institutionId.isNullOrEmpty()) {
                    _uiState.update { it.copy(error = "No se encontró la institución activa") }
                    return@launch
                }

                withContext(Dispatchers.Default) {
                    studentRepository.syncWithSupabase(institutionId)
                    attendanceRepository.syncWithCloud()
                    gradeRepository.syncWithCloud()

                    // Nuevos módulos
                    taskRepository.syncTasks(institutionId)
                    examRepository.syncExams(institutionId)
                    announcementRepository.syncAnnouncements(institutionId)
                    salaryRepository.syncSalaries(institutionId)
                    pucRepository.seedInitialPuc(institutionId)
                    pucRepository.syncEntries(institutionId)
                }

                loadDashboardData()
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update { it.copy(error = "Error al sincronizar: ${e.message}") }
            } finally {
                _isSyncing.value = false
            }
        }
    }

    // FIX: `studentRepository.getAllStudents()` y
    // `attendanceRepository.getAttendanceByDate()` devuelven
    // Flow<Resource<List<T>>>, no Flow<List<T>>. El `combine` original
    // trataba `students`/`attendance` como listas directamente
    // (`students.size`, `attendance.count{...}`), lo cual no compila.
    // Se corrige extrayendo `.data` de cada Resource. También se exige
    // institutionId antes de suscribirse (antes traía estudiantes de
    // cualquier institución).
    @OptIn(kotlin.time.ExperimentalTime::class)
    private fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val user = authRepository.getCurrentUser()
            val role = user?.userMetadata?.get("role")?.toString()?.let { UserRole.fromString(it) } ?: UserRole.RECTOR // Fallback for testing
            val userName = user?.userMetadata?.get("full_name")?.toString() ?: user?.email ?: "Usuario"

            _uiState.update { it.copy(role = role, userName = userName) }

            val institutionId = authRepository.getCurrentInstitutionId()
            if (institutionId.isNullOrEmpty()) {
                _uiState.update { it.copy(isLoading = false, error = "No se encontró la institución activa") }
                return@launch
            }

            // 1. Students and Attendance
            val studentsFlow = studentRepository.getAllStudents(institutionId)
            val now = kotlinxClock.System.now()
            val today = now.toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
            val attendanceFlow = attendanceRepository.getAttendanceByDate(today)

            combine(studentsFlow, attendanceFlow) { studentsRes, attendanceRes ->
                val students = studentsRes.data ?: emptyList()
                val attendance = attendanceRes.data ?: emptyList()
                val total = students.size
                val attended = attendance.count { it.estado == AttendanceStatus.PRESENT || it.estado == AttendanceStatus.LATE || it.estado == AttendanceStatus.PRESENTE || it.estado == AttendanceStatus.TARDE }
                val percentage = if (total > 0) (attended * 100) / total else 0

                Triple(total, percentage, students)
            }.collect { (total, percentage, students) ->
                _uiState.update { it.copy(
                    totalStudents = total,
                    todayAttendancePercentage = percentage,
                    isLoading = false
                ) }
                if (students.isNotEmpty()) {
                    calculateDetailedStats(students)
                }
            }
        }
    }

    private suspend fun calculateDetailedStats(students: List<com.sigeschool.domain.model.Student>) {
        if (students.isEmpty()) return

        val allGrades = mutableListOf<Double>()
        val distribution = mutableMapOf("0-10" to 0, "11-15" to 0, "16-20" to 0)
        val subjectScores = mutableMapOf<String, MutableList<Double>>()
        val periodScores = mutableMapOf<String, MutableList<Double>>()

        for (student in students) {
            val gradesRes = gradeRepository.getGradesByStudent(student.id).firstOrNull()
            val grades = gradesRes?.data ?: emptyList()
            if (grades.isNotEmpty()) {
                val studentAvg = grades.map { it.score }.average()
                allGrades.add(studentAvg)

                when {
                    studentAvg <= 10.5 -> distribution["0-10"] = distribution["0-10"]!! + 1
                    studentAvg <= 15.5 -> distribution["11-15"] = distribution["11-15"]!! + 1
                    else -> distribution["16-20"] = distribution["16-20"]!! + 1
                }

                // Agrupar por materia y periodo
                grades.forEach { grade ->
                    subjectScores.getOrPut(grade.subject) { mutableListOf() }.add(grade.score)
                    if (grade.period.isNotEmpty()) {
                        periodScores.getOrPut(grade.period) { mutableListOf() }.add(grade.score)
                    }
                }
            }
        }

        val subjectAverages = subjectScores.mapValues { it.value.average() }
        val periodAverages = periodScores.mapValues { it.value.average() }

        _uiState.update { it.copy(
            generalAverageGrade = if (allGrades.isNotEmpty()) allGrades.average() else 0.0,
            gradeDistribution = distribution,
            subjectAverages = subjectAverages,
            periodAverages = periodAverages
        ) }
    }
}
