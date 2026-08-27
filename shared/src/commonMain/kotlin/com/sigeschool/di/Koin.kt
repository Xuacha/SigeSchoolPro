package com.sigeschool.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Koin initialization for SigeSchoolPro.
 */
fun initKoin(additionalModules: List<Module> = emptyList(), appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            listOf(
                coreDataModule,
                coreRepositoryModule,
                coreUtilModule
            ) + additionalModules
        )
    }

val coreDataModule = module {
    single { com.sigeschool.data.remote.SupabaseClientProvider.client }
}

val coreRepositoryModule = module {
    single { com.sigeschool.domain.util.SessionManager() }
}

val coreUtilModule = module {
    single { com.sigeschool.domain.util.PermissionService() }
}
