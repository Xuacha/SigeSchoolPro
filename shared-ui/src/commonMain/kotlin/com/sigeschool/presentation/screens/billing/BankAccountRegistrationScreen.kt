package com.sigeschool.presentation.screens.billing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.billing.AccountType
import com.sigeschool.domain.model.billing.ColombianBanks
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankAccountRegistrationScreen(
    onBack: () -> Unit,
    viewModel: BankAccountViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    var bankName by remember { mutableStateOf("") }
    var accountType by remember { mutableStateOf(AccountType.AHORROS) }
    var accountNumber by remember { mutableStateOf("") }
    var holderName by remember { mutableStateOf("") }
    var holderDni by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var expandedBanks by remember { mutableStateOf(false) }
    var expandedType by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is BankAccountUiState.Success) {
            val account = (uiState as BankAccountUiState.Success).account
            if (account != null) {
                bankName = account.bankName
                accountType = account.accountType
                accountNumber = account.accountNumber
                holderName = account.holderName
                holderDni = account.holderDni
                email = account.notificationEmail ?: ""
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Cuenta Bancaria") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Configure la cuenta donde recibirá los recaudos de pensiones y otros servicios.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Banco
            ExposedDropdownMenuBox(
                expanded = expandedBanks,
                onExpandedChange = { expandedBanks = !expandedBanks }
            ) {
                OutlinedTextField(
                    value = bankName,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Banco") },
                    leadingIcon = { Icon(Icons.Default.AccountBalance, null) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedBanks) },
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expandedBanks,
                    onDismissRequest = { expandedBanks = false }
                ) {
                    ColombianBanks.entities.forEach { bank ->
                        DropdownMenuItem(
                            text = { Text(bank) },
                            onClick = {
                                bankName = bank
                                expandedBanks = false
                            }
                        )
                    }
                }
            }

            // Tipo de Cuenta
            ExposedDropdownMenuBox(
                expanded = expandedType,
                onExpandedChange = { expandedType = !expandedType }
            ) {
                OutlinedTextField(
                    value = accountType.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de Cuenta") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedType) },
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expandedType,
                    onDismissRequest = { expandedType = false }
                ) {
                    AccountType.values().forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type.name) },
                            onClick = {
                                accountType = type
                                expandedType = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = accountNumber,
                onValueChange = { accountNumber = it },
                label = { Text("Número de Cuenta") },
                leadingIcon = { Icon(Icons.Default.Numbers, null) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = holderName,
                onValueChange = { holderName = it },
                label = { Text("Nombre del Titular (Certificado)") },
                leadingIcon = { Icon(Icons.Default.Person, null) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = holderDni,
                onValueChange = { holderDni = it },
                label = { Text("NIT o Cédula del Titular") },
                leadingIcon = { Icon(Icons.Default.Numbers, null) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo para Notificaciones") },
                leadingIcon = { Icon(Icons.Default.Email, null) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (uiState is BankAccountUiState.Error) {
                Text(
                    text = (uiState as BankAccountUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                onClick = {
                    viewModel.saveOrUpdateAccount(
                        bankName, accountType, accountNumber, holderName, holderDni, email
                    )
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = uiState !is BankAccountUiState.Loading && bankName.isNotEmpty() && accountNumber.isNotEmpty()
            ) {
                if (uiState is BankAccountUiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Registrar Cuenta Oficial")
                }
            }
        }
    }
}
