package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification
import com.sigeschool.domain.model.WhatsAppConfig

actual class WhatsAppChannelService actual constructor(
    private val config: WhatsAppConfig
) : ChannelService {
    actual override val channelName: String = "WHATSAPP"
    
    actual override suspend fun sendMessage(notification: Notification): Result<String> {
        val dispatchId = "WASM-WA-${notification.idNotificacion}-${kotlinx.datetime.Clock.System.now().toEpochMilliseconds()}"
        return Result.success(dispatchId)
    }
}
