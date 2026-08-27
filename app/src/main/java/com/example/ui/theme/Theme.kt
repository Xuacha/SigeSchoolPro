package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val AuditDarkColorScheme = darkColorScheme(
    primary = CyberPrimary,
    onPrimary = CyberBackground,
    secondary = CyberSecondary,
    onSecondary = OnCyberBackground,
    tertiary = SuccessGreen,
    background = CyberBackground,
    onBackground = OnCyberBackground,
    surface = CyberSurface,
    onSurface = OnCyberBackground,
    surfaceVariant = CyberSurfaceVariant,
    onSurfaceVariant = OnCyberSurface,
    error = CriticalRed,
    onError = CyberBackground,
    outline = CyberBorder
)

private val AuditLightColorScheme = lightColorScheme(
    primary = Color(0xFF006875),
    onPrimary = Color.White,
    secondary = Color(0xFF62489C),
    onSecondary = Color.White,
    tertiary = Color(0xFF006C38),
    background = Color(0xFFF8F9FA),
    onBackground = Color(0xFF191C1E),
    surface = Color.White,
    onSurface = Color(0xFF191C1E),
    surfaceVariant = Color(0xFFE0E3E5),
    onSurfaceVariant = Color(0xFF40484C),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    outline = Color(0xFF70787D)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) AuditDarkColorScheme else AuditDarkColorScheme // Default to dark cyber theme for security tool look
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
