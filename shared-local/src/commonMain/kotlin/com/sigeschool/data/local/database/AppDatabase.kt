package com.sigeschool.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

import com.sigeschool.data.local.dao.*
import com.sigeschool.data.local.entity.*
import com.sigeschool.data.local.dao.billing.BillingDao
import com.sigeschool.data.local.dao.billing.FeeCategoryDao
import com.sigeschool.data.local.dao.billing.BankAccountDao
import com.sigeschool.data.local.dao.sie.AcademicDao
import com.sigeschool.data.local.dao.sie.SieDao
import com.sigeschool.data.local.dao.sie.PromotionDao
import com.sigeschool.data.local.entity.billing.*
import com.sigeschool.data.local.entity.sie.*
import com.sigeschool.data.local.converter.Converters

@Database(
    entities = [
        StudentEntity::class,
        AttendanceEntity::class,
        GradeEntity::class,
        ClassEntity::class,
        EmployeeEntity::class,
        SalaryRecordEntity::class,
        TaskEntity::class,
        ExamEntity::class,
        AnnouncementEntity::class,
        PucAccountEntity::class,
        AccountingEntryEntity::class,
        EmployeeAttendanceEntity::class,
        FeePaymentEntity::class,
        VacationRequestEntity::class,
        AdvanceRequestEntity::class,
        PayrollCalculationEntity::class,
        CashClosingEntity::class,
        InstitutionalDocumentEntity::class,
        DocumentBlockEntity::class,
        BlockHistoryEntity::class,
        InvoiceEntity::class,
        InvoiceItemEntity::class,
        PaymentRecordEntity::class,
        GradingScaleEntity::class,
        ScaleRangeEntity::class,
        GradeCategoryEntity::class,
        RubricEntity::class,
        RubricCriterionEntity::class,
        CriterionLevelEntity::class,
        CompetencyEntity::class,
        AchievementIndicatorEntity::class,
        RubricEvaluationEntity::class,
        CriterionSelectionEntity::class,
        CashTransactionEntity::class,
        PrivacyPolicyEntity::class,
        ConsentEntity::class,
        ConsentHistoryEntity::class,
        FeeCategoryEntity::class,
        AchievementEntity::class,
        AcademicGradeEntity::class,
        DisciplineRecordEntity::class,
        StudyPlanEntity::class,
        AreaPlanEntity::class,
        AuditEntryEntity::class,
        RoleEntity::class,
        PermisoEntity::class,
        RolePermisoCrossReference::class,
        PerfilPersonalEntity::class,
        HistorialCvEntity::class,
        AcudienteEntity::class,
        EstudianteAcudienteEntity::class,
        PreferenciaNotificacionEntity::class,
        NotificacionEntity::class,
        InstitutionalNotificationEntity::class,
        CircularEntity::class,
        LogNotificacionEntity::class,
        ImportEntity::class,
        ImportDetailEntity::class,
        BackupLogEntity::class,
        KeyBackupLogEntity::class,
        BankAccountEntity::class,
        BankAccountHistoryEntity::class,
        AutoevaluacionEntity::class,
        PromotionConfigEntity::class,
        NivelEducativoEntity::class,
        AcademicGradoEntity::class,
        PeriodoAcademicoEntity::class,
        PeriodoConfiguracionEntity::class,
        AreaConocimientoEntity::class,
        AsignaturaEntity::class,
        OfertaAcademicaEntity::class,
        DetalleOfertaAcademicaEntity::class,
        ClaseEntity::class,
        MatriculaEntity::class,
        PlanEstudiosEntity::class,
        PlanEstudiosDetalleEntity::class,
        PlanAulaEntity::class,
        AulaEntity::class,
        HorarioEntity::class,
        CalificacionEntity::class,
        AuditLogEntity::class,
        DocenteSyncConfigEntity::class,
        DocenteSyncLogEntity::class,
        DocumentoInstitucionalEntity::class,
        InstitutionEntity::class,
        InstitutionSettingsEntity::class,
        InstitutionThemeEntity::class,
        ListadoConfigEntity::class,
        PlanEntity::class,
        SuscripcionEntity::class,
        AccessLogEntity::class,
        AlertaInasistenciaEntity::class,
        AlertaTempranaEntity::class,
        CitaEntity::class,
        ConfiguracionAlertaEntity::class,
        ConfiguracionAlertasEntity::class,
        RetiroAnticipadoEntity::class,
        SeguimientoInasistenciaEntity::class,
        ServicioEntity::class,
        ServicioLogEntity::class,
        UserEntity::class,
        UserApprovalEntity::class,
        ConvivenciaCaseEntity::class,
        TestimonyEntity::class,
        BehavioralCompetencyEntity::class,
        BehavioralScoreEntity::class,
        FamilyAttendanceEntity::class,
        ExpenseEntity::class,
        FacturaEntity::class,
        PaymentEntity::class,
        OrdenPagoEntity::class,
        ConceptoPagoEntity::class,
        ConfirmacionPagoEntity::class,
        AcademicRecordEntity::class,
        CertificadoEntity::class,
        ManagedDocumentEntity::class,
        SalaryEntity::class,
        DocenteEntity::class,
        DocenteCursoEntity::class,
        HorarioAtencionEntity::class,
        NominaEntity::class,
        LibroEntity::class,
        PrestamoEntity::class,
        ScheduleEntity::class,
        ClassroomEntity::class,
        FirmaUsuarioEntity::class,
        ProgramEntity::class,
        RiskAnalysisEntity::class,
        RiskSummaryEntity::class,
        StudentProgramEntity::class,
        ParentGuardianEntity::class,
        TareaEntity::class,
        EntregaEntity::class,
        TareaAdjuntoEntity::class,
        SedeEntity::class,
        JornadaEntity::class,
        CursoEntity::class,
        NotificacionCalificacionEntity::class,
        ProgramaMappingEntity::class,
        ProgramaOfertaMappingEntity::class
    ],
    version = 60
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun gradeDao(): GradeDao
    abstract fun classDao(): ClassDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun salaryDao(): SalaryDao
    abstract fun taskDao(): TaskDao
    abstract fun examDao(): ExamDao
    abstract fun announcementDao(): AnnouncementDao
    abstract fun pucAccountDao(): PucAccountDao
    abstract fun accountingEntryDao(): AccountingEntryDao
    abstract fun feePaymentDao(): FeePaymentDao
    abstract fun laboralDao(): LaboralDao
    abstract fun cashClosingDao(): CashClosingDao
    abstract fun curricularDao(): CurricularDao
    abstract fun billingDao(): BillingDao
    abstract fun sieDao(): SieDao
    abstract fun academicDao(): AcademicDao
    abstract fun feeCategoryDao(): FeeCategoryDao
    abstract fun cashDao(): CashDao
    abstract fun consentDao(): ConsentDao
    abstract fun auditDao(): AuditDao
    abstract fun roleDao(): RoleDao
    abstract fun personalProfileDao(): PersonalProfileDao
    abstract fun parentDao(): ParentDao
    abstract fun notificationDao(): NotificationDao
    abstract fun importDao(): ImportDao
    abstract fun backupDao(): BackupDao
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun promotionDao(): PromotionDao

    abstract fun nivelEducativoDao(): NivelEducativoDao
    abstract fun academicGradoDao(): AcademicGradoDao
    abstract fun periodoAcademicoDao(): PeriodoAcademicoDao
    abstract fun periodoConfiguracionDao(): PeriodoConfiguracionDao
    abstract fun areaConocimientoDao(): AreaConocimientoDao
    abstract fun asignaturaDao(): AsignaturaDao
    abstract fun ofertaAcademicaDao(): OfertaAcademicaDao
    abstract fun detalleOfertaAcademicaDao(): DetalleOfertaAcademicaDao
    abstract fun claseDao(): ClaseDao
    abstract fun matriculaDao(): MatriculaDao
    abstract fun planEstudiosDao(): PlanEstudiosDao
    abstract fun planEstudiosDetalleDao(): PlanEstudiosDetalleDao
    abstract fun planAulaDao(): PlanAulaDao
    abstract fun aulaDao(): AulaDao
    abstract fun horarioDao(): HorarioDao
    abstract fun calificacionDao(): CalificacionDao
    abstract fun auditLogDao(): AuditLogDao
    abstract fun docenteSyncConfigDao(): DocenteSyncConfigDao
    abstract fun documentoInstitucionalDao(): DocumentoInstitucionalDao
    abstract fun institutionDao(): InstitutionDao
    abstract fun institutionSettingsDao(): InstitutionSettingsDao
    abstract fun institutionThemeDao(): InstitutionThemeDao
    abstract fun listadoConfigDao(): ListadoConfigDao
    abstract fun planDao(): PlanDao
    abstract fun suscripcionDao(): SuscripcionDao
    abstract fun accessLogDao(): AccessLogDao
    abstract fun alertaInasistenciaDao(): AlertaInasistenciaDao
    abstract fun alertaTempranaDao(): AlertaTempranaDao
    abstract fun citaDao(): CitaDao
    abstract fun configuracionAlertaDao(): ConfiguracionAlertaDao
    abstract fun configuracionAlertasDao(): ConfiguracionAlertasDao
    abstract fun retiroAnticipadoDao(): RetiroAnticipadoDao
    abstract fun seguimientoInasistenciaDao(): SeguimientoInasistenciaDao
    abstract fun servicioDao(): ServicioDao
    abstract fun servicioLogDao(): ServicioLogDao
    abstract fun userDao(): UserDao
    abstract fun userApprovalDao(): UserApprovalDao
    abstract fun convivenciaDao(): ConvivenciaDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun facturaDao(): FacturaDao
    abstract fun paymentDao(): PaymentDao
    abstract fun ordenPagoDao(): OrdenPagoDao
    abstract fun conceptoPagoDao(): ConceptoPagoDao
    abstract fun confirmacionPagoDao(): ConfirmacionPagoDao
    abstract fun academicRecordDao(): AcademicRecordDao
    abstract fun certificadoDao(): CertificadoDao
    abstract fun managedDocumentDao(): ManagedDocumentDao
    abstract fun docenteDao(): DocenteDao
    abstract fun docenteCursoDao(): DocenteCursoDao
    abstract fun horarioAtencionDao(): HorarioAtencionDao
    abstract fun nominaDao(): NominaDao
    abstract fun libroDao(): LibroDao
    abstract fun prestamoDao(): PrestamoDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun firmaUsuarioDao(): FirmaUsuarioDao
    abstract fun programDao(): ProgramDao
    abstract fun riskAnalysisDao(): RiskAnalysisDao
    abstract fun riskSummaryDao(): RiskSummaryDao
    abstract fun studentProgramDao(): StudentProgramDao
    abstract fun submissionDao(): SubmissionDao
    abstract fun sedeDao(): SedeDao
    abstract fun jornadaDao(): JornadaDao
    abstract fun cursoDao(): CursoDao
    abstract fun notificacionCalificacionDao(): NotificacionCalificacionDao
    abstract fun programaMappingDao(): ProgramaMappingDao
    abstract fun programaOfertaMappingDao(): ProgramaOfertaMappingDao
}

