package com.sigeschool.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
import com.sigeschool.presentation.theme.ThemeManager
import org.koin.compose.koinInject

// ==================== PALETA DE COLORES ====================

private val Primary = Color(0xFF1E88E5)      // Azul institucional
private val PrimaryVariant = Color(0xFF1565C0)
private val Secondary = Color(0xFF26A69A)    // Verde turquesa
private val Tertiary = Color(0xFF8E24AA)

private val LightBackground = Color(0xFFF8F9FA)
private val LightSurface = Color.White
private val DarkBackground = Color(0xFF121212)
private val DarkSurface = Color(0xFF1E1E1E)

private val Success = Color(0xFF4CAF50)
private val Warning = Color(0xFFFFC107)
private val Error = Color(0xFFEF5350)

// Light Theme
private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD1E4FF),
    onPrimaryContainer = Color(0xFF001D36),

    secondary = Secondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF97F1E0),
    onSecondaryContainer = Color(0xFF00201C),

    tertiary = Tertiary,
    onTertiary = Color.White,

    background = LightBackground,
    onBackground = Color(0xFF1A1C1E),

    surface = LightSurface,
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFDFE2EB),
    onSurfaceVariant = Color(0xFF43474E),

    error = Error,
    onError = Color.White,

    outline = Color(0xFF73777F)
)

// Dark Theme
private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF003258),
    onPrimaryContainer = Color(0xFFD1E4FF),

    secondary = Secondary,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF004D46),
    onSecondaryContainer = Color(0xFF97F1E0),

    tertiary = Tertiary,
    onTertiary = Color.White,

    background = DarkBackground,
    onBackground = Color(0xFFE2E2E6),

    surface = DarkSurface,
    onSurface = Color(0xFFE2E2E6),
    surfaceVariant = Color(0xFF43474E),
    onSurfaceVariant = Color(0xFFC3C7CF),

    error = Error,
    onError = Color.Black,

    outline = Color(0xFF8D9199)
)

// ==================== TIPOGRAFÍA ====================

val SigeSchoolTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)

// ==================== SHAPES ====================

val SigeSchoolShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

// ==================== TEMA PRINCIPAL ====================

@Composable
fun SigeSchoolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeManager: ThemeManager = koinInject(),
    content: @Composable () -> Unit
) {
    val dynamicTheme by themeManager.currentTheme.collectAsState()
    val colorScheme = themeManager.getColorScheme(dynamicTheme, darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SigeSchoolTypography,
        shapes = SigeSchoolShapes,
        content = content
    )
}
