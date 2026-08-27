package com.sigeschool.di

import com.sigeschool.services.pdf.PlatformPdfGenerator
import com.sigeschool.services.pdf.PdfGenerator
import com.sigeschool.domain.model.WhatsAppConfig
import com.sigeschool.domain.model.EmailConfig
import com.gestionescolar.sigeschoolpro.BuildConfig
import com.sigeschool.presentation.util.ColorExtractor
import org.koin.dsl.module

val androidPlatformModule = module {
    single<PdfGenerator> { PlatformPdfGenerator() }
    single { PlatformPdfGenerator() }
    single<com.sigeschool.services.vision.OcrService> { com.sigeschool.services.vision.AndroidOcrService() }
    single<com.sigeschool.services.ai.AiCurricularService> { 
        com.sigeschool.services.ai.LocalAiService()
    }
    single { com.sigeschool.services.ai.NerService() }

    // Servicios de notificación por plataforma
    single<com.sigeschool.domain.service.notification.ChannelService> { 
        com.sigeschool.domain.service.notification.WhatsAppChannelService(get())
    }

    single {
        WhatsAppConfig(
            apiUrl = BuildConfig.WHATSAPP_API_URL,
            phoneNumberId = BuildConfig.WHATSAPP_PHONE_ID,
            accessToken = BuildConfig.WHATSAPP_ACCESS_TOKEN
        )
    }
    single {
        EmailConfig(
            host = BuildConfig.EMAIL_SMTP_HOST,
            port = BuildConfig.EMAIL_SMTP_PORT.toIntOrNull() ?: 587,
            user = BuildConfig.EMAIL_USER,
            password = BuildConfig.EMAIL_PASSWORD
        )
    }
    single<com.sigeschool.services.export.ExportService> { com.sigeschool.services.export.PlatformExportService(get()) }
    single { com.sigeschool.data.service.PlatformBackupHelper(get()) }

    // IA & Security migrated components
    single { com.sigeschool.core.ai.DocumentClassifier(get()) }
    single { com.sigeschool.core.ai.AcademicStructureAnalyzer() }
    single { com.sigeschool.core.security.SecurityManager(get()) }
    single { ColorExtractor(get()) }
}
