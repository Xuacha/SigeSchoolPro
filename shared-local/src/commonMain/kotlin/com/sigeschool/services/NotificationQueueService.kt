package com.sigeschool.services

import com.sigeschool.data.repository.NotificationRepositoryImpl
import com.sigeschool.domain.model.Notification
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NotificationQueueService(
    private val notificationRepository: NotificationRepositoryImpl,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
) {
    private val queue = Channel<Notification>(Channel.UNLIMITED)

    init {
        scope.launch {
            queue.receiveAsFlow().collect { notification ->
                processNotification(notification)
            }
        }
    }

    fun enqueue(notification: Notification) {
        scope.launch {
            queue.send(notification)
        }
    }

    private suspend fun processNotification(notification: Notification) {
        try {
            notificationRepository.saveNotification(notification)
        } catch (e: Exception) {
            // Handle retry logic or logging
            e.printStackTrace()
        }
    }
}
