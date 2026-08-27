package com.sigeschool.domain.model

data class WhatsAppConfig(
    val apiUrl: String,
    val phoneNumberId: String,
    val accessToken: String
)

data class EmailConfig(
    val host: String,
    val port: Int,
    val user: String,
    val password: String
)
