package com.sigeschool.presentation.screens.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun AcademicConfigScreen(
    onBack: () -> Unit,
    viewModel: AcademicConfigViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración Académica") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    ConfigCategoryItem(
                        title = "Sedes Institucionales",
                        icon = Icons.Default.Business,
                        subtitle = "${uiState.sedes.size} sedes configuradas",
                        onClick = { /* Navegar a detalle sedes */ }
                    )
                }
                item {
                    ConfigCategoryItem(
                        title = "Jornadas",
                        icon = Icons.Default.Schedule,
                        subtitle = "Mañana, Tarde, Noche",
                        onClick = { /* Navegar a detalle jornadas */ }
                    )
                }
                item {
                    ConfigCategoryItem(
                        title = "Grados y Cursos",
                        icon = Icons.Default.Class,
                        subtitle = "Estructura de niveles educativos",
                        onClick = { /* Navegar a detalle cursos */ }
                    )
                }
            }
        }
    }
}

@Composable
fun ConfigCategoryItem(
    title: String,
    icon: ImageVector,
    subtitle: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
        leadingContent = { Icon(icon, null, tint = MaterialTheme.colorScheme.primary) },
        trailingContent = { Icon(Icons.Default.ChevronRight, null) },
        modifier = Modifier.clickable { onClick() }
    )
    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
}
