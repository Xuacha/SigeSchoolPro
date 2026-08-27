package com.sigeschool.presentation.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.set
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import kotlinx.browser.document

fun createJsArray(): JsArray<JsAny?> = js("([])")
fun pushToJsArray(arr: JsArray<JsAny?>, value: JsAny?): Unit = js("arr.push(value)")

actual object DownloadHelper {
    actual suspend fun downloadFile(bytes: ByteArray, fileName: String, mimeType: String): Result<Unit> {
        return withContext(Dispatchers.Main) {
            runCatching {
                val uint8Array = Uint8Array(bytes.size)
                bytes.forEachIndexed { index, byte -> uint8Array[index] = byte }
                
                // SEC-10: Sanitización de nombre de archivo y validación de MIME
                val safeFileName = fileName.replace(Regex("[^a-zA-Z0-9._-]"), "_")
                val allowedMimeTypes = listOf(
                    "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    "text/csv",
                    "image/jpeg",
                    "image/png",
                    "application/zip"
                )
                
                if (mimeType !in allowedMimeTypes) {
                    throw IllegalArgumentException("MIME type no permitido para descarga: $mimeType")
                }

                val arr = createJsArray()
                pushToJsArray(arr, uint8Array)
                
                val blob = Blob(arr, BlobPropertyBag(type = mimeType))
                val url = URL.createObjectURL(blob)
                val a = document.createElement("a") as HTMLAnchorElement
                a.href = url
                a.download = safeFileName
                document.body?.appendChild(a)
                a.click()
                document.body?.removeChild(a)
                URL.revokeObjectURL(url)
            }
        }
    }
}
