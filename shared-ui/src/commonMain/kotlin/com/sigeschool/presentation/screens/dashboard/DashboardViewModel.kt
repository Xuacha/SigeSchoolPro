package com.sigeschool.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.*
import com.sigeschool.domain.model.AttendanceStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock as kotlinxClock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

import com.sigeschool.domain.model.UserRole
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState

data class DashboardUiState(
    val role: UserRole = UserRole.INVITADO,
    val userName: String = "",
    val totalStudents: Int = 0,
    val todayAttendancePercentage: Int = 0,
    val generalAverageGrade: Double = 0.0,
    val gradeDistribution: Map<String, Int> = emptyMap(),
    val subjectAverages: Map<String, Double> = emptyMap(),
    val periodAverages: Map<String, Double> = emptyMap(),
    val totalRevenue: Double = 0.0,
    val totalPending: Double = 0.0,
    val morosidadRate: Double = 0.0,
    val riskSummary: Map<String, Int> = emptyMap(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModel(
    private val studentRepository: StudentRepository,
    private val attendanceRepository: AttendanceRepository,
    private val gradeRepository: GradeRepository,
    private val taskRepository: TaskRepository,
    private val examRepository: ExamRepository,
    private val announcementRepository: AnnouncementRepository,
    private val salaryRepository: SalaryRepository,
    private val pucRepository: PucRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing = _isSyncing.asStateFlow()

    init {
        observeDashboardData()
    }

    private fun observeDashboardData() {
        sessionManager.sessionState
            .onEach { state ->
                if (state is SessionState.LoggedIn) {
                    val role = state.user.userMetadata?.get("role")?.toString()?.let { UserRole.fromString(it) } ?: UserRole.RECTOR
                    val userName = state.user.userMetadata?.get("full_name")?.toString() ?: state.user.email ?: "Usuario"
                    _uiState.update { it.copy(role = role, userName = userName) }
                }
            }
            .flatMapLatest { state ->
                val instId = (state as? SessionState.LoggedIn)?.institutionId
                if (instId != null) {
                    _uiState.update { it.copy(isLoading = true) }
                    val now = kotlinxClock.System.now()
                    val today = now.toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
                    
                    combine(
                        studentRepository.getAllStudents(instId),
                        attendanceRepository.getAttendanceByDate(today, instId)
                    ) { studentsRes, attendanceRes ->
                        Triple(studentsRes, attendanceRes, instId)
                    }
                } else {
                    _uiState.update { it.copy(isLoading = false, totalStudents = 0, todayAttendancePercentage = 0) }
                    flowOf(null)
                }
            }
            .onEach { data ->
                if (data == null) return@onEach
                val (studentsRes, attendanceRes, instId) = data
                
                val students = studentsRes.data ?: emptyList()
                val attendance = attendanceRes.data ?: emptyList()
                
                val total = students.size
                val attended = attendance.count { 
                    it.estado == AttendanceStatus.PRESENT || 
                    it.estado == AttendanceStatus.LATE || 
                    it.estado == AttendanceStatus.PRESENTE || 
                    it.estado == AttendanceStatus.TARDE 
                }
                val percentage = if (total > 0) (attended * 100) / total else 0
                
                _uiState.update { it.copy(
                    totalStudents = total,
                    todayAttendancePercentage = percentage,
                    isLoading = false
                ) }

                if (students.isNotEmpty()) {
                    calculateDetailedStats(students, instId)
                    fetchFinancialStats(instId)
                }
            }
            .catch { e -> _uiState.update { it.copy(isLoading = false, errorMessage = e.message) } }
            .launchIn(viewModelScope)
    }

    private fun fetchFinancialStats(institutionId: String) {
        viewModelScope.launch {
            val revenue = 0.0
            val pending = 0.0
            val morosidad = if (revenue + pending > 0) pending / (revenue + pending) else 0.0
            
            val riskSummary = mapOf(
                "CRITICAL" to 0,
                "HIGH" to 0,
                "MEDIUM" to 0,
                "LOW" to 0
            )

            _uiState.update { it.copy(
                totalRevenue = revenue,
                totalPending = pending,
                morosidadRate = morosidad,
                riskSummary = riskSummary
            ) }
        }
    }

    private suspend fun calculateDetailedStats(students: List<com.sigeschool.domain.model.Student>, institutionId: String) {
        val allGrades = mutableListOf<Double>()
        val distribution = mutableMapOf("0-10" to 0, "11-15" to 0, "16-20" to 0)
        val subjectScores = mutableMapOf<String, MutableList<Double>>()
        val periodScores = mutableMapOf<String, MutableList<Double>>()

        withContext(Dispatchers.Default) {
            for (student in students) {
                val gradesRes = gradeRepository.getGradesByStudent(student.id, institutionId).firstOrNull()
                val grades = gradesRes?.data ?: emptyList()
                if (grades.isNotEmpty()) {
                    val studentAvg = grades.map { it.score }.average()
                    allGrades.add(studentAvg)
                    
                    when {
                        studentAvg <= 10.5 -> distribution["0-10"] = (distribution["0-10"] ?: 0) + 1
                        studentAvg <= 15.5 -> distribution["11-15"] = (distribution["11-15"] ?: 0) + 1
                        else -> distribution["16-20"] = (distribution["16-20"] ?: 0) + 1
                    }

                    grades.forEach { grade ->
                        subjectScores.getOrPut(grade.subjectId) { mutableListOf() }.add(grade.score)
                        if (grade.periodId.isNotEmpty()) {
                            periodScores.getOrPut(grade.periodId) { mutableListOf() }.add(grade.score)
                        }
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
