package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ConfiguracionAlertaEntity

@Dao
interface ConfiguracionAlertaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: ConfiguracionAlertaEntity): Long

    @Update
    suspend fun update(config: ConfiguracionAlertaEntity)

    @Query("SELECT * FROM configuracion_alerta WHERE institutionId = :instId LIMIT 1")
    suspend fun getByInstitution(instId: String): ConfiguracionAlertaEntity?

    @Query("SELECT * FROM configuracion_alerta WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<ConfiguracionAlertaEntity>

    @Query("UPDATE configuracion_alerta SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
