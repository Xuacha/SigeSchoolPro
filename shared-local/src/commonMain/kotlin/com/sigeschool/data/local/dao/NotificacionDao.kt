package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.NotificacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notificacion: NotificacionEntity): Long

    @Update
    suspend fun update(notificacion: NotificacionEntity)

    @Query("SELECT * FROM notificaciones WHERE usuarioId = :userId AND institutionId = :instId ORDER BY fecha DESC LIMIT :limit")
    fun getByUser(userId: String, instId: String, limit: Int = 50): Flow<List<NotificacionEntity>>

    @Query("SELECT * FROM notificaciones WHERE usuarioId = :userId AND institutionId = :instId AND leida = 0 ORDER BY fecha DESC")
    fun getNoLeidas(userId: String, instId: String): Flow<List<NotificacionEntity>>

    @Query("UPDATE notificaciones SET leida = 1 WHERE id = :id AND usuarioId = :userId AND institutionId = :instId")
    suspend fun marcarComoLeida(id: Long, userId: String, instId: String)

    @Query("UPDATE notificaciones SET leida = 1 WHERE usuarioId = :userId AND institutionId = :instId")
    suspend fun marcarTodasComoLeidas(userId: String, instId: String)

    @Query("SELECT * FROM notificaciones WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<NotificacionEntity>

    @Query("UPDATE notificaciones SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
