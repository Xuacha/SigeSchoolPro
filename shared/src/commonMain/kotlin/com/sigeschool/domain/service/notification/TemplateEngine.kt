package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification
import com.sigeschool.domain.model.Acudiente

class TemplateEngine {
    
    fun processTemplate(template: String, data: Map<String, String>): String {
        var result = template
        data.forEach { (key, value) ->
            result = result.replace("{{$key}}", value)
        }
        return result
    }

    fun generateCircular(titulo: String, contenido: String): String {
        return """
            $titulo
            
            $contenido
        """.trimIndent()
    }

    fun generateNotificationMessages(notification: Notification, acudiente: Acudiente): Notification {
        val data = mutableMapOf(
            "nombreAcudiente" to acudiente.nombreCompleto,
            "asunto" to notification.asunto,
            "mensaje" to notification.mensaje
        )
        notification.metadata?.let { data.putAll(it) }

        val mensajeWhatsapp = notification.mensajeWhatsapp ?: """
            *SigeSchool Pro - Notificación*
            
            Hola {{nombreAcudiente}},
            
            {{mensaje}}
            
            _Este es un mensaje automático._
        """.trimIndent()

        val mensajeEmail = notification.mensajeEmail ?: """
            <html>
            <body>
                <h1>SigeSchool Pro</h1>
                <p>Hola {{nombreAcudiente}},</p>
                <p>{{mensaje}}</p>
                <br>
                <p>Saludos,<br>Equipo SigeSchool Pro</p>
            </body>
            </html>
        """.trimIndent()

        return notification.copy(
            mensajeWhatsapp = processTemplate(mensajeWhatsapp, data),
            mensajeEmail = processTemplate(mensajeEmail, data)
        )
    }
}
