package com.sigeschool.presentation.screens.sie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoevaluacionConfigScreen(
    viewModel: ConfiguracionPromocionViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val config = uiState.config

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración de Promoción (SIEE)") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        // Icono volver
                    }
                }
            )
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (config != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Defina los criterios institucionales de evaluación y promoción según el Decreto 1290 de 2009.",
                    style = MaterialTheme.typography.bodySmall
                )

                ConfigField(
                    label = "Máximo de materias reprobadas",
                    value = config.maxFailedSubjects.toString(),
                    onValueChange = { 
                        it.toIntOrNull()?.let { valInt ->
                            viewModel.updateConfig(config.copy(maxFailedSubjects = valInt))
                        }
                    },
                    keyboardType = KeyboardType.Number
                )

                ConfigField(
                    label = "Porcentaje máximo de inasistencia (%)",
                    value = config.maxInattendancePercentage.toString(),
                    onValueChange = { 
                        it.toDoubleOrNull()?.let { valDouble ->
                            viewModel.updateConfig(config.copy(maxInattendancePercentage = valDouble))
                        }
                    },
                    keyboardType = KeyboardType.Decimal
                )

                ConfigField(
                    label = "Nota mínima de aprobación",
                    value = config.minimumPassingScore.toString(),
                    onValueChange = { 
                        it.toDoubleOrNull()?.let { valDouble ->
                            viewModel.updateConfig(config.copy(minimumPassingScore = valDouble))
                        }
                    },
                    keyboardType = KeyboardType.Decimal
                )

                ConfigField(
                    label = "Ponderación de la Autoevaluación (%)",
                    value = config.autoevaluacionWeight.toString(),
                    onValueChange = { 
                        it.toDoubleOrNull()?.let { valDouble ->
                            viewModel.updateConfig(config.copy(autoevaluacionWeight = valDouble))
                        }
                    },
                    keyboardType = KeyboardType.Decimal
                )

                Spacer(modifier = Modifier.weight(1f))

                if (uiState.saveSuccess) {
                    Text(
                        "Configuración guardada exitosamente",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = { viewModel.saveConfig() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isSaving
                ) {
                    if (uiState.isSaving) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text("Guardar Cambios Institucionales")
                    }
                }
            }
        }
    }
}

@Composable
private fun ConfigField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true
    )
}
