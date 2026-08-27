package com.sigeschool.util

import androidx.compose.runtime.Composable

import kotlinx.browser.window
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.set
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag

@Composable
actual fun SharePdfFile(pdfBytes: ByteArray, fileName: String) {
    val uint8Array = Uint8Array(pdfBytes.size)
    for (i in pdfBytes.indices) {
        uint8Array[i] = pdfBytes[i]
    }
    val parts = JsArray<JsAny?>()
    parts[0] = uint8Array
    val blob = Blob(parts, BlobPropertyBag(type = "application/pdf"))
    val url = URL.createObjectURL(blob)
    val anchor = window.document.createElement("a") as org.w3c.dom.HTMLAnchorElement
    anchor.href = url
    anchor.download = fileName
    anchor.click()
    URL.revokeObjectURL(url)
}

@Composable
actual fun OpenExternalUrl(url: String) {
    window.open(url, "_blank")
}
