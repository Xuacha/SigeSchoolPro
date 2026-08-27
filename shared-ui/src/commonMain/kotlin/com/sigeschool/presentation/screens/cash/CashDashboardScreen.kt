package com.sigeschool.presentation.screens.cash

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.billing.CashTransaction
import com.sigeschool.domain.model.billing.CashTransactionType
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashDashboardScreen(
    viewModel: CashViewModel = koinViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToFastCollection: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Manejo de errores y mensajes de éxito
    LaunchedEffect(uiState.error, uiState.successMessage) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessages()
        }
        uiState.successMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessages()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Caja y Finanzas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToFastCollection) {
                        Icon(Icons.Default.PointOfSale, contentDescription = "Cobro Rápido")
                    }
                    IconButton(onClick = { viewModel.loadTodayData() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refrescar")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            val role = uiState.userRole
            val canRegister = role.level >= 2 // SECRETARIA (2) o superior
            
            if (canRegister) {
                FloatingActionButton(onClick = onNavigateToRegister) {
                    Icon(Icons.Default.Add, contentDescription = "Registrar Movimiento")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            // Tarjeta de Arqueo (Resumen)
            ArqueoSummaryCard(
                initial = uiState.arqueo?.initialBalance ?: 0.0,
                incomes = uiState.arqueo?.totalIncomes ?: 0.0,
                expenses = uiState.arqueo?.totalExpenses ?: 0.0,
                final = uiState.arqueo?.finalBalance ?: 0.0
            )

            Text(
                "Movimientos del Día",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            if (uiState.transactions.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay movimientos registrados hoy", color = Color.Gray)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.transactions) { transaction ->
                        TransactionItem(transaction)
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun ArqueoSummaryCard(initial: Double, incomes: Double, expenses: Double, final: Double) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("RESUMEN DE CAJA (Hoy)", style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                SummaryItem("Ingresos", "+ $${incomes}", Color(0xFF2E7D32))
                SummaryItem("Egresos", "- $${expenses}", Color(0xFFC62828))
                SummaryItem("Saldo Final", "$${final}", MaterialTheme.colorScheme.onPrimaryContainer, isBold = true)
            }
        }
    }
}

@Composable
fun SummaryItem(label: String, value: String, color: Color, isBold: Boolean = false) {
    Column {
        Text(label, style = MaterialTheme.typography.bodySmall)
        Text(
            value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
                color = color
            )
        )
    }
}

@Composable
fun TransactionItem(transaction: CashTransaction) {
    ListItem(
        headlineContent = { Text(transaction.concept) },
        supportingContent = { Text("${transaction.category} • ${transaction.paymentMethod}") },
        trailingContent = {
            Text(
                "${if (transaction.type == CashTransactionType.INCOME) "+" else "-"} $${transaction.amount}",
                color = if (transaction.type == CashTransactionType.INCOME) Color(0xFF2E7D32) else Color(0xFFC62828),
                fontWeight = FontWeight.Bold
            )
        },
        overlineContent = { Text(transaction.personName ?: "Anónimo") }
    )
}
