package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification

interface ChannelService {
    val channelName: String
    suspend fun sendMessage(notification: Notification): Result<String>
}
