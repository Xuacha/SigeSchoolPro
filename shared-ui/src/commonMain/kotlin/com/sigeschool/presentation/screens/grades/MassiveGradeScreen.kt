package com.sigeschool.presentation.screens.grades

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.util.FilePicker
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun MassiveGradeScreen(
    onBack: () -> Unit,
    viewModel: MassiveGradeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val userRole by viewModel.userRole.collectAsState()
    
    var showFilePicker by remember { mutableStateOf(false) }

    val isPrivileged = userRole in listOf("ADMINISTRADOR", "SECRETARIA", "COORDINADOR")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carga Masiva de Notas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isPrivileged) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "🔓 Modo Administrativo: Acceso a carga global de notas.",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(12.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Button(
                onClick = { showFilePicker = true },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.FileOpen, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Seleccionar Archivo (CSV/Excel)")
            }

            if (state.isLoading) {
                Spacer(Modifier.height(16.dp))
                CircularProgressIndicator()
                Text("Procesando notas...", modifier = Modifier.padding(top = 8.dp))
            }

            state.error?.let {
                Spacer(Modifier.height(16.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            state.result?.let { summary ->
                Spacer(Modifier.height(16.dp))
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Resumen de Importación", style = MaterialTheme.typography.titleMedium)
                        Text("✅ Exitosos: ${summary.inserted}")
                        
                        if (summary.errors.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Text("❌ Errores (${summary.errors.size}):", color = MaterialTheme.colorScheme.error)
                            LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                                items(summary.errors) { error ->
                                    Text("- $error", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    FilePicker(
        show = showFilePicker,
        onFileSelected = { rows ->
            viewModel.processImportedData(rows)
            showFilePicker = false
        },
        onDismiss = { showFilePicker = false }
    )
}
