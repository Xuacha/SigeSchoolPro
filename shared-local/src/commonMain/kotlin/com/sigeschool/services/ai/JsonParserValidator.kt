package com.sigeschool.services.ai

import kotlinx.serialization.json.Json

/**
 * Capa de validación y parseo para asegurar la integridad de las respuestas de IA.
 */
class JsonParserValidator(val json: Json) {

    inline fun <reified T> parseAndValidate(jsonString: String, crossinline validator: (T) -> Boolean): AiResult<T> {
        return try {
            val decoded = json.decodeFromString<T>(jsonString)
            if (validator(decoded)) {
                AiResult.Success(decoded)
            } else {
                AiResult.FatalError("La respuesta no cumple con las reglas de negocio de validación.")
            }
        } catch (e: Exception) {
            AiResult.FatalError("Error de deserialización JSON: ${e.message}", e)
        }
    }
}
