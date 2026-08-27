package com.sigeschool.presentation.screens.puc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import com.sigeschool.domain.model.EntryDetail
import com.sigeschool.util.FilePicker
import com.sigeschool.util.SharePdfFile

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun AccountingEntryScreen(
    institutionId: String,
    onBack: () -> Unit,
    viewModel: PucViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var showFilePicker by remember { mutableStateOf(false) }

    FilePicker(
        show = showFilePicker,
        onFileSelected = { rows ->
            viewModel.importEntries(rows)
        },
        onDismiss = { showFilePicker = false }
    )

    if (uiState.pdfReport != null) {
        SharePdfFile(uiState.pdfReport!!.first, uiState.pdfReport!!.second)
        LaunchedEffect(uiState.pdfReport) {
            viewModel.clearPdfReport()
        }
    }

    LaunchedEffect(institutionId) {
        viewModel.loadAccounts(institutionId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asientos Contables") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = { showFilePicker = true }) {
                        Icon(Icons.Default.FileUpload, contentDescription = "Importar Asientos")
                    }
                    IconButton(onClick = { viewModel.generateFinancialStatement("BALANCE", "SigeSchool Pro") }) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Balance")
                    }
                    IconButton(onClick = { viewModel.generateFinancialStatement("RESULTADOS", "SigeSchool Pro") }) {
                        Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = "P&G")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo Asiento")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (uiState.error != null) {
                Text(
                    text = uiState.error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (uiState.entries.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("No hay registros", style = MaterialTheme.typography.bodyLarge)
                        Text("Balance: $0.00", color = MaterialTheme.colorScheme.secondary)
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.entries) { entry ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(entry.date, style = MaterialTheme.typography.labelMedium)
                                    Text("Total: $${entry.totalDebit}", style = MaterialTheme.typography.titleMedium)
                                }
                                Spacer(Modifier.height(4.dp))
                                Text(entry.description, style = MaterialTheme.typography.bodyLarge)
                                
                                Spacer(Modifier.height(8.dp))
                                entry.entries.forEach { detail ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(detail.accountName, modifier = Modifier.weight(1f))
                                        if (detail.debit > 0) Text("D: ${detail.debit}", color = MaterialTheme.colorScheme.primary)
                                        if (detail.credit > 0) Text("C: ${detail.credit}", color = MaterialTheme.colorScheme.secondary)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddEntryDialog(
            accounts = uiState.accounts,
            onDismiss = { showAddDialog = false },
            onConfirm = { desc, date, details ->
                viewModel.saveEntry(desc, details, date)
                showAddDialog = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEntryDialog(
    accounts: List<com.sigeschool.domain.model.PucAccount>,
    onDismiss: () -> Unit,
    onConfirm: (String, String, List<EntryDetail>) -> Unit
) {
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(kotlinx.datetime.Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()).date.toString()) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedAccount by remember { mutableStateOf<com.sigeschool.domain.model.PucAccount?>(null) }
    var amount by remember { mutableStateOf("") }
    var isDebit by remember { mutableStateOf(true) }
    val entryDetails = remember { mutableStateListOf<EntryDetail>() }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Asiento Contable") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción del Asiento") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = date,
                    onValueChange = { },
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Seleccionar Fecha")
                        }
                    }
                )

                if (showDatePicker) {
                    val datePickerState = rememberDatePickerState()
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let {
                                    val instant = kotlinx.datetime.Instant.fromEpochMilliseconds(it)
                                    date = instant.toLocalDateTime(kotlinx.datetime.TimeZone.UTC).date.toString()
                                }
                                showDatePicker = false
                            }) { Text("Aceptar") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDatePicker = false }) { Text("Cancelar") }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

                HorizontalDivider()
                Text("Agregar Detalle", style = MaterialTheme.typography.labelLarge)

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedAccount?.name ?: "Seleccionar Cuenta",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Cuenta PUC") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        accounts.filter { it.level >= 3 }.forEach { account ->
                            DropdownMenuItem(
                                text = { Text("${account.code} - ${account.name}") },
                                onClick = {
                                    selectedAccount = account
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("Monto") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = isDebit, onClick = { isDebit = true })
                            Text("Débito")
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(selected = !isDebit, onClick = { isDebit = false })
                            Text("Crédito")
                        }
                    }
                }

                Button(
                    onClick = {
                        val valAmount = amount.toDoubleOrNull() ?: 0.0
                        if (selectedAccount != null && valAmount > 0) {
                            entryDetails.add(
                                EntryDetail(
                                    accountCode = selectedAccount!!.code,
                                    accountName = selectedAccount!!.name,
                                    debit = if (isDebit) valAmount else 0.0,
                                    credit = if (!isDebit) valAmount else 0.0
                                )
                            )
                            selectedAccount = null
                            amount = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Añadir Movimiento")
                }

                HorizontalDivider()
                Text("Detalles Agregados:", style = MaterialTheme.typography.labelMedium)
                entryDetails.forEach { detail ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(detail.accountName, modifier = Modifier.weight(1f), maxLines = 1)
                        Text(if (detail.debit > 0) "D: ${detail.debit}" else "C: ${detail.credit}")
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(description, date, entryDetails.toList()) },
                enabled = description.isNotBlank() && entryDetails.isNotEmpty()
            ) {
                Text("Guardar Asiento")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
