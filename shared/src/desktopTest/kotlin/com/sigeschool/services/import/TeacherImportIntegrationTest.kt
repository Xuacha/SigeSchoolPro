package com.sigeschool.services.import

import com.sigeschool.data.repository.EmployeeRepository
import com.sigeschool.di.initKoin
import com.sigeschool.domain.model.ImportRequest
import com.sigeschool.domain.model.ImportType
import com.sigeschool.domain.usecase.import.BulkImportUseCase
import com.sigeschool.services.export.ExportService
import com.sigeschool.data.repository.NotificationRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import java.io.File
import kotlin.test.*

class TeacherImportIntegrationTest : KoinTest {

    private val bulkImportUseCase: BulkImportUseCase by inject()
    private val employeeRepository: EmployeeRepository by inject()

    @BeforeTest
    fun setup() {
        val koinApp = initKoin(
            additionalModules = listOf(
                com.sigeschool.local.di.localModule,
                com.sigeschool.local.di.databaseModule()
            )
        )
        
        // Manual wiring for DI circular dependency
        val repo = koinApp.koin.getOrNull<com.sigeschool.domain.repository.NotificationRepository>()
        val exportService = koinApp.koin.getOrNull<ExportService>()
        if (repo is NotificationRepositoryImpl && exportService != null) {
            repo.setExportService(exportService)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testImportTeachersFromCsv() = runTest {
        // 1. Localizar el archivo en resources
        val fileName = "import_docentes.csv"
        val csvFile = File("src/desktopTest/resources/$fileName")
        
        assertTrue(csvFile.exists(), "El archivo CSV debe existir: ${csvFile.absolutePath}")
        
        val fileBytes = csvFile.readBytes()
        
        // 2. Ejecutar importación
        val request = ImportRequest(
            fileName = fileName,
            fileContent = fileBytes,
            type = ImportType.DOCENTES
        )
        
        val result = bulkImportUseCase.execute(request)
        
        // 3. Verificaciones
        assertEquals(2, result.total, "Deberían procesarse 2 registros")
        assertEquals(2, result.created, "Deberían crearse 2 docentes")
        assertEquals(0, result.errors, "No debería haber errores")
        
        // 4. Verificar persistencia en base de datos
        val employees = employeeRepository.getEmployees("INST_DEFAULT").first()
        assertTrue(employees.any { it.dni == "12345678" }, "Debería existir el docente con DNI 12345678")
        
        val juan = employees.find { it.dni == "12345678" }
        assertNotNull(juan)
        assertEquals("Juan", juan.firstName)
        assertEquals("Perez", juan.lastName)
        assertEquals("Matematicas", juan.specialization)
    }
}
