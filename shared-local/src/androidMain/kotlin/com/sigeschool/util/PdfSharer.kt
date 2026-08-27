package com.sigeschool.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object PdfSharer {
    fun sharePdf(context: Context, pdfBytes: ByteArray, fileName: String) {
        val file = File(context.cacheDir, fileName)
        FileOutputStream(file).use { it.write(pdfBytes) }

        val contentUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Compartir Reporte"))
    }
}
