package com.sigeschool.domain.service.import

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class AcademicImporterService(
    private val parser: DocumentParser
) {
    private val _progress = MutableStateFlow<ImportProgress>(ImportProgress.Idle)
    val progress: StateFlow<ImportProgress> = _progress

    suspend fun importCatalog(byteArray: ByteArray, fileName: String, institutionId: String, replaceAll: Boolean) = withContext(Dispatchers.Default) {
        try {
            _progress.value = ImportProgress.Loading("Analizando archivo...", 10)
            
            val academicData = if (fileName.lowercase().endsWith(".docx")) {
                parser.parseWord(byteArray)
            } else {
                parser.parseExcel(byteArray)
            }

            _progress.value = ImportProgress.Loading("Procesando datos...", 40)
            
            val errors = mutableListOf<String>()
            val summary = processAndInsert(academicData, institutionId, replaceAll, errors)
            
            if (errors.isNotEmpty()) {
                _progress.value = ImportProgress.Error("Importación completada con algunos errores", errors)
            } else {
                _progress.value = ImportProgress.Success(summary)
            }

        } catch (e: Exception) {
            _progress.value = ImportProgress.Error("Error crítico: ${e.message}")
        }
    }

    private suspend fun processAndInsert(
        data: List<AcademicData>,
        institutionId: String,
        replaceAll: Boolean,
        errors: MutableList<String>
    ): String {
        var nivelesCount = 0
        var gradosCount = 0
        var areasCount = 0
        var asignaturasCount = 0

        // In a real KMP app, we would use repositories here.
        // For now, we simulate the processing based on the provided logic.
        
        data.filterIsInstance<AcademicData.Nivel>().forEach { item ->
            nivelesCount++
        }

        data.filterIsInstance<AcademicData.Grado>().forEach { item ->
            gradosCount++
        }

        data.filterIsInstance<AcademicData.Area>().forEach { item ->
            areasCount++
        }

        data.filterIsInstance<AcademicData.Asignatura>().forEach { item ->
            asignaturasCount++
        }

        return "Resumen: Niveles: $nivelesCount, Grados: $gradosCount, Áreas: $areasCount, Asignaturas: $asignaturasCount"
    }
}
