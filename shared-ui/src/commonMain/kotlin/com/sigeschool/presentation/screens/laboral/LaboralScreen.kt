package com.sigeschool.presentation.screens.laboral

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.sigeschool.domain.model.TeacherCategory
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.VacationRequest
import com.sigeschool.domain.model.LiquidationCalculation
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun LaboralScreen(
    viewModel: LaboralViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Nómina", "Vacaciones", "Liquidación")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión Laboral") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            when (selectedTab) {
                0 -> PayrollTab(uiState, viewModel)
                1 -> VacationsTab(uiState, viewModel)
                2 -> LiquidationTab(uiState, viewModel)
            }
        }
    }
}

@Composable
fun PayrollTab(uiState: LaboralUiState, viewModel: LaboralViewModel) {
    var selectedCategory by remember { mutableStateOf(TeacherCategory.D1278_G2_A) }
    var salary by remember { mutableStateOf(selectedCategory.baseSalary.toString()) }
    var days by remember { mutableStateOf("30") }
    var advances by remember { mutableStateOf("0") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        Text("Categoría de Contratación (Decreto 2026)", style = MaterialTheme.typography.titleMedium)
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedCategory.displayName)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                TeacherCategory.entries.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.displayName) },
                        onClick = {
                            selectedCategory = category
                            salary = category.baseSalary.toString()
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = salary,
            onValueChange = { salary = it },
            label = { Text("Asignación Básica Mensual") },
            modifier = Modifier.fillMaxWidth()
        )
        // ... resto de los campos ...
        OutlinedTextField(
            value = days,
            onValueChange = { days = it },
            label = { Text("Días Trabajados") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = advances,
            onValueChange = { advances = it },
            label = { Text("Adelantos Solicitados") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { 
                viewModel.calculatePayroll(
                    salary.toDoubleOrNull() ?: 0.0, 
                    days.toIntOrNull() ?: 0,
                    advances.toDoubleOrNull() ?: 0.0
                ) 
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Calcular Nómina con Adelanto")
        }

        uiState.payrollCalculation?.let { calc ->
            PayrollResultCard(calc)
        }
    }
}

@Composable
fun PayrollResultCard(calc: PayrollCalculation) {
    Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Resumen de Nómina 2026", style = MaterialTheme.typography.titleLarge)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            ResultRow("Salario Proporcional", calc.totalDevengado - calc.transportAllowance)
            ResultRow("Auxilio Transporte", calc.transportAllowance)
            ResultRow("Salud (4%)", -calc.healthDeduction)
            ResultRow("Pensión (4%)", -calc.pensionDeduction)
            if (calc.advances > 0) {
                ResultRow("Adelantos (Tope 50%)", -calc.advances)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                "Neto a Pagar: $ ${calc.netPay.toInt()}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun VacationsTab(uiState: LaboralUiState, viewModel: LaboralViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                item {
                    Text("Mis Solicitudes", style = MaterialTheme.typography.titleMedium)
                }
                items(uiState.vacationRequests) { request ->
                    VacationItem(request)
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(Icons.Default.Add, "Solicitar")
        }
    }

    if (showDialog) {
        // Dialogo simplificado para la solicitud
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Nueva Solicitud") },
            text = { Text("¿Desea solicitar 15 días de vacaciones a partir de hoy?") },
            confirmButton = {
                Button(onClick = {
                    viewModel.submitVacationRequest(0, 0, 15, "Solicitud generada")
                    showDialog = false
                }) { Text("Confirmar") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
fun VacationItem(request: VacationRequest) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        ListItem(
            headlineContent = { Text("Días: ${request.days}") },
            supportingContent = { Text("Estado: ${request.status}") },
            trailingContent = {
                Icon(
                    if (request.status == "APROBADA") Icons.Default.CheckCircle else Icons.Default.Info,
                    contentDescription = null,
                    tint = if (request.status == "APROBADA") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                )
            }
        )
    }
}

@Composable
fun LiquidationTab(uiState: LaboralUiState, viewModel: LaboralViewModel) {
    var lastSalary by remember { mutableStateOf("2000000") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = lastSalary,
            onValueChange = { lastSalary = it },
            label = { Text("Último Salario") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { viewModel.estimateLiquidation(lastSalary.toDoubleOrNull() ?: 0.0, 0, 360 * 1000L * 60 * 60 * 24) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Simular 1 Año de Trabajo")
        }

        uiState.liquidationEstimate?.let { est ->
            LiquidationResultCard(est)
        }
    }
}

@Composable
fun LiquidationResultCard(est: LiquidationCalculation) {
    Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Estimación de Liquidación", style = MaterialTheme.typography.titleLarge)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            ResultRow("Cesantías", est.cesantias)
            ResultRow("Int. Cesantías", est.interesesCesantias)
            ResultRow("Prima de Servicios", est.primaServicios)
            ResultRow("Vacaciones", est.vacacionesCompensadas)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                "Total Estimado: $ ${est.totalLiquidation.toInt()}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun ResultRow(label: String, value: Double) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label)
        Text("$ ${value.toInt()}")
    }
}
