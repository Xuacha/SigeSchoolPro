package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AlertaInasistenciaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlertaInasistenciaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alerta: AlertaInasistenciaEntity): Long

    @Update
    suspend fun update(alerta: AlertaInasistenciaEntity)

    @Query("SELECT * FROM alertas_inasistencia WHERE institutionId = :instId AND estado = 'ACTIVA' ORDER BY fechaAlerta DESC")
    fun getActivas(instId: String): Flow<List<AlertaInasistenciaEntity>>

    @Query("SELECT * FROM alertas_inasistencia WHERE estudianteId = :estudianteId AND institutionId = :instId ORDER BY fechaAlerta DESC")
    fun getByEstudiante(estudianteId: String, instId: String): Flow<List<AlertaInasistenciaEntity>>

    @Query("UPDATE alertas_inasistencia SET estado = 'RESUELTA', fechaResolucion = :fechaResolucion WHERE id = :id AND institutionId = :instId")
    suspend fun resolverAlerta(id: Long, fechaResolucion: Long, instId: String)

    @Query("SELECT * FROM alertas_inasistencia WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<AlertaInasistenciaEntity>

    @Query("UPDATE alertas_inasistencia SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
