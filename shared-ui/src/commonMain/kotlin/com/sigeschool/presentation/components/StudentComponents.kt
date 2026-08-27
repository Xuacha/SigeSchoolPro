package com.sigeschool.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus

@Composable
fun StudentStatusChip(status: StudentStatus) {
    val color = when (status) {
        StudentStatus.MATRICULADO -> Color(0xFF4CAF50)
        StudentStatus.ASPIRANTE -> Color(0xFF2196F3)
        StudentStatus.SUSPENDIDO -> Color(0xFFFF9800)
        StudentStatus.RETIRADO -> Color(0xFFF44336)
    }
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
    ) {
        Text(
            text = status.name,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StudentStatusChipCompact(status: StudentStatus) {
    val color = when (status) {
        StudentStatus.MATRICULADO -> Color(0xFF4CAF50)
        StudentStatus.ASPIRANTE -> Color(0xFF2196F3)
        StudentStatus.SUSPENDIDO -> Color(0xFFFF9800)
        StudentStatus.RETIRADO -> Color(0xFFF44336)
    }
    Text(
        text = "• ${status.name}",
        style = MaterialTheme.typography.labelSmall,
        color = color,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun StudentStatusDialog(
    student: Student,
    onDismiss: () -> Unit,
    onStatusChange: (StudentStatus, String?) -> Unit
) {
    var selectedStatus by remember { mutableStateOf(student.estadoMatricula) }
    var reason by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Gestionar Estado: ${student.nombreCompleto}") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Seleccione el nuevo estado:")
                
                StudentStatus.entries.forEach { status ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedStatus == status,
                            onClick = { selectedStatus = status }
                        )
                        Text(status.name, modifier = Modifier.padding(start = 8.dp))
                    }
                }

                if (selectedStatus == StudentStatus.RETIRADO) {
                    OutlinedTextField(
                        value = reason,
                        onValueChange = { reason = it },
                        label = { Text("Motivo del retiro") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onStatusChange(selectedStatus, if (selectedStatus == StudentStatus.RETIRADO) reason else null)
                }
            ) {
                Text("Guardar Cambios")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun ConflictResolutionDialog(
    studentName: String,
    onResolve: (Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Conflicto Detectado") },
        text = {
            Column {
                Text("Hay cambios remotos y locales para el estudiante:")
                Text(studentName, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("¿Qué versión desea conservar?", style = MaterialTheme.typography.bodyMedium)
            }
        },
        confirmButton = {
            Button(
                onClick = { onResolve(true) }, // Usar Remoto
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Usar Remota (Supabase)")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = { onResolve(false) } // Usar Local
            ) {
                Text("Usar Local")
            }
        }
    )
}
