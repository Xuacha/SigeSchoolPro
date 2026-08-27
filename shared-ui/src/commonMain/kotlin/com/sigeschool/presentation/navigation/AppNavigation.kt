package com.sigeschool.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.sigeschool.domain.model.UserRole
import com.sigeschool.presentation.screens.announcements.AnnouncementScreen
import com.sigeschool.presentation.screens.attendance.AttendanceScreen
import com.sigeschool.presentation.screens.attendance.EmployeeAttendanceScreen
import com.sigeschool.presentation.screens.auth.LoginScreen
import com.sigeschool.presentation.screens.auth.LoginViewModel
import com.sigeschool.presentation.screens.certificates.GenerateCertificateScreen
import com.sigeschool.presentation.screens.classes.ClassScreen
import com.sigeschool.presentation.screens.dashboard.DashboardScreen
import com.sigeschool.presentation.screens.employees.EmployeeScreen
import com.sigeschool.presentation.screens.exams.ExamScreen
import com.sigeschool.presentation.screens.grades.GradesScreen
import com.sigeschool.presentation.screens.laboral.LaboralScreen
import com.sigeschool.presentation.screens.reports.ReportsScreen
import com.sigeschool.presentation.screens.salaries.SalaryScreen
import com.sigeschool.presentation.screens.students.StudentDetailScreen
import com.sigeschool.presentation.screens.students.StudentListScreen
import com.sigeschool.presentation.screens.students.EnrollmentScreen
import com.sigeschool.presentation.screens.tasks.TaskScreen
import com.sigeschool.presentation.screens.billing.FastCollectionScreen
import com.sigeschool.presentation.screens.sie.AutoevaluacionScreen
import com.sigeschool.presentation.screens.sie.AutoevaluacionConfigScreen
import com.sigeschool.presentation.screens.sie.AutoevaluacionViewModel
import com.sigeschool.presentation.screens.sie.ConfiguracionPromocionViewModel
import com.sigeschool.presentation.screens.reports.AcademicReportScreen
import com.sigeschool.presentation.screens.admin.LogsMonitorScreen
import com.sigeschool.presentation.screens.admin.BackupSecurityScreen
import com.sigeschool.presentation.screens.parent.ParentDashboardScreen
import com.sigeschool.presentation.screens.parent.ParentSettingsScreen
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.SessionState
import com.sigeschool.util.isDesktop
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppNavigation() {
    val sessionManager: SessionManager = koinInject()
    val sessionState by sessionManager.sessionState.collectAsState()
    val loginViewModel: LoginViewModel = koinViewModel()

    when (sessionState) {
        is SessionState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
            return
        }
        is SessionState.LoggedOut -> {
            LoginScreen(
                onLoginSuccess = {
                    // Handled by SessionManager update in LoginViewModel
                }
            )
            return
        }
        is SessionState.LoggedIn -> {
            val user = (sessionState as SessionState.LoggedIn).user
            val role = UserRole.fromString(user.role)
            
            if (role == UserRole.PADRE_FAMILIA) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "parent_dashboard") {
                    composable("parent_dashboard") {
                        ParentDashboardScreen(
                            onNavigateToSettings = { navController.navigate("parent_settings") },
                            onNavigateToPayment = { studentId, studentName ->
                                navController.navigate("payment_portal/$studentId/$studentName")
                            },
                            onCheckConsent = { studentId, studentName ->
                                navController.navigate("consent_form/$studentId/$studentName")
                            }
                        )
                    }
                    composable("parent_settings") {
                        ParentSettingsScreen(onBack = { navController.popBackStack() })
                    }
                    composable(
                        "payment_portal/{studentId}/{studentName}",
                        arguments = listOf(
                            navArgument("studentId") { type = NavType.StringType },
                            navArgument("studentName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
                        val studentName = backStackEntry.arguments?.getString("studentName") ?: ""
                        com.sigeschool.presentation.screens.parent.PaymentPortalScreen(
                            studentId = studentId,
                            studentName = studentName,
                            onBack = { navController.popBackStack() },
                            onRedirectToPayment = { url ->
                                // En el portal real esto dispararía un evento al HOST
                                println("Redirigiendo a PayU: $url")
                            }
                        )
                    }
                    composable(
                        "consent_form/{studentId}/{studentName}",
                        arguments = listOf(
                            navArgument("studentId") { type = NavType.StringType },
                            navArgument("studentName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
                        val studentName = backStackEntry.arguments?.getString("studentName") ?: ""
                        com.sigeschool.presentation.screens.parent.ConsentScreen(
                            studentId = studentId,
                            studentName = studentName,
                            onCompleted = { navController.popBackStack() }
                        )
                    }
                }
                return
            }
        }
    }

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    AdaptiveNavigation(
        isDesktop = isDesktop(),
        navController = navController,
        drawerState = drawerState,
        scope = scope,
        onLogout = { loginViewModel.logout() }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Dashboard.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Dashboard.route) {
                DashboardScreen(
                    onOpenMenu = { scope.launch { drawerState.open() } },
                    onLogout = { loginViewModel.logout() },
                    onNavigateToAttendance = { navController.navigate(BottomNavItem.Attendance.route) },
                    onNavigateToGrades = { navController.navigate(BottomNavItem.Grades.route) },
                    onNavigateToStudents = { navController.navigate(BottomNavItem.Students.route) },
                    onNavigateToClasses = { navController.navigate(BottomNavItem.Classes.route) },
                    onNavigateToEmployees = { navController.navigate(BottomNavItem.Employees.route) },
                    onNavigateToSalaries = { navController.navigate(BottomNavItem.Salaries.route) },
                    onNavigateToTasks = { navController.navigate(BottomNavItem.Tasks.route) },
                    onNavigateToExams = { navController.navigate(BottomNavItem.Exams.route) },
                    onNavigateToAnnouncements = { navController.navigate(BottomNavItem.Announcements.route) },
                    onNavigateToChat = { navController.navigate(BottomNavItem.Chat.route) },
                    onNavigateToReports = { navController.navigate(BottomNavItem.Reports.route) },
                    onNavigateToCurricular = { navController.navigate(BottomNavItem.Curricular.route) },
                    onNavigateToCash = { navController.navigate(BottomNavItem.Cash.route) },
                    onNavigateToUsers = { /* Navegación a gestión de usuarios (Próxima versión) */ },
                    onNavigateToAutoevaluacion = { navController.navigate(BottomNavItem.Autoevaluacion.route) },
                    onNavigateToSieConfig = { navController.navigate(BottomNavItem.SieConfig.route) },
                    onNavigateToBackupSecurity = { navController.navigate(BottomNavItem.BackupSecurity.route) }
                )
            }
            composable(BottomNavItem.Students.route) {
                StudentListScreen(
                    onNavigateBack = { navController.navigate(BottomNavItem.Dashboard.route) },
                    onStudentClick = { id ->
                        navController.navigate("student_detail/$id")
                    },
                    onNavigateToImport = {
                        navController.navigate("enrollment")
                    }
                )
            }
            composable(
                route = "student_detail/{studentId}",
                arguments = listOf(navArgument("studentId") { type = NavType.StringType })
            ) { backStackEntry ->
                val studentId = backStackEntry.arguments?.getString("studentId") ?: ""
                StudentDetailScreen(
                    studentId = studentId,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToEdit = { /* navController.navigate("edit_student/$it") */ },
                    onNavigateToIdCard = { /* navController.navigate("id_card/$it") */ },
                    onNavigateToBehavior = { /* navController.navigate("behavior/$it") */ },
                    onNavigateToRecords = { /* navController.navigate("records/$it") */ },
                    onNavigateToGrades = { /* navController.navigate("grades/$it") */ }
                )
            }
            composable("enrollment") {
                EnrollmentScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToDetail = { id ->
                        navController.navigate("student_detail/$id") {
                            popUpTo("enrollment") { inclusive = true }
                        }
                    }
                )
            }

            composable(BottomNavItem.Attendance.route) {
                AttendanceScreen(
                    onNavigateToEmployeeAttendance = { navController.navigate("employee_attendance") }
                )
            }
            composable("employee_attendance") {
                EmployeeAttendanceScreen(onBack = { navController.popBackStack() })
            }
            composable(BottomNavItem.Grades.route) {
                GradesScreen()
            }
            composable(BottomNavItem.Classes.route) {
                ClassScreen(
                    onNavigateToDetail = { /* Navegación a detalle deshabilitada */ }
                )
            }
            composable(BottomNavItem.Reports.route) {
                ReportsScreen(
                    onGenerateCertificate = { navController.navigate("generate_certificate") },
                    onViewPuc = { navController.navigate("puc_accounts_internal") },
                    onViewAccounting = { navController.navigate("accounting_entries_internal") }
                )
            }
            composable(BottomNavItem.Curricular.route) {
                com.sigeschool.presentation.screens.curricular.CurricularScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable(BottomNavItem.Cash.route) {
                com.sigeschool.presentation.screens.cash.CashDashboardScreen(
                    onNavigateToRegister = { navController.navigate("cash_register") },
                    onNavigateToFastCollection = { navController.navigate(BottomNavItem.FastCollection.route) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(BottomNavItem.FastCollection.route) {
                FastCollectionScreen()
            }
            composable("cash_register") {
                com.sigeschool.presentation.screens.cash.CashRegisterScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable("accounting_entries_internal") {
                val authViewModel: LoginViewModel = koinViewModel()
                val authState by authViewModel.uiState.collectAsState()
                val institutionId = authState.institutionId ?: "demo_inst"

                LaunchedEffect(institutionId) {
                    if (institutionId != "demo_inst") {
                        navController.navigate("accounting_entries/$institutionId") {
                            popUpTo("accounting_entries_internal") { inclusive = true }
                        }
                    }
                }

                Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            composable("puc_accounts_internal") {
                val authViewModel: LoginViewModel = koinViewModel()
                val authState by authViewModel.uiState.collectAsState()
                val institutionId = authState.institutionId ?: "demo_inst"

                LaunchedEffect(institutionId) {
                    if (institutionId != "demo_inst") {
                        navController.navigate("puc_accounts/$institutionId") {
                            popUpTo("puc_accounts_internal") { inclusive = true }
                        }
                    }
                }

                Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            composable("generate_certificate") {
                GenerateCertificateScreen(onBack = { navController.popBackStack() })
            }
            composable(BottomNavItem.Employees.route) {
                EmployeeScreen()
            }
            composable(BottomNavItem.Salaries.route) {
                SalaryScreen()
            }
            composable(BottomNavItem.Laboral.route) {
                LaboralScreen()
            }
            composable(BottomNavItem.Tasks.route) {
                TaskScreen()
            }
            composable("student_tasks") {
                com.sigeschool.presentation.screens.tasks.StudentTaskScreen()
            }
            composable(BottomNavItem.Exams.route) {
                ExamScreen()
            }
            composable(BottomNavItem.Announcements.route) {
                AnnouncementScreen()
            }
            composable(BottomNavItem.Chat.route) {
                com.sigeschool.presentation.screens.chat.ChatScreen()
            }
            composable(BottomNavItem.LogsMonitor.route) {
                val sessionManager: SessionManager = koinInject()
                val sessionState by sessionManager.sessionState.collectAsState()
                
                if (sessionState is SessionState.LoggedIn) {
                    val user = (sessionState as SessionState.LoggedIn).user
                    val role = UserRole.fromString(user.role)
                    if (role == UserRole.RECTOR || role == UserRole.REPRESENTANTE_LEGAL || role == UserRole.COORDINADOR_ACADEMICO) {
                        LogsMonitorScreen()
                    } else {
                        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                            Text("No tiene permisos para acceder a esta sección")
                        }
                    }
                }
            }
            composable("parent_dashboard") {
                ParentDashboardScreen(
                    onNavigateToSettings = { navController.navigate("parent_settings") },
                    onNavigateToPayment = { studentId, institutionId ->
                         navController.navigate("billing/$institutionId") // Simplificado o ajustar según necesidad
                    },
                    onCheckConsent = { studentId, institutionId ->
                         // Navegar a pantalla de consentimiento
                    }
                )
            }
            composable("parent_settings") {
                ParentSettingsScreen(onBack = { navController.popBackStack() })
            }
            composable(BottomNavItem.Autoevaluacion.route) {
                AutoevaluacionScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable(BottomNavItem.SieConfig.route) {
                AutoevaluacionConfigScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable(BottomNavItem.BackupSecurity.route) {
                BackupSecurityScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Students,
        BottomNavItem.Attendance,
        BottomNavItem.Grades,
        BottomNavItem.Curricular,
        BottomNavItem.Cash
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    onLogout: () -> Unit
) {
    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))
        NavigationDrawerItem(
            label = { Text("Inicio") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.Dashboard.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.Home, null) }
        )
        NavigationDrawerItem(
            label = { Text("Empleados") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.Employees.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.People, null) }
        )
        NavigationDrawerItem(
            label = { Text("Gestión Curricular") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.Curricular.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, null) }
        )
        NavigationDrawerItem(
            label = { Text("Caja") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.Cash.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.AccountBalanceWallet, null) }
        )
        NavigationDrawerItem(
            label = { Text("Salarios") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.Salaries.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.Payments, null) }
        )
        NavigationDrawerItem(
            label = { Text("Reportes") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.Reports.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.Description, null) }
        )
        NavigationDrawerItem(
            label = { Text("Monitoreo Logs") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.LogsMonitor.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.MonitorHeart, null) }
        )
        NavigationDrawerItem(
            label = { Text("Configuración SIEE") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.SieConfig.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.Settings, null) }
        )
        NavigationDrawerItem(
            label = { Text("Seguridad y Backup") },
            selected = false,
            onClick = {
                navController.navigate(BottomNavItem.BackupSecurity.route)
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.Security, null) }
        )
        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        NavigationDrawerItem(
            label = { Text("Cerrar Sesión") },
            selected = false,
            onClick = {
                onLogout()
                scope.launch { drawerState.close() }
            },
            icon = { Icon(Icons.Default.ExitToApp, null) }
        )
    }
}
