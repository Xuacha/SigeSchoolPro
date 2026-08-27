package com.sigeschool.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun DateRangePicker(
    from: Long?,
    to: Long?,
    onFromChange: (Long?) -> Unit,
    onToChange: (Long?) -> Unit,
    modifier: Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    Row(modifier = modifier) {
        OutlinedButton(onClick = { showDialog = true }) {
            Text(from?.let { "Desde: ${formatDate(it)}" } ?: "Desde")
        }
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(onClick = { showDialog = true }) {
            Text(to?.let { "Hasta: ${formatDate(it)}" } ?: "Hasta")
        }
    }

    if (showDialog) {
        val state = rememberDateRangePickerState(
            initialSelectedStartDateMillis = from,
            initialSelectedEndDateMillis = to
        )

        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    onFromChange(state.selectedStartDateMillis)
                    onToChange(state.selectedEndDateMillis)
                    showDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            androidx.compose.material3.DateRangePicker(
                state = state,
                title = { Text("Seleccionar Rango", modifier = Modifier.padding(16.dp)) },
                showModeToggle = false,
                modifier = Modifier.size(width = 500.dp, height = 600.dp)
            )
        }
    }
}

private fun formatDate(millis: Long): String {
    val instant = Instant.fromEpochMilliseconds(millis)
    val date = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${date.dayOfMonth}/${date.monthNumber}/${date.year}"
}
