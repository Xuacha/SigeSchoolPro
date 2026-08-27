package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.ManagedDocumentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ManagedDocumentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(document: ManagedDocumentEntity)

    @Query("SELECT * FROM managed_documents WHERE institutionId = :institutionId AND type = :type LIMIT 1")
    fun getDocumentByType(institutionId: String, type: String): Flow<ManagedDocumentEntity?>

    @Query("SELECT * FROM managed_documents WHERE institutionId = :institutionId")
    fun getAllDocuments(institutionId: String): Flow<List<ManagedDocumentEntity>>
}
