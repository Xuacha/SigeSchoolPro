package com.sigeschool.presentation.screens.parent

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
fun ParentDashboardScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToPayment: (String, String) -> Unit,
    onCheckConsent: (String, String) -> Unit
) {
    // Simulando chequeo de consentimiento (en real vendría de un ViewModel)
    val needsConsent = true 

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Portal de Acudientes") },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Mis Hijos", style = MaterialTheme.typography.headlineSmall)
            }
            
            // Dummy list of students
            items(listOf("Juan Perez" to "101", "Maria Perez" to "102")) { (name, id) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { 
                        if (needsConsent) {
                            onCheckConsent(id, name)
                        } else {
                            onNavigateToPayment(id, name)
                        }
                    }
                ) {
                    ListItem(
                        headlineContent = { Text(name) },
                        supportingContent = { Text("Grado 5A - Saldo: $150.000") },
                        leadingContent = { Icon(Icons.Default.Person, null) },
                        trailingContent = { 
                            if (needsConsent) {
                                Icon(Icons.Default.Warning, contentDescription = "Requiere Consentimiento", tint = MaterialTheme.colorScheme.error)
                            } else {
                                Icon(Icons.Default.Payments, contentDescription = "Pagar")
                            }
                        }
                    )
                }
            }

            item {
                Text("Notificaciones Recientes", style = MaterialTheme.typography.titleLarge)
            }

            items(listOf("Inasistencia - 15/07/2026", "Circular - Reunión de Padres")) { notif ->
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )) {
                    ListItem(
                        headlineContent = { Text(notif) },
                        leadingContent = { Icon(Icons.Default.Notifications, null) }
                    )
                }
            }
        }
    }
}
