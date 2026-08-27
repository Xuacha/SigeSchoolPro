package com.sigeschool.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
actual fun FilePicker(
    show: Boolean,
    onFileSelected: (List<List<String>>) -> Unit,
    onDismiss: () -> Unit
) {
    LaunchedEffect(show) {
        if (show) {
            val fileChooser = JFileChooser().apply {
                fileFilter = FileNameExtensionFilter("CSV Files", "csv")
                dialogTitle = "Seleccionar archivo CSV"
            }
            val result = fileChooser.showOpenDialog(null)
            if (result == JFileChooser.APPROVE_OPTION) {
                val file = fileChooser.selectedFile
                val rows = file.readLines().map { line ->
                    line.split(",").map { it.trim().removeSurrounding("\"") }
                }
                onFileSelected(rows)
            }
            onDismiss()
        }
    }
}
