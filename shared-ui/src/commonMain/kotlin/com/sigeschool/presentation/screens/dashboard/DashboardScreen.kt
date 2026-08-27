package com.sigeschool.presentation.screens.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.rounded.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sigeschool.core.theme.SigeSchoolTheme
import com.sigeschool.util.isDesktop
import io.github.koalaplot.core.pie.PieChart
import io.github.koalaplot.core.pie.DefaultSlice
import io.github.koalaplot.core.util.generateHueColorPalette
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.line.LinePlot
import io.github.koalaplot.core.xygraph.DefaultPoint
import io.github.koalaplot.core.xygraph.XYGraph
import io.github.koalaplot.core.xygraph.rememberFloatLinearAxisModel
import io.github.koalaplot.core.style.LineStyle
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class, ExperimentalKoalaPlotApi::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel(),
    onOpenMenu: () -> Unit = {},
    onLogout: () -> Unit = {},
    onNavigateToAttendance: () -> Unit = {},
    onNavigateToGrades: () -> Unit = {},
    onNavigateToStudents: () -> Unit = {},
    onNavigateToClasses: () -> Unit = {},
    onNavigateToEmployees: () -> Unit = {},
    onNavigateToSalaries: () -> Unit = {},
    onNavigateToTasks: () -> Unit = {},
    onNavigateToExams: () -> Unit = {},
    onNavigateToAnnouncements: () -> Unit = {},
    onNavigateToChat: () -> Unit = {},
    onNavigateToReports: () -> Unit = {},
    onNavigateToCurricular: () -> Unit = {},
    onNavigateToCash: () -> Unit = {},
    onNavigateToUsers: () -> Unit = {},
    onNavigateToAutoevaluacion: () -> Unit = {},
    onNavigateToSieConfig: () -> Unit = {},
    onNavigateToBackupSecurity: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val isSyncing by viewModel.isSyncing.collectAsState()

    // Detectamos si hay riel de navegación externo (escritorio) para ajustar el padding y UI
    val hasNavigationRail = isDesktop()

    SigeSchoolTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("SigeSchool Pro") },
                    navigationIcon = {
                        if (!hasNavigationRail) {
                            IconButton(onClick = onOpenMenu) {
                                Icon(Icons.Default.Menu, contentDescription = "Abrir Menú")
                            }
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { /* Sync logic removed or moved */ },
                            enabled = !isSyncing
                        ) {
                            if (isSyncing) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Sync,
                                    contentDescription = "Sincronizar"
                                )
                            }
                        }
                        if (!hasNavigationRail) {
                            IconButton(onClick = onLogout) {
                                Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { paddingValues ->
            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                val scrollState = rememberScrollState()
                
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    val maxWidth = maxWidth
                    val isWide = maxWidth > 800.dp

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // Saludo
                        GreetingSection(uiState.userName, uiState.role)

                        if (uiState.role.level >= 3) {
                            RiskSummarySection(uiState.riskSummary)
                        }

                        if (isWide) {
                            // Layout para pantallas anchas (Escritorio)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(24.dp)
                            ) {
                                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(24.dp)) {
                                    if (uiState.role.level >= 3) {
                                        StatsGrid(uiState)
                                    }
                                    QuickAccessSection(
                                        role = uiState.role,
                                        onAttendance = onNavigateToAttendance,
                                        onGrades = onNavigateToGrades,
                                        onStudents = onNavigateToStudents,
                                        onClasses = onNavigateToClasses,
                                        onEmployees = onNavigateToEmployees,
                                        onSalaries = onNavigateToSalaries,
                                        onTasks = onNavigateToTasks,
                                        onExams = onNavigateToExams,
                                        onAnnouncements = onNavigateToAnnouncements,
                                        onChat = onNavigateToChat,
                                        onReports = onNavigateToReports,
                                        onCurricular = onNavigateToCurricular,
                                        onCash = onNavigateToCash,
                                        onUsers = onNavigateToUsers,
                                        onNavigateToAutoevaluacion = onNavigateToAutoevaluacion,
                                        onNavigateToSieConfig = onNavigateToSieConfig
                                    )
                                    if (uiState.role.level >= 2) {
                                        RecentActivitySection()
                                    }
                                }
                                
                                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(24.dp)) {
                                    if (uiState.role.level >= 2) {
                                        SubjectPerformanceSection(uiState.subjectAverages)
                                        GradeEvolutionChart(uiState.periodAverages)
                                        GradeDistributionChart(uiState.gradeDistribution)
                                    }
                                }
                            }
                        } else {
                            // Layout para móviles (Vertical)
                            if (uiState.role.level >= 3) {
                                StatsGrid(uiState)
                            }

                            QuickAccessSection(
                                role = uiState.role,
                                onAttendance = onNavigateToAttendance,
                                onGrades = onNavigateToGrades,
                                onStudents = onNavigateToStudents,
                                onClasses = onNavigateToClasses,
                                onEmployees = onNavigateToEmployees,
                                onSalaries = onNavigateToSalaries,
                                onTasks = onNavigateToTasks,
                                onExams = onNavigateToExams,
                                onAnnouncements = onNavigateToAnnouncements,
                                onChat = onNavigateToChat,
                                onReports = onNavigateToReports,
                                onCurricular = onNavigateToCurricular,
                                onCash = onNavigateToCash,
                                onUsers = onNavigateToUsers,
                                onNavigateToAutoevaluacion = onNavigateToAutoevaluacion,
                                onNavigateToSieConfig = onNavigateToSieConfig
                            )
                            
                            if (uiState.role.level >= 2) {
                                RecentActivitySection()
                                SubjectPerformanceSection(uiState.subjectAverages)
                                GradeEvolutionChart(uiState.periodAverages)
                                GradeDistributionChart(uiState.gradeDistribution)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
private fun GradeEvolutionChart(periodAverages: Map<String, Double>) {
    if (periodAverages.isEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = "Evolución de Promedio", style = MaterialTheme.typography.titleLarge)
            Card(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay datos históricos disponibles")
                }
            }
        }
        return
    }

    val sortedPeriods = periodAverages.keys.sorted()
    val data = sortedPeriods.mapIndexed { index, period ->
        DefaultPoint((index + 1).toFloat(), periodAverages[period]?.toFloat() ?: 0f)
    }

    val maxVal = (data.maxOfOrNull { it.y } ?: 0f).coerceAtLeast(20f)

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Evolución de Promedio",
            style = MaterialTheme.typography.titleLarge
        )
        Card(
            modifier = Modifier.fillMaxWidth().height(250.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            ChartLayout(
                modifier = Modifier.padding(16.dp).fillMaxSize(),
                title = { Text("Evolución de Notas") }
            ) {
                XYGraph(
                    xAxisModel = rememberFloatLinearAxisModel(1f..sortedPeriods.size.toFloat().coerceAtLeast(1f)),
                    yAxisModel = rememberFloatLinearAxisModel(0f..maxVal),
                    xAxisTitle = "Periodo",
                    yAxisTitle = "Nota"
                ) {
                    LinePlot(
                        data = data,
                        lineStyle = LineStyle(
                            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary),
                            strokeWidth = 2.dp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun SubjectPerformanceSection(subjectAverages: Map<String, Double>) {
    if (subjectAverages.isEmpty()) return

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Rendimiento por Asignatura",
            style = MaterialTheme.typography.titleLarge
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                subjectAverages.forEach { (subject, average) ->
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = subject,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = average.let { 
                                    val rounded = (kotlin.math.round(it * 10) / 10.0).toString()
                                    if (rounded.contains(".")) rounded else "$rounded.0"
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (average >= 13.0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                            )
                        }
                        LinearProgressIndicator(
                            progress = { (average.toFloat() / 20f).coerceIn(0f, 1f) },
                            modifier = Modifier.fillMaxWidth().height(6.dp).padding(top = 4.dp),
                            color = if (average >= 13.0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
private fun GradeDistributionChart(distribution: Map<String, Int>) {
    // Si no hay datos, mostrar un mensaje o espacio vacío
    if (distribution.isEmpty() || distribution.values.all { it == 0 }) {
        Card(modifier = Modifier.fillMaxWidth().height(100.dp)) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay datos de calificaciones disponibles")
            }
        }
        return
    }

    val values = distribution.values.map { it.toFloat() }
    val labels = distribution.keys.toList()
    val colors = generateHueColorPalette(values.size)

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Distribución de Calificaciones",
            style = MaterialTheme.typography.titleLarge
        )
        Card(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            ChartLayout(
                modifier = Modifier.padding(16.dp).fillMaxSize()
            ) {
                PieChart(
                    values = values,
                    label = { index: Int ->
                        Text(
                            text = "${labels[index]}: ${values[index].toInt()}",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    slice = { index: Int ->
                        DefaultSlice(
                            color = colors[index],
                            hoverExpandFactor = 1.05f
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun GreetingSection(userName: String, role: com.sigeschool.domain.model.UserRole) {
    Column {
        Text(
            text = "¡Hola, $userName!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Rol: ${role.name.replace("_", " ")}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = if (role.level >= 2) "Hoy es un gran día para gestionar la institución" else "Revisa tus actividades para hoy",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RiskSummarySection(riskSummary: Map<String, Int>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Riesgo de Deserción Escolar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RiskItem("Crítico", riskSummary["CRITICAL"] ?: 0, Color(0xFFD32F2F))
                RiskItem("Alto", riskSummary["HIGH"] ?: 0, Color(0xFFF57C00))
                RiskItem("Medio", riskSummary["MEDIUM"] ?: 0, Color(0xFFFBC02D))
                RiskItem("Bajo", riskSummary["LOW"] ?: 0, Color(0xFF388E3C))
            }
        }
    }
}

@Composable
private fun RiskItem(label: String, count: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            count.toString(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun StatsGrid(uiState: DashboardUiState) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            KpiCard(
                title = "Estudiantes",
                value = uiState.totalStudents.toString(),
                icon = Icons.Rounded.People,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            KpiCard(
                title = "Asistencia",
                value = "${uiState.todayAttendancePercentage}%",
                icon = Icons.Rounded.CheckCircle,
                color = Color(0xFF4CAF50),
                modifier = Modifier.weight(1f)
            )
        }
        
        if (uiState.role.level >= 3) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                KpiCard(
                    title = "Ingresos",
                    value = formatCurrency(uiState.totalRevenue),
                    icon = Icons.Rounded.Payments,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.weight(1f)
                )
                KpiCard(
                    title = "Cartera",
                    value = formatCurrency(uiState.totalPending),
                    icon = Icons.Rounded.ErrorOutline,
                    color = Color(0xFFF44336),
                    modifier = Modifier.weight(1f)
                )
            }
            
            KpiCard(
                title = "Tasa Morosidad",
                value = "${(uiState.morosidadRate * 100).toInt()}%",
                icon = Icons.Rounded.TrendingUp,
                color = if (uiState.morosidadRate > 0.2) Color(0xFFF44336) else Color(0xFF4CAF50)
            )
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                KpiCard(
                    title = "Promedio",
                    value = uiState.generalAverageGrade.let { 
                        val rounded = (kotlin.math.round(it * 10) / 10.0).toString()
                        if (rounded.contains(".")) rounded else "$rounded.0"
                    },
                    icon = Icons.Rounded.Grade,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )
                Box(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun KpiCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(title, style = MaterialTheme.typography.labelMedium, textAlign = TextAlign.Center)
            Text(
                value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = color,
                textAlign = TextAlign.Center
            )
        }
    }
}

private fun formatCurrency(amount: Double): String {
    // Formato simple para KMP
    val parts = amount.toString().split(".")
    val integerPart = parts[0].reversed().chunked(3).joinToString(",").reversed()
    return "$$integerPart"
}

@Composable
private fun RecentActivitySection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Actividad Reciente",
            style = MaterialTheme.typography.titleLarge
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("• Sincronización completada hace 5 min", style = MaterialTheme.typography.bodyMedium)
                Text("• Lista de asistencia guardada (Hoy)", style = MaterialTheme.typography.bodyMedium)
                Text("• 3 nuevas notas registradas", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun QuickAccessSection(
    role: com.sigeschool.domain.model.UserRole,
    onAttendance: () -> Unit,
    onGrades: () -> Unit,
    onStudents: () -> Unit,
    onClasses: () -> Unit,
    onEmployees: () -> Unit,
    onSalaries: () -> Unit,
    onTasks: () -> Unit,
    onExams: () -> Unit,
    onAnnouncements: () -> Unit,
    onChat: () -> Unit,
    onReports: () -> Unit,
    onCurricular: () -> Unit,
    onCash: () -> Unit,
    onUsers: () -> Unit,
    onNavigateToAutoevaluacion: () -> Unit,
    onNavigateToSieConfig: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "Acceso Rápido",
            style = MaterialTheme.typography.titleLarge
        )

        // Fila 1: Siempre visible pero con variaciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (role.level >= 2) {
                QuickActionButton("Asistencia", "Pasar Lista", onClick = onAttendance)
            }
            QuickActionButton("Tareas", if (role.level >= 2) "Pendientes" else "Mis Tareas", onClick = onTasks)
            QuickActionButton("Exámenes", if (role.level >= 2) "Programar" else "Realizar", onClick = onExams)
        }

        // Fila 2: Condicional
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionButton("Anuncios", "Comunicados", onClick = onAnnouncements)
            if (role.level >= 2) {
                QuickActionButton("Alumnos", "Ver todos", onClick = onStudents)
                QuickActionButton("Clases", "Gestionar", onClick = onClasses)
            }
            if (role == com.sigeschool.domain.model.UserRole.ESTUDIANTE) {
                QuickActionButton("Autoevaluación", "Realizar", onClick = onNavigateToAutoevaluacion)
            }
        }
        
        // Fila 3: Especializada
        if (role.level >= 3 || role == com.sigeschool.domain.model.UserRole.DOCENTE || role == com.sigeschool.domain.model.UserRole.SECRETARIA) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (role.level >= 2) QuickActionButton("Calificar", "Notas", onClick = onGrades)
                QuickActionButton("Chat", "Interno", onClick = onChat)
                if (role.level >= 3) QuickActionButton("Empleados", "Personal", onClick = onEmployees)
            }
        }

        // Fila 4: Gestión Curricular y Admin
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (role.level >= 2) {
                QuickActionButton("Curricular", "PEI/Planes", onClick = onCurricular)
            }
            if (role == com.sigeschool.domain.model.UserRole.RECTOR || 
                role == com.sigeschool.domain.model.UserRole.SECRETARIA || 
                role == com.sigeschool.domain.model.UserRole.REPRESENTANTE_LEGAL) {
                QuickActionButton("Caja", "Arqueo/Pagos", onClick = onCash)
            }
            if (role.level >= 3) {
                QuickActionButton("Salarios", "Pagos", onClick = onSalaries)
                QuickActionButton("Reportes", "PDF", onClick = onReports)
            }
        }

        // Fila 5: Usuarios Admin e Institucional
        if (role.level >= 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickActionButton("Usuarios", "Admin", onClick = onUsers)
                QuickActionButton("Config SIEE", "Promoción", onClick = onNavigateToSieConfig)
            }
        }
    }
}

@Composable
private fun RowScope.QuickActionButton(
    title: String, 
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.weight(1f),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

data class StatItem(
    val title: String,
    val value: String,
    val subtitle: String
)
