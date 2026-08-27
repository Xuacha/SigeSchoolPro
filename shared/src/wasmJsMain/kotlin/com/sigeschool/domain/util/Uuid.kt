package com.sigeschool.domain.util

import kotlin.js.JsAny
import kotlin.js.toJsString

// External function to call browser's crypto.randomUUID()
private fun cryptoRandomUUID(): String = js("crypto.randomUUID().toString()")

actual fun randomUUID(): String {
    return try {
        cryptoRandomUUID()
    } catch (e: Exception) {
        // Fallback for environments without crypto.randomUUID
        (1..4).joinToString("-") { 
            (1..8).map { "0123456789abcdef".random() }.joinToString("") 
        }
    }
}
