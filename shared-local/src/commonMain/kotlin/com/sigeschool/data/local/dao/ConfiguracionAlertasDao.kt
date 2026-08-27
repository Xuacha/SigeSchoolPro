package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ConfiguracionAlertasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfiguracionAlertasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: ConfiguracionAlertasEntity)

    @Update
    suspend fun update(config: ConfiguracionAlertasEntity)

    @Query("SELECT * FROM configuracion_alertas WHERE institutionId = :instId LIMIT 1")
    suspend fun getByInstitution(instId: String): ConfiguracionAlertasEntity?

    @Query("SELECT * FROM configuracion_alertas WHERE institutionId = :instId")
    fun getByInstitutionFlow(instId: String): Flow<ConfiguracionAlertasEntity?>

    @Query("SELECT * FROM configuracion_alertas WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<ConfiguracionAlertasEntity>

    @Query("UPDATE configuracion_alertas SET syncStatus = 0, lastModified = :timestamp WHERE institutionId = :instId")
    suspend fun markAsSynced(instId: String, timestamp: Long)
}
