package com.sigeschool.presentation.screens.grades

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student
import com.sigeschool.util.PdfSharer
import com.sigeschool.util.PdfPlatformGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
actual fun ReportButton(student: Student, grades: List<Grade>) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                val pdfBytes = PdfPlatformGenerator.generateStudentReport(student, grades)
                PdfSharer.sharePdf(context, pdfBytes, "boletin_${student.nombreCompleto}.pdf")
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
