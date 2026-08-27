package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.PlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plan: PlanEntity): Long

    @Query("SELECT * FROM planes WHERE id = :id")
    suspend fun getById(id: Long): PlanEntity?

    @Query("SELECT * FROM planes WHERE nombre = :nombre")
    suspend fun getByNombre(nombre: String): PlanEntity?

    @Query("SELECT * FROM planes WHERE activo = 1 ORDER BY limiteEstudiantes ASC")
    fun getActive(): Flow<List<PlanEntity>>

    @Query("SELECT * FROM planes WHERE syncStatus != 0")
    suspend fun getPendingSync(): List<PlanEntity>

    @Query("UPDATE planes SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
