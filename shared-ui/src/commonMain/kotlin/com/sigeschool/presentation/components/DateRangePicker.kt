package com.sigeschool.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun DateRangePicker(
    from: Long?,
    to: Long?,
    onFromChange: (Long?) -> Unit,
    onToChange: (Long?) -> Unit,
    modifier: Modifier = Modifier
)
