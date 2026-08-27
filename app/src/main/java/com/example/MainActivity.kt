package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.IssueDetailModal
import com.example.ui.screens.CompatibilityScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.IssuesScreen
import com.example.ui.screens.ReportExportScreen
import com.example.ui.theme.*
import com.example.viewmodel.AuditViewModel

enum class NavigationTab {
    DASHBOARD, ISSUES, COMPATIBILITY, REPORT
}

class MainActivity : ComponentActivity() {

    private val viewModel: AuditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AuditAppMainContent(viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuditAppMainContent(viewModel: AuditViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedTab by remember { mutableStateOf(NavigationTab.DASHBOARD) }
    val context = LocalContext.current

    LaunchedEffect(state.userMessage) {
        state.userMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.dismissUserMessage()
        }
    }

    val scoreColor = when {
        state.report.healthScore >= 90 -> SuccessGreen
        state.report.healthScore >= 70 -> MediumYellow
        else -> HighOrange
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = CyberPrimary,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Shield,
                                    contentDescription = null,
                                    tint = CyberBackground,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Column {
                            Text(
                                text = "AuditPro",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Auditoría & Estabilidad Android",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    Surface(
                        color = scoreColor.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(scoreColor)
                            )
                            Text(
                                text = "Salud: ${state.report.healthScore}%",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = scoreColor
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                windowInsets = WindowInsets.navigationBars
            ) {
                NavigationBarItem(
                    selected = selectedTab == NavigationTab.DASHBOARD,
                    onClick = { selectedTab = NavigationTab.DASHBOARD },
                    icon = { Icon(Icons.Default.Dashboard, contentDescription = null) },
                    label = { Text("Inicio") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CyberBackground,
                        selectedTextColor = CyberPrimary,
                        indicatorColor = CyberPrimary
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == NavigationTab.ISSUES,
                    onClick = { selectedTab = NavigationTab.ISSUES },
                    icon = { Icon(Icons.Default.BugReport, contentDescription = null) },
                    label = { Text("Hallazgos") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CyberBackground,
                        selectedTextColor = CyberPrimary,
                        indicatorColor = CyberPrimary
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == NavigationTab.COMPATIBILITY,
                    onClick = { selectedTab = NavigationTab.COMPATIBILITY },
                    icon = { Icon(Icons.Default.PhonelinkSetup, contentDescription = null) },
                    label = { Text("Dispositivos") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CyberBackground,
                        selectedTextColor = CyberPrimary,
                        indicatorColor = CyberPrimary
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == NavigationTab.REPORT,
                    onClick = { selectedTab = NavigationTab.REPORT },
                    icon = { Icon(Icons.Default.Description, contentDescription = null) },
                    label = { Text("Informe") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CyberBackground,
                        selectedTextColor = CyberPrimary,
                        indicatorColor = CyberPrimary
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (selectedTab) {
                NavigationTab.DASHBOARD -> DashboardScreen(
                    state = state,
                    onUrlChange = viewModel::onUrlInputChanged,
                    onStartScan = { viewModel.startLiveAuditScan() },
                    onIssueClick = viewModel::selectIssue,
                    onNavigateToIssues = { selectedTab = NavigationTab.ISSUES },
                    onFixAll = viewModel::fixAllIssues,
                    onResetFixes = viewModel::resetAllFixes,
                    onToggleFix = viewModel::toggleFixIssue
                )
                NavigationTab.ISSUES -> IssuesScreen(
                    state = state,
                    onSearchChange = viewModel::onSearchQueryChanged,
                    onCategorySelect = viewModel::selectCategory,
                    onIssueClick = viewModel::selectIssue,
                    onToggleFix = viewModel::toggleFixIssue
                )
                NavigationTab.COMPATIBILITY -> CompatibilityScreen(
                    rules = state.compatRules
                )
                NavigationTab.REPORT -> ReportExportScreen(
                    state = state
                )
            }

            // Modal dialog when an issue is selected
            state.selectedIssue?.let { issue ->
                IssueDetailModal(
                    issue = issue,
                    onDismiss = { viewModel.selectIssue(null) },
                    onToggleFix = { viewModel.toggleFixIssue(issue.id) }
                )
            }
        }
    }
}
