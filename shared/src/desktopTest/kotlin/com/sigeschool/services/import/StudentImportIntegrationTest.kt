package com.sigeschool.services.import

import com.sigeschool.data.repository.StudentRepository
import com.sigeschool.domain.repository.AcudienteRepository
import com.sigeschool.domain.service.import.ImportService
import com.sigeschool.domain.model.ImportRequest
import com.sigeschool.domain.model.ImportType
import com.sigeschool.domain.usecase.import.BulkImportUseCase
import kotlinx.coroutines.test.runTest
import org.koin.test.KoinTest
import org.koin.test.inject
import com.sigeschool.di.initKoin
import org.koin.core.context.stopKoin
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import java.io.File

class StudentImportIntegrationTest : KoinTest {

    private val studentRepository: StudentRepository by inject()
    private val acudienteRepository: AcudienteRepository by inject()
    private val bulkImportUseCase: BulkImportUseCase by inject()
    private val importService: ImportService by inject()

    @BeforeTest
    fun setup() {
        val koinApp = initKoin(
            additionalModules = listOf(
                com.sigeschool.local.di.localModule,
                com.sigeschool.local.di.databaseModule()
            )
        )
        
        val repo = koinApp.koin.getOrNull<com.sigeschool.domain.repository.NotificationRepository>()
        val exportService = koinApp.koin.getOrNull<com.sigeschool.services.export.ExportService>()
        if (repo is com.sigeschool.data.repository.NotificationRepositoryImpl && exportService != null) {
            repo.setExportService(exportService)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testClearAndImportFromCsv() = runTest {
        // 1. Eliminar datos de prueba actuales
        importService.clearTestData()
        
        // 2. Localizar el archivo en resources
        val fileName = "import_estudiantes.csv"
        val csvFile = File("src/desktopTest/resources/$fileName")
        
        assertTrue(csvFile.exists(), "El archivo CSV debe existir en resources: ${csvFile.absolutePath}")
        
        val fileBytes = csvFile.readBytes()
        
        // 3. Ejecutar la importación
        val request = ImportRequest(
            fileContent = fileBytes,
            fileName = fileName,
            type = ImportType.ESTUDIANTES_ACUDIENTES
        )
        
        val result = bulkImportUseCase.execute(request)
        
        // 4. Validaciones
        assertTrue(result.total > 0, "Debería haber procesado al menos un registro")
        assertEquals(0, result.errors, "No debería haber errores en la importación. Detalle de errores: ${result.details.filter { it.action == com.sigeschool.domain.model.ImportAction.ERROR }}")
        assertEquals(2, result.created, "Deberían haberse creado 2 registros")
        
        println("Importación exitosa: ${result.created} registros creados, ${result.usersCreated} usuarios nuevos.")
    }
}
