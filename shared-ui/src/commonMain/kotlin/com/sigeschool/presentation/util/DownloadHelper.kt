package com.sigeschool.presentation.util

expect object DownloadHelper {
    suspend fun downloadFile(bytes: ByteArray, fileName: String, mimeType: String): Result<Unit>
}
