package com.sigeschool.services.vision

import org.bytedeco.javacpp.BytePointer
import org.bytedeco.javacpp.Loader
import org.bytedeco.tesseract.TessBaseAPI
import org.bytedeco.leptonica.global.leptonica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

/**
 * Implementación de OCR para Desktop con gestión estricta de memoria nativa y
 * limitación de concurrencia para evitar saturación de recursos.
 */
class TesseractDesktopOcr : OcrService {
    private val api = TessBaseAPI()
    private val resourceManager = NativeResourceManager()
    
    // Hallazgo 1: Limitar concurrencia a nivel de hardware para evitar picos de memoria nativa
    private val concurrencySemaphore = Semaphore(3) 

    init {
        Loader.load(leptonica::class.java)
        
        val datapath = File("tessdata").absolutePath
        if (api.Init(datapath, "spa") != 0) {
            throw RuntimeException("CRÍTICO: Fallo al inicializar Tesseract en $datapath")
        }
    }

    override suspend fun recognizeText(imageBytes: ByteArray): Result<String> = withContext(Dispatchers.IO) {
        // Hallazgo 1: Control de concurrencia para procesos Batch
        concurrencySemaphore.withPermit {
            try {
                val pix = leptonica.pixReadMem(imageBytes, imageBytes.size.toLong())
                if (pix == null || pix.isNull) {
                    return@withContext Result.failure(Exception("Error decodificando imagen con Leptonica"))
                }

                // Usamos el NativeResourceManager para rastrear el PIX
                resourceManager.use(pix, "PIX_Image") { managedPix ->
                    api.SetImage(managedPix)
                    
                    val outTextPointer = api.GetUTF8Text()
                    if (outTextPointer == null || outTextPointer.isNull) {
                        return@use Result.failure(Exception("Error extrayendo texto"))
                    }

                    // Rastrear el puntero del texto devuelto
                    resourceManager.use(outTextPointer, "Tesseract_Result_Text") { textPtr ->
                        val result = textPtr.string ?: ""
                        Result.success(result)
                    }
                }
            } catch (e: Exception) {
                Result.failure(e)
            } finally {
                api.Clear() // Limpia el estado interno de Tesseract para la siguiente imagen
            }
        }
    }

    /**
     * Cierre definitivo de la instancia y liberación de recursos globales.
     */
    fun close() {
        api.End()
        resourceManager.close()
    }
}
