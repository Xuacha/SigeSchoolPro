package com.sigeschool.presentation.screens.exams

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Exam
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun ExamScreen(
    viewModel: ExamViewModel = koinViewModel(),
    onAddExam: () -> Unit = {},
    onTakeExam: (Exam) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Exámenes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Examen")
            }
        }
    ) { paddingValues ->
        if (showAddDialog) {
            AddExamDialog(
                onDismiss = { showAddDialog = false },
                onConfirm = { exam ->
                    viewModel.addExam(exam)
                    showAddDialog = false
                }
            )
        }

        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.error != null) {
                Text(
                    text = uiState.error ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (uiState.exams.isEmpty()) {
                Text(
                    text = "No hay exámenes programados",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.exams) { exam ->
                        ExamItem(exam, onClick = { onTakeExam(exam) })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExamDialog(
    onDismiss: () -> Unit,
    onConfirm: (Exam) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var subjectId by remember { mutableStateOf("") }
    var classId by remember { mutableStateOf("") }
    var maxScore by remember { mutableStateOf("20.0") }
    var dateMillis by remember { mutableStateOf(kotlinx.datetime.Clock.System.now().toEpochMilliseconds()) }
    var showDatePicker by remember { mutableStateOf(false) }

    val dateStr = Instant.fromEpochMilliseconds(dateMillis)
        .toLocalDateTime(TimeZone.UTC).date.toString()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Examen") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = dateStr,
                    onValueChange = {},
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Default.CalendarMonth, contentDescription = "Seleccionar Fecha")
                        }
                    }
                )
                OutlinedTextField(
                    value = subjectId,
                    onValueChange = { subjectId = it },
                    label = { Text("ID Materia") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = classId,
                    onValueChange = { classId = it },
                    label = { Text("ID Clase") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = maxScore,
                    onValueChange = { maxScore = it },
                    label = { Text("Puntaje Máximo") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (showDatePicker) {
                    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = dateMillis)
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let { dateMillis = it }
                                showDatePicker = false
                            }) { Text("Aceptar") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) { Text("Cancelar") }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(
                        Exam(
                            title = title,
                            subjectId = subjectId,
                            classId = classId,
                            maxScore = maxScore.toDoubleOrNull() ?: 20.0,
                            date = dateMillis
                        )
                    )
                },
                enabled = title.isNotBlank() && subjectId.isNotBlank() && classId.isNotBlank()
            ) {
                Text("Agregar")
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
fun ExamItem(exam: Exam, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = exam.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Materia ID: ${exam.subjectId}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val dateFormatted = Instant.fromEpochMilliseconds(exam.date)
                    .toLocalDateTime(TimeZone.UTC).date.toString()
                Text(
                    text = "Fecha: $dateFormatted",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Puntaje Máx: ${exam.maxScore}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
