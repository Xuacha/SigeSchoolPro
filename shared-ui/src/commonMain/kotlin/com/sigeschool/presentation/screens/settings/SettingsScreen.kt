package com.sigeschool.presentation.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val snackbarHostState = androidx.compose.runtime.remember { SnackbarHostState() }

    LaunchedEffect(uiState.message, uiState.error) {
        uiState.message?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessage()
        }
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración de Institución") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    if (uiState.isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        IconButton(onClick = { viewModel.saveChanges() }) {
                            Icon(Icons.Default.Save, contentDescription = "Guardar")
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(scrollState)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Información General", style = MaterialTheme.typography.titleMedium)
                
                OutlinedTextField(
                    value = uiState.institution.name,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(name = it)) },
                    label = { Text("Nombre de la Institución") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.institution.nit,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(nit = it)) },
                    label = { Text("NIT") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.institution.daneNumber,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(daneNumber = it)) },
                    label = { Text("Número DANE") },
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()
                Text("Legal y Ubicación", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = uiState.institution.resolutionNumber,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(resolutionNumber = it)) },
                    label = { Text("Número de Resolución") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.institution.territorialEntity,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(territorialEntity = it)) },
                    label = { Text("Entidad Territorial") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.institution.address,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(address = it)) },
                    label = { Text("Dirección") },
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()
                Text("Contacto", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = uiState.institution.phone,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(phone = it)) },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.institution.whatsappNumber,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(whatsappNumber = it)) },
                    label = { Text("WhatsApp (formato internacional)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.institution.email,
                    onValueChange = { viewModel.onInstitutionChange(uiState.institution.copy(email = it)) },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(Modifier.height(32.dp))
                
                Button(
                    onClick = { viewModel.saveChanges() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isSaving
                ) {
                    Text("GUARDAR CONFIGURACIÓN")
                }
            }
        }
    }
}
