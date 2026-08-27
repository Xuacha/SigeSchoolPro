package com.sigeschool.di

import org.koin.dsl.module

actual fun platformModule() = module {
    // IA & Security migrated components
    single { com.sigeschool.core.ai.DocumentClassifier() }
    single { com.sigeschool.core.ai.AcademicStructureAnalyzer() }
    single { com.sigeschool.core.security.SecurityManager() }
}
