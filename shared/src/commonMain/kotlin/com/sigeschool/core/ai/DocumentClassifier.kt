package com.sigeschool.core.ai

expect class DocumentClassifier {
    fun classify(text: String): String
    fun close()
}
