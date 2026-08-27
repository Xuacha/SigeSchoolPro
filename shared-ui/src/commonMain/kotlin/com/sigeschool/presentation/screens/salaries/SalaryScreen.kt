package com.sigeschool.presentation.screens.salaries

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.SalaryRecord
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun SalaryScreen(
    viewModel: SalaryViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    if (showAddDialog) {
        AddSalaryDialog(
            employees = uiState.employees,
            onDismiss = { showAddDialog = false },
            onConfirm = { record ->
                viewModel.addSalaryRecord(record)
                showAddDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pagos y Salarios") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Registrar Pago")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.error != null) {
                Text(
                    text = uiState.error ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (uiState.salaryRecords.isEmpty()) {
                Text(
                    text = "No hay registros de salarios",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.salaryRecords) { record ->
                        SalaryItem(record)
                    }
                }
            }
        }
    }
}

@Composable
fun SalaryItem(record: SalaryRecord) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Monto: S/ ${record.amount}", style = MaterialTheme.typography.titleMedium)
                Badge(
                    containerColor = if (record.status == "PAGADO") 
                        MaterialTheme.colorScheme.primaryContainer 
                    else 
                        MaterialTheme.colorScheme.errorContainer
                ) {
                    Text(record.status)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Tipo: ${record.type}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${record.date}", style = MaterialTheme.typography.labelSmall)
            if (record.observation.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = record.observation, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
