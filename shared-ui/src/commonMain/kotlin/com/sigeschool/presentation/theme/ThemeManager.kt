package com.sigeschool.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.sigeschool.data.local.entity.InstitutionThemeEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeManager {

    private val _currentTheme = MutableStateFlow<InstitutionThemeEntity?>(null)
    val currentTheme: StateFlow<InstitutionThemeEntity?> = _currentTheme

    fun updateTheme(theme: InstitutionThemeEntity) {
        _currentTheme.value = theme
    }

    fun getColorScheme(theme: InstitutionThemeEntity?, isSystemInDarkTheme: Boolean): ColorScheme {
        if (theme == null) {
            return if (isSystemInDarkTheme) darkColorScheme() else lightColorScheme()
        }

        val primary = Color(theme.primaryColor)
        val secondary = Color(theme.secondaryColor)
        val tertiary = Color(theme.accentColor)
        val background = Color(theme.backgroundColor)
        val onBackground = Color(theme.textColor)

        return if (theme.isDarkMode || isSystemInDarkTheme) {
            darkColorScheme(
                primary = primary,
                secondary = secondary,
                tertiary = tertiary,
                background = Color(0xFF121212),
                surface = Color(0xFF1E1E1E),
                onPrimary = Color.White,
                onSecondary = Color.White,
                onTertiary = Color.White,
                onBackground = Color.White,
                onSurface = Color.White
            )
        } else {
            lightColorScheme(
                primary = primary,
                secondary = secondary,
                tertiary = tertiary,
                background = background,
                surface = Color.White,
                onPrimary = Color.White,
                onSecondary = Color.White,
                onTertiary = Color.White,
                onBackground = onBackground,
                onSurface = onBackground
            )
        }
    }
}
