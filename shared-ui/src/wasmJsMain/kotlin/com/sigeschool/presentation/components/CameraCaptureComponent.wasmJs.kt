package com.sigeschool.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
actual fun CameraCaptureComponent(
    onPhotoCaptured: (ByteArray) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text("Cámara Web (MediaDevices API)")
    }
}
