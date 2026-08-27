package com.sigeschool.presentation.screens.import

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportDataScreen(
    viewModel: ImportViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var csvInput by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Importar Datos") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Ingrese el contenido CSV para importar acudientes.")
            
            OutlinedTextField(
                value = csvInput,
                onValueChange = { csvInput = it },
                label = { Text("Contenido CSV") },
                modifier = Modifier.fillMaxWidth().height(200.dp),
                placeholder = { Text("id,nombre,tipoDoc,documento,email,telefono,parentesco") }
            )
            
            Button(
                onClick = { 
                    viewModel.processCsvImport(csvInput)
                },
                enabled = !state.isLoading && csvInput.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Icon(Icons.Default.Upload, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Procesar Importación")
                }
            }

            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            state.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            state.successMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
