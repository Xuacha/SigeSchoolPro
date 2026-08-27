package com.sigeschool.presentation.screens.students

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun StudentDetailScreen(
    studentId: String,
    onNavigateBack: () -> Unit,
    onNavigateToEdit: (String) -> Unit,
    onNavigateToIdCard: (String) -> Unit,
    onNavigateToBehavior: (String) -> Unit,
    onNavigateToRecords: (String) -> Unit,
    onNavigateToGrades: (String) -> Unit,
    viewModel: StudentDetailViewModel = koinViewModel()
) {
    val student by viewModel.student.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(studentId) {
        viewModel.loadStudent(studentId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(student?.nombreCompleto ?: "Detalle Estudiante") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (student == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Estudiante no encontrado")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Info
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* showPhotoDialog = true */ }) {
                            Icon(Icons.Rounded.CameraAlt, contentDescription = "Cambiar Foto", tint = MaterialTheme.colorScheme.primary)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = student?.nombreCompleto ?: "",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "Documento: ${student?.dni}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                Text(text = "Gestión Académica y Disciplinaria", style = MaterialTheme.typography.titleMedium)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DetailActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Perfil / Editar",
                        icon = Icons.Rounded.Person,
                        onClick = { onNavigateToEdit(studentId) }
                    )
                    DetailActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Carnet PDF/QR",
                        icon = Icons.Rounded.QrCode,
                        onClick = { onNavigateToIdCard(studentId) }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DetailActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Comportamiento",
                        icon = Icons.Rounded.Gavel,
                        onClick = { onNavigateToBehavior(studentId) }
                    )
                    DetailActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Documentación",
                        icon = Icons.Rounded.Folder,
                        onClick = { onNavigateToRecords(studentId) }
                    )
                }

                DetailActionCard(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Calificaciones Académicas",
                    icon = Icons.Rounded.Grade,
                    onClick = { onNavigateToGrades(studentId) }
                )
            }
        }
    }
}

@Composable
fun DetailActionCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, style = MaterialTheme.typography.labelMedium, maxLines = 1)
        }
    }
}
