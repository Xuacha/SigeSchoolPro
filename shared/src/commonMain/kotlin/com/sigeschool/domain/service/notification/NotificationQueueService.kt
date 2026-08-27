package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification
import com.sigeschool.domain.model.LogNotificacion
import com.sigeschool.domain.repository.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class NotificationQueueService(
    private val repository: NotificationRepository,
    private val channelServices: List<ChannelService>,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    fun enqueue(notification: Notification) {
        scope.launch {
            repository.saveNotification(notification)
            processNotification(notification)
        }
    }

    private suspend fun processNotification(notification: Notification) {
        notification.canales.forEach { canal ->
            val service = channelServices.find { it.channelName.equals(canal, ignoreCase = true) }
            if (service != null) {
                val result = service.sendMessage(notification)
                val timestamp = Clock.System.now().toEpochMilliseconds()
                
                val log = LogNotificacion(
                    idLog = "${notification.idNotificacion}_$canal",
                    idNotificacion = notification.idNotificacion,
                    institutionId = notification.institutionId,
                    canal = canal,
                    fechaIntento = timestamp,
                    exito = result.isSuccess,
                    mensajeRespuesta = result.exceptionOrNull()?.message ?: "Enviado correctamente",
                    intentos = 1
                )
                
                repository.saveLog(log)
                
                val status = if (result.isSuccess) "ENVIADO" else "FALLIDO"
                repository.updateNotificationStatus(notification.idNotificacion, canal, status)
            }
        }
    }
}
