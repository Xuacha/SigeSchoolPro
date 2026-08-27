package com.sigeschool.presentation.components

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

@Composable
actual fun BarcodeImage(
    text: String,
    modifier: Modifier
) {
    val bitmap = remember(text) {
        generateBarcode(text)
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "Barcode $text",
            modifier = modifier,
            contentScale = ContentScale.FillBounds
        )
    }
}

private fun generateBarcode(text: String): Bitmap? {
    if (text.isBlank()) return null
    return try {
        val width = 500
        val height = 150
        val matrix = MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (matrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
