package com.sigeschool.util

/**
 * Utilidad multiplataforma para manejar enlaces de WhatsApp.
 * La implementación real de "abrir" el intent se manejará mediante expect/actual
 * o delegando a la capa de UI que tiene acceso al contexto/plataforma.
 */
object WhatsAppUtils {

    /**
     * Genera la URL de WhatsApp wa.me
     * @param phoneNumber Número en formato internacional (ej: 51987654321) sin el '+'
     * @param message Mensaje opcional a pre-cargar
     */
    fun createWhatsAppUrl(phoneNumber: String, message: String = ""): String {
        val cleanNumber = phoneNumber.replace("+", "").replace(" ", "").replace("-", "")
        return if (message.isNotEmpty()) {
            "https://wa.me/$cleanNumber?text=${message.encodeURL()}"
        } else {
            "https://wa.me/$cleanNumber"
        }
    }

    // Extensión simple para codificar URL en commonMain sin depender de java.net.URLEncoder
    private fun String.encodeURL(): String {
        return this.replace(" ", "%20")
            .replace("\n", "%0A")
            // Añadir más reemplazos si es necesario o usar una librería de Ktor si estuviera disponible
    }
}
