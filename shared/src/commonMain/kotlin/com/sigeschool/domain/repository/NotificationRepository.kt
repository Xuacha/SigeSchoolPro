package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Notification
import com.sigeschool.domain.model.Circular
import com.sigeschool.domain.model.LogNotificacion
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun saveNotification(notification: Notification): Result<Unit>
    suspend fun getNotificationById(id: String): Notification?
    suspend fun getNotificationsByAcudiente(acudienteId: String): Flow<List<Notification>>
    suspend fun updateNotificationStatus(id: String, channel: String, status: String): Result<Unit>
    
    suspend fun saveCircular(circular: Circular): Result<Unit>
    suspend fun getCircularById(id: String): Circular?
    suspend fun getPendingCirculares(): List<Circular>
    suspend fun updateCircularStatus(id: String, status: String): Result<Unit>
    
    suspend fun saveLog(log: LogNotificacion): Result<Unit>
    suspend fun getLogsByNotification(notificationId: String): List<LogNotificacion>

    // Monitoring
    suspend fun getLogsWithFilters(filtros: com.sigeschool.domain.model.FiltrosLogs): Result<com.sigeschool.domain.model.PagedResult<LogNotificacion>>
    suspend fun getMetricsSummary(filtros: com.sigeschool.domain.model.FiltrosLogs): Result<com.sigeschool.domain.model.MetricsSummary>
    suspend fun getLogDetalle(idLog: String): Result<com.sigeschool.domain.model.LogDetalle>
    suspend fun reenviarNotificacion(idNotificacion: String): Result<Unit>
    suspend fun exportarLogs(filtros: com.sigeschool.domain.model.FiltrosLogs, formato: String): Result<ByteArray>
    suspend fun limpiarLogsAntiguos(dias: Int): Result<Int>
}
