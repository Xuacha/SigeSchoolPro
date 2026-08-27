package com.sigeschool.domain.service.import

import com.sigeschool.domain.model.Acudiente
import com.sigeschool.domain.repository.AcudienteRepository
import com.sigeschool.domain.repository.ImportRepository

class ImportService(
    private val acudienteRepository: AcudienteRepository,
    private val studentRepository: com.sigeschool.domain.repository.StudentRepository,
    private val importRepository: ImportRepository,
    private val fileProcessor: FileProcessor
) {
    suspend fun clearTestData(institutionId: String) {
        acudienteRepository.clearAllAcudientes()
        studentRepository.clearAllStudents(institutionId)
    }

    suspend fun importAcudientesFromCsv(csvContent: String): Result<Int> {
        return try {
            val rows = fileProcessor.parseCsv(csvContent)
            var count = 0
            rows.forEach { row ->
                val acudiente = Acudiente(
                    idAcudiente = row["id"] ?: "",
                    nombreCompleto = row["nombre"] ?: "",
                    tipoDocumento = row["tipoDoc"] ?: "",
                    numeroDocumento = row["documento"] ?: "",
                    correoElectronico = row["email"] ?: "",
                    telefono = row["telefono"] ?: "",
                    whatsapp = row["whatsapp"],
                    parentesco = row["parentesco"] ?: "OTRO"
                )
                if (acudiente.idAcudiente.isNotEmpty()) {
                    acudienteRepository.saveAcudiente(acudiente)
                    count++
                }
            }
            Result.success(count)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
