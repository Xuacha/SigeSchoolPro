package com.sigeschool.local.di

import com.sigeschool.data.datasource.*
import com.sigeschool.data.datasource.sie.SieLocalDataSource
import com.sigeschool.data.local.datasource.*
import com.sigeschool.data.local.datasource.sie.SieLocalDataSourceImpl
import com.sigeschool.data.datasource.billing.BillingLocalDataSource
import com.sigeschool.data.local.datasource.billing.BillingLocalDataSourceImpl
import com.sigeschool.domain.repository.AcudienteRepository
import com.sigeschool.domain.repository.NotificationRepository
import com.sigeschool.data.repository.AcudienteRepositoryImpl
import com.sigeschool.data.repository.NotificationRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import com.sigeschool.data.local.database.AppDatabase

val localModule = module {
    single { get<AppDatabase>().studentDao() }
    single { get<AppDatabase>().parentDao() }
    single { get<AppDatabase>().notificationDao() }
    single { get<AppDatabase>().attendanceDao() }
    single { get<AppDatabase>().gradeDao() }
    single { get<AppDatabase>().classDao() }
    single { get<AppDatabase>().employeeDao() }
    single { get<AppDatabase>().salaryDao() }
    single { get<AppDatabase>().taskDao() }
    single { get<AppDatabase>().examDao() }
    single { get<AppDatabase>().announcementDao() }
    single { get<AppDatabase>().pucAccountDao() }
    single { get<AppDatabase>().accountingEntryDao() }
    single { get<AppDatabase>().feePaymentDao() }
    single { get<AppDatabase>().laboralDao() }
    single { get<AppDatabase>().billingDao() }
    single { get<AppDatabase>().sieDao() }
    single { get<AppDatabase>().curricularDao() }
    single { get<AppDatabase>().cashDao() }
    single { get<AppDatabase>().consentDao() }
    single { get<AppDatabase>().auditDao() }
    single { get<AppDatabase>().feeCategoryDao() }
    single { get<AppDatabase>().cashClosingDao() }
    single { get<AppDatabase>().importDao() }
    single { get<AppDatabase>().backupDao() }
    single { get<AppDatabase>().roleDao() }
    single { get<AppDatabase>().personalProfileDao() }
    single { get<AppDatabase>().academicDao() }
    single { get<AppDatabase>().promotionDao() }
    single { get<AppDatabase>().paymentDao() }
    single { get<AppDatabase>().expenseDao() }
    
    single { com.sigeschool.services.security.AuditLedger("SigeSchool_Secret_Key_Gold_2024") }
    single { com.sigeschool.services.security.LedgerVerificationService(get(), get()) }
    single { com.sigeschool.services.billing.FacturacionStandbyService(get()) }
    
    single<StudentLocalDataSource> { StudentLocalDataSourceImpl(get()) }
    single<AttendanceLocalDataSource> { AttendanceLocalDataSourceImpl(get()) }
    single<GradeLocalDataSource> { GradeLocalDataSourceImpl(get()) }
    single<EmployeeLocalDataSource> { EmployeeLocalDataSourceImpl(get()) }
    single<SalaryLocalDataSource> { SalaryLocalDataSourceImpl(get()) }
    single<TaskLocalDataSource> { TaskLocalDataSourceImpl(get()) }
    single<ExamLocalDataSource> { ExamLocalDataSourceImpl(get()) }
    single<AnnouncementLocalDataSource> { AnnouncementLocalDataSourceImpl(get()) }
    single<ClassLocalDataSource> { ClassLocalDataSourceImpl(get()) }
    single<PucLocalDataSource> { PucLocalDataSourceImpl(get(), get()) }
    single<FeeLocalDataSource> { FeeLocalDataSourceImpl(get()) }
    single<LaboralLocalDataSource> { LaboralLocalDataSourceImpl(get()) }
    single<BillingLocalDataSource> { BillingLocalDataSourceImpl(get(), get(), get(), get()) }
    single<CurricularLocalDataSource> { CurricularLocalDataSourceImpl(get()) }
    single<SieLocalDataSource> { SieLocalDataSourceImpl(get()) }
    single<CashLocalDataSource> { CashLocalDataSourceImpl(get()) }
    single<ConsentLocalDataSource> { ConsentLocalDataSourceImpl(get(), get(), get()) }
    single<BackupLocalDataSource> { BackupLocalDataSourceImpl(get()) }
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
    single<InstitutionLocalDataSource> { InstitutionLocalDataSourceImpl(get()) }

    single { get<AppDatabase>().libroDao() }
    single { get<AppDatabase>().prestamoDao() }
    single { get<AppDatabase>().convivenciaDao() }

    // Repositories
    single<com.sigeschool.domain.repository.KeyBackupRepository> { com.sigeschool.data.local.repository.KeyBackupRepositoryImpl(get()) }
    single<AcudienteRepository> { AcudienteRepositoryImpl(get(), get()) }
    single<NotificationRepository> { NotificationRepositoryImpl(get(), get()) }
    single<com.sigeschool.domain.repository.ImportRepository> { com.sigeschool.data.repository.ImportRepositoryImpl(get()) }
    single<com.sigeschool.domain.repository.sie.PromotionRepository> { com.sigeschool.data.repository.sie.PromotionRepositoryImpl(get()) }
    single<com.sigeschool.domain.repository.CertificateRepository> { com.sigeschool.data.repository.CertificateRepositoryImpl(get(), get()) }
    single<com.sigeschool.domain.repository.LibraryRepository> { com.sigeschool.data.repository.LibraryRepositoryImpl(get(), get(), get()) }
    single<com.sigeschool.domain.repository.BehaviorRepository> { com.sigeschool.data.repository.BehaviorRepositoryImpl(get(), get()) }
}

expect fun databaseModule(): Module
