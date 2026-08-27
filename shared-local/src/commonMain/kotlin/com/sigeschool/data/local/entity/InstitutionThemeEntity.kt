package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "institution_themes")
data class InstitutionThemeEntity(
    @PrimaryKey val institutionId: String,
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

fun InstitutionThemeEntity.toDomain() = com.sigeschool.domain.model.InstitutionTheme(
    institutionId = institutionId,
    primaryColor = primaryColor,
    secondaryColor = secondaryColor,
    accentColor = accentColor,
    backgroundColor = backgroundColor,
    textColor = textColor,
    isDarkMode = isDarkMode,
    themeMode = themeMode,
    presetName = presetName,
    extractedFromLogo = extractedFromLogo,
    lastUpdated = lastUpdated
)

fun com.sigeschool.domain.model.InstitutionTheme.toEntity() = InstitutionThemeEntity(
    institutionId = institutionId,
    primaryColor = primaryColor,
    secondaryColor = secondaryColor,
    accentColor = accentColor,
    backgroundColor = backgroundColor,
    textColor = textColor,
    isDarkMode = isDarkMode,
    themeMode = themeMode,
    presetName = presetName,
    extractedFromLogo = extractedFromLogo,
    lastUpdated = lastUpdated
)
