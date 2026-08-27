package com.sigeschool.presentation.screens.billing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.billing.Invoice
import com.sigeschool.presentation.viewmodel.billing.BillingEvent
import com.sigeschool.presentation.viewmodel.billing.BillingViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceDetailScreen(
    invoiceId: String,
    onNavigateBack: () -> Unit,
    viewModel: BillingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val invoice = uiState.invoices.find { it.id == invoiceId } ?: uiState.selectedInvoice

    LaunchedEffect(invoiceId) {
        // En un caso real, cargaríamos la factura por ID si no está en el estado
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Factura") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        invoice?.let { viewModel.onEvent(BillingEvent.GenerateInvoicePdf(it)) }
                    }) {
                        Icon(Icons.Default.Download, contentDescription = "Descargar PDF")
                    }
                    IconButton(onClick = { /* Simular envío email */ }) {
                        Icon(Icons.Default.Email, contentDescription = "Enviar por Email")
                    }
                }
            )
        }
    ) { padding ->
        if (invoice == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    InvoiceHeader(invoice)
                }
                
                item {
                    HorizontalDivider()
                    Text(
                        "Detalle de Cobro",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(invoice.items) { item ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(item.description, fontWeight = FontWeight.Medium)
                            Text("Cantidad: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
                        }
                        Text("$${item.total}", fontWeight = FontWeight.Bold)
                    }
                }

                item {
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                    SummaryRow("Subtotal", "$${invoice.totalAmount}")
                    SummaryRow("Descuentos", "$0.0")
                    SummaryRow("Total", "$${invoice.totalAmount}", isBold = true)
                    SummaryRow("Pagado", "$${invoice.paidAmount}")
                    SummaryRow("Saldo Pendiente", "$${invoice.balance}", color = MaterialTheme.colorScheme.error)
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { /* Registrar Pago */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar Pago")
                    }
                }
            }
        }
    }
}

@Composable
fun InvoiceHeader(invoice: Invoice) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(invoice.number, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                StatusBadge(status = invoice.status)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Estudiante: ${invoice.studentName}", style = MaterialTheme.typography.bodyLarge)
            Text("Grado: ${invoice.grade}", style = MaterialTheme.typography.bodyMedium)
            Text("Concepto: ${invoice.concept}", style = MaterialTheme.typography.bodyMedium)
            Text("Vence: ${invoice.dueDate}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, isBold: Boolean = false, color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Unspecified) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = if (isBold) MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyMedium)
        Text(value, style = if (isBold) MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyMedium, color = color)
    }
}
