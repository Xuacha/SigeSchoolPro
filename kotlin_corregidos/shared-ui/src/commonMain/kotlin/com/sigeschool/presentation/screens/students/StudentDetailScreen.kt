package com.sigeschool.presentation.screens.students

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.domain.model.AcademicStatus
import com.sigeschool.presentation.screens.auth.LoginViewModel
import com.sigeschool.util.OpenWhatsApp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun StudentDetailScreen(
    studentId: Long,
    onBack: () -> Unit,
    viewModel: StudentViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val feeViewModel: FeeViewModel = koinViewModel()
    val authViewModel: LoginViewModel = koinViewModel()
    val authState by authViewModel.uiState.collectAsState()
    
    val student = uiState.students.find { it.id == studentId }
    val payments by feeViewModel.payments.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(studentId) {
        feeViewModel.loadPayments(studentId)
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    var isEditing by remember { mutableStateOf(false) }
    var editedStudent by remember(student) { mutableStateOf(student?.copy() ?: Student()) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Editar Alumno" else "Detalle del Alumno") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    // Botón de sincronización manual
                    IconButton(
                        onClick = { viewModel.syncStudent(studentId) },
                        enabled = !uiState.isLoading
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                        } else {
                            Icon(Icons.Default.Sync, contentDescription = "Sincronizar")
                        }
                    }
                    TextButton(onClick = { isEditing = !isEditing }) {
                        Text(if (isEditing) "Cancelar" else "Editar")
                    }
                    if (isEditing) {
                        TextButton(onClick = {
                            viewModel.updateStudent(editedStudent)
                            isEditing = false
                        }) {
                            Text("Guardar", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            )
        }
    ) { padding ->
        if (student == null) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Alumno no encontrado")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Indicador de sincronización dinámico
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sincronizando...", style = MaterialTheme.typography.bodySmall)
                } else {
                    Icon(
                        Icons.Default.Sync,
                        null,
                        tint = if (student.sincronizado) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        if (student.sincronizado) "Sincronizado con la nube" else "Pendiente de sincronización",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (student.sincronizado) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.error
                    )
                }
            }

            // Cabecera con Avatar
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = editedStudent.nombreCompleto.take(2).uppercase(),
                            fontSize = 32.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = editedStudent.nombreCompleto,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "DNI: ${editedStudent.dni}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (isEditing) {
                            Text(
                                text = "Editando información...",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }

            if (isEditing) {
                // === FORMULARIO DE EDICIÓN ===
                EditForm(
                    student = editedStudent, 
                    onChange = { editedStudent = it },
                    availableGrades = uiState.availableGrades
                )
            } else {
                // === MODO LECTURA ===
                ViewMode(
                    student = student, 
                    payments = payments,
                    onAddPayment = { amount, concept, method ->
                        feeViewModel.registerPayment(
                            studentId = studentId,
                            institutionId = authState.institutionId ?: "demo",
                            amount = amount,
                            concept = concept,
                            user = authState.userEmail ?: "admin",
                            method = method
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ViewMode(
    student: Student,
    payments: List<com.sigeschool.domain.model.FeePayment>,
    onAddPayment: (Double, String, String) -> Unit
) {
    var triggerWhatsApp by remember { mutableStateOf(false) }
    var showPaymentDialog by remember { mutableStateOf(false) }

    if (showPaymentDialog) {
        PaymentDialog(
            onDismiss = { showPaymentDialog = false },
            onConfirm = { amount, concept, method ->
                onAddPayment(amount, concept, method)
                showPaymentDialog = false
            }
        )
    }

    if (triggerWhatsApp) {
        OpenWhatsApp(
            phoneNumber = student.telefono,
            message = "Hola, soy el docente de la institución. Me contacto por el alumno ${student.nombreCompleto}"
        )
        // Reset trigger after use
        LaunchedEffect(Unit) { triggerWhatsApp = false }
    }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        DetailSection("Información Académica") {
            DetailRow("Grado y Sección", "${student.grado} - ${student.seccion}")
            DetailRow("Estado Matrícula", student.estadoMatricula.name)
            DetailRow("Estado Académico", student.estadoAcademico.name)
            DetailRow("DNI", student.dni)
        }

        DetailSection("Tesorería y Pagos") {
            if (payments.isEmpty()) {
                Text("No hay pagos registrados", style = MaterialTheme.typography.bodySmall)
            } else {
                payments.forEach { payment ->
                    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(payment.concepto, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                                Text(
                                    text = "${payment.fecha.take(10)} • ${payment.metodoPago}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text("$${payment.monto}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        }
                        if (payment.receiptUrl != null) {
                            val uriHandler = androidx.compose.ui.platform.LocalUriHandler.current
                            TextButton(
                                onClick = { payment.receiptUrl?.let { feeViewModel.viewReceipt(it) } },
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Text("Ver Recibo PDF", style = MaterialTheme.typography.labelMedium)
                            }
                            // FIX: antes se abría directamente el valor guardado en
                            // `receiptUrl` (una URL pública fija y adivinable). Ahora
                            // ese campo solo guarda la ruta interna del archivo; la URL
                            // real y temporal se resuelve en el ViewModel y se abre aquí
                            // en cuanto está lista.
                            LaunchedEffect(Unit) {
                                snapshotFlow { feeViewModel.receiptUrlToOpen.value }.collect { url ->
                                    if (url != null) {
                                        uriHandler.openUri(url)
                                        feeViewModel.consumeReceiptUrl()
                                    }
                                }
                            }
                        }
                        HorizontalDivider(modifier = Modifier.padding(top = 8.dp), thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
                    }
                }
            }
            
            Button(
                onClick = { showPaymentDialog = true },
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Default.Sync, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Registrar Nuevo Pago")
            }
        }

        DetailSection("Contacto") {
            DetailRow("Teléfono", student.telefono)
            DetailRow("Correo Electrónico", student.email)
            DetailRow("Dirección", student.direccion)
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Button(
                onClick = { triggerWhatsApp = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF25D366) // WhatsApp Green
                )
            ) {
                Text("Contactar por WhatsApp", color = androidx.compose.ui.graphics.Color.White)
            }
        }

        DetailSection("Registro") {
            DetailRow("Fecha de Registro", student.fechaRegistro)
            DetailRow("Estado", if (student.activo) "Activo" else "Inactivo")
        }
    }
}

@Composable
private fun EditForm(
    student: Student, 
    onChange: (Student) -> Unit,
    availableGrades: List<String> = emptyList()
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = student.nombre,
            onValueChange = { onChange(student.copy(nombre = it)) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = student.apellido,
            onValueChange = { onChange(student.copy(apellido = it)) },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            var expandedGrado by remember { mutableStateOf(false) }
            
            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = student.grado,
                    onValueChange = { onChange(student.copy(grado = it)) },
                    label = { Text("Grado/Nivel") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = availableGrades.isNotEmpty(),
                    trailingIcon = {
                        if (availableGrades.isNotEmpty()) {
                            IconButton(onClick = { expandedGrado = true }) {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                            }
                        }
                    }
                )
                DropdownMenu(
                    expanded = expandedGrado,
                    onDismissRequest = { expandedGrado = false }
                ) {
                    availableGrades.forEach { gradeOption ->
                        DropdownMenuItem(
                            text = { Text(gradeOption) },
                            onClick = {
                                onChange(student.copy(grado = gradeOption))
                                expandedGrado = false
                            }
                        )
                    }
                }
            }
            OutlinedTextField(
                value = student.seccion,
                onValueChange = { onChange(student.copy(seccion = it)) },
                label = { Text("Sección") },
                modifier = Modifier.weight(1f)
            )
        }

        OutlinedTextField(
            value = student.dni,
            onValueChange = { onChange(student.copy(dni = it)) },
            label = { Text("DNI") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = student.fechaNacimiento,
            onValueChange = { onChange(student.copy(fechaNacimiento = it)) },
            label = { Text("Fecha de Nacimiento") },
            placeholder = { Text("YYYY-MM-DD") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = student.telefono,
            onValueChange = { onChange(student.copy(telefono = it)) },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = student.email,
            onValueChange = { onChange(student.copy(email = it)) },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = student.direccion,
            onValueChange = { onChange(student.copy(direccion = it)) },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
    }
}

@Composable
fun PaymentDialog(onDismiss: () -> Unit, onConfirm: (Double, String, String) -> Unit) {
    var amount by remember { mutableStateOf("") }
    var concept by remember { mutableStateOf("Pensión") }
    var method by remember { mutableStateOf("EFECTIVO") }
    val methods = listOf("EFECTIVO", "TRANSFERENCIA", "TARJETA")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Registrar Pago") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { if (it.all { char -> char.isDigit() || char == '.' }) amount = it },
                    label = { Text("Monto ($)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Decimal
                    )
                )
                OutlinedTextField(
                    value = concept,
                    onValueChange = { concept = it },
                    label = { Text("Concepto") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Text("Método de Pago", style = MaterialTheme.typography.labelMedium)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    methods.forEach { m ->
                        FilterChip(
                            selected = method == m,
                            onClick = { method = m },
                            label = { Text(m, fontSize = 10.sp) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { 
                    amount.toDoubleOrNull()?.let { onConfirm(it, concept, method) }
                },
                enabled = amount.isNotEmpty() && concept.isNotEmpty()
            ) {
                Text("Cobrar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}

@Composable
private fun DetailSection(title: String, content: @Composable () -> Unit) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value.ifBlank { "N/A" }, fontWeight = FontWeight.Medium)
    }
}
