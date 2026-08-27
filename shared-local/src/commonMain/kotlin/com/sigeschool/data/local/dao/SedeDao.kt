package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.SedeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SedeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sede: SedeEntity): Long

    @Update
    suspend fun update(sede: SedeEntity)

    @Query("SELECT * FROM academic_sedes WHERE institutionId = :instId AND activa = 1 ORDER BY nombre ASC")
    fun getAll(instId: String): Flow<List<SedeEntity>>

    @Query("SELECT * FROM academic_sedes WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): SedeEntity?

    @Query("DELETE FROM academic_sedes WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)
}
