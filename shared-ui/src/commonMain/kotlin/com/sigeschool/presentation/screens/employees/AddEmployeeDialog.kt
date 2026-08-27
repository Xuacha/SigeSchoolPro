package com.sigeschool.presentation.screens.employees

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.EmployeeStatus
import com.sigeschool.domain.model.UserRole
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployeeDialog(
    employee: Employee? = null,
    onDismiss: () -> Unit,
    onConfirm: (Employee) -> Unit
) {
    var firstName by remember { mutableStateOf(employee?.firstName ?: "") }
    var lastName by remember { mutableStateOf(employee?.lastName ?: "") }
    var dni by remember { mutableStateOf(employee?.dni ?: "") }
    var email by remember { mutableStateOf(employee?.email ?: "") }
    var phone by remember { mutableStateOf(employee?.phone ?: "") }
    var role by remember { mutableStateOf(employee?.role ?: UserRole.DOCENTE) }
    var status by remember { mutableStateOf(employee?.status ?: EmployeeStatus.ACTIVO) }
    var qualification by remember { mutableStateOf(employee?.qualification ?: "") }
    var specialization by remember { mutableStateOf(employee?.specialization ?: "") }
    var department by remember { mutableStateOf(employee?.department ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (employee == null) "Agregar Personal" else "Editar Personal") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("Nombres") })
                OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Apellidos") })
                OutlinedTextField(value = dni, onValueChange = { dni = it }, label = { Text("DNI/Cédula") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Teléfono") })
                
                Text("Rol", style = MaterialTheme.typography.labelMedium)
                var roleExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(expanded = roleExpanded, onExpandedChange = { roleExpanded = it }) {
                    OutlinedTextField(
                        value = role.name,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = roleExpanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(expanded = roleExpanded, onDismissRequest = { roleExpanded = false }) {
                        UserRole.entries.forEach { entry ->
                            DropdownMenuItem(text = { Text(entry.name) }, onClick = { role = entry; roleExpanded = false })
                        }
                    }
                }

                Text("Estado", style = MaterialTheme.typography.labelMedium)
                var statusExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(expanded = statusExpanded, onExpandedChange = { statusExpanded = it }) {
                    OutlinedTextField(
                        value = status.name,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusExpanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(expanded = statusExpanded, onDismissRequest = { statusExpanded = false }) {
                        EmployeeStatus.entries.forEach { entry ->
                            DropdownMenuItem(text = { Text(entry.name) }, onClick = { status = entry; statusExpanded = false })
                        }
                    }
                }

                OutlinedTextField(value = qualification, onValueChange = { qualification = it }, label = { Text("Formación") })
                OutlinedTextField(value = specialization, onValueChange = { specialization = it }, label = { Text("Especialidad") })
                OutlinedTextField(value = department, onValueChange = { department = it }, label = { Text("Departamento") })
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(
                        Employee(
                            id = employee?.id ?: "",
                            firstName = firstName,
                            lastName = lastName,
                            dni = dni,
                            email = email,
                            phone = phone,
                            role = role,
                            status = status,
                            qualification = qualification,
                            specialization = specialization,
                            department = department,
                            hireDate = employee?.hireDate ?: Clock.System.now().toEpochMilliseconds()
                        )
                    )
                },
                enabled = firstName.isNotBlank() && lastName.isNotBlank() && dni.isNotBlank()
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
