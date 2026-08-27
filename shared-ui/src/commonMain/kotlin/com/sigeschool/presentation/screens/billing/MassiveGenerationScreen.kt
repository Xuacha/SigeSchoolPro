package com.sigeschool.presentation.screens.billing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sigeschool.presentation.viewmodel.billing.BillingEvent
import com.sigeschool.presentation.viewmodel.billing.BillingViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MassiveGenerationScreen(
    institutionId: String,
    onNavigateBack: () -> Unit,
    viewModel: BillingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    var concept by remember { mutableStateOf("Pensión Mes de Julio") }
    var amount by remember { mutableStateOf("450000") }
    var month by remember { mutableStateOf("7") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Generación Masiva") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = concept,
                onValueChange = { concept = it },
                label = { Text("Concepto") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Valor sugerido") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = month,
                onValueChange = { month = it },
                label = { Text("Mes (1-12)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                "Seleccionar Estudiantes (${uiState.students.size} disponibles)",
                style = MaterialTheme.typography.titleSmall
            )

            var selectedIds by remember { mutableStateOf(setOf<String>()) }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(uiState.students) { student ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedIds = if (selectedIds.contains(student.id)) {
                                    selectedIds - student.id
                                } else {
                                    selectedIds + student.id
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedIds.contains(student.id),
                            onCheckedChange = { checked ->
                                selectedIds = if (checked) {
                                    selectedIds + student.id
                                } else {
                                    selectedIds - student.id
                                }
                            }
                        )
                        Text(
                            text = student.nombreCompleto,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.onEvent(
                        BillingEvent.GenerateMassive(
                            studentIds = selectedIds.toList(),
                            concept = concept,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            month = month.toIntOrNull() ?: 1
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isMassiveGenerationLoading && selectedIds.isNotEmpty()
            ) {
                if (uiState.isMassiveGenerationLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Generar Facturas para ${selectedIds.size} estudiantes")
                }
            }
            
            if (uiState.error != null) {
                Text(uiState.error!!, color = MaterialTheme.colorScheme.error)
            }
        }
    }
    
    // Si se termina la generación, podríamos volver atrás o mostrar éxito
    LaunchedEffect(uiState.isMassiveGenerationLoading) {
        if (!uiState.isMassiveGenerationLoading && uiState.invoices.isNotEmpty()) {
            // onNavigateBack()
        }
    }
}
