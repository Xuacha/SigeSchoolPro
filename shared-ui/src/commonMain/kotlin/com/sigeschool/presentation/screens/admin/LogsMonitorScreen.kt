package com.sigeschool.presentation.screens.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.*
import com.sigeschool.presentation.components.DateRangePicker
import com.sigeschool.presentation.viewmodel.admin.LogsMonitorViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogsMonitorScreen(
    viewModel: LogsMonitorViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val metrics by viewModel.metrics.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Monitoreo de Notificaciones") },
                actions = {
                    IconButton(
                        onClick = { viewModel.exportarLogs("EXCEL") },
                        enabled = !state.isExporting
                    ) {
                        if (state.isExporting) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        } else {
                            Icon(Icons.Default.Download, contentDescription = "Exportar a Excel")
                        }
                    }
                    IconButton(
                        onClick = { viewModel.exportarLogs("PDF") },
                        enabled = !state.isExporting
                    ) {
                        Icon(Icons.Default.PictureAsPdf, contentDescription = "Exportar a PDF")
                    }
                    IconButton(onClick = { viewModel.loadLogs(state.filtrosActuales) }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refrescar")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            Column {
                // 1. Resumen de métricas
                metrics?.let {
                    MetricsRow(metrics = it)
                }

                // 2. Barra de filtros
                FilterBar(
                    filtros = state.filtrosActuales,
                    onFiltrosChanged = { viewModel.actualizarFiltros(it) }
                )

                // 3. Tabla de logs
                Box(modifier = Modifier.weight(1f)) {
                    LogsTable(
                        logs = state.logs,
                        isLoading = state.isLoading,
                        onRowClick = { viewModel.verDetalle(it.idLog) }
                    )
                }

                // 4. Paginación
                Pagination(
                    paginaActual = state.paginaActual,
                    totalRegistros = state.totalRegistros,
                    registrosPorPagina = state.registrosPorPagina,
                    onPageChange = { viewModel.cambiarPagina(it) }
                )
            }

            // SnackBar para éxito
            state.mensajeExito?.let { mensaje ->
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
                    action = {
                        TextButton(onClick = { viewModel.limpiarMensaje() }) {
                            Text("Cerrar")
                        }
                    }
                ) {
                    Text(mensaje)
                }
            }

            // SnackBar para error
            state.error?.let { mensaje ->
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    action = {
                        TextButton(onClick = { viewModel.limpiarError() }) {
                            Text("Cerrar")
                        }
                    }
                ) {
                    Text(mensaje)
                }
            }
        }
    }

    // Modal de detalle
    state.detalleSeleccionado?.let { detalle ->
        LogDetalleDialog(
            detalle = detalle,
            onDismiss = { viewModel.cerrarDetalle() },
            onReenviar = { viewModel.reenviarNotificacion(it) }
        )
    }
}

@Composable
fun MetricsRow(metrics: MetricsSummary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MetricCard(
            title = "Total",
            value = metrics.totalNotificaciones.toString(),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )
            MetricCard(
                title = "Éxito",
                value = "${metrics.tasaExito}%",
                color = Color(0xFF4CAF50),
                modifier = Modifier.weight(1f)
            )
        MetricCard(
            title = "Fallidos",
            value = metrics.totalFallidos.toString(),
            color = Color(0xFFF44336),
            modifier = Modifier.weight(1f)
        )
        MetricCard(
            title = "Pendientes",
            value = metrics.totalPendientes.toString(),
            color = Color(0xFFFFC107),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MetricCard(title: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.labelMedium, color = color)
            Text(value, style = MaterialTheme.typography.titleMedium, color = color)
        }
    }
}

