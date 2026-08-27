package com.sigeschool.presentation.util

import com.sigeschool.data.local.entity.InstitutionThemeEntity

expect class ColorExtractor {
    suspend fun extractColorsFromImage(institutionId: String, imageSource: Any): InstitutionThemeEntity?
}
