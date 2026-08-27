package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification
import com.sigeschool.domain.model.WhatsAppConfig

expect class WhatsAppChannelService(config: WhatsAppConfig) : ChannelService {
    override val channelName: String
    override suspend fun sendMessage(notification: Notification): Result<String>
}
