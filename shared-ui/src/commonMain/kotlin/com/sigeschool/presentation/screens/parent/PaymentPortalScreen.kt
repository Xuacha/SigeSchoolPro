package com.sigeschool.presentation.screens.parent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentPortalScreen(
    studentId: String,
    studentName: String,
    onBack: () -> Unit,
    onRedirectToPayment: (String) -> Unit,
    viewModel: PaymentPortalViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedMethod by remember { mutableStateOf("PSE") }
    val amountToPay = 150000.0 // Ejemplo: Valor de la pensión

    LaunchedEffect(uiState) {
        val state = uiState
        if (state is PaymentUiState.Success) {
            onRedirectToPayment(state.redirectUrl)
            viewModel.resetState()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pagar Pensión - $studentName") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total a Pagar", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "$${amountToPay}",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("Concepto: Pensión Mensual Julio 2026", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Text("Seleccione Método de Pago", style = MaterialTheme.typography.titleMedium)

            PaymentMethodItem(
                title = "PSE (Débito Bancario)",
                icon = Icons.Default.AccountBalance,
                selected = selectedMethod == "PSE",
                onClick = { selectedMethod = "PSE" }
            )

            PaymentMethodItem(
                title = "Nequi / Daviplata",
                icon = Icons.Default.Smartphone,
                selected = selectedMethod == "NEQUI",
                onClick = { selectedMethod = "NEQUI" }
            )

            PaymentMethodItem(
                title = "Bre-B (Pagos Inmediatos)",
                icon = Icons.Default.AccountBalance,
                selected = selectedMethod == "BRE_B",
                onClick = { selectedMethod = "BRE_B" }
            )

            PaymentMethodItem(
                title = "Tarjeta de Crédito",
                icon = Icons.Default.CreditCard,
                selected = selectedMethod == "CC",
                onClick = { selectedMethod = "CC" }
            )

            Spacer(modifier = Modifier.weight(1f))

            if (uiState is PaymentUiState.Error) {
                Text(
                    text = (uiState as PaymentUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                onClick = {
                    viewModel.processPayment(
                        amount = amountToPay,
                        description = "Pago Pensión Julio - $studentName",
                        studentId = studentId,
                        conceptId = "PENSION_JUL_2026",
                        method = selectedMethod
                    )
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = uiState !is PaymentUiState.Loading
            ) {
                if (uiState is PaymentUiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Proceder al Pago Seguro")
                }
            }
            
            Text(
                "Protegido por PayU Colombia. SigeSchool no almacena sus datos bancarios.",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun PaymentMethodItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        color = if (selected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(selected = selected, onClick = null)
        }
    }
}
