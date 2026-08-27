package com.sigeschool.presentation.screens.classes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Add
import com.sigeschool.domain.model.Class
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun ClassScreen(
    viewModel: ClassViewModel = koinViewModel(),
    onNavigateToDetail: (String) -> Unit
) {
    val classes by viewModel.classes.collectAsState()
    val teachers by viewModel.teachers.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    if (showAddDialog) {
        AddClassDialog(
            teachers = teachers,
            onDismiss = { showAddDialog = false },
            onConfirm = { name, level, teacherId ->
                viewModel.addClass(name, level, teacherId)
                showAddDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gestión de Clases") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(androidx.compose.material.icons.Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        if (classes.isEmpty()) {
            Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                    Text("No hay clases registradas", style = MaterialTheme.typography.bodyLarge)
                    Text("Pulsa + para crear una nueva", style = MaterialTheme.typography.bodySmall)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding).fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(classes) { clazz ->
                    ClassCard(clazz, onClick = { onNavigateToDetail(clazz.id) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassCard(clazz: Class, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = clazz.name, style = MaterialTheme.typography.titleMedium)
            Text(text = clazz.level, style = MaterialTheme.typography.bodySmall)
        }
    }
}
