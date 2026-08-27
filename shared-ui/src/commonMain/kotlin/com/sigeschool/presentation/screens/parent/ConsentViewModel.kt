package com.sigeschool.presentation.screens.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Consent
import com.sigeschool.domain.model.PrivacyPolicy
import com.sigeschool.domain.repository.ConsentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

sealed class ConsentUiState {
    object Loading : ConsentUiState()
    data class Success(val policy: PrivacyPolicy) : ConsentUiState()
    object Error : ConsentUiState()
    object Completed : ConsentUiState()
}

class ConsentViewModel(
    private val consentRepository: ConsentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ConsentUiState>(ConsentUiState.Loading)
    val uiState: StateFlow<ConsentUiState> = _uiState.asStateFlow()

    init {
        loadActivePolicy()
    }

    private fun loadActivePolicy() {
        viewModelScope.launch {
            val policy = consentRepository.getActivePolicy()
            if (policy != null) {
                _uiState.value = ConsentUiState.Success(policy)
            } else {
                _uiState.value = ConsentUiState.Error
            }
        }
    }

    fun submitConsent(
        studentId: String,
        nombre: String,
        dni: String,
        parentesco: String,
        email: String,
        telefono: String,
        policyId: String,
        signaturePoints: List<androidx.compose.ui.geometry.Offset>
    ) {
        viewModelScope.launch {
            val signatureHash = signaturePoints.hashCode().toString() // Implementación real usaría un hash criptográfico de los puntos
            val consent = Consent(
                id = "", // Generado por BD
                studentId = studentId,
                acudienteNombre = nombre,
                acudienteDni = dni,
                acudienteParentesco = parentesco,
                acudienteEmail = email,
                acudienteTelefono = telefono,
                politicaId = policyId,
                fechaAceptacion = Clock.System.now().toEpochMilliseconds(),
                deviceInfo = "Mobile App",
                hashFirmaDigital = signatureHash,
                granularConsent = mapOf("marketing" to false, "shared_third_party" to true)
            )
            
            consentRepository.registerConsent(consent)
                .onSuccess { _uiState.value = ConsentUiState.Completed }
                .onFailure { _uiState.value = ConsentUiState.Error }
        }
    }
}
