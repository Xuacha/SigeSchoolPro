package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.AuditCategory
import com.example.model.AuditIssue
import com.example.model.AuditReport
import com.example.model.DeviceCompatRule
import com.example.repository.AuditRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuditUiState(
    val report: AuditReport,
    val selectedCategory: AuditCategory? = null,
    val searchQuery: String = "",
    val isScanning: Boolean = false,
    val scanProgress: Int = 100,
    val inputUrl: String = "",
    val selectedIssue: AuditIssue? = null,
    val userMessage: String? = null,
    val compatRules: List<DeviceCompatRule> = emptyList()
)

class AuditViewModel(
    private val repository: AuditRepository = AuditRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AuditUiState(
            report = repository.getInitialReport(),
            inputUrl = repository.defaultDriveUrl(),
            compatRules = repository.getDeviceCompatibilityRules()
        )
    )
    val uiState: StateFlow<AuditUiState> = _uiState.asStateFlow()

    fun onUrlInputChanged(newUrl: String) {
        _uiState.value = _uiState.value.copy(inputUrl = newUrl)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun selectCategory(category: AuditCategory?) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun selectIssue(issue: AuditIssue?) {
        _uiState.value = _uiState.value.copy(selectedIssue = issue)
    }

    fun toggleFixIssue(issueId: String) {
        val currentIssues = _uiState.value.report.issues
        val updatedIssues = currentIssues.map { issue ->
            if (issue.id == issueId) {
                val newStatus = !issue.isFixed
                issue.copy(
                    isFixed = newStatus,
                    fixedTimestamp = if (newStatus) "Subsanado hace un momento" else null
                )
            } else issue
        }

        val updatedReport = repository.calculateReportFromIssues(updatedIssues, _uiState.value.inputUrl)
        val targetIssue = updatedIssues.find { it.id == issueId }
        val msg = if (targetIssue?.isFixed == true) "✅ Subsanación aplicada para '${targetIssue.title}'" else "ℹ️ Subsanación revertida para '${targetIssue?.title}'"

        _uiState.value = _uiState.value.copy(
            report = updatedReport,
            selectedIssue = _uiState.value.selectedIssue?.let { current ->
                updatedIssues.find { it.id == current.id }
            },
            userMessage = msg
        )
    }

    fun fixAllIssues() {
        val currentIssues = _uiState.value.report.issues
        val updatedIssues = currentIssues.map { issue ->
            issue.copy(isFixed = true, fixedTimestamp = "Subsanado automáticamente")
        }

        val updatedReport = repository.calculateReportFromIssues(updatedIssues, _uiState.value.inputUrl)
        _uiState.value = _uiState.value.copy(
            report = updatedReport,
            selectedIssue = null,
            userMessage = "🚀 ¡Todas las vulnerabilidades y fallos de compilación han sido subsanados (Puntuación 100/100)!"
        )
    }

    fun resetAllFixes() {
        val currentIssues = _uiState.value.report.issues
        val updatedIssues = currentIssues.map { issue ->
            issue.copy(isFixed = false, fixedTimestamp = null)
        }

        val updatedReport = repository.calculateReportFromIssues(updatedIssues, _uiState.value.inputUrl)
        _uiState.value = _uiState.value.copy(
            report = updatedReport,
            userMessage = "Se ha restablecido el estado inicial de la auditoría."
        )
    }

    fun dismissUserMessage() {
        _uiState.value = _uiState.value.copy(userMessage = null)
    }

    fun startLiveAuditScan(customUrl: String? = null) {
        val targetUrl = customUrl ?: _uiState.value.inputUrl
        if (targetUrl.isBlank()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isScanning = true,
                scanProgress = 0,
                userMessage = "Iniciando análisis estático y dinámico..."
            )

            repository.runLiveScan(targetUrl).collect { progress ->
                _uiState.value = _uiState.value.copy(scanProgress = progress)
            }

            // Fresh report generated after scan
            val freshReport = repository.getInitialReport().copy(
                urlScanned = targetUrl,
                timestamp = "Ahora mismo"
            )

            _uiState.value = _uiState.value.copy(
                report = freshReport,
                isScanning = false,
                scanProgress = 100,
                userMessage = "¡Auditoría completada exitosamente!"
            )
        }
    }
}
