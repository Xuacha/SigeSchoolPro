package com.sigeschool.services.vision

import android.graphics.BitmapFactory
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Implementación de producción para reconocimiento de texto en Android usando ML Kit.
 */
class AndroidOcrService : OcrService {
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    override suspend fun recognizeText(imageBytes: ByteArray): Result<String> = withContext(Dispatchers.IO) {
        try {
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                ?: return@withContext Result.failure(Exception("No se pudo decodificar la imagen"))
            
            val image = InputImage.fromBitmap(bitmap, 0)
            val result = recognizer.process(image).await()
            
            if (result.text.isBlank()) {
                Result.failure(Exception("No se detectó texto en la imagen"))
            } else {
                Result.success(result.text)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
