package com.sigeschool.presentation.screens.grades

import androidx.compose.runtime.Composable
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student

@Composable
actual fun ReportButton(student: Student, grades: List<Grade>) {
    // La exportación a PDF para Web se implementará en una fase posterior
    // usando librerías compatibles con Wasm/JS
}
