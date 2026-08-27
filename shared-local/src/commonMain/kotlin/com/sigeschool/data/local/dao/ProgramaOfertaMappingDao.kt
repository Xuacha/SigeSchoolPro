package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ProgramaOfertaMappingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramaOfertaMappingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mapping: ProgramaOfertaMappingEntity): Long

    @Query("SELECT * FROM programa_oferta_mapping WHERE codigoFormulario = :codigo AND institutionId = :instId AND activo = 1 LIMIT 1")
    suspend fun getByCodigo(codigo: String, instId: String): ProgramaOfertaMappingEntity?

    @Query("SELECT * FROM programa_oferta_mapping WHERE institutionId = :instId AND activo = 1")
    fun getAllActive(instId: String): Flow<List<ProgramaOfertaMappingEntity>>

    @Query("DELETE FROM programa_oferta_mapping WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)
}
