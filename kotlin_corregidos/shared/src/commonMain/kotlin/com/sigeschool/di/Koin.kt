package com.sigeschool.di

import com.sigeschool.domain.repository.*
import com.sigeschool.data.repository.*
import com.sigeschool.data.remote.*
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.util.ReceiptGenerator
import com.sigeschool.util.ReceiptGeneratorImpl
import io.github.jan.supabase.postgrest.postgrest
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(additionalModules: List<Module> = emptyList(), appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            listOf(
                platformModule(),
                dataModule,
                repositoryModule,
                utilModule
            ) + additionalModules
        )
    }

val dataModule = module {
    single { SupabaseClientProvider.client }
    single { StudentRemoteDataSource(get()) }
    single { EmployeeRemoteDataSource(get()) }
    single { TaskRemoteDataSource(get()) }
    single { ExamRemoteDataSource(get()) }
    single { AnnouncementRemoteDataSource(get()) }
    single { SalaryRemoteDataSource(get<io.github.jan.supabase.SupabaseClient>().postgrest) }
    single { PucRemoteDataSource(get<io.github.jan.supabase.SupabaseClient>().postgrest) }
    single { LaboralRemoteDataSource(get<io.github.jan.supabase.SupabaseClient>().postgrest) }
    single { FeeRemoteDataSource(get()) }
}

val repositoryModule = module {
    single { SessionManager() }
    single { AuthRepository(get(), get()) }
    single<StudentRepository> { StudentRepositoryImpl(get(), get()) }
    single<AttendanceRepository> { AttendanceRepositoryImpl(get(), get(), get()) }
    single<GradeRepository> { GradeRepositoryImpl(get(), get()) }
    single<ClassRepository> { ClassRepositoryImpl(get()) }
    single<EmployeeRepository> { EmployeeRepositoryImpl(get(), get()) }
    single<SalaryRepository> { SalaryRepositoryImpl(get(), get()) }
    single<TaskRepository> { TaskRepositoryImpl(get(), get()) }
    single<ExamRepository> { ExamRepositoryImpl(get(), get()) }
    single<AnnouncementRepository> { AnnouncementRepositoryImpl(get(), get()) }
    single<PucRepository> { PucRepositoryImpl(get(), get()) }
    single<LaboralRepository> { LaboralRepositoryImpl(get()) }
    single<FeeRepository> { FeeRepositoryImpl(get(), get(), get(), get()) }
}

val utilModule = module {
    single<ReceiptGenerator> { ReceiptGeneratorImpl() }
}

expect fun platformModule(): Module
