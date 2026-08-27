package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.CurricularLocalDataSource
import com.sigeschool.data.local.dao.CurricularDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.DocumentBlock
import com.sigeschool.domain.model.DocumentType
import com.sigeschool.domain.model.InstitutionalDocument
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurricularLocalDataSourceImpl(
    private val curricularDao: CurricularDao
) : CurricularLocalDataSource {
    override fun getDocumentsByType(type: DocumentType): Flow<List<InstitutionalDocument>> {
        return curricularDao.getDocumentsByType(type.name).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getBlocksByDocumentId(documentId: String): Flow<List<DocumentBlock>> {
        return curricularDao.getBlocksByDocumentId(documentId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertDocument(document: InstitutionalDocument) {
        curricularDao.insertDocument(document.toEntity())
    }

    override suspend fun insertBlocks(blocks: List<DocumentBlock>) {
        curricularDao.insertBlocks(blocks.map { it.toEntity() })
    }

    override suspend fun getDocumentById(documentId: String): InstitutionalDocument? {
        return curricularDao.getDocumentById(documentId)?.toDomain()
    }

    override fun getBlockHistory(blockId: String): Flow<List<DocumentBlock>> {
        return curricularDao.getHistoryByBlockId(blockId).map { entities ->
            entities.map { 
                // Conversion manually for history if needed, or update mapper
                DocumentBlock(
                    id = it.id,
                    documentId = "", // Not stored in history entity but required by domain
                    order = 0,
                    title = "",
                    contentHtml = it.contentHtml,
                    updatedAt = it.modifiedAt,
                    modifiedBy = it.modifiedBy
                )
            }
        }
    }
}
