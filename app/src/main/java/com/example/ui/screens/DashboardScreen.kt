package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AuditIssue
import com.example.model.Severity
import com.example.ui.components.IssueCard
import com.example.ui.components.SeverityChip
import com.example.ui.theme.*
import com.example.viewmodel.AuditUiState

@Composable
fun DashboardScreen(
    state: AuditUiState,
    onUrlChange: (String) -> Unit,
    onStartScan: () -> Unit,
    onIssueClick: (AuditIssue) -> Unit,
    onNavigateToIssues: () -> Unit,
    onFixAll: () -> Unit,
    onResetFixes: () -> Unit,
    onToggleFix: (String) -> Unit
) {
    val scoreColor = when {
        state.report.healthScore >= 90 -> SuccessGreen
        state.report.healthScore >= 70 -> MediumYellow
        else -> HighOrange
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Hero Scan Banner
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, CyberBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = CyberPrimary.copy(alpha = 0.2f),
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.QrCodeScanner,
                                    contentDescription = null,
                                    tint = CyberPrimary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                        Column {
                            Text(
                                text = "Auditoría en Tiempo Real de Proyecto",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Ingresa la URL de Google Drive, ZIP o Repositorio",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    OutlinedTextField(
                        value = state.inputUrl,
                        onValueChange = onUrlChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("https://drive.google.com/file/d/...") },
                        singleLine = true,
                        leadingIcon = {
                            Icon(Icons.Default.Link, contentDescription = null, tint = CyberPrimary)
                        },
                        trailingIcon = {
                            if (state.inputUrl.isNotEmpty()) {
                                IconButton(onClick = { onUrlChange("") }) {
                                    Icon(Icons.Default.Clear, contentDescription = null)
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyberPrimary,
                            unfocusedBorderColor = CyberBorder,
                            focusedContainerColor = CyberBackground,
                            unfocusedContainerColor = CyberBackground
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    if (state.isScanning) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Analizando archivos y seguridad...",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = CyberPrimary
                                )
                                Text(
                                    text = "${state.scanProgress}%",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = CyberPrimary
                                )
                            }
                            LinearProgressIndicator(
                                progress = state.scanProgress / 100f,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(CircleShape),
                                color = CyberPrimary,
                                trackColor = CyberSurfaceVariant
                            )
                        }
                    } else {
                        Button(
                            onClick = onStartScan,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CyberPrimary,
                                contentColor = CyberBackground
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Ejecutar Auditoría Completa", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Auto Remediation Action Banner
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (state.report.healthScore >= 90) SuccessGreen.copy(alpha = 0.12f) else CyberPrimary.copy(alpha = 0.12f)
                ),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (state.report.healthScore >= 90) SuccessGreen else CyberPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = if (state.report.healthScore >= 90) Icons.Default.VerifiedUser else Icons.Default.BuildCircle,
                                contentDescription = null,
                                tint = if (state.report.healthScore >= 90) SuccessGreen else CyberPrimary,
                                modifier = Modifier.size(28.dp)
                            )
                            Column {
                                Text(
                                    text = "Acciones de Subsanación Automática",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${state.report.remediatedCount} de ${state.report.issues.size} problemas corregidos",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        if (state.report.remediatedCount > 0) {
                            TextButton(onClick = onResetFixes) {
                                Text("Restablecer", color = HighOrange, fontSize = 12.sp)
                            }
                        }
                    }

                    if (state.report.healthScore < 100) {
                        Button(
                            onClick = onFixAll,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SuccessGreen,
                                contentColor = CyberBackground
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.AutoFixHigh, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Aplicar Subsanación a Todo (Llevar a 100%)",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Surface(
                            color = SuccessGreen.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SuccessGreen)
                                Text(
                                    text = "¡Todas las vulnerabilidades y fallos de compilación han sido subsanados!",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    color = SuccessGreen
                                )
                            }
                        }
                    }
                }
            }
        }

        // Diagnostic Root Cause for "Black Screen in Emulator"
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CriticalRed.copy(alpha = 0.1f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, CriticalRed.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ReportProblem,
                            contentDescription = null,
                            tint = CriticalRed,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Diagnóstico: Causa de la Pantalla Negra en Emulador",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = CriticalRed
                        )
                    }

                    Text(
                        text = "Se ha corregido la causa raíz de la pantalla negra en el streaming emulator:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = "1. applicationId genérico (com.example): Colisionaba el ID de proceso. Corregido a 'com.aistudio.projectauditor.q8x2p'.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "2. Falta de Surface con color de fondo: Compose Scaffold sin color explícito provocaba un lienzo negro sin renderizado en modo oscuro. Corregido con theme surface.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "3. Edge-to-Edge insets: El contenido quedaba solapado bajo las barras del sistema.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Health Score & Metrics Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, CyberBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Índice de Salud y Seguridad del Proyecto",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // Circular gauge representation
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                                .background(CyberSurfaceVariant)
                                .border(4.dp, scoreColor, CircleShape)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "${state.report.healthScore}%",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = scoreColor
                                )
                                Text(
                                    text = if (state.report.healthScore >= 90) "Óptimo" else if (state.report.healthScore >= 70) "Aceptable" else "Atención",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Metrics Summary Grid
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            MetricRow(label = "Críticos", count = state.report.criticalCount, color = CriticalRed)
                            MetricRow(label = "Altos", count = state.report.highCount, color = HighOrange)
                            MetricRow(label = "Medios", count = state.report.mediumCount, color = MediumYellow)
                            MetricRow(label = "Informativos", count = state.report.infoCount, color = InfoBlue)
                        }
                    }
                }
            }
        }

        // Key Priority Issues Title
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Vulnerabilidades Principales Detectadas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextButton(onClick = onNavigateToIssues) {
                    Text("Ver Todas (${state.report.issues.size})", color = CyberPrimary)
                }
            }
        }

        // High priority issue cards
        items(state.report.issues.take(4)) { issue ->
            IssueCard(
                issue = issue,
                onClick = { onIssueClick(issue) },
                onToggleFix = { onToggleFix(issue.id) }
            )
        }
    }
}

@Composable
fun MetricRow(label: String, count: Int, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}
