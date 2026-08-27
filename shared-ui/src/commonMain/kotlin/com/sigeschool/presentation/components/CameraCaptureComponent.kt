package com.sigeschool.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CameraCaptureComponent(
    onPhotoCaptured: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
)
