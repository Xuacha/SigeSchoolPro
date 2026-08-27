package com.sigeschool.domain.service.import

import kotlinx.serialization.Serializable

@Serializable
sealed class AcademicData {
    @Serializable
    data class Nivel(val orden: Int, val nombre: String, val codigo: String, val descripcion: String? = null, val marcoNormativo: String? = null) : AcademicData()
    @Serializable
    data class Grado(val orden: Int, val nombre: String, val codigo: String, val descripcion: String? = null, val nivel: String) : AcademicData()
    @Serializable
    data class Area(val orden: Int, val nombre: String, val descripcion: String? = null, val programas: String? = null) : AcademicData()
    @Serializable
    data class Asignatura(
        val codigo: String,
        val nombre: String,
        val semestre: Int,
        val horasTeoricas: Int,
        val horasPracticas: Int,
        val horasTotales: Int,
        val nucleo: String,
        val gradoCodigo: String,
        val areaNombre: String
    ) : AcademicData()
}

data class AcademicStructure(
    val areas: List<AnalyzedArea> = emptyList(),
    val planEstudioName: String = "Nuevo Plan de Estudios",
    val description: String? = null
)

data class AnalyzedArea(
    val name: String,
    val description: String? = null,
    val subjects: List<AnalyzedSubject> = emptyList()
)

data class AnalyzedSubject(
    val name: String,
    val code: String? = null,
    val description: String? = null,
    val weeklyHours: Int = 0,
    val competencies: List<String> = emptyList()
)

data class ContentImportResult(
    val processed: Int,
    val errors: List<String>
)

data class AcademicImportResult(
    val totalRows: Int,
    val inserted: Int,
    val updated: Int,
    val errors: List<ImportError>,
    val warnings: List<ImportError>
)

data class ImportError(val row: Int, val message: String)

sealed class ImportProgress {
    object Idle : ImportProgress()
    data class Loading(val message: String, val percentage: Int) : ImportProgress()
    data class Success(val summary: String) : ImportProgress()
    data class Error(val message: String, val details: List<String> = emptyList()) : ImportProgress()
}
