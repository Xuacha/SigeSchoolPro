package com.sigeschool.presentation.components

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
actual fun DateRangePicker(
    from: Long?,
    to: Long?,
    onFromChange: (Long?) -> Unit,
    onToChange: (Long?) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    
    Row(modifier = modifier) {
        OutlinedButton(
            onClick = {
                val calendar = Calendar.getInstance()
                from?.let { calendar.timeInMillis = it }
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val selected = Calendar.getInstance().apply {
                            set(year, month, day, 0, 0, 0)
                        }.timeInMillis
                        onFromChange(selected)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        ) {
            Text(from?.let { "Desde: ${formatDate(it)}" } ?: "Desde")
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        OutlinedButton(
            onClick = {
                val calendar = Calendar.getInstance()
                to?.let { calendar.timeInMillis = it }
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val selected = Calendar.getInstance().apply {
                            set(year, month, day, 23, 59, 59)
                        }.timeInMillis
                        onToChange(selected)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        ) {
            Text(to?.let { "Hasta: ${formatDate(it)}" } ?: "Hasta")
        }
    }
}

private fun formatDate(millis: Long): String {
    val calendar = Calendar.getInstance().apply { timeInMillis = millis }
    return "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
}
