package com.sigeschool.presentation.util

import com.sigeschool.data.local.entity.InstitutionThemeEntity

actual class ColorExtractor {
    actual suspend fun extractColorsFromImage(institutionId: String, imageSource: Any): InstitutionThemeEntity? {
        // Stub for desktop, return default or null
        return null
    }
}
