package com.sigeschool.presentation.screens.students

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.presentation.components.CameraCaptureComponent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun EnrollmentScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: EnrollmentViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showCamera by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is EnrollmentViewModel.UiEvent.SaveSuccess -> {
                    onNavigateToDetail(event.studentId)
                }
                is EnrollmentViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Matrícula de Estudiante") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        if (showCamera) {
            Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                CameraCaptureComponent(
                    onPhotoCaptured = { bytes ->
                        viewModel.onEvent(EnrollmentViewModel.EnrollmentEvent.PhotoCaptured(bytes))
                        showCamera = false
                    },
                    modifier = Modifier.fillMaxSize()
                )
                Button(
                    onClick = { showCamera = false },
                    modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
                ) {
                    Text("Cancelar")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Photo Section
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.photoBytes != null) {
                        // Aquí se mostraría la imagen capturada
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = "Foto capturada",
                            modifier = Modifier.size(100.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp),
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }

                Button(
                    onClick = { showCamera = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Rounded.CameraAlt, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Capturar Foto")
                }

                OutlinedTextField(
                    value = state.firstName,
                    onValueChange = { viewModel.onEvent(EnrollmentViewModel.EnrollmentEvent.EnteredFirstName(it)) },
                    label = { Text("Nombres") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = state.lastName,
                    onValueChange = { viewModel.onEvent(EnrollmentViewModel.EnrollmentEvent.EnteredLastName(it)) },
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = state.documentId,
                    onValueChange = { viewModel.onEvent(EnrollmentViewModel.EnrollmentEvent.EnteredDocumentId(it)) },
                    label = { Text("Documento de Identidad") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { viewModel.onEvent(EnrollmentViewModel.EnrollmentEvent.SaveStudent) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Rounded.Check, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Guardar Matrícula")
                }
            }
        }
    }
}
