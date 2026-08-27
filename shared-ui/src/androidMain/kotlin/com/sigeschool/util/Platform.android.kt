package com.sigeschool.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun SharePdfFile(pdfBytes: ByteArray, fileName: String) {
    val context = LocalContext.current
    PdfSharer.sharePdf(context, pdfBytes, fileName)
}

@Composable
actual fun OpenExternalUrl(url: String) {
    val context = LocalContext.current
    val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
    context.startActivity(intent)
}
