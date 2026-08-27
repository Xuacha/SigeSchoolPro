package com.sigeschool.domain.service.user

import com.sigeschool.domain.model.Acudiente
import com.sigeschool.domain.service.notification.NotificationQueueService
import com.sigeschool.domain.model.Notification
import kotlinx.datetime.Clock

class UserCreationService(
    private val notificationQueue: NotificationQueueService
) {
    fun generateTemporaryPassword(numeroDocumento: String): String {
        val last4 = if (numeroDocumento.length >= 4) {
            numeroDocumento.takeLast(4)
        } else {
            numeroDocumento.padStart(4, '0')
        }
        val year = Clock.System.now().toString().substring(2, 4) // Simplistic YY
        return "Sige${last4}${year}"
    }

    suspend fun createAndNotifyAcudiente(acudiente: Acudiente, institutionId: String) {
        val tempPassword = generateTemporaryPassword(acudiente.numeroDocumento)
        
        val welcomeNotification = Notification(
            idNotificacion = "WELCOME_${acudiente.idAcudiente}",
            institutionId = institutionId,
            idAcudiente = acudiente.idAcudiente,
            idUsuarioRemitente = "SYSTEM",
            tipoNotificacion = "SISTEMA",
            asunto = "Bienvenido a SigeSchool Pro",
            mensaje = "Hola ${acudiente.nombreCompleto}, tu cuenta ha sido creada. Tu contraseña temporal es: $tempPassword",
            fechaEnvio = Clock.System.now().toEpochMilliseconds(),
            canales = listOf("EMAIL", "WHATSAPP"),
            prioridad = "ALTA"
        )
        
        notificationQueue.enqueue(welcomeNotification)
    }
}
