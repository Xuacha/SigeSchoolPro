package com.sigeschool.presentation.screens.students

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FileUpload
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.SyncProblem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.presentation.components.StudentStatusChipCompact
import com.sigeschool.presentation.components.StudentStatusDialog
import com.sigeschool.presentation.components.ConflictResolutionDialog
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun StudentListScreen(
    onNavigateBack: () -> Unit,
    onStudentClick: (String) -> Unit,
    onNavigateToImport: () -> Unit = {},
    viewModel: StudentListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    var studentToManage by remember { mutableStateOf<Student?>(null) }
    var showStatusDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is StudentListViewModel.UiEvent.NavigateToDetail -> {
                    onStudentClick(event.studentId)
                }
                is StudentListViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    if (state.conflictStudent != null) {
        ConflictResolutionDialog(
            studentName = state.conflictStudent?.nombreCompleto ?: "",
            onResolve = { useRemote -> viewModel.resolveConflict(useRemote) },
            onDismiss = { viewModel.dismissConflictDialog() }
        )
    }


    if (showStatusDialog && studentToManage != null) {
        StudentStatusDialog(
            student = studentToManage!!,
            onDismiss = { showStatusDialog = false; studentToManage = null },
            onStatusChange = { newStatus, reason ->
                viewModel.updateStudentStatus(studentToManage!!.id, newStatus, reason)
                showStatusDialog = false
                studentToManage = null
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Listado de Estudiantes") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { /* viewModel.exportStudents("excel") */ }) {
                        Icon(Icons.Rounded.Download, contentDescription = "Exportar a Excel")
                    }
                    IconButton(onClick = onNavigateToImport) {
                        Icon(Icons.Rounded.FileUpload, contentDescription = "Importar desde Excel")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por nombre o documento...") },
                leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) },
                shape = MaterialTheme.shapes.medium
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            val filteredStudents = state.students.filter {
                it.nombreCompleto.contains(searchQuery, ignoreCase = true) ||
                it.dni.contains(searchQuery)
            }

            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = filteredStudents,
                    key = { it.id }
                ) { student ->
                    StudentItem(
                        student = student,
                        onClick = { viewModel.onStudentClick(student) },
                        onManageClick = { 
                            studentToManage = student
                            showStatusDialog = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StudentItem(
    student: Student,
    onClick: () -> Unit,
    onManageClick: () -> Unit
) {
    val isConflict = !student.sincronizado
    
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(72.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isConflict) 
                MaterialTheme.colorScheme.errorContainer 
            else 
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        student.nombre.take(1).uppercase(),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = student.nombreCompleto,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (isConflict) MaterialTheme.colorScheme.onErrorContainer else Color.Unspecified
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = student.dni,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    StudentStatusChipCompact(status = student.estadoMatricula)
                }
            }
            
            if (isConflict) {
                Icon(
                    imageVector = Icons.Rounded.SyncProblem,
                    contentDescription = "Conflicto",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            IconButton(onClick = onManageClick, modifier = Modifier.size(40.dp)) {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = "Gestionar",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
