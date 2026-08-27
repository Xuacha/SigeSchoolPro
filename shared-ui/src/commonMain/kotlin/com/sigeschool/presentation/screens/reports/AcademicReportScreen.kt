package com.sigeschool.presentation.screens.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.util.SharePdfFile
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademicReportScreen(
    studentId: String,
    period: Int,
    onBack: () -> Unit = {},
    viewModel: AcademicReportViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reporte de ${state.pdfData?.size ?: ""}") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Generador de Boletines", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = { viewModel.generateReport(studentId, period) },
                enabled = !state.isLoading
            ) {
                Icon(Icons.Default.PictureAsPdf, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (state.isLoading) "Generando..." else "Generar Boletín PDF")
            }
            
            if (state.isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator()
            }

            state.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            state.pdfData?.let { data ->
                Text("PDF Generado: ${data.size} bytes", color = MaterialTheme.colorScheme.primary)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(onClick = { /* Implementar previsualización */ }) {
                    Text("Ver PDF")
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                SharePdfFile(data, "Boletin_Periodo${period}.pdf")
            }
        }
    }
}
