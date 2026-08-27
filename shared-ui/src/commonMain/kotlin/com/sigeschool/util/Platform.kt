package com.sigeschool.util

import androidx.compose.runtime.Composable

@Composable
expect fun SharePdfFile(pdfBytes: ByteArray, fileName: String)

@Composable
expect fun OpenExternalUrl(url: String)

@Composable
fun OpenWhatsApp(phoneNumber: String, message: String = "") {
    val url = WhatsAppUtils.createWhatsAppUrl(phoneNumber, message)
    OpenExternalUrl(url)
}
