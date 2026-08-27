package com.sigeschool.presentation.screens.students

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Student
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.Clock
import com.sigeschool.presentation.components.CameraCaptureComponent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AddStudentDialog(
    onDismiss: () -> Unit,
    onSave: (Student, com.sigeschool.domain.model.Consent?) -> Unit,
    viewModel: StudentViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var grado by remember { mutableStateOf("") }
    var expandedGrado by remember { mutableStateOf(false) }
    var seccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var photoBytes by remember { mutableStateOf<ByteArray?>(null) }
    var showCamera by remember { mutableStateOf(false) }
    var showConsentDialog by remember { mutableStateOf(false) }
    var capturedConsent by remember { mutableStateOf<com.sigeschool.domain.model.Consent?>(null) }

    fun isMinor(birthDate: String): Boolean {
        return try {
            val parts = birthDate.split("-")
            if (parts.size == 3) {
                val year = parts[0].toInt()
                val month = parts[1].toInt()
                val day = parts[2].toInt()
                val birth = kotlinx.datetime.LocalDate(year, month, day)
                val today = kotlinx.datetime.Clock.System.now()
                    .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()).date
                
                val age = today.year - birth.year
                val isBeforeBirthday = if (today.month.ordinal < birth.month.ordinal) {
                    true
                } else if (today.month.ordinal == birth.month.ordinal) {
                    today.dayOfMonth < birth.dayOfMonth
                } else {
                    false
                }
                
                if (isBeforeBirthday) {
                    age - 1 < 18
                } else {
                    age < 18
                }
            } else false
        } catch (e: Exception) {
            false
        }
    }

    if (showConsentDialog) {
        ConsentDialog(
            studentId = "temp_new_student",
            studentName = "$nombre $apellido",
            onConsentCaptured = {
                capturedConsent = it
                showConsentDialog = false
            },
            onDismiss = { showConsentDialog = false }
        )
    }

    if (showCamera) {
        AlertDialog(
            onDismissRequest = { showCamera = false },
            title = { Text("Capturar Foto del Estudiante") },
            text = {
                Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
                    CameraCaptureComponent(
                        onPhotoCaptured = {
                            photoBytes = it
                            showCamera = false
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showCamera = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Alumno") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    label = { Text("Apellido") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = dni,
                    onValueChange = { dni = it },
                    label = { Text("DNI") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = grado,
                            onValueChange = { grado = it },
                            label = { Text("Grado/Nivel") },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = uiState.availableGrades.isNotEmpty(),
                            trailingIcon = {
                                if (uiState.availableGrades.isNotEmpty()) {
                                    IconButton(onClick = { expandedGrado = true }) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = "Seleccionar Grado"
                                        )
                                    }
                                }
                            }
                        )
                        DropdownMenu(
                            expanded = expandedGrado,
                            onDismissRequest = { expandedGrado = false }
                        ) {
                            uiState.availableGrades.forEach { gradeOption ->
                                DropdownMenuItem(
                                    text = { Text(gradeOption) },
                                    onClick = {
                                        grado = gradeOption
                                        expandedGrado = false
                                    }
                                )
                            }
                        }
                    }
                    OutlinedTextField(
                        value = seccion,
                        onValueChange = { seccion = it },
                        label = { Text("Sección") },
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = fechaNacimiento,
                    onValueChange = { fechaNacimiento = it },
                    label = { Text("Fecha de Nacimiento") },
                    placeholder = { Text("YYYY-MM-DD") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (photoBytes != null) {
                    Text("Foto capturada con éxito", color = MaterialTheme.colorScheme.primary)
                }

                Button(
                    onClick = { showCamera = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (photoBytes == null) "Capturar Foto en Vivo" else "Recapturar Foto")
                }
            }
        },
        confirmButton = {
            Button(
                enabled = photoBytes != null,
                onClick = {
                    if (nombre.isNotBlank() && apellido.isNotBlank() && photoBytes != null) {
                        if (isMinor(fechaNacimiento) && capturedConsent == null) {
                            showConsentDialog = true
                            return@Button
                        }

                        val newStudent = Student(
                            nombre = nombre.trim(),
                            apellido = apellido.trim(),
                            dni = dni.trim(),
                            fechaNacimiento = fechaNacimiento.trim(),
                            grado = grado.trim(),
                            seccion = seccion.trim(),
                            telefono = telefono.trim(),
                            email = email.trim(),
                            direccion = direccion.trim(),
                            // Mapear datos del acudiente si existen en el consentimiento
                            telefonoAcudiente = capturedConsent?.acudienteTelefono,
                            emailAcudiente = capturedConsent?.acudienteEmail
                        )
                        onSave(newStudent, capturedConsent)
                    }
                }
            ) {
                Text("Finalizar Matrícula")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
