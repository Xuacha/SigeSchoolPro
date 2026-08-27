package com.sigeschool.presentation.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.sigeschool.presentation.viewmodel.admin.BackupSecurityViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun BackupSecurityScreen(
    onBack: () -> Unit,
    viewModel: BackupSecurityViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seguridad y Master Key") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
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
            Icon(
                Icons.Default.Security,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(Modifier.height(16.dp))
            
            Text(
                "Gestión de Respaldo de Llaves",
                style = MaterialTheme.typography.headlineSmall
            )
            
            Spacer(Modifier.height(8.dp))
            
            Text(
                "Esta sección permite respaldar y recuperar la clave maestra de cifrado. Sin esta clave, los datos PII no podrán ser leídos.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            // Estado del Backup
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (state.hasRemoteBackup) 
                        MaterialTheme.colorScheme.primaryContainer 
                    else 
                        MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        if (state.hasRemoteBackup) Icons.Default.CloudDone else Icons.Default.CloudOff,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(
                            if (state.hasRemoteBackup) "Backup disponible en la nube" else "Sin backup en la nube",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            if (state.hasRemoteBackup) "Protegido con PIN" else "Se recomienda realizar un respaldo inmediato",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Formulario de PIN
            OutlinedTextField(
                value = state.pinInput,
                onValueChange = { viewModel.onPinChanged(it) },
                label = { Text("PIN de Seguridad (4+ dígitos)") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                singleLine = true
            )

            Spacer(Modifier.height(24.dp))

            // Botones de Acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { viewModel.createBackup() },
                    modifier = Modifier.weight(1f),
                    enabled = !state.isLoading && state.pinInput.isNotEmpty()
                ) {
                    Icon(Icons.Default.VpnKey, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Crear Backup")
                }

                OutlinedButton(
                    onClick = { viewModel.restoreBackup() },
                    modifier = Modifier.weight(1f),
                    enabled = !state.isLoading && state.hasRemoteBackup && state.pinInput.isNotEmpty()
                ) {
                    Text("Restaurar")
                }
            }

            if (state.isLoading) {
                Spacer(Modifier.height(16.dp))
                CircularProgressIndicator()
            }

            state.error?.let {
                Spacer(Modifier.height(16.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            state.successMessage?.let {
                Spacer(Modifier.height(16.dp))
                Text(it, color = Color(0xFF2E7D32))
            }
        }
    }
}
