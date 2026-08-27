package com.sigeschool.presentation.screens.sie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.sie.GradingScale
import com.sigeschool.domain.model.sie.ScaleRange
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradingScaleScreen(
    viewModel: SieViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // viewModel.loadScales("INST-001") // ID de ejemplo
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Configuración SIE - Decreto 1290") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Escala")
            }
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding).fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.scales) { scale ->
                    GradingScaleCard(scale)
                }
            }
        }
    }

    if (showAddDialog) {
        AddScaleDialog(
            onDismiss = { showAddDialog = false },
            onSave = { scale ->
                // viewModel.saveScale(scale)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun AddScaleDialog(
    onDismiss: () -> Unit,
    onSave: (GradingScale) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var minScore by remember { mutableStateOf("0.0") }
    var maxScore by remember { mutableStateOf("5.0") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva Escala Valorativa") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre de la Escala") })
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = minScore, onValueChange = { minScore = it }, label = { Text("Min") }, modifier = Modifier.weight(1f))
                    OutlinedTextField(value = maxScore, onValueChange = { maxScore = it }, label = { Text("Max") }, modifier = Modifier.weight(1f))
                }
                Text("Nota: Por defecto se crearán los rangos del Decreto 1290 (Superior, Alto, Básico, Bajo).", style = MaterialTheme.typography.bodySmall)
            }
        },
        confirmButton = {
            Button(onClick = {
                val scale = GradingScale(
                    id = "scale_${Clock.System.now().toEpochMilliseconds()}",
                    institutionId = "INST-001",
                    name = name,
                    minScore = minScore.toDoubleOrNull() ?: 0.0,
                    maxScore = maxScore.toDoubleOrNull() ?: 5.0,
                    isDefault = true,
                    ranges = listOf(
                        ScaleRange("r1", "", "Superior", 4.6, 5.0, "Desempeño excepcional", "#4CAF50"),
                        ScaleRange("r2", "", "Alto", 4.0, 4.5, "Buen desempeño", "#2196F3"),
                        ScaleRange("r3", "", "Básico", 3.0, 3.9, "Desempeño mínimo", "#FFC107"),
                        ScaleRange("r4", "", "Bajo", 0.0, 2.9, "No alcanza objetivos", "#F44336")
                    )
                )
                onSave(scale)
            }) { Text("Crear") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

@Composable
fun GradingScaleCard(scale: GradingScale) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = scale.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Rango: ${scale.minScore} - ${scale.maxScore}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            scale.ranges.forEach { range ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(range.name)
                    Text("${range.minLimit} a ${range.maxLimit}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
