package com.sigeschool.presentation.screens.parent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentSettingsScreen(onBack: () -> Unit) {
    var whatsappEnabled by remember { mutableStateOf(true) }
    var emailEnabled by remember { mutableStateOf(true) }
    var pushEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preferencias de Notificación") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Elija cómo desea recibir las comunicaciones de la institución:", style = MaterialTheme.typography.bodyLarge)
            
            Spacer(modifier = Modifier.height(24.dp))

            NotificationToggle(
                title = "WhatsApp",
                description = "Notificaciones instantáneas de asistencia y urgencias",
                checked = whatsappEnabled,
                onCheckedChange = { whatsappEnabled = it }
            )
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            NotificationToggle(
                title = "Correo Electrónico",
                description = "Circulares, boletines y reportes académicos",
                checked = emailEnabled,
                onCheckedChange = { emailEnabled = it }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            NotificationToggle(
                title = "Notificaciones Push (App)",
                description = "Alertas en tiempo real en este dispositivo",
                checked = pushEnabled,
                onCheckedChange = { pushEnabled = it }
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = { 
                    // Lógica para guardar preferencias del acudiente
                    println("Guardando preferencias de notificación...")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}

@Composable
fun NotificationToggle(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