// Room KMP Migration for version 19
val MIGRATION_18_19 = object : Migration(18, 19) {
    override fun migrate(connection: SQLiteConnection) {
        val tables = listOf(
            "students", "fee_payments", "attendance", "grades", 
            "consentimientos", "invoices", "cash_transactions"
        )
        tables.forEach { table ->
            connection.execSQL("ALTER TABLE $table ADD COLUMN version INTEGER NOT NULL DEFAULT 0")
            connection.execSQL("ALTER TABLE $table ADD COLUMN deviceId TEXT NOT NULL DEFAULT ''")
            connection.execSQL("ALTER TABLE $table ADD COLUMN lastModified INTEGER NOT NULL DEFAULT 0")
            connection.execSQL("ALTER TABLE $table ADD COLUMN syncStatus INTEGER NOT NULL DEFAULT 0")
            connection.execSQL("ALTER TABLE $table ADD COLUMN syncAttempts INTEGER NOT NULL DEFAULT 0")
        }
    }
}

val MIGRATION_25_26 = object : Migration(25, 26) {
    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL("""
            CREATE TABLE IF NOT EXISTS logs_backup_llaves (
                idLog TEXT PRIMARY KEY NOT NULL,
                accion TEXT NOT NULL,
                fecha INTEGER NOT NULL,
                usuarioId TEXT NOT NULL,
                exito INTEGER NOT NULL DEFAULT 0,
                mensajeError TEXT,
                metadata TEXT
            )
        """)
    }
}

// The expected constructor for Room KMP
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>,
    passphrase: String? = null
): AppDatabase {
    return builder
        .setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
        .setQueryCoroutineContext(kotlin.coroutines.EmptyCoroutineContext)
        .build()
}
