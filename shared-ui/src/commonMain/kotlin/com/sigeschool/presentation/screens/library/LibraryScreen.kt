package com.sigeschool.presentation.screens.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Biblioteca") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Libro")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when (val state = uiState) {
                is LibraryUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is LibraryUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is LibraryUiState.Success -> {
                    if (state.libros.isEmpty()) {
                        Text(
                            text = "No hay libros registrados en el catálogo.",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(state.libros) { libro ->
                                Card(modifier = Modifier.fillMaxWidth()) {
                                    ListItem(
                                        headlineContent = { Text(libro.titulo) },
                                        supportingContent = { Text("Autor: ${libro.autor} | Disponibles: ${libro.ejemplaresDisponibles}/${libro.ejemplaresTotales}") },
                                        leadingContent = { Icon(Icons.Default.Book, contentDescription = null) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        var titulo by remember { mutableStateOf("") }
        var autor by remember { mutableStateOf("") }
        var isbn by remember { mutableStateOf("") }
        var cantidad by remember { mutableStateOf("1") }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Registrar Nuevo Libro") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = { Text("Título del Libro") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = autor,
                        onValueChange = { autor = it },
                        label = { Text("Autor") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = isbn,
                        onValueChange = { isbn = it },
                        label = { Text("ISBN (Opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = cantidad,
                        onValueChange = { cantidad = it },
                        label = { Text("Cantidad de Ejemplares") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (titulo.isNotBlank() && autor.isNotBlank()) {
                            viewModel.saveLibro(
                                titulo = titulo,
                                autor = autor,
                                isbn = isbn.ifBlank { null },
                                cantidad = cantidad.toIntOrNull() ?: 1
                            )
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Guardar")
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
