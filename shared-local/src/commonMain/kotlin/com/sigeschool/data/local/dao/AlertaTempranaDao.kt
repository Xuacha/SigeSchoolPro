package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AlertaTempranaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlertaTempranaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alerta: AlertaTempranaEntity): Long

    @Update
    suspend fun update(alerta: AlertaTempranaEntity)

    @Query("SELECT * FROM alertas_tempranas WHERE institutionId = :instId AND estado = 'ACTIVA' ORDER BY fechaDeteccion DESC")
    fun getActivas(instId: String): Flow<List<AlertaTempranaEntity>>

    @Query("SELECT * FROM alertas_tempranas WHERE studentId = :studentId AND tipo = :tipo AND estado = 'ACTIVA' LIMIT 1")
    suspend fun getActivaByStudentAndTipo(studentId: String, tipo: String): AlertaTempranaEntity?

    @Query("SELECT * FROM alertas_tempranas WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<AlertaTempranaEntity>

    @Query("UPDATE alertas_tempranas SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
