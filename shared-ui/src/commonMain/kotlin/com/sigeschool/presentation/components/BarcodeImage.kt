package com.sigeschool.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BarcodeImage(
    text: String,
    modifier: Modifier = Modifier
)
