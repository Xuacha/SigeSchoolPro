package com.sigeschool.presentation.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.io.File

actual object DownloadHelper {
    actual suspend fun downloadFile(bytes: ByteArray, fileName: String, mimeType: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val userHome = System.getProperty("user.home")
                val downloadsDir = File(userHome, "Downloads")
                if (!downloadsDir.exists()) downloadsDir.mkdirs()
                
                val outputFile = File(downloadsDir, fileName)
                outputFile.writeBytes(bytes)
                
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(outputFile)
                }
            }
        }
    }
}
