package com.sigeschool.presentation.screens.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigeschool.data.local.entity.InstitutionThemeEntity
import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.repository.ThemeRepository
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.presentation.theme.ThemeManager
import com.sigeschool.presentation.theme.ThemePreset
import com.sigeschool.presentation.util.ColorExtractor
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ThemeSettingsViewModel(
    private val themeRepository: ThemeRepository,
    private val themeManager: ThemeManager,
    private val sessionManager: SessionManager,
    private val auditRepository: AuditRepository,
    private val colorExtractor: ColorExtractor
) : ViewModel() {

    private val institutionId = sessionManager.getCurrentInstitutionId() ?: "DEFAULT"

    val currentTheme = themeRepository.getThemeByInstitutionId(institutionId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        viewModelScope.launch {
            currentTheme.collect { theme ->
                theme?.let { themeManager.updateTheme(it) }
            }
        }
    }

    fun onPresetSelected(preset: ThemePreset) {
        val newTheme = InstitutionThemeEntity(
            institutionId = institutionId,
            primaryColor = preset.primary.toInt(),
            secondaryColor = preset.secondary.toInt(),
            accentColor = preset.accent.toInt(),
            backgroundColor = preset.background.toInt(),
            textColor = preset.text.toInt(),
            themeMode = "PRESET",
            presetName = preset.name,
            lastUpdated = 0
        )
        saveTheme(newTheme)
    }

    fun onManualColorChange(primary: Int, secondary: Int) {
        val current = currentTheme.value ?: getDefaultTheme()
        val newTheme = current.copy(
            primaryColor = primary,
            secondaryColor = secondary,
            themeMode = "MANUAL",
            presetName = null,
            extractedFromLogo = false,
            lastUpdated = 0
        )
        saveTheme(newTheme)
    }

    fun extractFromLogo(imageSource: Any) {
        viewModelScope.launch {
            val extracted = colorExtractor.extractColorsFromImage(institutionId, imageSource)
            extracted?.let { saveTheme(it) }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        val current = currentTheme.value ?: getDefaultTheme()
        val newTheme = current.copy(
            isDarkMode = enabled,
            lastUpdated = 0
        )
        saveTheme(newTheme)
    }

    private fun saveTheme(theme: InstitutionThemeEntity) {
        viewModelScope.launch {
            themeRepository.saveTheme(theme)
            themeManager.updateTheme(theme)
            auditRepository.log("THEME_CHANGED", "InstitutionTheme", mapOf("mode" to theme.themeMode))
        }
    }

    private fun getDefaultTheme() = InstitutionThemeEntity(
        institutionId = institutionId,
        primaryColor = 0xFF1A237E.toInt(),
        secondaryColor = 0xFF0D47A1.toInt(),
        accentColor = 0xFF2196F3.toInt(),
        backgroundColor = 0xFFFFFFFF.toInt(),
        textColor = 0xFF212121.toInt(),
        lastUpdated = 0
    )
}
