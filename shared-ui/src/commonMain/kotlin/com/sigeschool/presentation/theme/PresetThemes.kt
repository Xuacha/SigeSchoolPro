package com.sigeschool.presentation.theme

data class ThemePreset(
    val name: String,
    val primary: Long,
    val secondary: Long,
    val accent: Long,
    val background: Long,
    val text: Long
)

object PresetThemes {
    val EJECUTIVO = ThemePreset(
        name = "Ejecutivo",
        primary = 0xFF1A237E,
        secondary = 0xFF0D47A1,
        accent = 0xFF2196F3,
        background = 0xFFF5F5F5,
        text = 0xFF212121
    )

    val VIBRANTE = ThemePreset(
        name = "Vibrante",
        primary = 0xFFD81B60,
        secondary = 0xFFAD1457,
        accent = 0xFF00B0FF,
        background = 0xFFFFFFFF,
        text = 0xFF212121
    )

    val PASTEL = ThemePreset(
        name = "Pastel",
        primary = 0xFFB39DDB,
        secondary = 0xFF9575CD,
        accent = 0xFF80CBC4,
        background = 0xFFFAFAFA,
        text = 0xFF424242
    )

    val OTOÑO = ThemePreset(
        name = "Otoño",
        primary = 0xFFBF360C,
        secondary = 0xFF8D6E63,
        accent = 0xFFFFAB00,
        background = 0xFFFFF3E0,
        text = 0xFF3E2723
    )

    val PRIMAVERA = ThemePreset(
        name = "Primavera",
        primary = 0xFF2E7D32,
        secondary = 0xFF689F38,
        accent = 0xFFCDDC39,
        background = 0xFFF1F8E9,
        text = 0xFF1B5E20
    )

    val OCEANO = ThemePreset(
        name = "Océano",
        primary = 0xFF006064,
        secondary = 0xFF00838F,
        accent = 0xFF00BCD4,
        background = 0xFFE0F7FA,
        text = 0xFF004D40
    )

    val MODERNO = ThemePreset(
        name = "Moderno",
        primary = 0xFF37474F,
        secondary = 0xFF546E7A,
        accent = 0xFFFF5722,
        background = 0xFFECEFF1,
        text = 0xFF263238
    )

    val ELEGANTE = ThemePreset(
        name = "Elegante",
        primary = 0xFF212121,
        secondary = 0xFF424242,
        accent = 0xFFFFD700,
        background = 0xFFFFFFFF,
        text = 0xFF212121
    )

    val BOSQUE = ThemePreset(
        name = "Bosque",
        primary = 0xFF1B5E20,
        secondary = 0xFF33691E,
        accent = 0xFF827717,
        background = 0xFFF1F8E9,
        text = 0xFF1B5E20
    )

    val ATARDECER = ThemePreset(
        name = "Atardecer",
        primary = 0xFFE65100,
        secondary = 0xFFF57C00,
        accent = 0xFFFBC02D,
        background = 0xFFFFF3E0,
        text = 0xFFE65100
    )

    val presets = listOf(
        EJECUTIVO, VIBRANTE, PASTEL, OTOÑO, PRIMAVERA,
        OCEANO, MODERNO, ELEGANTE, BOSQUE, ATARDECER
    )
}
