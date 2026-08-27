package com.sigeschool.presentation.screens.cash

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.billing.CashTransactionType
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CashRegisterScreen(
    viewModel: CashViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    var type by remember { mutableStateOf(CashTransactionType.INCOME) }
    var concept by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("MATRICULA") }
    var amount by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("EFECTIVO") }
    var personName by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val categories = if (type == CashTransactionType.INCOME) {
        listOf("MATRICULA", "MENSUALIDAD", "CERTIFICADO", "DUPLICADO_DIPLOMA", "OTROS")
    } else {
        listOf("NOMINA", "SERVICIOS", "PROVEEDORES", "MANTENIMIENTO", "OTROS")
    }

    val paymentMethods = listOf("EFECTIVO", "TRANSFERENCIA", "CHEQUE", "TARJETA")

    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            // Podríamos navegar hacia atrás o limpiar campos
            viewModel.clearMessages()
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (type == CashTransactionType.INCOME) "Registrar Ingreso" else "Registrar Egreso") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Selector de Tipo
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                SegmentedButton(
                    selected = type == CashTransactionType.INCOME,
                    onClick = { type = CashTransactionType.INCOME; category = "MATRICULA" },
                    shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2)
                ) { Text("Ingreso") }
                SegmentedButton(
                    selected = type == CashTransactionType.EXPENSE,
                    onClick = { type = CashTransactionType.EXPENSE; category = "PROVEEDORES" },
                    shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2)
                ) { Text("Egreso") }
            }

            // Categoría (Concepto Predefinido)
            Text("Categoría", style = MaterialTheme.typography.labelLarge)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                categories.forEach { cat ->
                    FilterChip(
                        selected = category == cat,
                        onClick = { category = cat },
                        label = { Text(cat) }
                    )
                }
            }

            OutlinedTextField(
                value = amount,
                onValueChange = { if (it.isEmpty() || it.toDoubleOrNull() != null) amount = it },
                label = { Text("Valor ($)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                prefix = { Text("$ ") }
            )

            OutlinedTextField(
                value = personName,
                onValueChange = { personName = it },
                label = { Text(if (type == CashTransactionType.INCOME) "Nombre del Estudiante / Pagador" else "Beneficiario") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = concept,
                onValueChange = { concept = it },
                label = { Text("Concepto Detallado") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ej: Pago mes de Marzo") }
            )

            // Medio de Pago
            Text("Medio de Pago", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                paymentMethods.forEach { method ->
                    FilterChip(
                        selected = paymentMethod == method,
                        onClick = { paymentMethod = method },
                        label = { Text(method) }
                    )
                }
            }

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Observaciones Adicionales") },
                modifier = Modifier.fillMaxWidth().height(100.dp),
                maxLines = 3
            )

            if (uiState.error != null) {
                Text(uiState.error!!, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    viewModel.registerTransaction(
                        type = type,
                        concept = concept.ifEmpty { category },
                        category = category,
                        amount = amount.toDoubleOrNull() ?: 0.0,
                        paymentMethod = paymentMethod,
                        personName = personName,
                        notes = notes
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                enabled = amount.isNotEmpty() && !uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("GUARDAR REGISTRO")
                }
            }
        }
    }
}
