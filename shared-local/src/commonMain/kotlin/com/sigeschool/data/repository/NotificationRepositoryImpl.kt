package com.sigeschool.data.repository

import com.sigeschool.data.local.dao.NotificationDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.*
import com.sigeschool.domain.repository.NotificationRepository
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.services.export.ExportService
import androidx.room.RoomRawQuery
import androidx.sqlite.SQLiteStatement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotificationRepositoryImpl(
    private val notificationDao: NotificationDao,
    private val sessionManager: SessionManager
) : NotificationRepository {
    private var exportService: ExportService? = null

    fun setExportService(service: ExportService) {
        this.exportService = service
    }

    private fun getInstitutionId(): String? {
        val state = sessionManager.sessionState.value
        return if (state is SessionState.LoggedIn) state.institutionId else null
    }

    override suspend fun saveNotification(notification: Notification): Result<Unit> = runCatching {
        notificationDao.insertNotificacion(notification.toEntity())
    }

    override suspend fun getNotificationById(id: String): Notification? {
        return notificationDao.getNotificacionById(id)?.toDomain()
    }

    override suspend fun getNotificationsByAcudiente(acudienteId: String): Flow<List<Notification>> {
        return notificationDao.getNotificacionesByAcudiente(acudienteId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun updateNotificationStatus(id: String, channel: String, status: String): Result<Unit> = runCatching {
        when (channel.uppercase()) {
            "EMAIL" -> notificationDao.updateEmailStatus(id, status)
            "WHATSAPP" -> notificationDao.updateWhatsappStatus(id, status)
        }
    }

    override suspend fun saveCircular(circular: Circular): Result<Unit> = runCatching {
        notificationDao.insertCircular(circular.toEntity())
    }

    override suspend fun getCircularById(id: String): Circular? {
        return notificationDao.getCircularById(id)?.toDomain()
    }

    override suspend fun getPendingCirculares(): List<Circular> {
        val currentTime = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        return notificationDao.getPendingCirculares(currentTime).map { it.toDomain() }
    }

    override suspend fun updateCircularStatus(id: String, status: String): Result<Unit> = runCatching {
        notificationDao.updateCircularStatus(id, status)
    }

    override suspend fun saveLog(log: LogNotificacion): Result<Unit> = runCatching {
        notificationDao.insertLog(log.toEntity())
    }

    override suspend fun getLogsByNotification(notificationId: String): List<LogNotificacion> {
        return notificationDao.getLogsByNotification(notificationId).map { it.toDomain() }
    }

    override suspend fun getLogsWithFilters(filtros: FiltrosLogs): Result<PagedResult<LogNotificacion>> = runCatching {
        val institutionId = getInstitutionId() ?: throw Exception("Usuario no autenticado")
        
        val queryBuilder = StringBuilder("SELECT * FROM log_notificaciones WHERE institutionId = ?")
        val countBuilder = StringBuilder("SELECT COUNT(*) FROM log_notificaciones WHERE institutionId = ?")
        val args = mutableListOf<Any>(institutionId)

        if (filtros.canales.isNotEmpty()) {
            val placeholders = filtros.canales.joinToString(",") { "?" }
            val clause = " AND canal IN ($placeholders)"
            queryBuilder.append(clause)
            countBuilder.append(clause)
            args.addAll(filtros.canales)
        }

        if (filtros.fechaDesde != null) {
            queryBuilder.append(" AND fechaIntento >= ?")
            countBuilder.append(" AND fechaIntento >= ?")
            args.add(filtros.fechaDesde!!)
        }

        if (filtros.fechaHasta != null) {
            queryBuilder.append(" AND fechaIntento <= ?")
            countBuilder.append(" AND fechaIntento <= ?")
            args.add(filtros.fechaHasta!!)
        }

        if (filtros.busqueda.isNotEmpty()) {
            val search = "%${filtros.busqueda}%"
            queryBuilder.append(" AND (idNotificacion LIKE ? OR mensajeRespuesta LIKE ?)")
            countBuilder.append(" AND (idNotificacion LIKE ? OR mensajeRespuesta LIKE ?)")
            args.add(search)
            args.add(search)
        }

        queryBuilder.append(" ORDER BY fechaIntento DESC LIMIT ? OFFSET ?")
        val limit = filtros.registrosPorPagina
        val offset = (filtros.pagina - 1) * limit
        val queryArgs = args.toMutableList().apply {
            add(limit)
            add(offset)
        }

        val roomQuery = RoomRawQuery(queryBuilder.toString()) { statement ->
            queryArgs.forEachIndexed { index, arg ->
                bindArgument(statement, index + 1, arg)
            }
        }

        val countQuery = RoomRawQuery(countBuilder.toString()) { statement ->
            args.forEachIndexed { index, arg ->
                bindArgument(statement, index + 1, arg)
            }
        }

        val entities = notificationDao.getLogsWithFilters(roomQuery)
        val total = notificationDao.getCountWithFilters(countQuery)

        PagedResult(
            data = entities.map { it.toDomain() },
            total = total,
            page = filtros.pagina,
            pageSize = limit
        )
    }

    override suspend fun getMetricsSummary(filtros: FiltrosLogs): Result<MetricsSummary> = runCatching {
        val institutionId = getInstitutionId() ?: throw Exception("Usuario no autenticado")
        
        // Obtenemos los logs filtrados por institución
        val queryBuilder = StringBuilder("SELECT * FROM log_notificaciones WHERE institutionId = ?")
        val args = mutableListOf<Any>(institutionId)

        if (filtros.fechaDesde != null) {
            queryBuilder.append(" AND fechaIntento >= ?")
            args.add(filtros.fechaDesde as Any)
        }
        if (filtros.fechaHasta != null) {
            queryBuilder.append(" AND fechaIntento <= ?")
            args.add(filtros.fechaHasta as Any)
        }

        val roomQuery = RoomRawQuery(queryBuilder.toString()) { statement ->
            args.forEachIndexed { index, arg ->
                bindArgument(statement, index + 1, arg)
            }
        }

        val entities = notificationDao.getLogsWithFilters(roomQuery)
        
        val total = entities.size
        val exitos = entities.count { it.exito }
        val fallidos = total - exitos
        
        MetricsSummary(
            totalNotificaciones = total,
            totalEnviados = exitos,
            totalFallidos = fallidos,
            totalPendientes = 0,
            totalLeidos = 0,
            tasaExito = if (total > 0) (exitos.toDouble() / total * 100) else 0.0,
            fallosPorCanal = entities.filter { !it.exito }.groupBy { it.canal }.mapValues { it.value.size },
            promedioTiempoEntrega = 0.0
        )
    }

    override suspend fun getLogDetalle(idLog: String): Result<LogDetalle> = runCatching {
        val logEntity = notificationDao.getLogById(idLog) ?: throw Exception("Log no encontrado")
        val log = logEntity.toDomain()
        val notificacion = notificationDao.getNotificacionById(log.idNotificacion)?.toDomain() 
            ?: throw Exception("Notificación no encontrada")
        val historial = notificationDao.getLogsByNotification(log.idNotificacion).map { it.toDomain() }
        
        LogDetalle(log, notificacion, historial)
    }

    override suspend fun reenviarNotificacion(idNotificacion: String): Result<Unit> = runCatching {
        // Lógica para resetear estados y reencolar
        val notif = notificationDao.getNotificacionById(idNotificacion)
        if (notif != null) {
            notificationDao.updateEmailStatus(idNotificacion, "PENDIENTE")
            notificationDao.updateWhatsappStatus(idNotificacion, "PENDIENTE")
        }
    }

    override suspend fun exportarLogs(filtros: FiltrosLogs, formato: String): Result<ByteArray> {
        val service = exportService ?: return Result.failure(Exception("ExportService not initialized"))
        return if (formato == "EXCEL") {
            service.exportToExcel(filtros)
        } else {
            service.exportToPDF(filtros)
        }
    }

    override suspend fun limpiarLogsAntiguos(dias: Int): Result<Int> = runCatching {
        val threshold = kotlinx.datetime.Clock.System.now().toEpochMilliseconds() - (dias.toLong() * 24 * 60 * 60 * 1000)
        notificationDao.deleteOldLogs(threshold)
    }

    private fun bindArgument(statement: SQLiteStatement, index: Int, arg: Any) {
        when (arg) {
            is String -> statement.bindText(index, arg)
            is Long -> statement.bindLong(index, arg)
            is Int -> statement.bindLong(index, arg.toLong())
            is Double -> statement.bindDouble(index, arg)
            is Boolean -> statement.bindLong(index, if (arg) 1 else 0)
            else -> statement.bindText(index, arg.toString())
        }
    }
}
