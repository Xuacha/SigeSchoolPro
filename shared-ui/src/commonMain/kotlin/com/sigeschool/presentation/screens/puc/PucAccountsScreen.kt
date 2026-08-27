package com.sigeschool.presentation.screens.puc

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun PucAccountsScreen(
    institutionId: String,
    onBack: () -> Unit,
    onNavigateToEntries: () -> Unit,
    viewModel: PucViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateFlowOf("") }

    LaunchedEffect(institutionId) {
        // La carga se realiza en el ViewModel
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Plan Único de Cuentas (PUC)") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToEntries) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Asientos")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = { Text("Buscar cuenta o código...") },
                leadingIcon = { Icon(Icons.Default.Search, null) }
            )

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (uiState.accounts.isEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("Catálogo vacío. Se generará al iniciar.", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    val filteredAccounts = uiState.accounts.filter {
                        it.code.contains(searchQuery) || it.name.contains(searchQuery, ignoreCase = true)
                    }.sortedBy { it.code }

                    items(
                        items = filteredAccounts,
                        key = { it.code } // Optimización para LazyColumn
                    ) { account ->
                        PucAccountItem(account)
                    }
                }
            }
        }
    }
}

@Composable
fun PucAccountItem(account: com.sigeschool.domain.model.PucAccount) {
    val level = when (account.code.length) {
        1 -> 0
        2 -> 1
        4 -> 2
        6 -> 3
        else -> 4
    }

    ListItem(
        modifier = Modifier.padding(start = (level * 16).dp),
        headlineContent = { Text(account.name) },
        supportingContent = { Text(account.code) },
        trailingContent = {
            Text(
                text = account.accountType.name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
    HorizontalDivider(modifier = Modifier.padding(start = (level * 16).dp))
}

private fun <T> mutableStateFlowOf(value: T) = mutableStateOf(value)
