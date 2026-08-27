package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AuditIssue
import com.example.model.Severity
import com.example.ui.theme.*

@Composable
fun SeverityChip(severity: Severity) {
    val (bgColor, textColor, text) = when (severity) {
        Severity.CRITICAL -> Triple(CriticalRed.copy(alpha = 0.2f), CriticalRed, "CRÍTICO")
        Severity.HIGH -> Triple(HighOrange.copy(alpha = 0.2f), HighOrange, "ALTO")
        Severity.MEDIUM -> Triple(MediumYellow.copy(alpha = 0.2f), MediumYellow, "MEDIO")
        Severity.INFO -> Triple(InfoBlue.copy(alpha = 0.2f), InfoBlue, "INFO")
    }

    Surface(
        color = bgColor,
        shape = RoundedCornerShape(6.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, textColor.copy(alpha = 0.5f))
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun IssueCard(
    issue: AuditIssue,
    onClick: () -> Unit,
    onToggleFix: (() -> Unit)? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (issue.isFixed) SuccessGreen.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (issue.isFixed) SuccessGreen.copy(alpha = 0.5f) else CyberBorder
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = if (issue.isFixed) Icons.Default.CheckCircle else when (issue.severity) {
                            Severity.CRITICAL -> Icons.Default.Dangerous
                            Severity.HIGH -> Icons.Default.Warning
                            Severity.MEDIUM -> Icons.Default.Info
                            Severity.INFO -> Icons.Default.CheckCircle
                        },
                        contentDescription = null,
                        tint = if (issue.isFixed) SuccessGreen else when (issue.severity) {
                            Severity.CRITICAL -> CriticalRed
                            Severity.HIGH -> HighOrange
                            Severity.MEDIUM -> MediumYellow
                            Severity.INFO -> InfoBlue
                        },
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = issue.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                if (issue.isFixed) {
                    Surface(
                        color = SuccessGreen.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SuccessGreen)
                    ) {
                        Text(
                            text = "SUBSANADO",
                            color = SuccessGreen,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                } else {
                    SeverityChip(severity = issue.severity)
                }
            }

            Text(
                text = issue.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CyberSurfaceVariant, RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "📍 ${issue.codeLocation}",
                    style = MaterialTheme.typography.labelMedium,
                    fontFamily = FontFamily.Monospace,
                    color = CyberPrimary
                )

                onToggleFix?.let { toggle ->
                    Button(
                        onClick = toggle,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (issue.isFixed) CyberSurfaceVariant else SuccessGreen,
                            contentColor = if (issue.isFixed) MaterialTheme.colorScheme.onSurface else CyberBackground
                        ),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                        modifier = Modifier.height(28.dp),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Icon(
                            imageVector = if (issue.isFixed) Icons.Default.Undo else Icons.Default.Build,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (issue.isFixed) "Revertir" else "Subsanar",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } ?: Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Solución",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun IssueDetailModal(
    issue: AuditIssue,
    onDismiss: () -> Unit,
    onToggleFix: (() -> Unit)? = null
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                onToggleFix?.let { toggle ->
                    Button(
                        onClick = {
                            toggle()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (issue.isFixed) HighOrange else SuccessGreen,
                            contentColor = CyberBackground
                        )
                    ) {
                        Icon(
                            imageVector = if (issue.isFixed) Icons.Default.Undo else Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (issue.isFixed) "Revertir Subsanación" else "Aplicar Subsanación", fontWeight = FontWeight.Bold)
                    }
                }

                Button(
                    onClick = {
                        copyToClipboard(context, "Solución para ${issue.title}:\n\n${issue.codeSnippetFix}")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CyberPrimary, contentColor = CyberBackground)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Copiar Código")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar", color = MaterialTheme.colorScheme.onSurface)
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeverityChip(severity = issue.severity)
                Text(
                    text = issue.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = issue.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Divider(color = CyberBorder)

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Ubicación en el Proyecto:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = CyberPrimary
                    )
                    Text(
                        text = issue.codeLocation,
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Impacto en Dispositivos / Emulador:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = HighOrange
                    )
                    Text(
                        text = issue.impactOnDevice,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Código de Corrección Recomendado:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = SuccessGreen
                    )
                    Surface(
                        color = CyberBackground,
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CyberBorder)
                    ) {
                        Text(
                            text = issue.codeSnippetFix,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFFA6E22E),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp)
    )
}

fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("AuditPro Fix", text)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "¡Código de solución copiado al portapapeles!", Toast.LENGTH_SHORT).show()
}
