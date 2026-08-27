package com.sigeschool.util

import kotlin.js.js
import kotlin.random.Random

actual fun generateId(): String {
    return js("crypto.randomUUID()") as String
}

actual fun generateUUID(): String {
    return try {
        js("crypto.randomUUID()") as String
    } catch (e: Exception) {
        // Fallback si crypto.randomUUID no está disponible (Safari < 15.4)
        generateFallbackUUID()
    }
}

private fun generateFallbackUUID(): String {
    val random = Random.Default
    return buildString {
        repeat(32) {
            append(String.format("%x", random.nextInt(16)))
            when (it) {
                7, 11, 15, 19 -> append("-")
            }
        }
    }
}
