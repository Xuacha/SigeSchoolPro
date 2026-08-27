package com.sigeschool.data.datasource

import com.sigeschool.domain.model.DocumentBlock
import com.sigeschool.domain.model.DocumentType
import com.sigeschool.domain.model.InstitutionalDocument
import kotlinx.coroutines.flow.Flow

interface CurricularLocalDataSource {
    fun getDocumentsByType(type: DocumentType): Flow<List<InstitutionalDocument>>
    fun getBlocksByDocumentId(documentId: String): Flow<List<DocumentBlock>>
    suspend fun insertDocument(document: InstitutionalDocument)
    suspend fun insertBlocks(blocks: List<DocumentBlock>)
    suspend fun getDocumentById(documentId: String): InstitutionalDocument?
    fun getBlockHistory(blockId: String): Flow<List<DocumentBlock>>
}
