package com.sigeschool.presentation.screens.behavior

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyCitationScreen(
    studentId: String,
    viewModel: BehaviorViewModel,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(studentId) {
        viewModel.loadStudentBehavior(studentId)
    }

    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Observador y Citaciones a Acudientes") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Citación")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when (val state = uiState) {
                is BehaviorUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is BehaviorUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is BehaviorUiState.Success -> {
                    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                        Text("Casos de Convivencia", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))

                        if (state.cases.isEmpty()) {
                            Text("No hay casos convivenciales registrados.", style = MaterialTheme.typography.bodyMedium)
                        } else {
                            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(state.cases) { caso ->
                                    Card(modifier = Modifier.fillMaxWidth()) {
                                        ListItem(
                                            headlineContent = { Text(caso.description) },
                                            supportingContent = { Text("Estado: ${caso.status} | Resolución: ${caso.resolution ?: 'Sin resolución'}") },
                                            leadingContent = { Icon(Icons.Default.Warning, contentDescription = null) }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        Text("Citaciones a Acudientes", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))

                        if (state.citations.isEmpty()) {
                            Text("No hay citaciones programadas.", style = MaterialTheme.typography.bodyMedium)
                        } else {
                            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(state.citations) { cit ->
                                    Card(modifier = Modifier.fillMaxWidth()) {
                                        ListItem(
                                            headlineContent = { Text(cit.reason) },
                                            supportingContent = { Text("Asistencia: ${if (cit.attended) 'ATENDIDO' else 'PENDIENTE'}") },
                                            leadingContent = { Icon(Icons.Default.EventNote, contentDescription = null) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        var reason by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Programar Citación a Acudiente") },
            text = {
                OutlinedTextField(
                    value = reason,
                    onValueChange = { reason = it },
                    label = { Text("Motivo de la citación") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (reason.isNotBlank()) {
                            viewModel.createCitation(studentId, reason)
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Programar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
