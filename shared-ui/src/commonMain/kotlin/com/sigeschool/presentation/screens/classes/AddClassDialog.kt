package com.sigeschool.presentation.screens.classes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Class
import com.sigeschool.domain.model.Employee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClassDialog(
    teachers: List<Employee>,
    onDismiss: () -> Unit,
    onConfirm: (name: String, level: String, teacherId: String?) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var selectedTeacher by remember { mutableStateOf<Employee?>(null) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva Clase / Curso") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre de la Clase (ej: 5°A)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = level,
                    onValueChange = { level = it },
                    label = { Text("Nivel (ej: Primaria, Curso Libre)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Asignar Docente (Opcional)", style = MaterialTheme.typography.labelMedium)
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedTeacher?.fullName ?: "Sin asignar",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Sin asignar") },
                            onClick = { selectedTeacher = null; expanded = false }
                        )
                        teachers.forEach { teacher ->
                            DropdownMenuItem(
                                text = { Text(teacher.fullName) },
                                onClick = { selectedTeacher = teacher; expanded = false }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name, level, selectedTeacher?.id) },
                enabled = name.isNotBlank()
            ) {
                Text("Crear")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
