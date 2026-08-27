package com.sigeschool.presentation.screens.students

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ConsentDialog(
    studentId: String,
    studentName: String,
    onConsentCaptured: (com.sigeschool.domain.model.Consent) -> Unit,
    onDismiss: () -> Unit,
    viewModel: ConsentViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    var acudienteNombre by remember { mutableStateOf("") }
    var acudienteDni by remember { mutableStateOf("") }
    var acudienteParentesco by remember { mutableStateOf("") }
    var acudienteEmail by remember { mutableStateOf("") }
    var acudienteTelefono by remember { mutableStateOf("") }
    var showFullPolicy by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Autorización de Tratamiento de Datos") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "El estudiante $studentName es menor de edad. Se requiere la autorización de su representante legal.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Divider()

                Text("Datos del Acudiente / Representante", style = MaterialTheme.typography.titleSmall)

                OutlinedTextField(
                    value = acudienteNombre,
                    onValueChange = { acudienteNombre = it },
                    label = { Text("Nombre Completo del Acudiente") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = acudienteDni,
                    onValueChange = { acudienteDni = it },
                    label = { Text("Documento de Identidad") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = acudienteParentesco,
                    onValueChange = { acudienteParentesco = it },
                    label = { Text("Parentesco (Padre, Madre, Tutor)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = acudienteEmail,
                    onValueChange = { acudienteEmail = it },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = acudienteTelefono,
                    onValueChange = { acudienteTelefono = it },
                    label = { Text("Teléfono de Contacto") },
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()

                Text("Finalidades del Tratamiento", style = MaterialTheme.typography.titleSmall)
                
                uiState.granularConsent.forEach { (key, value) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = value,
                            onCheckedChange = { viewModel.updateGranularConsent(key, it) },
                            enabled = key != "academico" // El académico suele ser obligatorio para la prestación del servicio
                        )
                        Text(
                            text = when(key) {
                                "academico" -> "Gestión Académica (Obligatorio)"
                                "financiero" -> "Gestión Financiera y Cobro"
                                "comunicaciones" -> "Comunicaciones Institucionales"
                                "uso_imagen" -> "Uso de Imagen en Redes/Web"
                                else -> key
                            },
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                TextButton(onClick = { showFullPolicy = true }) {
                    Text("Ver Política de Privacidad Completa")
                }

                if (showFullPolicy) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    ) {
                        Text(
                            text = uiState.activePolicy?.contenidoTexto ?: "Cargando política...",
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                enabled = acudienteNombre.isNotBlank() && acudienteDni.isNotBlank() && acudienteEmail.isNotBlank(),
                onClick = {
                    // Generar el objeto Consent
                    val consent = com.sigeschool.domain.model.Consent(
                        id = com.sigeschool.domain.util.randomUUID(),
                        studentId = studentId,
                        acudienteNombre = acudienteNombre,
                        acudienteDni = acudienteDni,
                        acudienteParentesco = acudienteParentesco,
                        acudienteEmail = acudienteEmail,
                        acudienteTelefono = acudienteTelefono,
                        politicaId = uiState.activePolicy?.id ?: "unknown",
                        fechaAceptacion = kotlinx.datetime.Clock.System.now().toEpochMilliseconds(),
                        deviceInfo = "Android/Desktop Device",
                        hashFirmaDigital = "HASH-${acudienteDni}-${kotlinx.datetime.Clock.System.now().toEpochMilliseconds()}",
                        granularConsent = uiState.granularConsent
                    )
                    onConsentCaptured(consent)
                }
            ) {
                Text("Aceptar y Firmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
