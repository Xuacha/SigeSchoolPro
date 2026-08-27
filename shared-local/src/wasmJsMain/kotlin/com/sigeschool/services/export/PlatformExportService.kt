package com.sigeschool.services.export

import com.sigeschool.domain.model.FiltrosLogs
import com.sigeschool.domain.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual class PlatformExportService actual constructor(
    private val logRepository: NotificationRepository
) : ExportService {
    override suspend fun exportToExcel(filtros: FiltrosLogs): Result<ByteArray> = withContext(Dispatchers.Default) {
        runCatching {
            val result = logRepository.getLogsWithFilters(filtros).getOrThrow()
            ExcelGenerator().generateExcel(result.data)
        }
    }

    override suspend fun exportToPDF(filtros: FiltrosLogs): Result<ByteArray> = withContext(Dispatchers.Default) {
        runCatching {
            val result = logRepository.getLogsWithFilters(filtros).getOrThrow()
            PdfGenerator().generatePDF(result.data)
        }
    }
}
