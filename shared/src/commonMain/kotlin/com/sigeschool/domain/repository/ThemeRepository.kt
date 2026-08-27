package com.sigeschool.domain.repository

import com.sigeschool.domain.model.InstitutionTheme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getThemeByInstitutionId(institutionId: String): Flow<InstitutionTheme?>
    suspend fun saveTheme(theme: InstitutionTheme)
    suspend fun syncThemes(institutionId: String)
}
