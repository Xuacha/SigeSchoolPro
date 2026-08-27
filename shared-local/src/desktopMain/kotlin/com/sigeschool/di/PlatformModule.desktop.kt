package com.sigeschool.di

import com.sigeschool.services.pdf.PlatformPdfGenerator
import com.sigeschool.services.pdf.PdfGenerator
import com.sigeschool.domain.model.WhatsAppConfig
import com.sigeschool.domain.model.EmailConfig
import com.sigeschool.presentation.util.ColorExtractor
import org.koin.dsl.module

val desktopPlatformModule = module {
    single<PdfGenerator> { PlatformPdfGenerator() }
    single { PlatformPdfGenerator() }
    single<com.sigeschool.services.vision.OcrService> { com.sigeschool.services.vision.DesktopOcrService() }
    single<com.sigeschool.services.ai.AiCurricularService> { com.sigeschool.services.ai.NoOpAiService() }
    single { com.sigeschool.services.ai.NerService() }

    single<com.sigeschool.domain.service.notification.ChannelService> { 
        com.sigeschool.domain.service.notification.WhatsAppChannelService(get())
    }

    single {
        WhatsAppConfig(
            apiUrl = "",
            phoneNumberId = "",
            accessToken = ""
        )
    }
    single {
        EmailConfig(
            host = "",
            port = 587,
            user = "",
            password = ""
        )
    }
    single<com.sigeschool.services.export.ExportService> { com.sigeschool.services.export.PlatformExportService(get()) }
    single { com.sigeschool.data.service.PlatformBackupHelper() }
    
    // IA & Security migrated components
    single { com.sigeschool.core.ai.DocumentClassifier() }
    single { com.sigeschool.core.ai.AcademicStructureAnalyzer() }
    single { com.sigeschool.core.security.SecurityManager() }
    single { ColorExtractor() }
}
