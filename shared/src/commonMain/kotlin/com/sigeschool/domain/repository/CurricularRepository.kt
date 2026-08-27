package com.sigeschool.domain.repository

import com.sigeschool.domain.model.DocumentBlock
import com.sigeschool.domain.model.DocumentType
import com.sigeschool.domain.model.InstitutionalDocument
import kotlinx.coroutines.flow.Flow

interface CurricularRepository {
    fun getDocumentsByType(type: DocumentType): Flow<List<InstitutionalDocument>>
    fun getBlocksByDocumentId(documentId: String): Flow<List<DocumentBlock>>
    suspend fun uploadDocument(title: String, type: DocumentType, fileBytes: ByteArray, metadata: Map<String, String?>)
    suspend fun updateBlock(block: DocumentBlock, userId: String)
    suspend fun getBlockHistory(blockId: String): Flow<List<DocumentBlock>>
}
