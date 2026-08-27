package com.sigeschool.data.local.dao

import androidx.room.*
import androidx.sqlite.SQLiteConnection
import com.sigeschool.data.local.entity.InstitutionalNotificationEntity
import com.sigeschool.data.local.entity.CircularEntity
import com.sigeschool.data.local.entity.LogNotificacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotificacion(notificacion: InstitutionalNotificationEntity)

    @Query("SELECT * FROM institutional_notificaciones WHERE idNotificacion = :id")
    suspend fun getNotificacionById(id: String): InstitutionalNotificationEntity?

    @Query("SELECT * FROM institutional_notificaciones WHERE idAcudiente = :idAcudiente ORDER BY fechaEnvio DESC")
    fun getNotificacionesByAcudiente(idAcudiente: String): Flow<List<InstitutionalNotificationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCircular(circular: CircularEntity)

    @Query("SELECT * FROM circulares WHERE idCircular = :id")
    suspend fun getCircularById(id: String): CircularEntity?

    @Query("SELECT * FROM circulares WHERE estado = 'PROGRAMADA' AND fechaProgramacion <= :currentTime")
    suspend fun getPendingCirculares(currentTime: Long): List<CircularEntity>

    @Query("UPDATE circulares SET estado = :estado WHERE idCircular = :id")
    suspend fun updateCircularStatus(id: String, estado: String)

    @Query("SELECT * FROM circulares ORDER BY fechaCreacion DESC")
    fun getCirculares(): Flow<List<CircularEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: LogNotificacionEntity)

    @Query("SELECT * FROM log_notificaciones WHERE idNotificacion = :notificationId")
    suspend fun getLogsByNotification(notificationId: String): List<LogNotificacionEntity>

    @Query("UPDATE institutional_notificaciones SET estadoEnvioEmail = :estado WHERE idNotificacion = :id")
    suspend fun updateEmailStatus(id: String, estado: String)

    @Query("UPDATE institutional_notificaciones SET estadoEnvioWhatsapp = :estado WHERE idNotificacion = :id")
    suspend fun updateWhatsappStatus(id: String, estado: String)

    /* @RawQuery
    suspend fun getLogsWithFilters(query: RoomRawQuery): List<LogNotificacionEntity>

    @RawQuery
    suspend fun getCountWithFilters(query: RoomRawQuery): Int */

    @Query("SELECT * FROM log_notificaciones WHERE idLog = :idLog")
    suspend fun getLogById(idLog: String): LogNotificacionEntity?

    @Query("DELETE FROM log_notificaciones WHERE fechaIntento < :threshold")
    suspend fun deleteOldLogs(threshold: Long): Int
}
