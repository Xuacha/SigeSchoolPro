package com.sigeschool.presentation.screens.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.Consent
import com.sigeschool.domain.model.PrivacyPolicy
import com.sigeschool.domain.repository.ConsentRepository
import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.util.randomUUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock

data class ConsentUiState(
    val activePolicy: PrivacyPolicy? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isConsentRegistered: Boolean = false,
    val granularConsent: Map<String, Boolean> = mapOf(
        "academico" to true,
        "financiero" to false,
        "comunicaciones" to false,
        "uso_imagen" to false
    )
)

class ConsentViewModel(
    private val consentRepository: ConsentRepository,
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConsentUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadActivePolicy()
    }

    private fun loadActivePolicy() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val policy = consentRepository.getActivePolicy()
            if (policy == null) {
                val defaultPolicy = PrivacyPolicy(
                    id = "policy-2024-v1",
                    version = 1,
                    fechaPublicacion = Clock.System.now().toEpochMilliseconds(),
                    contenidoHash = "SHA256-SIGESCHOOL-POLICY-V1",
                    contenidoTexto = """
                        POLÍTICA DE TRATAMIENTO DE DATOS PERSONALES - SIGESCHOOL
                        
                        En cumplimiento de la Ley 1581 de 2012 y el Decreto 1377 de 2013, la Institución Educativa informa:
                        
                        1. FINALIDADES: Los datos de menores serán tratados para:
                           - Gestión académica y boletines.
                           - Procesos de facturación y cobranza.
                           - Comunicaciones de seguridad y emergencias.
                           - Actividades extracurriculares (previa autorización).
                        
                        2. DERECHOS DEL TITULAR: Conocer, actualizar y rectificar sus datos personales; solicitar prueba de la autorización; ser informado del uso dado; presentar quejas ante la SIC; y revocar la autorización.
                        
                        3. DATOS SENSIBLES: La captura de fotos y datos biométricos es facultativa salvo para fines de identificación institucional.
                    """.trimIndent(),
                    esActiva = true
                )
                consentRepository.insertPolicy(defaultPolicy)
                _uiState.update { it.copy(activePolicy = defaultPolicy, isLoading = false) }
            } else {
                _uiState.update { it.copy(activePolicy = policy, isLoading = false) }
            }
        }
    }

    fun updateGranularConsent(key: String, value: Boolean) {
        _uiState.update { state ->
            val newMap = state.granularConsent.toMutableMap()
            newMap[key] = value
            state.copy(granularConsent = newMap)
        }
    }

    suspend fun registerConsent(
        studentId: String,
        acudienteNombre: String,
        acudienteDni: String,
        acudienteParentesco: String,
        acudienteEmail: String,
        acudienteTelefono: String,
        deviceInfo: String
    ): Result<Unit> {
        val policy = _uiState.value.activePolicy ?: return Result.failure(Exception("No hay política activa"))
        
        val consent = Consent(
            id = randomUUID(),
            studentId = studentId,
            acudienteNombre = acudienteNombre,
            acudienteDni = acudienteDni,
            acudienteParentesco = acudienteParentesco,
            acudienteEmail = acudienteEmail,
            acudienteTelefono = acudienteTelefono,
            politicaId = policy.id,
            fechaAceptacion = Clock.System.now().toEpochMilliseconds(),
            deviceInfo = deviceInfo,
            hashFirmaDigital = "SIG-${studentId}-${Clock.System.now().toEpochMilliseconds()}",
            granularConsent = _uiState.value.granularConsent
        )

        return consentRepository.registerConsent(consent).also { result ->
            if (result.isSuccess) {
                _uiState.update { it.copy(isConsentRegistered = true) }
            }
        }
    }

    suspend fun getAuditReport(studentId: String, studentName: String): ByteArray {
        return try {
            val history = consentRepository.getConsentHistory(studentId).first()
            com.sigeschool.util.PdfPlatformGenerator.generateConsentAuditReport(history, studentName)
        } catch (e: Exception) {
            com.sigeschool.util.PdfPlatformGenerator.generateConsentAuditReport(emptyList(), studentName)
        }
    }
}
