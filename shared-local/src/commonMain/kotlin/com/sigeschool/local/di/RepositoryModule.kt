package com.sigeschool.local.di

import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.repository.*
import com.sigeschool.domain.repository.billing.*
import com.sigeschool.domain.repository.sie.*
import com.sigeschool.data.repository.*
import com.sigeschool.data.repository.billing.*
import com.sigeschool.data.repository.sie.*
import com.sigeschool.domain.util.*
import org.koin.dsl.module

val repositoryImplementationModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get(), get()) }
    single<AuditRepository> { AuditRepositoryImpl(get(), get(), get()) }
    single<StudentRepository> { StudentRepositoryImpl(get(), get(), get(), get()) }
    single<AttendanceRepository> { AttendanceRepositoryImpl(get(), get(), get(), get()) }
    single<GradeRepository> { GradeRepositoryImpl(get(), get(), get()) }
    single<ClassRepository> { ClassRepositoryImpl(get(), get()) }
    single<EmployeeRepository> { EmployeeRepositoryImpl(get(), get(), get()) }
    single<SalaryRepository> { SalaryRepositoryImpl(get(), get()) }
    single<TaskRepository> { TaskRepositoryImpl(get(), get()) }
    single<ExamRepository> { ExamRepositoryImpl(get(), get()) }
    single<AnnouncementRepository> { AnnouncementRepositoryImpl(get(), get()) }
    single<PucRepository> { PucRepositoryImpl(get(), get()) }
    single<LaboralRepository> { LaboralRepositoryImpl(get(), get(), get(), get(), get()) }
    single<FeeRepository> { FeeRepositoryImpl(get(), get(), get(), get()) }
    single<CertificateRepository> { CertificateRepositoryImpl(get(), get()) }
    single<CurricularRepository> { CurricularRepositoryImpl(get(), get(), get()) }
    single<BillingRepository> { BillingRepositoryImpl(get(), get(), get()) }
    single<CashRepository> { CashRepositoryImpl(get()) }
    single<SieRepository> { SieRepositoryImpl(get()) }
    single<AcademicRepository> { AcademicRepositoryImpl(get()) }
    single<ConsentRepository> { ConsentRepositoryImpl(get()) }
    single<BankAccountRepository> { BankAccountRepositoryImpl(get()) }
    single<PaymentRepository> { PaymentRepositoryImpl(get(), get()) }
    single<ExpenseRepository> { ExpenseRepositoryImpl(get(), get()) }
    single<ThemeRepository> { ThemeRepositoryImpl(get(), get()) }
    
    single { 
        com.sigeschool.services.sync.SyncManager(
            get(), get(), get(), get(), get(), get(), get(), 
            get(), get(), get(), get(), get(), get()
        ) 
    }
}
