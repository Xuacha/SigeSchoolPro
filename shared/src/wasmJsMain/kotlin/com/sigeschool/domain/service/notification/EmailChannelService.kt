package com.sigeschool.domain.service.notification

import com.sigeschool.domain.model.Notification

actual class EmailChannelService : ChannelService {
    actual override val channelName: String = "EMAIL"
    
    actual override suspend fun sendMessage(notification: Notification): Result<String> {
        val dispatchId = "WASM-EMAIL-${notification.idNotificacion}-${kotlinx.datetime.Clock.System.now().toEpochMilliseconds()}"
        return Result.success(dispatchId)
    }
}
