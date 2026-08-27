package com.sigeschool.presentation.screens.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import com.sigeschool.presentation.screens.students.StudentViewModel
import com.sigeschool.util.SharePdfFile
import com.sigeschool.presentation.screens.students.StudentUiState
import androidx.compose.material.icons.filled.ArrowDropDown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel = koinViewModel(),
    onGenerateCertificate: () -> Unit = {},
    onViewPuc: () -> Unit = {},
    onViewAccounting: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    var showBulkDialog by remember { mutableStateOf(false) }
    var pdfToShare by remember { mutableStateOf<Pair<ByteArray, String>?>(null) }

    if (pdfToShare != null) {
        SharePdfFile(pdfToShare!!.first, pdfToShare!!.second)
        // Reset after sharing to avoid re-triggering on recomposition
        LaunchedEffect(pdfToShare) {
            pdfToShare = null
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Reportes Premium") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Generación de Documentos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Card(
                        onClick = { showBulkDialog = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        ListItem(
                            headlineContent = { Text("Boletines Masivos") },
                            supportingContent = { Text("Generar PDF por grado y sección") },
                            leadingContent = { Icon(Icons.Default.Description, null) },
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                        )
                    }

                    Card(
                        onClick = onGenerateCertificate,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ListItem(
                            headlineContent = { Text("Certificados de Estudios") },
                            supportingContent = { Text("Generar documentos de graduación y asistencia") },
                            leadingContent = { Icon(Icons.Default.Assessment, null) }
                        )
                    }

                    Card(
                        onClick = onViewPuc,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ListItem(
                            headlineContent = { Text("Plan de Cuentas (PUC)") },
                            supportingContent = { Text("Gestionar catálogo contable institucional") },
                            leadingContent = { Icon(Icons.Default.AccountBalance, null) }
                        )
                    }

                    Card(
                        onClick = onViewAccounting,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ListItem(
                            headlineContent = { Text("Asientos Contables") },
                            supportingContent = { Text("Registro de débitos y créditos") },
                            leadingContent = { Icon(Icons.Default.Assessment, null) }
                        )
                    }

                    Card(
                        onClick = { /* Informe Personalizado */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ListItem(
                            headlineContent = { Text("Informe Personalizado") },
                            supportingContent = { Text("Reportes a medida") },
                            leadingContent = { Icon(Icons.Default.Description, null) }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ranking de Rendimiento (Top 10)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            if (state.isLoading && state.topStudents.isEmpty()) {
                item {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            itemsIndexed(state.topStudents) { index, ranking ->
                val medalColor = when (index) {
                    0 -> Color(0xFFFFD700) // Gold
                    1 -> Color(0xFFC0C0C0) // Silver
                    2 -> Color(0xFFCD7F32) // Bronze
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    ListItem(
                        leadingContent = {
                            Surface(
                                shape = MaterialTheme.shapes.small,
                                color = medalColor,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${index + 1}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        },
                        headlineContent = { Text(ranking.student.nombreCompleto) },
                        supportingContent = { Text("${ranking.student.grado} - ${ranking.student.seccion}") },
                        trailingContent = {
                            Text(
                                text = ((ranking.average * 10).toInt() / 10.0).toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }
            }
        }
    }

    if (showBulkDialog) {
        val studentViewModel: StudentViewModel = koinViewModel()
        val studentUiState by studentViewModel.uiState.collectAsState()
        
        BulkReportDialog(
            onDismiss = { showBulkDialog = false },
            onConfirm = { grado, seccion ->
                viewModel.generateBulkReports(grado, seccion, 1) { bytes ->
                    pdfToShare = Pair(bytes, "Reporte_${grado}_${seccion}.pdf")
                }
                showBulkDialog = false
            },
            availableGrades = studentUiState.availableGrades
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BulkReportDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit,
    availableGrades: List<String> = emptyList()
) {
    var grado by remember { mutableStateOf("") }
    var seccion by remember { mutableStateOf("") }
    var expandedGrado by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Generar Reportes Masivos") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = grado,
                        onValueChange = { grado = it },
                        label = { Text("Grado/Nivel") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = availableGrades.isNotEmpty(),
                        trailingIcon = {
                            if (availableGrades.isNotEmpty()) {
                                IconButton(onClick = { expandedGrado = true }) {
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                                }
                            }
                        }
                    )
                    DropdownMenu(
                        expanded = expandedGrado,
                        onDismissRequest = { expandedGrado = false }
                    ) {
                        availableGrades.forEach { gradeOption ->
                            DropdownMenuItem(
                                text = { Text(gradeOption) },
                                onClick = {
                                    grado = gradeOption
                                    expandedGrado = false
                                }
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = seccion,
                    onValueChange = { seccion = it },
                    label = { Text("Sección (ej: A)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(grado, seccion) }) {
                Text("Generar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
