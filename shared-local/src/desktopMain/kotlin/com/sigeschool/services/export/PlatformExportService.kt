package com.sigeschool.services.export

import com.sigeschool.domain.model.FiltrosLogs
import com.sigeschool.domain.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.ByteArrayOutputStream
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue

actual class PlatformExportService actual constructor(
    private val logRepository: NotificationRepository
) : ExportService {
    
    override suspend fun exportToExcel(filtros: FiltrosLogs): Result<ByteArray> = withContext(Dispatchers.IO) {
        try {
            val logsResult = logRepository.getLogsWithFilters(filtros)
            val logs = logsResult.getOrNull()?.data ?: emptyList()
            
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Logs de Notificaciones")

            val headers = listOf("Fecha", "Canal", "ID Notificacion", "Estado", "Intentos")
            val headerRow = sheet.createRow(0)
            headers.forEachIndexed { index, header ->
                val cell = headerRow.createCell(index)
                cell.setCellValue(header)
                val style = workbook.createCellStyle()
                val font = workbook.createFont()
                font.bold = true
                style.setFont(font)
                cell.cellStyle = style
            }

            logs.forEachIndexed { index, log ->
                val row = sheet.createRow(index + 1)
                row.createCell(0).setCellValue(log.fechaIntento.toDouble())
                row.createCell(1).setCellValue(log.canal)
                row.createCell(2).setCellValue(log.idNotificacion)
                row.createCell(3).setCellValue(if (log.exito) "EXITO" else "FALLIDO")
                row.createCell(4).setCellValue(log.intentos.toDouble())
            }

            for (i in 0..4) {
                sheet.autoSizeColumn(i)
            }

            val baos = ByteArrayOutputStream()
            workbook.write(baos)
            workbook.close()
            Result.success(baos.toByteArray())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun exportToPDF(filtros: FiltrosLogs): Result<ByteArray> = withContext(Dispatchers.IO) {
        try {
            val logsResult = logRepository.getLogsWithFilters(filtros)
            val logs = logsResult.getOrNull()?.data ?: emptyList()
            
            val baos = ByteArrayOutputStream()
            val writer = PdfWriter(baos)
            val pdf = PdfDocument(writer)
            val document = Document(pdf)
            
            document.add(Paragraph("Reporte de Logs de Notificaciones")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18f))

            document.add(Paragraph("\n"))

            val table = Table(UnitValue.createPointArray(floatArrayOf(100f, 60f, 150f, 60f, 40f)))
            table.addHeaderCell("Fecha")
            table.addHeaderCell("Canal")
            table.addHeaderCell("ID Notificacion")
            table.addHeaderCell("Estado")
            table.addHeaderCell("Intentos")

            logs.forEach { log ->
                table.addCell(log.fechaIntento.toString())
                table.addCell(log.canal)
                table.addCell(log.idNotificacion)
                table.addCell(if (log.exito) "EXITO" else "FALLIDO")
                table.addCell(log.intentos.toString())
            }

            document.add(table)
            document.close()
            Result.success(baos.toByteArray())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
