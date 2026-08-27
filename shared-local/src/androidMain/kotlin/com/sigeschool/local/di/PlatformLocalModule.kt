package com.sigeschool.local.di

import com.sigeschool.data.local.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun databaseModule() = module {
    single { getDatabaseBuilder(get()).build() }
}
