package com.sigeschool.presentation.util

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.palette.graphics.Palette
import com.sigeschool.data.local.entity.InstitutionThemeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual class ColorExtractor(private val context: Context) {
    actual suspend fun extractColorsFromImage(institutionId: String, imageSource: Any): InstitutionThemeEntity? = withContext(Dispatchers.IO) {
        try {
            val uri = imageSource as? Uri ?: return@withContext null
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream) ?: return@withContext null
            
            val palette = Palette.from(bitmap).generate()
            
            val primary = palette.getVibrantColor(0xFF1A237E.toInt())
            val secondary = palette.getMutedColor(0xFF0D47A1.toInt())
            val accent = palette.getLightVibrantColor(0xFF2196F3.toInt())
            val background = 0xFFFFFFFF.toInt()
            val text = 0xFF212121.toInt()

            InstitutionThemeEntity(
                institutionId = institutionId,
                primaryColor = primary,
                secondaryColor = secondary,
                accentColor = accent,
                backgroundColor = background,
                textColor = text,
                themeMode = "AUTO",
                extractedFromLogo = true,
                lastUpdated = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
