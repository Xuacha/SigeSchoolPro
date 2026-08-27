package com.sigeschool.presentation.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

actual object DownloadHelper {
    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    actual suspend fun downloadFile(bytes: ByteArray, fileName: String, mimeType: String): Result<Unit> {
        val ctx = context ?: return Result.failure(Exception("Context not initialized. Call DownloadHelper.init(context)"))
        
        return withContext(Dispatchers.IO) {
            runCatching {
                val downloadsDir = ctx.getExternalFilesDir(null)
                val outputFile = File(downloadsDir, fileName)
                outputFile.writeBytes(bytes)
                
                val uri = FileProvider.getUriForFile(
                    ctx,
                    "${ctx.packageName}.fileprovider",
                    outputFile
                )
                
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, mimeType)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                ctx.startActivity(intent)
            }
        }
    }
}
