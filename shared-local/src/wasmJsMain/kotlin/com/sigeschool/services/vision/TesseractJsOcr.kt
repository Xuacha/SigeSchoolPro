package com.sigeschool.services.vision

import kotlinx.coroutines.await
import kotlin.js.Promise

@JsModule("tesseract.js")
@JsName("default")
external object Tesseract : JsAny {
    fun recognize(image: JsAny, lang: String): Promise<JsAny>
}

class TesseractJsOcr : OcrService {
    override suspend fun recognizeText(imageBytes: ByteArray): Result<String> {
        return try {
            val promise: Promise<JsAny> = Tesseract.recognize(imageBytes.toJsArray(), "spa")
            val result: JsAny = promise.await()
            val text = getResultText(result)
            Result.success(text)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private fun getResultText(result: JsAny): String = js("result.data.text")

private fun ByteArray.toJsArray(): JsAny {
    val array = Int8Array(this.size)
    for (i in 0 until this.size) {
        array[i] = this[i]
    }
    return array
}

@JsName("Int8Array")
external class Int8Array(length: Int) : JsAny {
    operator fun set(index: Int, value: Byte)
    val length: Int
}
