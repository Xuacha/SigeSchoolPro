package com.sigeschool.presentation.screens.billing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.billing.FeeCategory
import com.sigeschool.domain.model.billing.PaymentMethod
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FastCollectionScreen(
    viewModel: FastCollectionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Row(modifier = Modifier.fillMaxSize()) {
        // Columna Izquierda: Búsqueda y Selección
        Column(modifier = Modifier.weight(1f).padding(16.dp)) {
            Text("Cobro Rápido", style = MaterialTheme.typography.headlineMedium)
            
            Spacer(modifier = Modifier.height(16.dp))

            if (state.selectedStudent == null) {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.onSearchQueryChanged(it) },
                    label = { Text("Buscar Estudiante (Nombre o DNI)") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                )

                LazyColumn {
                    items(state.searchResults) { student ->
                        ListItem(
                            headlineContent = { Text(student.nombreCompleto) },
                            supportingContent = { Text("Grado: ${student.grado} - DNI: ${student.dni}") },
                            modifier = Modifier.fillMaxWidth(),
                            trailingContent = {
                                IconButton(onClick = { viewModel.selectStudent(student) }) {
                                    Icon(Icons.Default.Add, contentDescription = "Seleccionar")
                                }
                            }
                        )
                    }
                }
            } else {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(state.selectedStudent!!.nombreCompleto, style = MaterialTheme.typography.titleLarge)
                            Text("Grado: ${state.selectedStudent!!.grado}")
                        }
                        IconButton(onClick = { viewModel.clearStudent() }) {
                            Icon(Icons.Default.Close, contentDescription = "Cambiar Estudiante")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text("Categorías de Pago", style = MaterialTheme.typography.titleMedium)
                LazyColumn {
                    items(state.availableCategories) { category ->
                        ListItem(
                            headlineContent = { Text(category.name) },
                            supportingContent = { Text("$ ${category.basePrice}") },
                            trailingContent = {
                                Button(onClick = { viewModel.addItemToCart(category) }) {
                                    Text("Agregar")
                                }
                            }
                        )
                    }
                }
            }
        }

        // Columna Derecha: Carrito y Pago
        Surface(
            modifier = Modifier.width(400.dp).fillMaxHeight(),
            tonalElevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Resumen de Cobro", style = MaterialTheme.typography.titleLarge)
                
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.cartItems) { item ->
                        ListItem(
                            headlineContent = { Text(item.description) },
                            supportingContent = { Text("$ ${item.total}") },
                            trailingContent = {
                                IconButton(onClick = { viewModel.removeItemFromCart(item.id) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                                }
                            }
                        )
                    }
                }

                HorizontalDivider()
                
                Column(modifier = Modifier.padding(vertical = 16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("TOTAL", style = MaterialTheme.typography.headlineSmall)
                        Text("$ ${state.total}", style = MaterialTheme.typography.headlineSmall)
                    }
                }

                Text("Método de Pago")
                Row {
                    PaymentMethod.values().forEach { method ->
                        FilterChip(
                            selected = state.selectedPaymentMethod == method,
                            onClick = { viewModel.setPaymentMethod(method) },
                            label = { Text(method.name) },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

                if (state.selectedPaymentMethod == PaymentMethod.EFECTIVO) {
                    OutlinedTextField(
                        value = state.amountReceived.toString(),
                        onValueChange = { viewModel.setAmountReceived(it.toDoubleOrNull() ?: 0.0) },
                        label = { Text("Monto Recibido") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text("Cambio: $ ${state.change}", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.processPayment() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.selectedStudent != null && state.cartItems.isNotEmpty() && !state.isProcessing
                ) {
                    if (state.isProcessing) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text("PROCESAR PAGO")
                    }
                }
            }
        }
    }

    // Dialogs for errors/success
    if (state.error != null) {
        AlertDialog(
            onDismissRequest = { viewModel.clearMessages() },
            title = { Text("Error") },
            text = { Text(state.error!!) },
            confirmButton = { TextButton(onClick = { viewModel.clearMessages() }) { Text("OK") } }
        )
    }

    if (state.successMessage != null) {
        AlertDialog(
            onDismissRequest = { viewModel.clearMessages() },
            title = { Text("Éxito") },
            text = { Text(state.successMessage!!) },
            confirmButton = { TextButton(onClick = { viewModel.clearMessages() }) { Text("OK") } }
        )
    }
}
