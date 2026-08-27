package com.sigeschool.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope

@Composable
fun AdaptiveNavigation(
    isDesktop: Boolean,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    onLogout: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    if (isDesktop) {
        Row(modifier = Modifier.fillMaxSize()) {
            NavigationRail(
                containerColor = MaterialTheme.colorScheme.surface,
                header = {
                    Text(
                        "SigeSchool",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    BottomNavItem.Dashboard,
                    BottomNavItem.Students,
                    BottomNavItem.Tasks,
                    BottomNavItem.Exams,
                    BottomNavItem.Attendance,
                    BottomNavItem.Classes,
                    BottomNavItem.Employees,
                    BottomNavItem.Salaries
                )

                items.forEach { item ->
                    NavigationRailItem(
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
                
                Spacer(Modifier.weight(1f))
                
                NavigationRailItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout") },
                    label = { Text("Salir") },
                    selected = false,
                    onClick = onLogout
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                content(PaddingValues(0.dp))
            }
        }
    } else {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(navController, drawerState, scope, onLogout = onLogout)
            }
        ) {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController)
                }
            ) { paddingValues ->
                content(paddingValues)
            }
        }
    }
}
