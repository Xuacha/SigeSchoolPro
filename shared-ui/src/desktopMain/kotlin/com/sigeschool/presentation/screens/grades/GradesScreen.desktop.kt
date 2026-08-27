package com.sigeschool.presentation.screens.grades

import androidx.compose.runtime.Composable
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.util.ReportGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.awt.Desktop

@Composable
actual fun ReportButton(student: Student, grades: List<Grade>) {
    IconButton(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val pdfBytes = ReportGenerator.generateBoletin(student, grades)
                val file = File(System.getProperty("user.home"), "boletin_${student.nombre}_${student.apellido}.pdf")
                file.writeBytes(pdfBytes)
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file)
                }
            }
        },
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.PictureAsPdf,
            contentDescription = "Exportar PDF",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
