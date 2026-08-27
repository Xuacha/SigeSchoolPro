package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.SuscripcionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuscripcionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(suscripcion: SuscripcionEntity): Long

    @Update
    suspend fun update(suscripcion: SuscripcionEntity)

    @Query("SELECT * FROM suscripciones WHERE institutionId = :instId AND estado = 'ACTIVA' LIMIT 1")
    suspend fun getActiva(instId: String): SuscripcionEntity?

    @Query("SELECT * FROM suscripciones WHERE institutionId = :instId ORDER BY fechaInicio DESC LIMIT 1")
    suspend fun getUltima(instId: String): SuscripcionEntity?

    @Query("UPDATE suscripciones SET estado = 'VENCIDA' WHERE fechaFin < :fechaActual AND estado = 'ACTIVA'")
    suspend fun actualizarVencidas(fechaActual: Long)

    @Query("SELECT * FROM suscripciones WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<SuscripcionEntity>

    @Query("UPDATE suscripciones SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
