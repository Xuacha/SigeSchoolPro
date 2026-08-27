package com.sigeschool.presentation.screens.cash

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.billing.PaymentMethod
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    onNavigateBack: () -> Unit,
    onNavigateToExpenses: () -> Unit,
    viewModel: PaymentViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PaymentViewModel.PaymentUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is PaymentViewModel.PaymentUiEvent.PaymentSaved -> {
                    snackbarHostState.showSnackbar("Pago registrado exitosamente")
                }
                is PaymentViewModel.PaymentUiEvent.InitiateGatewayPayment -> {
                    snackbarHostState.showSnackbar("Iniciando pago externo con ${event.method}")
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Registrar Pago (Caja)") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    TextButton(onClick = onNavigateToExpenses) {
                        Text("Gastos", color = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = uiState.studentDocumentId,
                onValueChange = { viewModel.onEvent(PaymentViewModel.PaymentEvent.EnteredStudentId(it)) },
                label = { Text("Documento del Estudiante") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            if (uiState.studentName.isNotEmpty()) {
                Text(
                    text = "Estudiante: ${uiState.studentName}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            OutlinedTextField(
                value = uiState.amount,
                onValueChange = { viewModel.onEvent(PaymentViewModel.PaymentEvent.EnteredAmount(it)) },
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            OutlinedTextField(
                value = uiState.concept,
                onValueChange = { viewModel.onEvent(PaymentViewModel.PaymentEvent.EnteredConcept(it)) },
                label = { Text("Concepto") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Método de Pago", style = MaterialTheme.typography.titleMedium)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PaymentMethod.entries.forEach { method ->
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        RadioButton(
                            selected = uiState.paymentMethod == method,
                            onClick = { viewModel.onEvent(PaymentViewModel.PaymentEvent.ChangedPaymentMethod(method)) }
                        )
                        Text(text = when(method) {
                            PaymentMethod.CASH -> "Efectivo"
                            PaymentMethod.CARD -> "Tarjeta"
                            PaymentMethod.TRANSFER -> "Transferencia"
                            PaymentMethod.WOMPI -> "Wompi"
                            PaymentMethod.STRIPE -> "Stripe"
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.onEvent(PaymentViewModel.PaymentEvent.ProcessPayment) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Registrar Pago")
                }
            }
        }
    }
}
