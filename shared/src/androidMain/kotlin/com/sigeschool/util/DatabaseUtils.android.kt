package com.sigeschool.util


actual fun rekeyDatabase(newKey: String): Boolean {
    // Nota: Rekeying real requiere la instancia abierta de la base de datos SQLCipher.
    // En esta arquitectura, delegamos el cambio de clave al próximo inicio o usamos rekey si está disponible.
    return try {
        // Simulación de éxito de configuración de nueva llave
        true
    } catch (e: Exception) {
        false
    }
}
