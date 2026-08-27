package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class EducationalLevel(val displayName: String) {
    // Educación Formal
    INICIAL("Educación Inicial"),
    PREESCOLAR("Educación Preescolar"),
    BASICA_PRIMARIA("Básica Primaria"),
    BASICA_SECUNDARIA("Básica Secundaria"),
    MEDIA("Educación Media"),
    SUPERIOR_PREGRADO("Educación Superior - Pregrado"),
    SUPERIOR_POSGRADO("Educación Superior - Posgrado"),
    
    // Formación Continua
    ETDH("Educación para el Trabajo (ETDH)"),
    CONTINUA("Educación Continua"),
    INFORMAL("Educación Informal")
}

object EducationalGrades {
    val gradesMap = mapOf(
        EducationalLevel.INICIAL to listOf("Sala Cuna", "Caminadores", "Párvulos", "Pre-Jardín", "Jardín"),
        EducationalLevel.PREESCOLAR to listOf("Transición"),
        EducationalLevel.BASICA_PRIMARIA to listOf("1°", "2°", "3°", "4°", "5°"),
        EducationalLevel.BASICA_SECUNDARIA to listOf("6°", "7°", "8°", "9°"),
        EducationalLevel.MEDIA to listOf("10°", "11°"),
        EducationalLevel.SUPERIOR_PREGRADO to listOf("Técnico Profesional", "Tecnólogo", "Normal Superior", "Profesional Universitario"),
        EducationalLevel.SUPERIOR_POSGRADO to listOf("Especialización", "Maestría", "Doctorado"),
        EducationalLevel.ETDH to listOf("Técnico Laboral", "Conocimientos Académicos"),
        EducationalLevel.CONTINUA to listOf("Diplomado", "Curso de Profundización", "Curso de Actualización", "Seminario", "Evento Académico"),
        EducationalLevel.INFORMAL to listOf("Curso Libre", "Taller")
    )

    fun getGradesForLevels(levels: List<String>): List<String> {
        return levels.mapNotNull { levelStr ->
            val level = try { EducationalLevel.valueOf(levelStr) } catch (e: Exception) { null }
            level?.let { gradesMap[it] }
        }.flatten().distinct()
    }
}
