package com.sigeschool.presentation.screens.salaries

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.SalaryRecord
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSalaryDialog(
    employees: List<Employee>,
    onDismiss: () -> Unit,
    onConfirm: (SalaryRecord) -> Unit
) {
    var selectedEmployee by remember { mutableStateOf<Employee?>(null) }
    var amount by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("OTRO PAGO") }
    var observation by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var typeExpanded by remember { mutableStateOf(false) }

    val paymentTypes = listOf("SALARIO", "PARAFISCAL", "COMPRA", "SERVICIO", "OTRO PAGO")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Registrar Gasto / Pago Manual") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Persona/Proveedor (Opcional)", style = MaterialTheme.typography.labelMedium)
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedEmployee?.fullName ?: "Seleccionar Persona",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(text = { Text("Ninguno / Externo") }, onClick = { selectedEmployee = null; expanded = false })
                        employees.forEach { emp ->
                            DropdownMenuItem(text = { Text(emp.fullName) }, onClick = { selectedEmployee = emp; expanded = false })
                        }
                    }
                }

                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Monto ($)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Tipo de Gasto", style = MaterialTheme.typography.labelMedium)
                ExposedDropdownMenuBox(expanded = typeExpanded, onExpandedChange = { typeExpanded = it }) {
                    OutlinedTextField(
                        value = type,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = typeExpanded, onDismissRequest = { typeExpanded = false }) {
                        paymentTypes.forEach { t ->
                            DropdownMenuItem(text = { Text(t) }, onClick = { type = t; typeExpanded = false })
                        }
                    }
                }

                OutlinedTextField(
                    value = observation,
                    onValueChange = { observation = it },
                    label = { Text("Concepto / Observación") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(
                        SalaryRecord(
                            employeeId = selectedEmployee?.id ?: "EXTERNO",
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            type = type,
                            observation = observation,
                            date = Clock.System.now().toEpochMilliseconds(),
                            status = "PAGADO"
                        )
                    )
                },
                enabled = amount.isNotBlank() && observation.isNotBlank()
            ) {
                Text("Registrar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
