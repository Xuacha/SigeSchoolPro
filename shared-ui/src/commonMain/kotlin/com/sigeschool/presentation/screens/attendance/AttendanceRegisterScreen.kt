package com.sigeschool.presentation.screens.attendance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.AttendanceStatus
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.Class
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceRegisterScreen(
    classItem: Class,
    onNavigateBack: () -> Unit,
    viewModel: AttendanceRegisterViewModel = koinViewModel()
) {
    val students by viewModel.students.collectAsState()
    val attendanceMap by viewModel.attendanceMap.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(classItem.id) {
        viewModel.loadStudents(classItem)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asistencia: ${classItem.name}") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        viewModel.saveAttendance(classItem.id)
                        onNavigateBack()
                    }) {
                        Icon(Icons.Rounded.Save, contentDescription = "Guardar")
                    }
                }
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(students) { student ->
                    AttendanceRow(
                        student = student,
                        selectedStatus = attendanceMap[student.id] ?: AttendanceStatus.PRESENT,
                        onStatusChange = { viewModel.updateStatus(student.id, it) }
                    )
                }
            }
        }
    }
}

@Composable
fun AttendanceRow(
    student: Student,
    selectedStatus: AttendanceStatus,
    onStatusChange: (AttendanceStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(student.nombreCompleto, style = MaterialTheme.typography.titleMedium)
                Text("Doc: ${student.dni}", style = MaterialTheme.typography.bodySmall)
            }
            
            Box {
                AssistChip(
                    onClick = { expanded = true },
                    label = { 
                        Text(when(selectedStatus) {
                            AttendanceStatus.PRESENT -> "Presente"
                            AttendanceStatus.ABSENT -> "Falta"
                            AttendanceStatus.LATE -> "Tarde"
                            AttendanceStatus.JUSTIFIED -> "Justificado"
                            else -> selectedStatus.name
                        })
                    }
                )
                
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    AttendanceStatus.entries.filter { 
                        it == AttendanceStatus.PRESENT || it == AttendanceStatus.ABSENT || 
                        it == AttendanceStatus.LATE || it == AttendanceStatus.JUSTIFIED 
                    }.forEach { status ->
                        DropdownMenuItem(
                            text = { 
                                Text(when(status) {
                                    AttendanceStatus.PRESENT -> "Presente"
                                    AttendanceStatus.ABSENT -> "Falta"
                                    AttendanceStatus.LATE -> "Tarde"
                                    AttendanceStatus.JUSTIFIED -> "Justificado"
                                    else -> status.name
                                })
                            },
                            onClick = {
                                onStatusChange(status)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
