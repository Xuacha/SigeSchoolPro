package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.DocenteSyncConfigEntity
import com.sigeschool.data.local.entity.DocenteSyncLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocenteSyncConfigDao {
    @Query("SELECT * FROM docente_sync_configs WHERE institutionId = :institutionId AND docenteId = :docenteId")
    fun getConfigsByDocente(institutionId: String, docenteId: String): Flow<List<DocenteSyncConfigEntity>>

    @Query("SELECT * FROM docente_sync_configs WHERE isActive = 1")
    suspend fun getAllActiveConfigs(): List<DocenteSyncConfigEntity>

    @Query("SELECT * FROM docente_sync_configs WHERE id = :id")
    suspend fun getConfigById(id: String): DocenteSyncConfigEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: DocenteSyncConfigEntity)

    @Update
    suspend fun updateConfig(config: DocenteSyncConfigEntity)

    @Delete
    suspend fun deleteConfig(config: DocenteSyncConfigEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: DocenteSyncLogEntity)

    @Query("SELECT * FROM docente_sync_logs WHERE configId = :configId ORDER BY timestamp DESC LIMIT 50")
    fun getLogsByConfig(configId: String): Flow<List<DocenteSyncLogEntity>>
}