@Composable
fun FilterBar(
    filtros: FiltrosLogs,
    onFiltrosChanged: (FiltrosLogs) -> Unit
) {
    var busqueda by remember { mutableStateOf(filtros.busqueda) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = busqueda,
            onValueChange = { 
                busqueda = it
                onFiltrosChanged(filtros.copy(busqueda = it))
            },
            label = { Text("Buscar por ID o Mensaje") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (busqueda.isNotEmpty()) {
                    IconButton(onClick = { 
                        busqueda = ""
                        onFiltrosChanged(filtros.copy(busqueda = ""))
                    }) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        )
        
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DateRangePicker(
                from = filtros.fechaDesde,
                to = filtros.fechaHasta,
                onFromChange = { onFiltrosChanged(filtros.copy(fechaDesde = it)) },
                onToChange = { onFiltrosChanged(filtros.copy(fechaHasta = it)) }
            )
        }
        
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = filtros.estados.contains("FALLIDO"),
                onClick = {
                    val nuevosEstados = if (filtros.estados.contains("FALLIDO")) 
                        filtros.estados - "FALLIDO" else filtros.estados + "FALLIDO"
                    onFiltrosChanged(filtros.copy(estados = nuevosEstados))
                },
                label = { Text("Solo Fallidos") }
            )
            FilterChip(
                selected = filtros.canales.contains("WHATSAPP"),
                onClick = {
                    val nuevosCanales = if (filtros.canales.contains("WHATSAPP")) 
                        filtros.canales - "WHATSAPP" else filtros.canales + "WHATSAPP"
                    onFiltrosChanged(filtros.copy(canales = nuevosCanales))
                },
                label = { Text("WhatsApp") }
            )
            FilterChip(
                selected = filtros.canales.contains("EMAIL"),
                onClick = {
                    val nuevosCanales = if (filtros.canales.contains("EMAIL")) 
                        filtros.canales - "EMAIL" else filtros.canales + "EMAIL"
                    onFiltrosChanged(filtros.copy(canales = nuevosCanales))
                },
                label = { Text("Email") }
            )
        }
    }
}

@Composable
fun LogsTable(
    logs: List<LogNotificacion>,
    isLoading: Boolean,
    onRowClick: (LogNotificacion) -> Unit
) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (logs.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No se encontraron registros")
        }
    } else {
        LazyColumn {
            items(logs) { log ->
                LogRow(log = log, onClick = { onRowClick(log) })
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun LogRow(log: LogNotificacion, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onClick() },
        headlineContent = { Text("Notificación: ${log.idNotificacion.take(8)}...") },
        supportingContent = { 
            Text("Canal: ${log.canal} | Intentos: ${log.intentos} | ${if(log.exito) "Éxito" else "Error"}")
        },
        trailingContent = {
            val color = if (log.exito) Color(0xFF4CAF50) else Color(0xFFF44336)
            Icon(
                imageVector = if (log.exito) Icons.Default.CheckCircle else Icons.Default.Error,
                contentDescription = null,
                tint = color
            )
        }
    )
}

@Composable
fun Pagination(
    paginaActual: Int,
    totalRegistros: Int,
    registrosPorPagina: Int,
    onPageChange: (Int) -> Unit
) {
    val totalPaginas = (totalRegistros + registrosPorPagina - 1) / registrosPorPagina
    
    if (totalPaginas > 1) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onPageChange(paginaActual - 1) },
                enabled = paginaActual > 1
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Text("Página $paginaActual de $totalPaginas", modifier = Modifier.padding(horizontal = 16.dp))
            IconButton(
                onClick = { onPageChange(paginaActual + 1) },
                enabled = paginaActual < totalPaginas
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}

@Composable
fun LogDetalleDialog(
    detalle: LogDetalle,
    onDismiss: () -> Unit,
    onReenviar: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Detalle de Notificación") },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                DetailItem("ID Notificación", detalle.notificacion.idNotificacion)
                DetailItem("Asunto", detalle.notificacion.asunto)
                DetailItem("Mensaje", detalle.notificacion.mensaje)
                DetailItem("Canal", detalle.log.canal)
                DetailItem("Fecha", detalle.log.fechaIntento.toString()) // Formatear en app real
                DetailItem("Último Error", detalle.log.mensajeRespuesta ?: "N/A")
                
                Text("Historial de Intentos", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 16.dp))
                detalle.historialIntentos.forEach { intento ->
                    Text("- ${intento.fechaIntento}: ${if(intento.exito) "Éxito" else "Error (${intento.codigoRespuesta})"}")
                }
            }
        },
        confirmButton = {
            if (!detalle.log.exito) {
                Button(onClick = { 
                    onReenviar(detalle.notificacion.idNotificacion)
                    onDismiss()
                }) {
                    Text("Reenviar")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}

@Composable
fun DetailItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}
