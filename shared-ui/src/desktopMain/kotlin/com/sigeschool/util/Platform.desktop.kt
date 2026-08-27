package com.sigeschool.util

import androidx.compose.runtime.Composable
import java.io.File
import java.net.URI
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
actual fun SharePdfFile(pdfBytes: ByteArray, fileName: String) {
    // En Desktop, "Compartir" se traduce en "Guardar como"
    val fileChooser = JFileChooser().apply {
        dialogTitle = "Guardar Recibo"
        selectedFile = File(fileName)
        fileFilter = FileNameExtensionFilter("Documentos PDF", "pdf")
    }

    val userSelection = fileChooser.showSaveDialog(null)
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        var fileToSave = fileChooser.selectedFile
        if (!fileToSave.name.lowercase().endsWith(".pdf")) {
            fileToSave = File(fileToSave.absolutePath + ".pdf")
        }
        try {
            fileToSave.writeBytes(pdfBytes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
actual fun OpenExternalUrl(url: String) {
    try {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop.getDesktop().browse(URI(url))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
