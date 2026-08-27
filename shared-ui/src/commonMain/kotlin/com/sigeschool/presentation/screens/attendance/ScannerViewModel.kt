package com.sigeschool.presentation.screens.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.repository.AttendanceRepository
import com.sigeschool.domain.model.AttendanceScan
import com.sigeschool.domain.model.ScanType
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class ScannerViewModel(
    private val attendanceRepository: AttendanceRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<ScannerUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var isProcessing = false

    fun onQrScanned(content: String) {
        if (isProcessing) return
        isProcessing = true

        viewModelScope.launch {
            val instId = sessionManager.getCurrentInstitutionId() ?: ""
            val scan = AttendanceScan(
                type = ScanType.STUDENT_ENTRY,
                identifier = content,
                timestamp = Clock.System.now().toString()
            )
            
            val result = attendanceRepository.registerScan(scan, instId)
            when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(ScannerUiEvent.ShowSuccess("Registro exitoso: $content"))
                    kotlinx.coroutines.delay(2000)
                }
                is Resource.Error -> {
                    _eventFlow.emit(ScannerUiEvent.ShowError(result.message ?: "Error al registrar"))
                    kotlinx.coroutines.delay(2000)
                }
                is Resource.Loading -> {}
            }
            isProcessing = false
        }
    }

    sealed class ScannerUiEvent {
        data class ShowSuccess(val message: String) : ScannerUiEvent()
        data class ShowError(val message: String) : ScannerUiEvent()
    }
}
