package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PagedResult<T>(
    val data: List<T>,
    val total: Int,
    val page: Int,
    val pageSize: Int
)

@Serializable
data class FiltrosLogs(
    val estados: List<String> = emptyList(), // ENVIADO, FALLIDO, PENDIENTE, LEIDO
    val canales: List<String> = emptyList(), // EMAIL, WHATSAPP, SMS, PUSH
    val fechaDesde: Long? = null,
    val fechaHasta: Long? = null,
    val busqueda: String = "",
    val pagina: Int = 1,
    val registrosPorPagina: Int = 50
)

@Serializable
data class MetricsSummary(
    val totalNotificaciones: Int,
    val totalEnviados: Int,
    val totalFallidos: Int,
    val totalPendientes: Int,
    val totalLeidos: Int,
    val tasaExito: Double,
    val fallosPorCanal: Map<String, Int>,
    val promedioTiempoEntrega: Double // en segundos
)

@Serializable
data class LogDetalle(
    val log: LogNotificacion,
    val notificacion: Notification,
    val historialIntentos: List<LogNotificacion>
)
