package com.sigeschool.presentation.screens.certificates

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Student
import com.sigeschool.presentation.screens.students.StudentViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun GenerateCertificateScreen(
    onBack: () -> Unit = {},
    studentViewModel: StudentViewModel = koinViewModel()
) {
    val studentState by studentViewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedStudent by remember { mutableStateOf<Student?>(null) }
    var certificateType by remember { mutableStateOf("Constancia de Estudio") }
    var showStudentList by remember { mutableStateOf(false) }

    val filteredStudents = studentState.students.filter {
        it.nombreCompleto.contains(searchQuery, ignoreCase = true) || it.dni.contains(searchQuery)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Generar Certificado") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.CardMembership,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                "Emisión de Documentos Legales",
                style = MaterialTheme.typography.titleMedium
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { 
                        searchQuery = it
                        showStudentList = it.isNotEmpty() && selectedStudent?.nombreCompleto != it
                    },
                    label = { Text("Buscar Estudiante (Nombre o DNI)") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        if (selectedStudent != null) {
                            Text("SELECCIONADO", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                )

                if (showStudentList && filteredStudents.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 64.dp)
                            .heightIn(max = 200.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        LazyColumn {
                            items(filteredStudents) { student ->
                                DropdownMenuItem(
                                    text = { Text("${student.nombreCompleto} (${student.grado})") },
                                    onClick = {
                                        selectedStudent = student
                                        searchQuery = student.nombreCompleto
                                        showStudentList = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            var expanded by remember { mutableStateOf(false) }
            val types = listOf(
                "Constancia de Estudio", 
                "Certificado de Notas", 
                "Certificado de Conducta", 
                "Diploma de Excelencia",
                "Certificado de Retiro"
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = certificateType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de Documento") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    types.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                certificateType = type
                                expanded = false
                            }
                        )
                    }
                }
            }

            if (selectedStudent != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Datos para el documento:", style = MaterialTheme.typography.labelLarge)
                        Text("Estudiante: ${selectedStudent?.nombreCompleto}")
                        Text("Grado: ${selectedStudent?.grado}")
                        Text("DNI: ${selectedStudent?.dni}")
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Lógica para generar PDF con los datos seleccionados */ },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedStudent != null
            ) {
                Text("Generar y Firmar Digitalmente")
            }
        }
    }
}
