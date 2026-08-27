package com.sigeschool.core.ai

actual class DocumentClassifier {
    actual fun classify(text: String): String = "observation"
    actual fun close() {}
}
