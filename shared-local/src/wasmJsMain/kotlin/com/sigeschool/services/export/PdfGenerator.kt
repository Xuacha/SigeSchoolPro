package com.sigeschool.services.export

import com.sigeschool.domain.model.LogNotificacion
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class PdfGenerator {
    fun generatePDF(logs: List<LogNotificacion>): ByteArray {
        val doc = JsPDF("p", "mm", "a4")
        
        doc.text("Reporte de Logs de Notificaciones", 14.0, 16.0)
        doc.text("Fecha: ${formatDate(kotlinx.datetime.Clock.System.now().toEpochMilliseconds())}", 14.0, 22.0)
        
        val columns = jsarray()
        jsPush(columns, "Fecha".toJsString())
        jsPush(columns, "Canal".toJsString())
        jsPush(columns, "ID Notificación".toJsString())
        jsPush(columns, "Resultado".toJsString())
        jsPush(columns, "Intentos".toJsString())

        val rows = jsarray()
        logs.forEach { log ->
            val row = jsarray()
            jsPush(row, formatDate(log.fechaIntento).toJsString())
            jsPush(row, log.canal.toJsString())
            jsPush(row, log.idNotificacion.toJsString())
            jsPush(row, (if (log.exito) "Éxito" else "Error").toJsString())
            jsPush(row, log.intentos.toString().toJsString())
            jsPush(rows, row)
        }
        
        val options = jsobject()
        jsSet(options, "head", jsarray().apply { jsPush(this, columns) })
        jsSet(options, "body", rows)
        jsSet(options, "startY", 30.0.toJsNumber())
        jsSet(options, "theme", "striped".toJsString())
        
        AutoTable.autoTable(doc, options)
        
        val pdfOutput = doc.output("arraybuffer")
        return pdfOutput.toByteArray()
    }

    private fun formatDate(timestamp: Long): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${dateTime.dayOfMonth}/${dateTime.monthNumber}/${dateTime.year} ${dateTime.hour}:${dateTime.minute}"
    }
}
