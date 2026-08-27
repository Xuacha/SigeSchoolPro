package com.sigeschool.util

import androidx.compose.runtime.Composable

@Composable
expect fun FilePicker(
    show: Boolean,
    onFileSelected: (List<List<String>>) -> Unit,
    onDismiss: () -> Unit
)
