package com.example.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.AuditCategory
import com.example.model.AuditIssue
import com.example.ui.components.IssueCard
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberPrimary
import com.example.ui.theme.CyberSurfaceVariant
import com.example.viewmodel.AuditUiState

@Composable
fun IssuesScreen(
    state: AuditUiState,
    onSearchChange: (String) -> Unit,
    onCategorySelect: (AuditCategory?) -> Unit,
    onIssueClick: (AuditIssue) -> Unit,
    onToggleFix: (String) -> Unit
) {
    val filteredIssues = state.report.issues.filter { issue ->
        val matchesCategory = state.selectedCategory == null || issue.category == state.selectedCategory
        val matchesSearch = state.searchQuery.isBlank() ||
                issue.title.contains(state.searchQuery, ignoreCase = true) ||
                issue.description.contains(state.searchQuery, ignoreCase = true) ||
                issue.codeLocation.contains(state.searchQuery, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Search & Filter Header
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Vulnerabilidades y Problemas (${filteredIssues.size})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = onSearchChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Buscar error, archivo o clase...") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = CyberPrimary)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CyberPrimary,
                        unfocusedBorderColor = CyberBorder,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = state.selectedCategory == null,
                        onClick = { onCategorySelect(null) },
                        label = { Text("Todas") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = CyberPrimary,
                            selectedLabelColor = MaterialTheme.colorScheme.background
                        )
                    )

                    AuditCategory.values().forEach { category ->
                        val label = when (category) {
                            AuditCategory.SECURITY -> "Seguridad"
                            AuditCategory.COMPILATION -> "Compilación"
                            AuditCategory.STABILITY -> "Estabilidad"
                            AuditCategory.PERMISSIONS -> "Permisos"
                        }
                        FilterChip(
                            selected = state.selectedCategory == category,
                            onClick = { onCategorySelect(category) },
                            label = { Text(label) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CyberPrimary,
                                selectedLabelColor = MaterialTheme.colorScheme.background
                            )
                        )
                    }
                }
            }
        }

        if (filteredIssues.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron hallazgos para este filtro.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            items(filteredIssues) { issue ->
                IssueCard(
                    issue = issue,
                    onClick = { onIssueClick(issue) },
                    onToggleFix = { onToggleFix(issue.id) }
                )
            }
        }
    }
}
