package com.sigeschool.presentation.screens.billing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.billing.InvoiceStatus
import com.sigeschool.domain.model.billing.Invoice
import com.sigeschool.presentation.viewmodel.billing.BillingEvent
import com.sigeschool.presentation.viewmodel.billing.BillingViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillingScreen(
    institutionId: String,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToMassive: () -> Unit,
    viewModel: BillingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(institutionId) {
        viewModel.onEvent(BillingEvent.LoadInvoices)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Facturación") },
                actions = {
                    IconButton(onClick = { /* Buscar */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToMassive) {
                Icon(Icons.Default.Add, contentDescription = "Generación Masiva")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.invoices.isEmpty()) {
                Text(
                    "No hay facturas generadas",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.invoices) { invoice ->
                        InvoiceItem(
                            invoice = invoice,
                            onClick = { onNavigateToDetail(invoice.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InvoiceItem(
    invoice: Invoice,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = invoice.number,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = invoice.studentName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${invoice.concept} - ${invoice.grade}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${invoice.totalAmount}",
                    style = MaterialTheme.typography.titleLarge
                )
                StatusBadge(status = invoice.status)
            }
        }
    }
}

@Composable
fun StatusBadge(status: InvoiceStatus) {
    val color = when (status) {
        InvoiceStatus.ACCEPTED -> Color(0xFF4CAF50)
        InvoiceStatus.SENT -> Color(0xFF2196F3)
        InvoiceStatus.DRAFT -> Color(0xFFFFC107)
        InvoiceStatus.REJECTED -> Color(0xFFF44336)
        InvoiceStatus.ANNULLED -> Color.Gray
    }
    
    Surface(
        color = color.copy(alpha = 0.2f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = status.name,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}
