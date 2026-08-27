package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification
import com.sigeschool.domain.model.WhatsAppConfig

actual class WhatsAppChannelService actual constructor(
    private val config: WhatsAppConfig
) : ChannelService {
    actual override val channelName: String = "WHATSAPP"
    
    actual override suspend fun sendMessage(notification: Notification): Result<String> {
        if (config.accessToken.isEmpty()) {
            return Result.success("MOCK-DESKTOP-${notification.idNotificacion}")
        }
        val dispatchId = "DESKTOP-WA-${notification.idNotificacion}-${System.currentTimeMillis()}"
        return Result.success(dispatchId)
    }
}
