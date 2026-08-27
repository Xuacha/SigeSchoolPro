package com.sigeschool.domain.model

object QualitativeToGrade {

    private val positiveWords = listOf(
        "excelente", "sobresaliente", "muy bien", "bueno", "destacado", "participa", "cumple", "avanzado"
    )

    private val negativeWords = listOf(
        "necesita", "mejorar", "bajo", "insuficiente", "débil", "problema", "falta"
    )

    fun evaluateObservation(observation: String): Double {
        val text = observation.lowercase()
        var score = 10.0  // Nota base neutral

        // Palabras positivas suben la nota
        positiveWords.forEach { word ->
            if (text.contains(word)) score += 2.5
        }

        // Palabras negativas bajan la nota
        negativeWords.forEach { word ->
            if (text.contains(word)) score -= 2.5
        }

        // Ajustes adicionales
        if (text.contains("excelente") || text.contains("sobresaliente")) score += 3.0
        if (text.contains("regular") || text.contains("aceptable")) score += 0.0
        if (text.contains("mal") || text.contains("deficiente")) score -= 5.0

        // Limitar entre 0 y 20
        return score.coerceIn(0.0, 20.0)
    }
}
