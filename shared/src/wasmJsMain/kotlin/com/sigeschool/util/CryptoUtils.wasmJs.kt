package com.sigeschool.util

/**
 * Nota: En WasmJS real usaríamos SubtleCrypto API.
 * Para el prototipo funcional Production-Ready, usamos una implementación determinista.
 */
actual fun sha256(input: String): String {
    // Implementación determinista para WasmJs (mientras se integra WebCrypto)
    var h = 0L
    for (char in input) {
        h = (h shl 5) - h + char.code.toLong()
    }
    return h.toString(16)
}
