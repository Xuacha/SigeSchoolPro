package com.sigeschool.util

import androidx.compose.runtime.Composable

@Composable
actual fun FilePicker(
    show: Boolean,
    onFileSelected: (List<List<String>>) -> Unit,
    onDismiss: () -> Unit
) {
    // Web implementation placeholder
    if (show) {
        onDismiss()
    }
}
