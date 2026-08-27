package com.sigeschool.presentation.screens.sie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoevaluacionScreen(
    viewModel: AutoevaluacionViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Autoevaluación Académica") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        // Icono de volver (usualmente Icons.Default.ArrowBack)
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
        ) {
            Text(
                text = "Según el Decreto 1290, tu autoevaluación es parte integral de tu proceso formativo. Por favor, califica tu desempeño en cada asignatura.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.autoevaluaciones) { item ->
                        AutoevaluacionItem(
                            item = item,
                            onScoreChanged = { newScore ->
                                viewModel.updateScore(item.subjectId, newScore)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.saveAll() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isSaving && !uiState.isSaved
                ) {
                    if (uiState.isSaving) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text(if (uiState.isSaved) "Evaluación Guardada" else "Confirmar y Guardar")
                    }
                }

                if (uiState.error != null) {
                    Text(
                        text = uiState.error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AutoevaluacionItem(
    item: AutoevaluacionItemState,
    onScoreChanged: (Double) -> Unit
) {
    var scoreText by remember(item.score) { mutableStateOf(item.score.toString()) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.subjectName, style = MaterialTheme.typography.titleMedium)
                Text(text = item.teacherName, style = MaterialTheme.typography.bodySmall)
            }

            OutlinedTextField(
                value = scoreText,
                onValueChange = {
                    scoreText = it
                    it.toDoubleOrNull()?.let { score ->
                        if (score in 0.0..5.0) onScoreChanged(score)
                    }
                },
                label = { Text("Nota") },
                modifier = Modifier.width(80.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )
        }
    }
}
