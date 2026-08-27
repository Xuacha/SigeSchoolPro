package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ParentGuardianEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParentGuardianDao {
    @Query("SELECT * FROM parents_guardians WHERE institutionId = :institutionId")
    fun getParentsByInstitution(institutionId: String): Flow<List<ParentGuardianEntity>>

    @Query("SELECT * FROM parents_guardians WHERE documentId = :documentId AND institutionId = :institutionId LIMIT 1")
    suspend fun getParentByDocument(documentId: String, institutionId: String): ParentGuardianEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParent(parent: ParentGuardianEntity)
}
