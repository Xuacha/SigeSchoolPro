package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ConfirmacionPagoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfirmacionPagoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(confirmacion: ConfirmacionPagoEntity): Long

    @Update
    suspend fun update(confirmacion: ConfirmacionPagoEntity)

    @Query("SELECT * FROM confirmaciones_pago WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): ConfirmacionPagoEntity?

    @Query("SELECT * FROM confirmaciones_pago WHERE ordenPagoId = :ordenId AND institutionId = :instId")
    suspend fun getByOrden(ordenId: Long, instId: String): ConfirmacionPagoEntity?

    @Query("SELECT * FROM confirmaciones_pago WHERE institutionId = :instId AND estadoValidacion = 'PENDIENTE'")
    fun getPendientesValidacion(instId: String): Flow<List<ConfirmacionPagoEntity>>

    @Query("SELECT * FROM confirmaciones_pago WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<ConfirmacionPagoEntity>

    @Query("UPDATE confirmaciones_pago SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
