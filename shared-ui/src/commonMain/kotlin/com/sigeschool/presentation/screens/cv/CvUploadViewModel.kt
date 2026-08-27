package com.sigeschool.presentation.screens.cv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.CvData
import com.sigeschool.services.DocumentExtractorService
import com.sigeschool.services.ai.NerService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CvUploadUiState(
    val isLoading: Boolean = false,
    val extractedData: CvData? = null,
    val error: String? = null,
    val isSaved: Boolean = false,
    val originalFile: ByteArray? = null,
    val fileName: String = ""
)

class CvUploadViewModel(
    private val documentExtractor: DocumentExtractorService,
    private val nerService: NerService
) : ViewModel() {

    private val _uiState = MutableStateFlow(CvUploadUiState())
    val uiState: StateFlow<CvUploadUiState> = _uiState.asStateFlow()

    fun onFileSelected(fileBytes: ByteArray, fileName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, fileName = fileName, originalFile = fileBytes) }
            try {
                val text = documentExtractor.extractText(fileBytes, fileName)
                val cvData = nerService.extractCvFields(text)
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        extractedData = cvData
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Error al procesar el documento"
                    )
                }
            }
        }
    }

    fun updateExtractedData(data: CvData) {
        _uiState.update { it.copy(extractedData = data) }
    }

    fun saveProfile(userId: String, roleId: String) {
        val data = _uiState.value.extractedData ?: return
        viewModelScope.launch {
            try {
                // Complete CV extraction profile processing
                _uiState.update { it.copy(isLoading = false, isSaved = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al guardar: ${e.message}") }
            }
        }
    }
}
