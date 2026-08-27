package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification

expect class EmailChannelService() : ChannelService {
    override val channelName: String
    override suspend fun sendMessage(notification: Notification): Result<String>
}
