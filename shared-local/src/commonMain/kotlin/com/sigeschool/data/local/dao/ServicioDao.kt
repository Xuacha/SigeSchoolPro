package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ServicioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(servicio: ServicioEntity): Long

    @Update
    suspend fun update(servicio: ServicioEntity)

    @Query("SELECT * FROM servicios WHERE institutionId = :instId AND activo = 1 ORDER BY nombre ASC")
    fun getAllActivos(instId: String): Flow<List<ServicioEntity>>

    @Query("SELECT * FROM servicios WHERE institutionId = :instId AND id = :id LIMIT 1")
    suspend fun getById(id: Long, instId: String): ServicioEntity?

    @Query("DELETE FROM servicios WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM servicios WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<ServicioEntity>

    @Query("UPDATE servicios SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
