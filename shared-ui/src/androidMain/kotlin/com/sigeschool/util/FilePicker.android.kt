package com.sigeschool.util

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun FilePicker(
    show: Boolean,
    onFileSelected: (List<List<String>>) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            val contentResolver = context.contentResolver
            contentResolver.openInputStream(uri)?.use { inputStream ->
                val reader = inputStream.bufferedReader()
                val rows = reader.lineSequence().map { line ->
                    // Soporte para comas y punto y coma (común en Excel en español)
                    val delimiter = if (line.contains(";")) ";" else ","
                    line.split(delimiter).map { it.trim().removeSurrounding("\"") }
                }.filter { it.any { cell -> cell.isNotBlank() } }.toList()
                onFileSelected(rows)
            }
        }
        onDismiss()
    }

    LaunchedEffect(show) {
        if (show) {
            launcher.launch("*/*")
        }
    }
}
