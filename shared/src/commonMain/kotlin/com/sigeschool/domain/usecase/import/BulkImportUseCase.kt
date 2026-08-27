package com.sigeschool.domain.usecase.import

import com.sigeschool.domain.model.*
import com.sigeschool.domain.repository.*
import com.sigeschool.domain.service.*
import com.sigeschool.domain.service.notification.TemplateEngine
import kotlinx.datetime.Clock
import kotlin.random.Random

class BulkImportUseCase(
    private val studentRepository: StudentRepository,
    private val acudienteRepository: AcudienteRepository,
    private val employeeRepository: EmployeeRepository,
    private val fileParserService: FileParserService,
    private val passwordGenerator: PasswordGenerator,
    private val templateEngine: TemplateEngine,
    private val notificationRepository: NotificationRepository,
    private val consentRepository: ConsentRepository
) {
    suspend fun execute(request: ImportRequest, institutionId: String): ImportResult {
        val records = fileParserService.parseFile(request.fileContent, request.fileName)
        val details = mutableListOf<ImportDetail>()
        var usersCreated = 0
        var notificationsSent = 0
        
        val now = Clock.System.now().toEpochMilliseconds()
        val batchId = "BATCH_$now"
        val fileHash = com.sigeschool.util.sha256(request.fileContent.decodeToString())

        for (index in records.indices) {
            val record = records[index] ?: continue
            try {
                when (request.type) {
                    ImportType.ESTUDIANTES_ACUDIENTES -> {
                        val docEst = record["documento_estudiante"] ?: ""
                        val newStudent = Student(
                            nombre = record["nombre_estudiante"] ?: "N/A",
                            dni = docEst,
                            institutionId = institutionId
                        )
                        studentRepository.saveStudent(newStudent)
                        details.add(ImportDetail(index + 1, docEst, ImportAction.CREATED, "Éxito"))
                    }
                    ImportType.DOCENTES -> {
                        val doc = record["dni"] ?: ""
                        val employee = Employee(id = "", institutionId = institutionId, firstName = "N/A", dni = doc)
                        employeeRepository.addEmployee(employee)
                        details.add(ImportDetail(index + 1, doc, ImportAction.CREATED, "Éxito"))
                    }
                    else -> {
                         details.add(ImportDetail(index + 1, "N/A", ImportAction.SKIPPED_DUPLICATE, "Tipo no soportado"))
                    }
                }
            } catch (e: Exception) {
                details.add(ImportDetail(index + 1, "N/A", ImportAction.ERROR, e.message ?: "Error"))
            }
        }

        return ImportResult(
            id = "IMP_$now",
            batchId = batchId,
            fileHash = fileHash,
            timestamp = now,
            total = records.size,
            created = details.count { it.action == ImportAction.CREATED },
            updated = 0,
            errors = details.count { it.action == ImportAction.ERROR },
            duplicates = 0,
            usersCreated = usersCreated,
            notificationsSent = notificationsSent,
            details = details
        )
    }
}
