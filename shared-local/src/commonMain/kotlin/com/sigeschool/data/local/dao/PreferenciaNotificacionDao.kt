package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.PreferenciaNotificacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PreferenciaNotificacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(preferencia: PreferenciaNotificacionEntity): Long

    @Update
    suspend fun update(preferencia: PreferenciaNotificacionEntity)

    @Query("SELECT * FROM preferencias_notificaciones WHERE usuarioId = :userId AND institutionId = :instId")
    fun getByUser(userId: String, instId: String): Flow<List<PreferenciaNotificacionEntity>>

    @Query("SELECT * FROM preferencias_notificaciones WHERE usuarioId = :userId AND tipoEvento = :tipo AND institutionId = :instId LIMIT 1")
    suspend fun getByUserAndTipo(userId: String, tipo: String, instId: String): PreferenciaNotificacionEntity?

    @Query("SELECT * FROM preferencias_notificaciones WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<PreferenciaNotificacionEntity>

    @Query("UPDATE preferencias_notificaciones SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
