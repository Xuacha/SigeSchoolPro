package com.sigeschool.presentation.screens.attendance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.AttendanceStatus
import com.sigeschool.domain.model.ScanType
import com.sigeschool.domain.model.Student
import com.sigeschool.presentation.components.BarcodeScanner
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun AttendanceScreen(
    viewModel: AttendanceViewModel = koinViewModel(),
    onNavigateToEmployeeAttendance: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }
    var showScanner by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    if (showScanner) {
        AlertDialog(
            onDismissRequest = { showScanner = false },
            properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier.fillMaxSize(),
            content = {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        TopAppBar(
                            title = { Text("Escanear Asistencia") },
                            navigationIcon = {
                                IconButton(onClick = { showScanner = false }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Cerrar")
                                }
                            }
                        )
                        BarcodeScanner(
                            onScan = { barcode ->
                                viewModel.onScanResult(barcode, ScanType.STUDENT_ENTRY)
                                showScanner = false
                            },
                            modifier = Modifier.weight(1f)
                        )
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Coloque el código de barras frente a la cámara")
                        }
                    }
                }
            }
        )
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        viewModel.updateDate(it)
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Pasar Lista") },
                actions = {
                    IconButton(onClick = onNavigateToEmployeeAttendance) {
                        Icon(Icons.Default.Badge, contentDescription = "Asistencia Empleados")
                    }
                    IconButton(onClick = { showScanner = true }) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = "Escanear")
                    }
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Cambiar fecha")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { viewModel.saveAttendance() },
                icon = { Icon(Icons.Default.Check, "Guardar") },
                text = { Text("Guardar Asistencia") },
                expanded = !uiState.isSaving
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Header con Fecha
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Fecha: ",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = uiState.date,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp) // Espacio para el FAB
                ) {
                    items(uiState.students) { student ->
                        AttendanceItem(
                            student = student,
                            selectedStatus = uiState.attendanceMap[student.id] ?: AttendanceStatus.PRESENT,
                            onStatusChange = { status ->
                                viewModel.updateStatus(student.id, status)
                            }
                        )
                    }
                }
            }
        }

        if (uiState.isSaving) {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {},
                title = { Text("Guardando...") },
                text = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            )
        }
    }
}

@Composable
fun AttendanceItem(
    student: Student,
    selectedStatus: AttendanceStatus,
    onStatusChange: (AttendanceStatus) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = student.nombreCompleto,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AttendanceStatusChip(
                    status = AttendanceStatus.PRESENT,
                    isSelected = selectedStatus == AttendanceStatus.PRESENT,
                    onClick = { onStatusChange(AttendanceStatus.PRESENT) }
                )
                AttendanceStatusChip(
                    status = AttendanceStatus.ABSENT,
                    isSelected = selectedStatus == AttendanceStatus.ABSENT,
                    onClick = { onStatusChange(AttendanceStatus.ABSENT) }
                )
                AttendanceStatusChip(
                    status = AttendanceStatus.LATE,
                    isSelected = selectedStatus == AttendanceStatus.LATE,
                    onClick = { onStatusChange(AttendanceStatus.LATE) }
                )
                AttendanceStatusChip(
                    status = AttendanceStatus.JUSTIFIED,
                    isSelected = selectedStatus == AttendanceStatus.JUSTIFIED,
                    onClick = { onStatusChange(AttendanceStatus.JUSTIFIED) }
                )
            }
        }
    }
}

@Composable
fun AttendanceStatusChip(
    status: AttendanceStatus,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val containerColor = when {
        !isSelected -> MaterialTheme.colorScheme.surface
        status == AttendanceStatus.PRESENT -> MaterialTheme.colorScheme.primaryContainer
        status == AttendanceStatus.ABSENT -> MaterialTheme.colorScheme.errorContainer
        status == AttendanceStatus.LATE -> MaterialTheme.colorScheme.tertiaryContainer
        status == AttendanceStatus.JUSTIFIED -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.surface
    }

    val contentColor = when {
        !isSelected -> MaterialTheme.colorScheme.onSurface
        status == AttendanceStatus.PRESENT -> MaterialTheme.colorScheme.onPrimaryContainer
        status == AttendanceStatus.ABSENT -> MaterialTheme.colorScheme.onErrorContainer
        status == AttendanceStatus.LATE -> MaterialTheme.colorScheme.onTertiaryContainer
        status == AttendanceStatus.JUSTIFIED -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onSurface
    }

    InputChip(
        selected = isSelected,
        onClick = onClick,
        label = { 
            val label = when(status) {
                AttendanceStatus.PRESENT -> "Presente"
                AttendanceStatus.ABSENT -> "Falta"
                AttendanceStatus.LATE -> "Tarde"
                AttendanceStatus.JUSTIFIED -> "Justif."
                else -> status.name
            }
            Text(label) 
        },
        colors = InputChipDefaults.inputChipColors(
            selectedContainerColor = containerColor,
            selectedLabelColor = contentColor
        )
    )
}
