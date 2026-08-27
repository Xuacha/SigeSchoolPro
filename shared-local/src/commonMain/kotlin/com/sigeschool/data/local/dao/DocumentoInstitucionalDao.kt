package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.DocumentoInstitucionalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentoInstitucionalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(documento: DocumentoInstitucionalEntity): Long

    @Query("SELECT * FROM documentos_institucionales WHERE institutionId = :instId AND tipo = :tipo")
    fun getByTipo(instId: String, tipo: String): Flow<List<DocumentoInstitucionalEntity>>

    @Query("SELECT * FROM documentos_institucionales WHERE institutionId = :instId")
    fun getAll(instId: String): Flow<List<DocumentoInstitucionalEntity>>

    @Query("SELECT * FROM documentos_institucionales WHERE id = :id")
    suspend fun getById(id: Long): DocumentoInstitucionalEntity?

    @Query("DELETE FROM documentos_institucionales WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)
}
