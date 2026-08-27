package com.sigeschool.presentation.screens.grades

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.QualitativeToGrade
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun GradesScreen(
    viewModel: GradesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var gradeToEdit by remember { mutableStateOf<Grade?>(null) }
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
                title = { Text("Calificaciones") },
                actions = {
                    IconButton(onClick = { viewModel.syncGrades() }) {
                        Icon(Icons.Default.Sync, contentDescription = "Sincronizar")
                    }
                }
            )
        },
        floatingActionButton = {
            if (uiState.selectedStudent != null) {
                FloatingActionButton(onClick = { 
                    gradeToEdit = null
                    showAddDialog = true 
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Nota")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Selector de Alumno y Botón de Reporte
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    StudentSelector(
                        students = uiState.students,
                        selectedStudent = uiState.selectedStudent,
                        onStudentSelected = { viewModel.selectStudent(it) }
                    )
                }
                
                if (uiState.selectedStudent != null) {
                    ReportButton(student = uiState.selectedStudent!!, grades = uiState.grades)
                }
            }

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (uiState.selectedStudent == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Seleccione un alumno para ver sus notas", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                val average = if (uiState.grades.isEmpty()) 0.0 else uiState.grades.map { it.score }.average()
                
                // Promedio General (destacado) - Estilo Mejorado
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (average >= 13.0) MaterialTheme.colorScheme.primaryContainer 
                                        else MaterialTheme.colorScheme.errorContainer
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Promedio General", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = if (average % 1.0 == 0.0) average.toInt().toString() else average.toString().take(4),
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (average >= 13.0) MaterialTheme.colorScheme.onPrimaryContainer 
                                    else MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }

                GradesGroupedList(
                    grades = uiState.grades,
                    onEdit = { 
                        gradeToEdit = it
                        showAddDialog = true
                    },
                    onDelete = { viewModel.deleteGrade(it) }
                )
            }
        }
    }

    if (showAddDialog && uiState.selectedStudent != null) {
        AddGradeDialog(
            studentId = uiState.selectedStudent!!.id,
            grade = gradeToEdit,
            qualitativeGrade = uiState.currentQualitativeGrade,
            onScoreChanged = { viewModel.onScoreChanged(it) },
            onDismiss = { 
                showAddDialog = false
                gradeToEdit = null
            },
            onSave = { grade ->
                viewModel.saveGrade(grade)
                showAddDialog = false
                gradeToEdit = null
            }
        )
    }
}

@Composable
expect fun ReportButton(student: Student, grades: List<Grade>)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentSelector(
    students: List<Student>,
    selectedStudent: Student?,
    onStudentSelected: (Student) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
    ) {
        OutlinedTextField(
            value = selectedStudent?.nombreCompleto ?: "Seleccionar Alumno",
            onValueChange = {},
            readOnly = true,
            label = { Text("Alumno") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            students.forEach { student ->
                DropdownMenuItem(
                    text = { Text(student.nombreCompleto) },
                    onClick = {
                        onStudentSelected(student)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun GradesGroupedList(
    grades: List<Grade>,
    onEdit: (Grade) -> Unit,
    onDelete: (String) -> Unit
) {
    if (grades.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay calificaciones registradas")
        }
    } else {
        val groupedGrades = grades.groupBy { it.subjectId }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            groupedGrades.forEach { (subject, subjectGrades) ->
                item {
                    SubjectGradeCard(subject, subjectGrades, onEdit, onDelete)
                }
            }
        }
    }
}

@Composable
fun SubjectGradeCard(
    subject: String,
    grades: List<Grade>,
    onEdit: (Grade) -> Unit,
    onDelete: (String) -> Unit
) {
    val subjectAvg = grades.map { it.score }.average()
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subject,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = if (subjectAvg >= 10.5) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer
                ) {
                    Text(
                        text = if (subjectAvg % 1.0 == 0.0) subjectAvg.toInt().toString() else subjectAvg.toString().take(4),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(8.dp))
            
            grades.forEach { grade ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(grade.periodId, style = MaterialTheme.typography.bodyMedium)
                        if (grade.observations.isNotBlank()) {
                            Text(
                                grade.observations, 
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = grade.score.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = if (grade.score >= 10.5) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        GradeActionMenu(
                            onEdit = { onEdit(grade) },
                            onDelete = { onDelete(grade.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GradeActionMenu(onEdit: () -> Unit, onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Opciones")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text("Editar") },
                onClick = {
                    onEdit()
                    expanded = false
                },
                leadingIcon = { Icon(Icons.Default.Edit, null) }
            )
            DropdownMenuItem(
                text = { Text("Eliminar", color = MaterialTheme.colorScheme.error) },
                onClick = {
                    onDelete()
                    expanded = false
                },
                leadingIcon = { Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error) }
            )
        }
    }
}

@Composable
fun AddGradeDialog(
    studentId: String,
    grade: Grade? = null,
    qualitativeGrade: String = "",
    onScoreChanged: (String) -> Unit = {},
    onDismiss: () -> Unit,
    onSave: (Grade) -> Unit
) {
    var subject by remember { mutableStateOf(grade?.subjectId ?: "") }
    var score by remember { mutableStateOf(grade?.score?.toString() ?: "") }
    var period by remember { mutableStateOf(grade?.periodId ?: "Primer Trimestre") }
    var observations by remember { mutableStateOf(grade?.observations ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (grade == null) "Nueva Calificación" else "Editar Calificación") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = subject, onValueChange = { subject = it }, label = { Text("Materia") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = period, onValueChange = { period = it }, label = { Text("Periodo") }, modifier = Modifier.fillMaxWidth())
                
                OutlinedTextField(
                    value = observations, 
                    onValueChange = { observations = it }, 
                    label = { Text("Observaciones / Feedback") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )

                Button(
                    onClick = {
                        val suggested = QualitativeToGrade.evaluateObservation(observations)
                        score = suggested.toInt().toString()
                        onScoreChanged(score)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Icon(Icons.Default.Sync, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Sugerir Nota por IA")
                }

                OutlinedTextField(
                    value = score, 
                    onValueChange = { 
                        score = it
                        onScoreChanged(it)
                    }, 
                    label = { Text("Nota Final") },
                    modifier = Modifier.fillMaxWidth(),
                    supportingText = {
                        if (qualitativeGrade.isNotEmpty()) {
                            Text("Equivalencia SIE: $qualitativeGrade", color = MaterialTheme.colorScheme.primary)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = if ((score.toDoubleOrNull() ?: 0.0) < 3.0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val scoreDouble = score.toDoubleOrNull() ?: 0.0
                onSave(Grade(
                    id = grade?.id ?: "",
                    studentId = studentId,
                    subjectId = subject,
                    score = scoreDouble,
                    periodId = period,
                    observations = observations,
                    institutionId = "" // Handled by ViewModel
                ))
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
