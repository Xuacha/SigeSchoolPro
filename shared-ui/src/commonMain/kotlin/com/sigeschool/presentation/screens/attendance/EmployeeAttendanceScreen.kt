package com.sigeschool.presentation.screens.attendance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.EmployeeAttendance
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun EmployeeAttendanceScreen(
    viewModel: EmployeeAttendanceViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asistencia de Empleados") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text(
                "Fecha: ${uiState.date}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            
            if (uiState.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.employees) { employee ->
                        val attendance = uiState.attendance.find { it.employeeId == employee.id.toString() }
                        EmployeeAttendanceItem(
                            employeeName = employee.fullName,
                            attendance = attendance,
                            onApproveOvertime = { attendance?.let { viewModel.approveOvertime(it.id) } }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeAttendanceItem(
    employeeName: String,
    attendance: EmployeeAttendance?,
    onApproveOvertime: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(employeeName, style = MaterialTheme.typography.titleMedium)
            
            if (attendance != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Entrada: ${attendance.checkIn?.split("T")?.get(1)?.take(5) ?: "--:--"}")
                        Text("Salida: ${attendance.checkOut?.split("T")?.get(1)?.take(5) ?: "--:--"}")
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Horas: ${attendance.totalHours}")
                        if (attendance.extraHours > 0) {
                            Text("Extra: ${attendance.extraHours}", color = Color.Red)
                        }
                    }
                }
                
                if (attendance.extraHours > 0) {
                    Spacer(Modifier.height(8.dp))
                    if (attendance.isExtraApproved) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, "Aprobado", tint = Color.Green, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Horas extra aprobadas por ${attendance.approvedBy}", style = MaterialTheme.typography.bodySmall)
                        }
                    } else {
                        Button(
                            onClick = onApproveOvertime,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                        ) {
                            Text("Aprobar Horas Extra")
                        }
                    }
                }
            } else {
                Text("Sin registros hoy", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}
