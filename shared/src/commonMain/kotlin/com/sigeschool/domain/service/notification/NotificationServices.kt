package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.WhatsAppConfig
import com.sigeschool.domain.repository.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object NotificationServiceFactory {
    fun createQueueService(
        repository: NotificationRepository,
        whatsAppConfig: WhatsAppConfig,
        scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    ): NotificationQueueService {
        val channels = listOf(
            EmailChannelService(),
            WhatsAppChannelService(whatsAppConfig)
        )
        return NotificationQueueService(repository, channels, scope)
    }
}
