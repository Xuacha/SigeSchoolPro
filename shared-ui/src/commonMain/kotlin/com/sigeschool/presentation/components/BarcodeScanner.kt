package com.sigeschool.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BarcodeScanner(
    onScan: (String) -> Unit,
    modifier: Modifier = Modifier
)
