package com.sigeschool.presentation.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sigeschool.data.local.entity.InstitutionThemeEntity
import com.sigeschool.presentation.theme.PresetThemes
import com.sigeschool.presentation.theme.ThemePreset
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSettingsScreen(
    viewModel: ThemeSettingsViewModel = koinViewModel()
) {
    val theme by viewModel.currentTheme.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Personalización de Identidad") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Vista Previa", style = MaterialTheme.typography.titleMedium)
            ThemePreviewCard(theme)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DarkMode, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Modo Oscuro Institucional")
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = theme?.isDarkMode ?: false,
                    onCheckedChange = { viewModel.toggleDarkMode(it) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Paletas Predefinidas", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(PresetThemes.presets) { preset ->
                    PresetItem(preset) { viewModel.onPresetSelected(preset) }
                }
            }
        }
    }
}

@Composable
fun ThemePreviewCard(theme: InstitutionThemeEntity?) {
    val primary = theme?.primaryColor?.let { Color(it) } ?: MaterialTheme.colorScheme.primary
    val secondary = theme?.secondaryColor?.let { Color(it) } ?: MaterialTheme.colorScheme.secondary
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(theme?.backgroundColor ?: 0xFFFFFFFF.toInt()))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("SigeSchool Pro", color = Color(theme?.textColor ?: 0xFF212121.toInt()), style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = primary)) {
                    Text("Primario")
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = {}, border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(secondary))) {
                    Text("Secundario", color = secondary)
                }
            }
        }
    }
}

@Composable
fun PresetItem(preset: ThemePreset, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.height(40.dp)) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color(preset.primary)))
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color(preset.secondary)))
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color(preset.accent)))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(preset.name, style = MaterialTheme.typography.labelMedium)
        }
    }
}
