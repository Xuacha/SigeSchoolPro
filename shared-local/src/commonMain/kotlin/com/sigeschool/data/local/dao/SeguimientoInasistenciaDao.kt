package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.SeguimientoInasistenciaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeguimientoInasistenciaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(seguimiento: SeguimientoInasistenciaEntity): Long

    @Query("SELECT * FROM seguimiento_inasistencia WHERE alertaId = :alertaId AND institutionId = :instId ORDER BY fechaSeguimiento DESC")
    fun getByAlerta(alertaId: Long, instId: String): Flow<List<SeguimientoInasistenciaEntity>>

    @Query("SELECT * FROM seguimiento_inasistencia WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<SeguimientoInasistenciaEntity>

    @Query("UPDATE seguimiento_inasistencia SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
