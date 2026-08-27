package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ProgramaMappingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramaMappingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mapping: ProgramaMappingEntity)

    @Query("SELECT * FROM programa_mapping WHERE institutionId = :instId AND codigoFormulario = :codigo LIMIT 1")
    suspend fun getByCodigo(instId: String, codigo: String): ProgramaMappingEntity?

    @Query("SELECT * FROM programa_mapping WHERE institutionId = :instId")
    fun getAllByInstitution(instId: String): Flow<List<ProgramaMappingEntity>>
}
