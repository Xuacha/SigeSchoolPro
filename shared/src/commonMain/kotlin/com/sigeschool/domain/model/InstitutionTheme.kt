package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class InstitutionTheme(
    val institutionId: String,
    val primaryColor: Int,
    val secondaryColor: Int,
    val accentColor: Int,
    val backgroundColor: Int,
    val textColor: Int,
    val isDarkMode: Boolean = false,
    val themeMode: String = "AUTO",
    val presetName: String? = null,
    val extractedFromLogo: Boolean = false,
    val lastUpdated: Long = 0
)
