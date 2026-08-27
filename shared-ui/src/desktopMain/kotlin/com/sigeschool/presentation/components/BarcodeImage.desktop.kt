package com.sigeschool.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import java.awt.image.BufferedImage

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
            bitmap = it,
            contentDescription = "Barcode $text",
            modifier = modifier,
            contentScale = ContentScale.FillBounds
        )
    }
}

private fun generateBarcode(text: String): ImageBitmap? {
    if (text.isBlank()) return null
    return try {
        val width = 500
        val height = 150
        val matrix = MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, width, height)
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        
        for (x in 0 until width) {
            for (y in 0 until height) {
                val color = if (matrix.get(x, y)) java.awt.Color.BLACK.rgb else java.awt.Color.WHITE.rgb
                image.setRGB(x, y, color)
            }
        }
        image.toComposeImageBitmap()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
