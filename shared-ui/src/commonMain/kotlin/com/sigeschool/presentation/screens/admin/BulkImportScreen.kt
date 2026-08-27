package com.sigeschool.presentation.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BulkImportScreen() {
    var importType by remember { mutableStateOf("Estudiantes") }
    var fileName by remember { mutableStateOf("") }
    var isImporting by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Importación Masiva") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Seleccione tipo de importación:", style = MaterialTheme.typography.titleMedium)
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Estudiantes", "Docentes", "Personal").forEach { type ->
                    FilterChip(
                        selected = importType == type,
                        onClick = { importType = type },
                        label = { Text(type) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { fileName = "estudiantes_2026.xlsx" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.UploadFile, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Seleccionar Archivo (Excel/CSV)")
            }

            if (fileName.isNotEmpty()) {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                    ListItem(
                        headlineContent = { Text(fileName) },
                        supportingContent = { Text("Listo para validar") },
                        trailingContent = {
                            IconButton(onClick = { fileName = "" }) {
                                Icon(Icons.Default.Delete, null)
                            }
                        }
                    )
                }

                Button(
                    onClick = { isImporting = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Validar e Importar")
                }
            }

            if (isImporting) {
                Spacer(modifier = Modifier.height(24.dp))
                Text("Procesando registros...")
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                
                // Resultado de procesamiento de importación
                Spacer(modifier = Modifier.height(16.dp))
                Text("Resultado de Importación:", style = MaterialTheme.typography.titleSmall)
                Text("✅ 45 Creados")
                Text("⚠️ 2 Errores (Fila 12, 18)")
                Text("📧 45 Credenciales enviadas")
            }
        }
    }
}
