package com.sigeschool

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sigeschool.presentation.screens.splash.SplashScreen
import com.sigeschool.core.theme.SigeSchoolTheme
import com.sigeschool.presentation.navigation.AppNavigation

@Composable
fun App() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen(onSplashFinished = { showSplash = false })
    } else {
        AppNavigation()
    }
}

@Composable
fun MainContent() {
    SigeSchoolTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome to SigeSchool Pro",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
