package com.sigeschool.domain.service

interface ChannelService {
    suspend fun sendEmail(to: String, subject: String, body: String): Result<Unit>
    suspend fun sendWhatsApp(to: String, message: String): Result<Unit>
    suspend fun sendWhatsAppTemplate(to: String, templateName: String, params: Map<String, String>): Result<Unit>
    suspend fun sendSMS(to: String, message: String): Result<Unit>
    suspend fun sendPush(token: String, title: String, message: String): Result<Unit>
}
