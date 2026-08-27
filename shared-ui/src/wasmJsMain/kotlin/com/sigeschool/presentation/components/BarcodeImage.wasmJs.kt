package com.sigeschool.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
actual fun BarcodeImage(
    text: String,
    modifier: Modifier
) {
    // Para WasmJs, mostramos el texto como fallback por ahora.
    // Se podría integrar una librería JS de códigos de barras.
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text("Barcode: $text")
    }
}
