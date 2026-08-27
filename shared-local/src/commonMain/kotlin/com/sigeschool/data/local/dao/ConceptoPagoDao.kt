package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ConceptoPagoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConceptoPagoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ConceptoPagoEntity): Long

    @Query("SELECT * FROM cashier_conceptos WHERE institutionId = :instId")
    fun getAll(instId: String): Flow<List<ConceptoPagoEntity>>

    @Query("SELECT * FROM cashier_conceptos WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): ConceptoPagoEntity?

    @Query("DELETE FROM cashier_conceptos WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)
}
