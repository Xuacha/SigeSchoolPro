package com.sigeschool.services.export

import com.sigeschool.domain.model.LogNotificacion
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ExcelGenerator {
    fun generateExcel(logs: List<LogNotificacion>): ByteArray {
        val data = jsarray()
        
        logs.forEach { log ->
            val row = jsobject()
            jsSet(row, "ID Log", log.idLog.toJsString())
            jsSet(row, "ID Notificación", log.idNotificacion.toJsString())
            jsSet(row, "Canal", log.canal.toJsString())
            jsSet(row, "Fecha", formatDate(log.fechaIntento).toJsString())
            jsSet(row, "Resultado", (if (log.exito) "Éxito" else "Error").toJsString())
            jsSet(row, "Mensaje", (log.mensajeRespuesta ?: "").toJsString())
            jsSet(row, "Intentos", log.intentos.toJsNumber())
            jsPush(data, row)
        }
        
        val ws = XLSX.utils.json_to_sheet(data)
        val wb = XLSX.utils.book_new()
        XLSX.utils.book_append_sheet(wb, ws, "Logs de Notificaciones")
        
        val writeOptions = jsobject()
        jsSet(writeOptions, "bookType", "xlsx".toJsString())
        jsSet(writeOptions, "type", "array".toJsString())
        
        val wbout = XLSX.write(wb, writeOptions)
        return wbout.toByteArray()
    }

    private fun formatDate(timestamp: Long): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${dateTime.dayOfMonth}/${dateTime.monthNumber}/${dateTime.year} ${dateTime.hour}:${dateTime.minute}"
    }
}
