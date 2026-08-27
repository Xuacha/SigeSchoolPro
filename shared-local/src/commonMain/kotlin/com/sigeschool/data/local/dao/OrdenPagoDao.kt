package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.OrdenPagoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdenPagoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orden: OrdenPagoEntity): Long

    @Update
    suspend fun update(orden: OrdenPagoEntity)

    @Query("SELECT * FROM ordenes_pago WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): OrdenPagoEntity?

    @Query("SELECT * FROM ordenes_pago WHERE facturaId = :facturaId AND institutionId = :instId")
    suspend fun getByFactura(facturaId: String, instId: String): OrdenPagoEntity?

    @Query("SELECT * FROM ordenes_pago WHERE institutionId = :instId AND estado = 'PENDIENTE' ORDER BY fechaVencimiento ASC")
    fun getPendientes(instId: String): Flow<List<OrdenPagoEntity>>

    @Query("SELECT * FROM ordenes_pago WHERE institutionId = :instId AND estado = 'PAGADA' ORDER BY fechaGeneracion DESC LIMIT :limit")
    fun getPagadas(instId: String, limit: Int = 50): Flow<List<OrdenPagoEntity>>

    @Query("UPDATE ordenes_pago SET estado = 'VENCIDA' WHERE fechaVencimiento < :fechaActual AND estado = 'PENDIENTE' AND institutionId = :instId")
    suspend fun actualizarVencidas(fechaActual: Long, instId: String)

    @Query("SELECT * FROM ordenes_pago WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<OrdenPagoEntity>

    @Query("UPDATE ordenes_pago SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
