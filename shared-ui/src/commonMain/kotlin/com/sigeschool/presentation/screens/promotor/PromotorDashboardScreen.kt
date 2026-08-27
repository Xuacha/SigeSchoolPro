package com.sigeschool.presentation.screens.promotor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromotorDashboardScreen(
    viewModel: PromotorDashboardViewModel,
    onNavigateToEnroll: () -> Unit,
    onNavigateToMerge: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel de Promotor de Matrículas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToEnroll) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Matrícula")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Gestión Rápida de Inscripciones", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onNavigateToMerge,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.CompareArrows, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Gestionar Registros Duplicados (Fusión)")
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Matrículas Recientes", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            when (val state = uiState) {
                is PromotorUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is PromotorUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                is PromotorUiState.Success -> {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(state.recentStudents) { student ->
                            Card(modifier = Modifier.fillMaxWidth()) {
                                ListItem(
                                    headlineContent = { Text(student.nombres + " " + student.apellidos) },
                                    supportingContent = { Text("Documento: ${student.numeroDocumento}") },
                                    leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
