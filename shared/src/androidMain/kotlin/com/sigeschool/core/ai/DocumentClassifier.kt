package com.sigeschool.core.ai

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

actual class DocumentClassifier(private val context: Context) {
    private var interpreter: Interpreter? = null

    init {
        try {
            interpreter = Interpreter(loadModelFile())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor = context.assets.openFd("document_classifier.tflite")
        val inputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            assetFileDescriptor.startOffset,
            assetFileDescriptor.declaredLength
        )
    }

    actual fun classify(text: String): String {
        if (interpreter == null) return "unknown"
        
        // Simulating the logic based on keywords as requested from prototype
        return when {
            text.contains("médico", ignoreCase = true) || text.contains("salud", ignoreCase = true) -> "medical_info"
            text.contains("nota", ignoreCase = true) || text.contains("calificación", ignoreCase = true) -> "academic_record"
            else -> "observation"
        }
    }
    
    actual fun close() {
        interpreter?.close()
    }
}
