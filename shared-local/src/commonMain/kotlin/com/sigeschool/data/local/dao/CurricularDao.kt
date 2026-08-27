package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.BlockHistoryEntity
import com.sigeschool.data.local.entity.DocumentBlockEntity
import com.sigeschool.data.local.entity.InstitutionalDocumentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurricularDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(document: InstitutionalDocumentEntity)

    @Query("SELECT * FROM institutional_documents WHERE type = :type")
    fun getDocumentsByType(type: String): Flow<List<InstitutionalDocumentEntity>>

    @Query("SELECT * FROM institutional_documents WHERE id = :documentId")
    suspend fun getDocumentById(documentId: String): InstitutionalDocumentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlocks(blocks: List<DocumentBlockEntity>)

    @Query("SELECT * FROM document_blocks WHERE documentId = :documentId ORDER BY orderIndex ASC")
    fun getBlocksByDocumentId(documentId: String): Flow<List<DocumentBlockEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: BlockHistoryEntity)

    @Query("SELECT * FROM block_history WHERE blockId = :blockId ORDER BY modifiedAt DESC")
    fun getHistoryByBlockId(blockId: String): Flow<List<BlockHistoryEntity>>
}
