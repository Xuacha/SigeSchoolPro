package com.sigeschool.data.repository

import com.sigeschool.data.datasource.CurricularLocalDataSource
import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.model.DocumentBlock
import com.sigeschool.domain.model.DocumentType
import com.sigeschool.domain.model.InstitutionalDocument
import com.sigeschool.domain.repository.CurricularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class CurricularRepositoryImpl(
    private val localDataSource: CurricularLocalDataSource,
    private val aiService: com.sigeschool.services.ai.AiCurricularService,
    private val auditRepository: AuditRepository
) : CurricularRepository {
    override fun getDocumentsByType(type: DocumentType): Flow<List<InstitutionalDocument>> {
        return localDataSource.getDocumentsByType(type)
    }

    override fun getBlocksByDocumentId(documentId: String): Flow<List<DocumentBlock>> {
        return localDataSource.getBlocksByDocumentId(documentId)
    }

    override suspend fun uploadDocument(
        title: String,
        type: DocumentType,
        fileBytes: ByteArray,
        metadata: Map<String, String?>
    ) {
        val documentId = "doc_${Clock.System.now().toEpochMilliseconds()}"
        val doc = InstitutionalDocument(
            id = documentId,
            title = title,
            type = type,
            institutionId = metadata["institutionId"] ?: "",
            grade = metadata["grade"],
            subject = metadata["subject"],
            teacherId = metadata["teacherId"],
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds()
        )
        localDataSource.insertDocument(doc)
        
        // Intentar decodificar el texto de forma segura
        val textToAnalyze = try {
            fileBytes.decodeToString()
        } catch (e: Exception) {
            "Error al decodificar contenido: ${e.message}"
        }

        val aiResult = aiService.analyzeText(textToAnalyze)
        
        val blocks = when (aiResult) {
            is com.sigeschool.services.ai.AiResult.Success -> {
                aiResult.data.blocks.mapIndexed { index, proposal ->
                    DocumentBlock(
                        id = "${documentId}_$index",
                        documentId = documentId,
                        order = index + 1,
                        title = proposal.title,
                        contentHtml = "<p>${proposal.content}</p>",
                        updatedAt = Clock.System.now().toEpochMilliseconds()
                    )
                }
            }
            else -> emptyList()
        }
        
        if (blocks.isEmpty()) {
            // Fallback si la IA no devuelve nada
            localDataSource.insertBlocks(listOf(
                DocumentBlock(
                    id = "${documentId}_1",
                    documentId = documentId,
                    order = 1,
                    title = "Documento Importado",
                    contentHtml = "<p>El documento ha sido cargado pero no se detectaron bloques automáticos.</p>"
                )
            ))
        } else {
            localDataSource.insertBlocks(blocks)
        }

        auditRepository.log(
            action = "UPLOAD_CURRICULAR_DOC",
            resource = "curricular/docs/$documentId",
            payload = mapOf("title" to title, "type" to type.name)
        )
    }

    override suspend fun updateBlock(block: DocumentBlock, userId: String) {
        val updatedBlock = block.copy(
            updatedAt = Clock.System.now().toEpochMilliseconds(),
            modifiedBy = userId
        )
        localDataSource.insertBlocks(listOf(updatedBlock))

        auditRepository.log(
            action = "UPDATE_CURRICULAR_BLOCK",
            resource = "curricular/blocks/${block.id}",
            payload = mapOf("documentId" to block.documentId, "userId" to userId)
        )
    }

    override suspend fun getBlockHistory(blockId: String): Flow<List<DocumentBlock>> {
        return localDataSource.getBlockHistory(blockId)
    }
}
