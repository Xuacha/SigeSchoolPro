package com.sigeschool.presentation.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sigeschool.presentation.viewmodel.admin.BackupViewModel
import com.sigeschool.domain.service.BackupInfo
import com.sigeschool.domain.service.RemoteBackupInfo
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupScreen(viewModel: BackupViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Backup y Recuperación") },
                actions = {
                    IconButton(onClick = { viewModel.refreshRemoteBackups() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refrescar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Respaldo en la Nube", style = MaterialTheme.typography.titleLarge)
                        Text("Copia de seguridad cifrada en Supabase Storage", style = MaterialTheme.typography.bodyMedium)
                    }
                    Button(
                        onClick = { viewModel.createBackup() },
                        enabled = !state.isCreating,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        if (state.isCreating) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(Icons.Default.Backup, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Respaldar Ahora")
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Text("Backups Disponibles", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))

            if (state.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.remoteBackups) { backup ->
                        RemoteBackupItem(backup)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            
            Text("Historial de Actividad", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            
            LazyColumn(modifier = Modifier.height(200.dp)) {
                items(state.logs) { log ->
                    LogItem(log)
                }
            }
        }
    }
}

@Composable
fun RemoteBackupItem(backup: RemoteBackupInfo) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.CloudDownload, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(backup.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    formatTimestamp(backup.createdAt),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            TextButton(onClick = { /* Restaurar */ }) {
                Text("RESTAURAR")
            }
        }
    }
}

@Composable
fun LogItem(log: BackupInfo) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.History, 
            contentDescription = null, 
            tint = if (log.estado == "EXITOSO") Color.Green else Color.Red,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(formatTimestamp(log.fecha), style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.width(8.dp))
        Text(log.estado, style = MaterialTheme.typography.bodySmall)
        if (log.error != null) {
            Spacer(Modifier.width(8.dp))
            Text(log.error ?: "", style = MaterialTheme.typography.bodySmall, color = Color.Red)
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    val dt = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${dt.dayOfMonth}/${dt.monthNumber}/${dt.year} ${dt.hour}:${dt.minute}"
}
