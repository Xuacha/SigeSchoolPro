package com.sigeschool.presentation.viewmodel.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.*
import com.sigeschool.domain.repository.NotificationRepository
import com.sigeschool.presentation.util.DownloadHelper
import kotlinx.datetime.Clock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LogsMonitorViewModel(
    private val logRepository: NotificationRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LogsMonitorUiState())
    val uiState: StateFlow<LogsMonitorUiState> = _uiState.asStateFlow()

    private val _metrics = MutableStateFlow<MetricsSummary?>(null)
    val metrics: StateFlow<MetricsSummary?> = _metrics.asStateFlow()

    init {
        loadLogs(_uiState.value.filtrosActuales)
        loadMetrics(_uiState.value.filtrosActuales)
    }

    fun loadLogs(filtros: FiltrosLogs) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = logRepository.getLogsWithFilters(filtros)
            result.onSuccess { pagedResult ->
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        logs = pagedResult.data,
                        totalRegistros = pagedResult.total,
                        paginaActual = filtros.pagina,
                        registrosPorPagina = filtros.registrosPorPagina,
                        filtrosActuales = filtros
                    )
                }
            }.onFailure { error ->
                _uiState.update { 
                    it.copy(isLoading = false, error = error.message) 
                }
            }
        }
    }

    fun loadMetrics(filtros: FiltrosLogs) {
        viewModelScope.launch {
            val result = logRepository.getMetricsSummary(filtros)
            result.onSuccess { metrics ->
                _metrics.value = metrics
            }
        }
    }

    fun reenviarNotificacion(idNotificacion: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = logRepository.reenviarNotificacion(idNotificacion)
            result.onSuccess {
                loadLogs(_uiState.value.filtrosActuales)
                loadMetrics(_uiState.value.filtrosActuales)
            }.onFailure { error ->
                _uiState.update { 
                    it.copy(isLoading = false, error = error.message) 
                }
            }
        }
    }

    fun exportarLogs(formato: String) {
        viewModelScope.launch {
            val filtros = _uiState.value.filtrosActuales
            _uiState.update { it.copy(isExporting = true) }
            
            val result = logRepository.exportarLogs(filtros, formato)
            result.onSuccess { bytes ->
                val extension = if (formato.uppercase() == "EXCEL") "xlsx" else "pdf"
                val mimeType = if (formato.uppercase() == "EXCEL") 
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" 
                else 
                    "application/pdf"
                
                val fileName = "logs_${Clock.System.now().toEpochMilliseconds()}.$extension"
                
                DownloadHelper.downloadFile(bytes, fileName, mimeType).onSuccess {
                    _uiState.update { 
                        it.copy(isExporting = false, mensajeExito = "Archivo exportado correctamente") 
                    }
                }.onFailure { error ->
                    _uiState.update { 
                        it.copy(isExporting = false, error = "Error al descargar: ${error.message}") 
                    }
                }
            }.onFailure { error ->
                _uiState.update { 
                    it.copy(isExporting = false, error = "Error al exportar: ${error.message}") 
                }
            }
        }
    }

    fun limpiarMensaje() {
        _uiState.update { it.copy(mensajeExito = null) }
    }

    fun limpiarError() {
        _uiState.update { it.copy(error = null) }
    }

    fun actualizarFiltros(filtros: FiltrosLogs) {
        loadLogs(filtros)
        loadMetrics(filtros)
    }

    fun cambiarPagina(pagina: Int) {
        val filtros = _uiState.value.filtrosActuales.copy(pagina = pagina)
        actualizarFiltros(filtros)
    }

    fun verDetalle(idLog: String) {
        viewModelScope.launch {
            val result = logRepository.getLogDetalle(idLog)
            result.onSuccess { detalle ->
                _uiState.update { it.copy(detalleSeleccionado = detalle) }
            }
        }
    }

    fun cerrarDetalle() {
        _uiState.update { it.copy(detalleSeleccionado = null) }
    }
}

data class LogsMonitorUiState(
    val isLoading: Boolean = false,
    val isExporting: Boolean = false,
    val logs: List<LogNotificacion> = emptyList(),
    val totalRegistros: Int = 0,
    val paginaActual: Int = 1,
    val registrosPorPagina: Int = 50,
    val filtrosActuales: FiltrosLogs = FiltrosLogs(),
    val detalleSeleccionado: LogDetalle? = null,
    val mensajeExito: String? = null,
    val error: String? = null
)
