package com.sigeschool.presentation.screens.curricular

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.DocumentType
import com.sigeschool.domain.model.InstitutionalDocument
import com.sigeschool.domain.model.DocumentBlock
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun CurricularScreen(
    viewModel: CurricularViewModel = koinViewModel(),
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    var showUploadDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión Curricular") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            if (viewModel.canUploadDocument()) {
                FloatingActionButton(onClick = { showUploadDialog = true }) {
                    Icon(Icons.Default.FileUpload, contentDescription = "Importar Documento")
                }
            }
        }
    ) { padding ->
        if (showUploadDialog) {
            UploadDocumentDialog(
                onDismiss = { showUploadDialog = false },
                onConfirm = { title, content ->
                    viewModel.processDocumentContent(title, content)
                    showUploadDialog = false
                }
            )
        }
        // ... resto del contenido ...
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            ScrollableTabRow(selectedTabIndex = uiState.selectedType.ordinal) {
                DocumentType.entries.forEach { type ->
                    Tab(
                        selected = uiState.selectedType == type,
                        onClick = { viewModel.selectType(type) },
                        text = { Text(type.name) }
                    )
                }
            }

            if (uiState.selectedDocument == null) {
                DocumentList(uiState.documents) { viewModel.selectDocument(it) }
            } else {
                DocumentDetail(
                    document = uiState.selectedDocument!!,
                    blocks = uiState.blocks,
                    canEdit = when(uiState.selectedType) {
                        DocumentType.PEI -> uiState.userRole.canManagePEI()
                        DocumentType.PLAN_ESTUDIOS -> uiState.userRole.canManagePlanEstudios()
                        DocumentType.PLAN_AULA -> uiState.userRole.canManagePlanAula()
                    },
                    onUpdateBlock = { viewModel.updateBlock(it) }
                )
            }
        }
    }
}

@Composable
fun DocumentList(documents: List<InstitutionalDocument>, onSelect: (InstitutionalDocument) -> Unit) {
    LazyColumn {
        items(documents) { doc ->
            ListItem(
                headlineContent = { Text(doc.title) },
                supportingContent = { Text("Actualizado: ${doc.updatedAt}") },
                trailingContent = { Icon(Icons.Default.ChevronRight, null) },
                modifier = Modifier.clickable { onSelect(doc) }
            )
        }
    }
}

@Composable
fun DocumentDetail(
    document: InstitutionalDocument,
    blocks: List<DocumentBlock>,
    canEdit: Boolean,
    onUpdateBlock: (DocumentBlock) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text(document.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
        }
        items(blocks) { block ->
            BlockCard(block, canEdit, onUpdateBlock)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun BlockCard(block: DocumentBlock, canEdit: Boolean, onUpdate: (DocumentBlock) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var content by remember { mutableStateOf(block.contentHtml) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(block.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                if (canEdit) {
                    IconButton(onClick = { 
                        if (isEditing) onUpdate(block.copy(contentHtml = content))
                        isEditing = !isEditing 
                    }) {
                        Icon(if (isEditing) Icons.Default.Save else Icons.Default.Edit, null)
                    }
                }
            }
            if (isEditing) {
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp)
                )
            } else {
                Text(block.contentHtml) // Aquí se usaría un Render HTML
            }
        }
    }
}

@Composable
fun UploadDocumentDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Importar Documento (IA)") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Pegue aquí el contenido extraído del PDF o Word para que la IA lo clasifique y organice.")
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título del Documento") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Contenido del Texto") },
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(title, content) },
                enabled = title.isNotBlank() && content.isNotBlank()
            ) { Text("Procesar con IA") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
