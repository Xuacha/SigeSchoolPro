package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Message(
    val id: String = "",
    @SerialName("sender_id")
    val senderId: String = "",
    @SerialName("receiver_id")
    val receiverId: String = "",
    val content: String = "",
    val timestamp: Long = 0,
    @SerialName("institution_id")
    val institutionId: String = ""
)
