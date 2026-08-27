package com.sigeschool.di

import com.sigeschool.data.datasource.*
import com.sigeschool.data.datasource.billing.BillingLocalDataSource
import com.sigeschool.data.datasource.sie.SieLocalDataSource
import com.sigeschool.services.pdf.PdfGenerator
import com.sigeschool.services.pdf.PlatformPdfGenerator
import org.koin.dsl.module

val wasmJsPlatformModule = module {
    single<PdfGenerator> { PlatformPdfGenerator() }
    single { PlatformPdfGenerator() }
    single<ClassLocalDataSource> { NoOpClassLocalDataSource() }
    single<PucLocalDataSource> { NoOpPucLocalDataSource() }
    single<ExamLocalDataSource> { NoOpExamLocalDataSource() }
    single<TaskLocalDataSource> { NoOpTaskLocalDataSource() }
    single<GradeLocalDataSource> { NoOpGradeLocalDataSource() }
    single<SalaryLocalDataSource> { NoOpSalaryLocalDataSource() }
    single<StudentLocalDataSource> { NoOpStudentLocalDataSource() }
    single<EmployeeLocalDataSource> { NoOpEmployeeLocalDataSource() }
    single<AttendanceLocalDataSource> { NoOpAttendanceLocalDataSource() }
    single<AnnouncementLocalDataSource> { NoOpAnnouncementLocalDataSource() }
    single<CurricularLocalDataSource> { NoOpCurricularLocalDataSource() }
    single<LaboralLocalDataSource> { NoOpLaboralLocalDataSource() }
    single<FeeLocalDataSource> { NoOpFeeLocalDataSource() }
    single<BillingLocalDataSource> { NoOpBillingLocalDataSource() }
    single<SieLocalDataSource> { NoOpSieLocalDataSource() }
    single<com.sigeschool.services.vision.OcrService> { com.sigeschool.services.vision.WasmOcrService() }
    single<com.sigeschool.services.ai.AiCurricularService> { com.sigeschool.services.ai.WasmNoOpAiService() }
    single { com.sigeschool.services.ai.NerService() }
    
    single {
        com.sigeschool.domain.model.WhatsAppConfig("", "", "")
    }
    single {
        com.sigeschool.domain.model.EmailConfig("", 587, "", "")
    }
    single<com.sigeschool.domain.service.notification.ChannelService> { 
        com.sigeschool.domain.service.notification.WhatsAppChannelService(get())
    }
    single<com.sigeschool.services.export.ExportService> { com.sigeschool.services.export.PlatformExportService(get()) }
    single { com.sigeschool.data.service.PlatformBackupHelper() }

    // IA & Security migrated components
    single { com.sigeschool.core.ai.DocumentClassifier() }
    single { com.sigeschool.core.ai.AcademicStructureAnalyzer() }
    single { com.sigeschool.core.security.SecurityManager() }
}
