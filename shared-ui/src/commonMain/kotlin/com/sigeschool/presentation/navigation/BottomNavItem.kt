package com.sigeschool.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Dashboard : BottomNavItem("dashboard", "Inicio", Icons.Default.Home)
    data object Students : BottomNavItem("students", "Alumnos", Icons.Default.Person)
    data object Attendance : BottomNavItem("attendance", "Asistencia", Icons.Default.CheckCircle)
    data object Grades : BottomNavItem("grades", "Calificaciones", Icons.Default.Star)
    data object Classes : BottomNavItem("classes", "Clases", Icons.Default.School)
    data object Employees : BottomNavItem("employees", "Empleados", Icons.Default.People)
    data object Salaries : BottomNavItem("salaries", "Salarios", Icons.Default.Payments)
    data object Laboral : BottomNavItem("laboral", "Laboral", Icons.Default.Work)
    data object Tasks : BottomNavItem("tasks", "Tareas", Icons.AutoMirrored.Filled.Assignment)
    data object Exams : BottomNavItem("exams", "Exámenes", Icons.Default.Quiz)
    data object Announcements : BottomNavItem("announcements", "Anuncios", Icons.AutoMirrored.Filled.Announcement)
    data object Chat : BottomNavItem("chat", "Chat", Icons.AutoMirrored.Filled.Chat)
    data object Reports : BottomNavItem("reports", "Reportes", Icons.Default.Description)
    data object Curricular : BottomNavItem("curricular", "Gestión Curricular", Icons.AutoMirrored.Filled.MenuBook)
    data object Cash : BottomNavItem("cash", "Caja", Icons.Default.AccountBalanceWallet)
    data object FastCollection : BottomNavItem("fast_collection", "Cobro Rápido", Icons.Default.PointOfSale)
    data object LogsMonitor : BottomNavItem("logs_monitor", "Monitoreo", Icons.Default.MonitorHeart)
    data object Autoevaluacion : BottomNavItem("estudiante/autoevaluacion", "Autoevaluación", Icons.Default.Grade)
    data object SieConfig : BottomNavItem("admin/sie-config", "Configuración SIEE", Icons.Default.Settings)
    data object BackupSecurity : BottomNavItem("admin/backup-security", "Seguridad y Backup", Icons.Default.Security)
}
