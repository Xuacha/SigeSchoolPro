package com.sigeschool.presentation.screens.parent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.presentation.components.SignaturePad
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsentScreen(
    studentId: String,
    studentName: String,
    onCompleted: () -> Unit,
    viewModel: ConsentViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var nombre by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var parentesco by remember { mutableStateOf("Padre/Madre") }
    var email by remember { mutableStateOf("") }
    var signaturePoints by remember { mutableStateOf(listOf<androidx.compose.ui.geometry.Offset>()) }

    LaunchedEffect(uiState) {
        if (uiState is ConsentUiState.Completed) {
            onCompleted()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Consentimiento Ley 1581") }) }
    ) { padding ->
        when (val state = uiState) {
            is ConsentUiState.Loading -> CircularProgressIndicator()
            is ConsentUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Autorización de Tratamiento de Datos Personales",
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Text(
                        state.policy.contenidoTexto,
                        style = MaterialTheme.typography.bodySmall
                    )

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre del Acudiente") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = dni,
                        onValueChange = { dni = it },
                        label = { Text("Cédula / ID") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Firma Digital (Dibuje abajo)", style = MaterialTheme.typography.titleMedium)
                    
                    Surface(
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        SignaturePad(
                            onSignatureCaptured = { signaturePoints = it },
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.submitConsent(
                                studentId = studentId,
                                nombre = nombre,
                                dni = dni,
                                parentesco = parentesco,
                                email = email,
                                telefono = "",
                                policyId = state.policy.id,
                                signaturePoints = signaturePoints
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = nombre.isNotEmpty() && dni.isNotEmpty() && signaturePoints.isNotEmpty()
                    ) {
                        Text("Aceptar y Continuar")
                    }
                }
            }
            is ConsentUiState.Error -> Text("Error al cargar la política de privacidad")
            else -> {}
        }
    }
}
