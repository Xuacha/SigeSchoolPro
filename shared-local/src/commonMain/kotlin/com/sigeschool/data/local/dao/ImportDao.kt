package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ImportEntity
import com.sigeschool.data.local.entity.ImportDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImport(importation: ImportEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImportDetail(detail: ImportDetailEntity)

    @Query("SELECT * FROM importaciones ORDER BY fechaImportacion DESC")
    fun getAllImports(): Flow<List<ImportEntity>>

    @Query("SELECT * FROM importaciones_detalle WHERE idImportacion = :idImport")
    suspend fun getDetailsByImport(idImport: String): List<ImportDetailEntity>
}
