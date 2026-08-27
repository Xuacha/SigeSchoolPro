package com.sigeschool.services.export

import com.sigeschool.domain.model.FiltrosLogs
import com.sigeschool.domain.repository.NotificationRepository

interface ExportService {
    suspend fun exportToExcel(filtros: FiltrosLogs): Result<ByteArray>
    suspend fun exportToPDF(filtros: FiltrosLogs): Result<ByteArray>
}

expect class PlatformExportService(logRepository: NotificationRepository) : ExportService
