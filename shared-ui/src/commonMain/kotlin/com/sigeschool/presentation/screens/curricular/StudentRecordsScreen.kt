package com.sigeschool.presentation.screens.curricular

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Certificate
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun StudentRecordsScreen(
    studentId: String,
    onBack: () -> Unit,
    viewModel: StudentRecordsViewModel = koinViewModel { parametersOf(studentId) }
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text("Historial Académico")
                        uiState.student?.let { 
                            Text(
                                it.nombreCompleto, 
                                style = MaterialTheme.typography.labelMedium
                            ) 
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0, 
                    onClick = { selectedTab = 0 },
                    text = { Text("Calificaciones") }
                )
                Tab(
                    selected = selectedTab == 1, 
                    onClick = { selectedTab = 1 },
                    text = { Text("Certificados") }
                )
            }

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                when (selectedTab) {
                    0 -> GradesList(grades = uiState.grades)
                    1 -> CertificatesList(
                        certificates = uiState.certificates,
                        onGenerate = { viewModel.generateCertificate(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun GradesList(grades: List<Grade>) {
    if (grades.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay calificaciones registradas", color = MaterialTheme.colorScheme.outline)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(grades) { grade ->
                ListItem(
                    headlineContent = { Text(grade.subjectId) },
                    supportingContent = { Text(grade.periodId) },
                    trailingContent = { 
                        Text(
                            grade.score.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            color = if (grade.score >= 3.0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                        )
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun CertificatesList(
    certificates: List<Certificate>,
    onGenerate: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { onGenerate("STUDY") },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Add, null)
                Text("Constancia")
            }
            Button(
                onClick = { onGenerate("GRADES") },
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Add, null)
                Text("Certificado")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (certificates.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().weight(1f), contentAlignment = Alignment.Center) {
                Text("No hay certificados generados", color = MaterialTheme.colorScheme.outline)
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(certificates) { cert ->
                    ListItem(
                        headlineContent = { Text(cert.type) },
                        supportingContent = { Text("Expedido: ${cert.issueDate}") },
                        trailingContent = {
                            IconButton(onClick = { /* Descargar */ }) {
                                Icon(Icons.Default.Download, contentDescription = "Descargar")
                            }
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
