package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ServicioLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicioLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: ServicioLogEntity): Long

    @Update
    suspend fun update(log: ServicioLogEntity)

    @Query("SELECT * FROM servicio_logs WHERE id = :id AND institutionId = :instId LIMIT 1")
    suspend fun getById(id: Long, instId: String): ServicioLogEntity?

    @Query("SELECT * FROM servicio_logs WHERE studentId = :studentId AND institutionId = :instId ORDER BY fechaHoraSalida DESC")
    fun getByStudent(studentId: String, instId: String): Flow<List<ServicioLogEntity>>

    @Query("SELECT * FROM servicio_logs WHERE servicioId = :servicioId AND institutionId = :instId ORDER BY fechaHoraSalida DESC")
    fun getByServicio(servicioId: Long, instId: String): Flow<List<ServicioLogEntity>>

    @Query("SELECT * FROM servicio_logs WHERE institutionId = :instId AND estado = 'EN_CURSO'")
    fun getEnCurso(instId: String): Flow<List<ServicioLogEntity>>

    @Query("SELECT * FROM servicio_logs WHERE studentId = :studentId AND institutionId = :instId AND estado = 'EN_CURSO'")
    suspend fun getEnCursoByStudent(studentId: String, instId: String): List<ServicioLogEntity>

    @Query("UPDATE servicio_logs SET estado = 'COMPLETADO', fechaHoraRegreso = :timestamp WHERE id = :id AND institutionId = :instId")
    suspend fun completarLog(id: Long, timestamp: Long, instId: String)

    @Query("UPDATE servicio_logs SET fechaHoraLlegada = :timestamp WHERE id = :id AND institutionId = :instId")
    suspend fun registrarLlegada(id: Long, timestamp: Long, instId: String)

    @Query("SELECT * FROM servicio_logs WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<ServicioLogEntity>

    @Query("UPDATE servicio_logs SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
