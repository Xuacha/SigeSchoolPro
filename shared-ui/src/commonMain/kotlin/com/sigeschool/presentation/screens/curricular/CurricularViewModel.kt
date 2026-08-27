package com.sigeschool.presentation.screens.curricular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.domain.model.DocumentBlock
import com.sigeschool.domain.model.DocumentType
import com.sigeschool.domain.model.InstitutionalDocument
import com.sigeschool.data.repository.AuthRepository
import com.sigeschool.domain.repository.CurricularRepository
import com.sigeschool.domain.model.UserRole
import com.sigeschool.domain.util.PermissionService
import kotlinx.datetime.Clock
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class CurricularUiState(
    val selectedType: DocumentType = DocumentType.PEI,
    val documents: List<InstitutionalDocument> = emptyList(),
    val selectedDocument: InstitutionalDocument? = null,
    val blocks: List<DocumentBlock> = emptyList(),
    val userRole: UserRole = UserRole.INVITADO,
    val isLoading: Boolean = false,
    val error: String? = null
)

class CurricularViewModel(
    private val repository: CurricularRepository,
    private val authRepository: AuthRepository,
    private val permissionService: PermissionService
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurricularUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUserRole()
        loadDocuments(DocumentType.PEI)
    }

    private fun loadUserRole() {
        viewModelScope.launch {
            val user = authRepository.getCurrentUser()
            val role = user?.userMetadata?.get("role")?.toString()?.let { UserRole.fromString(it) } ?: UserRole.DOCENTE
            _uiState.update { it.copy(userRole = role) }
        }
    }

    fun selectType(type: DocumentType) {
        _uiState.update { it.copy(selectedType = type, selectedDocument = null, blocks = emptyList()) }
        loadDocuments(type)
    }

    private fun loadDocuments(type: DocumentType) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getDocumentsByType(type).collect { docs ->
                _uiState.update { it.copy(documents = docs, isLoading = false) }
            }
        }
    }

    fun selectDocument(document: InstitutionalDocument) {
        _uiState.update { it.copy(selectedDocument = document) }
        loadBlocks(document.id)
    }

    private fun loadBlocks(documentId: String) {
        viewModelScope.launch {
            repository.getBlocksByDocumentId(documentId).collect { blocks ->
                _uiState.update { it.copy(blocks = blocks) }
            }
        }
    }

    fun updateBlock(block: DocumentBlock) {
        val role = _uiState.value.userRole
        val type = _uiState.value.selectedType
        
        val canEdit = when(type) {
            DocumentType.PEI -> role.canManagePEI()
            DocumentType.PLAN_ESTUDIOS -> role.canManagePlanEstudios()
            DocumentType.PLAN_AULA -> role.canManagePlanAula()
        }

        if (!canEdit) {
            _uiState.update { it.copy(error = "Acceso Denegado: Su rol no permite editar este tipo de documento.") }
            return
        }

        viewModelScope.launch {
            val user = authRepository.getCurrentUser()
            repository.updateBlock(block, user?.id ?: "unknown")
        }
    }

    fun processDocumentContent(title: String, content: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val type = when {
                    content.contains("Misión", ignoreCase = true) || content.contains("PEI") -> DocumentType.PEI
                    content.contains("Intensidad Horaria", ignoreCase = true) -> DocumentType.PLAN_ESTUDIOS
                    else -> DocumentType.PLAN_AULA
                }

                val documentId = "doc_${Clock.System.now().toEpochMilliseconds()}"
                repository.uploadDocument(title, type, content.encodeToByteArray(), emptyMap())
                
                val sections = content.split("\n\n").filter { it.isNotBlank() }
                sections.forEachIndexed { index, text ->
                    val blockTitle = when {
                        text.contains("Misión", true) -> "MISIÓN"
                        text.contains("Visión", true) -> "VISIÓN"
                        text.contains("Objetivo", true) -> "OBJETIVOS"
                        else -> "CONTENIDO GENERAL"
                    }
                    repository.updateBlock(
                        DocumentBlock(
                            id = "${documentId}_$index",
                            documentId = documentId,
                            title = blockTitle,
                            contentHtml = text.trim(),
                            order = index,
                            updatedAt = Clock.System.now().toEpochMilliseconds(),
                            modifiedBy = "AI_SYSTEM"
                        ),
                        "AI_SYSTEM"
                    )
                }
                
                selectType(type)
                _uiState.update { it.copy(isLoading = false, error = "¡IA: Documento procesado con éxito!") }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error en IA: ${e.message}") }
            }
        }
    }

    fun canUploadDocument(): Boolean {
        // En el futuro usaremos el objeto Role real, por ahora usamos el level de UserRole como fallback
        return uiState.value.userRole.level >= 2
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
