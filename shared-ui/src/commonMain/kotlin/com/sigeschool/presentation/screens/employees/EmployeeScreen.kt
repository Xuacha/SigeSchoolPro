package com.sigeschool.presentation.screens.employees

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Employee
import com.sigeschool.util.FilePicker
import com.sigeschool.util.OpenWhatsApp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun EmployeeScreen(
    viewModel: EmployeeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showFilePicker by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    var employeeToEdit by remember { mutableStateOf<Employee?>(null) }

    if (showAddDialog) {
        AddEmployeeDialog(
            employee = employeeToEdit,
            onDismiss = { 
                showAddDialog = false
                employeeToEdit = null
            },
            onConfirm = { employee ->
                viewModel.addEmployee(employee)
                showAddDialog = false
                employeeToEdit = null
            }
        )
    }

    FilePicker(
        show = showFilePicker,
        onFileSelected = { rows ->
            viewModel.importEmployees(rows)
        },
        onDismiss = { showFilePicker = false }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Empleados") },
                actions = {
                    IconButton(onClick = { showFilePicker = true }) {
                        Icon(Icons.Default.FileUpload, contentDescription = "Importar Empleados")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Empleado")
            }
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
                items(uiState.employees) { employee ->
                    EmployeeCard(
                        employee = employee,
                        onEdit = {
                            employeeToEdit = it
                            showAddDialog = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EmployeeCard(
    employee: Employee,
    onEdit: (Employee) -> Unit
) {
    var triggerWhatsApp by remember { mutableStateOf(false) }

    if (triggerWhatsApp) {
        OpenWhatsApp(
            phoneNumber = employee.phone,
            message = "Hola ${employee.fullName}, te contacto desde la administración de la institución."
        )
        LaunchedEffect(Unit) { triggerWhatsApp = false }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = employee.fullName, style = MaterialTheme.typography.titleMedium)
            Text(text = "Cargo: ${employee.role}")
            Text(text = "DNI: ${employee.dni}")
            Text(text = "Email: ${employee.email}")
            Text(
                text = "Estado: ${employee.status}",
                color = if (employee.isActive) androidx.compose.ui.graphics.Color.Blue else androidx.compose.ui.graphics.Color.Red
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { triggerWhatsApp = true },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFF25D366)
                    )
                ) {
                    Text("WhatsApp", color = androidx.compose.ui.graphics.Color.White)
                }

                OutlinedButton(
                    onClick = { onEdit(employee) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Editar")
                }
            }
        }
    }
}
