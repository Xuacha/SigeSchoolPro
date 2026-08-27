package com.sigeschool.presentation.screens.idcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Print
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sigeschool.domain.model.IdCard
import com.sigeschool.domain.model.UserRole
import com.sigeschool.presentation.components.BarcodeImage

import kotlinx.datetime.Clock
import com.sigeschool.util.SharePdfFile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdCardScreen(
    viewModel: IdCardViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val pdfData = uiState.pdfData
    if (pdfData != null) {
        SharePdfFile(pdfData, "Carnets_${Clock.System.now().toEpochMilliseconds()}.pdf")
        viewModel.clearPdfData()
    }
    var showFilters by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadAllCards()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Generación de Carnets") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = { showFilters = !showFilters }) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filtros")
                    }
                    IconButton(onClick = { viewModel.generatePdf() }) {
                        Icon(Icons.Default.Print, contentDescription = "Imprimir Todos")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (showFilters) {
                FiltersSection(
                    selectedRole = uiState.selectedRole,
                    onRoleSelected = { viewModel.onRoleSelected(it) },
                    selectedGrade = uiState.selectedGrade,
                    grades = uiState.grades,
                    onGradeSelected = { viewModel.onGradeSelected(it) }
                )
            }

            if (uiState.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.filteredCards) { card ->
                        IdCardItem(card)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FiltersSection(
    selectedRole: UserRole?,
    onRoleSelected: (UserRole?) -> Unit,
    selectedGrade: String?,
    grades: List<String>,
    onGradeSelected: (String?) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Filtrar por Rol", style = MaterialTheme.typography.titleSmall)
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedRole == null,
                    onClick = { onRoleSelected(null) },
                    label = { Text("Todos") }
                )
                UserRole.entries.filter { it.level > 0 }.forEach { role ->
                    FilterChip(
                        selected = selectedRole == role,
                        onClick = { onRoleSelected(role) },
                        label = { Text(role.name) }
                    )
                }
            }

            if (selectedRole == UserRole.ESTUDIANTE || selectedRole == null) {
                Spacer(Modifier.height(8.dp))
                Text("Filtrar por Grado", style = MaterialTheme.typography.titleSmall)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedGrade == null,
                        onClick = { onGradeSelected(null) },
                        label = { Text("Todos") }
                    )
                    grades.forEach { grade ->
                        FilterChip(
                            selected = selectedGrade == grade,
                            onClick = { onGradeSelected(grade) },
                            label = { Text(grade) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IdCardItem(card: IdCard) {
    val navyBlue = Color(0xFF1A2A44)
    val goldColor = Color(0xFFD4AF37)

    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Left Side - Photo and Profile (Deep Navy)
            Column(
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight()
                    .background(navyBlue)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier.size(90.dp).padding(2.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, goldColor)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (card.photoUrl != null) {
                            // Placeholder for AsyncImage (not available in commonMain easily without Coil)
                            Text(card.ownerName.take(1), fontSize = 40.sp, color = navyBlue)
                        } else {
                            Text(card.ownerName.take(1), fontSize = 40.sp, color = Color.Gray)
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    card.ownerRole.uppercase(),
                    color = goldColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    card.grade ?: "",
                    color = Color.White,
                    fontSize = 10.sp
                )
            }

            // Right Side - Branding and Barcode
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            card.institutionName.uppercase(),
                            color = navyBlue,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.weight(1f)
                        )
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(goldColor, RoundedCornerShape(4.dp))
                        )
                    }
                    Divider(color = goldColor, thickness = 1.dp, modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        card.ownerName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = navyBlue
                    )
                    Text(
                        "DNI: ${card.identifier}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.DarkGray
                    )
                }

                // Barcode Section
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BarcodeImage(
                        text = card.identifier,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(45.dp)
                    )
                    Text(
                        card.identifier,
                        fontSize = 9.sp,
                        color = navyBlue,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 2.sp
                    )
                }
            }
        }
    }
}
