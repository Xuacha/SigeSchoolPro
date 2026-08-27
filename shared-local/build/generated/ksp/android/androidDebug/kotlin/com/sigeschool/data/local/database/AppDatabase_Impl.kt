package com.sigeschool.`data`.local.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.sigeschool.`data`.local.dao.AcademicGradoDao
import com.sigeschool.`data`.local.dao.AcademicGradoDao_Impl
import com.sigeschool.`data`.local.dao.AcademicRecordDao
import com.sigeschool.`data`.local.dao.AcademicRecordDao_Impl
import com.sigeschool.`data`.local.dao.AccessLogDao
import com.sigeschool.`data`.local.dao.AccessLogDao_Impl
import com.sigeschool.`data`.local.dao.AccountingEntryDao
import com.sigeschool.`data`.local.dao.AccountingEntryDao_Impl
import com.sigeschool.`data`.local.dao.AlertaInasistenciaDao
import com.sigeschool.`data`.local.dao.AlertaInasistenciaDao_Impl
import com.sigeschool.`data`.local.dao.AlertaTempranaDao
import com.sigeschool.`data`.local.dao.AlertaTempranaDao_Impl
import com.sigeschool.`data`.local.dao.AnnouncementDao
import com.sigeschool.`data`.local.dao.AnnouncementDao_Impl
import com.sigeschool.`data`.local.dao.AreaConocimientoDao
import com.sigeschool.`data`.local.dao.AreaConocimientoDao_Impl
import com.sigeschool.`data`.local.dao.AsignaturaDao
import com.sigeschool.`data`.local.dao.AsignaturaDao_Impl
import com.sigeschool.`data`.local.dao.AttendanceDao
import com.sigeschool.`data`.local.dao.AttendanceDao_Impl
import com.sigeschool.`data`.local.dao.AuditDao
import com.sigeschool.`data`.local.dao.AuditDao_Impl
import com.sigeschool.`data`.local.dao.AuditLogDao
import com.sigeschool.`data`.local.dao.AuditLogDao_Impl
import com.sigeschool.`data`.local.dao.AulaDao
import com.sigeschool.`data`.local.dao.AulaDao_Impl
import com.sigeschool.`data`.local.dao.BackupDao
import com.sigeschool.`data`.local.dao.BackupDao_Impl
import com.sigeschool.`data`.local.dao.CalificacionDao
import com.sigeschool.`data`.local.dao.CalificacionDao_Impl
import com.sigeschool.`data`.local.dao.CashClosingDao
import com.sigeschool.`data`.local.dao.CashClosingDao_Impl
import com.sigeschool.`data`.local.dao.CashDao
import com.sigeschool.`data`.local.dao.CashDao_Impl
import com.sigeschool.`data`.local.dao.CertificadoDao
import com.sigeschool.`data`.local.dao.CertificadoDao_Impl
import com.sigeschool.`data`.local.dao.CitaDao
import com.sigeschool.`data`.local.dao.CitaDao_Impl
import com.sigeschool.`data`.local.dao.ClaseDao
import com.sigeschool.`data`.local.dao.ClaseDao_Impl
import com.sigeschool.`data`.local.dao.ClassDao
import com.sigeschool.`data`.local.dao.ClassDao_Impl
import com.sigeschool.`data`.local.dao.ConceptoPagoDao
import com.sigeschool.`data`.local.dao.ConceptoPagoDao_Impl
import com.sigeschool.`data`.local.dao.ConfiguracionAlertaDao
import com.sigeschool.`data`.local.dao.ConfiguracionAlertaDao_Impl
import com.sigeschool.`data`.local.dao.ConfiguracionAlertasDao
import com.sigeschool.`data`.local.dao.ConfiguracionAlertasDao_Impl
import com.sigeschool.`data`.local.dao.ConfirmacionPagoDao
import com.sigeschool.`data`.local.dao.ConfirmacionPagoDao_Impl
import com.sigeschool.`data`.local.dao.ConsentDao
import com.sigeschool.`data`.local.dao.ConsentDao_Impl
import com.sigeschool.`data`.local.dao.ConvivenciaDao
import com.sigeschool.`data`.local.dao.ConvivenciaDao_Impl
import com.sigeschool.`data`.local.dao.CurricularDao
import com.sigeschool.`data`.local.dao.CurricularDao_Impl
import com.sigeschool.`data`.local.dao.CursoDao
import com.sigeschool.`data`.local.dao.CursoDao_Impl
import com.sigeschool.`data`.local.dao.DetalleOfertaAcademicaDao
import com.sigeschool.`data`.local.dao.DetalleOfertaAcademicaDao_Impl
import com.sigeschool.`data`.local.dao.DocenteCursoDao
import com.sigeschool.`data`.local.dao.DocenteCursoDao_Impl
import com.sigeschool.`data`.local.dao.DocenteDao
import com.sigeschool.`data`.local.dao.DocenteDao_Impl
import com.sigeschool.`data`.local.dao.DocenteSyncConfigDao
import com.sigeschool.`data`.local.dao.DocenteSyncConfigDao_Impl
import com.sigeschool.`data`.local.dao.DocumentoInstitucionalDao
import com.sigeschool.`data`.local.dao.DocumentoInstitucionalDao_Impl
import com.sigeschool.`data`.local.dao.EmployeeDao
import com.sigeschool.`data`.local.dao.EmployeeDao_Impl
import com.sigeschool.`data`.local.dao.ExamDao
import com.sigeschool.`data`.local.dao.ExamDao_Impl
import com.sigeschool.`data`.local.dao.ExpenseDao
import com.sigeschool.`data`.local.dao.ExpenseDao_Impl
import com.sigeschool.`data`.local.dao.FacturaDao
import com.sigeschool.`data`.local.dao.FacturaDao_Impl
import com.sigeschool.`data`.local.dao.FeePaymentDao
import com.sigeschool.`data`.local.dao.FeePaymentDao_Impl
import com.sigeschool.`data`.local.dao.FirmaUsuarioDao
import com.sigeschool.`data`.local.dao.FirmaUsuarioDao_Impl
import com.sigeschool.`data`.local.dao.GradeDao
import com.sigeschool.`data`.local.dao.GradeDao_Impl
import com.sigeschool.`data`.local.dao.HorarioAtencionDao
import com.sigeschool.`data`.local.dao.HorarioAtencionDao_Impl
import com.sigeschool.`data`.local.dao.HorarioDao
import com.sigeschool.`data`.local.dao.HorarioDao_Impl
import com.sigeschool.`data`.local.dao.ImportDao
import com.sigeschool.`data`.local.dao.ImportDao_Impl
import com.sigeschool.`data`.local.dao.InstitutionDao
import com.sigeschool.`data`.local.dao.InstitutionDao_Impl
import com.sigeschool.`data`.local.dao.InstitutionSettingsDao
import com.sigeschool.`data`.local.dao.InstitutionSettingsDao_Impl
import com.sigeschool.`data`.local.dao.InstitutionThemeDao
import com.sigeschool.`data`.local.dao.InstitutionThemeDao_Impl
import com.sigeschool.`data`.local.dao.JornadaDao
import com.sigeschool.`data`.local.dao.JornadaDao_Impl
import com.sigeschool.`data`.local.dao.LaboralDao
import com.sigeschool.`data`.local.dao.LaboralDao_Impl
import com.sigeschool.`data`.local.dao.LibroDao
import com.sigeschool.`data`.local.dao.LibroDao_Impl
import com.sigeschool.`data`.local.dao.ListadoConfigDao
import com.sigeschool.`data`.local.dao.ListadoConfigDao_Impl
import com.sigeschool.`data`.local.dao.ManagedDocumentDao
import com.sigeschool.`data`.local.dao.ManagedDocumentDao_Impl
import com.sigeschool.`data`.local.dao.MatriculaDao
import com.sigeschool.`data`.local.dao.MatriculaDao_Impl
import com.sigeschool.`data`.local.dao.NivelEducativoDao
import com.sigeschool.`data`.local.dao.NivelEducativoDao_Impl
import com.sigeschool.`data`.local.dao.NominaDao
import com.sigeschool.`data`.local.dao.NominaDao_Impl
import com.sigeschool.`data`.local.dao.NotificacionCalificacionDao
import com.sigeschool.`data`.local.dao.NotificacionCalificacionDao_Impl
import com.sigeschool.`data`.local.dao.NotificationDao
import com.sigeschool.`data`.local.dao.NotificationDao_Impl
import com.sigeschool.`data`.local.dao.OfertaAcademicaDao
import com.sigeschool.`data`.local.dao.OfertaAcademicaDao_Impl
import com.sigeschool.`data`.local.dao.OrdenPagoDao
import com.sigeschool.`data`.local.dao.OrdenPagoDao_Impl
import com.sigeschool.`data`.local.dao.ParentDao
import com.sigeschool.`data`.local.dao.ParentDao_Impl
import com.sigeschool.`data`.local.dao.PaymentDao
import com.sigeschool.`data`.local.dao.PaymentDao_Impl
import com.sigeschool.`data`.local.dao.PeriodoAcademicoDao
import com.sigeschool.`data`.local.dao.PeriodoAcademicoDao_Impl
import com.sigeschool.`data`.local.dao.PeriodoConfiguracionDao
import com.sigeschool.`data`.local.dao.PeriodoConfiguracionDao_Impl
import com.sigeschool.`data`.local.dao.PersonalProfileDao
import com.sigeschool.`data`.local.dao.PersonalProfileDao_Impl
import com.sigeschool.`data`.local.dao.PlanAulaDao
import com.sigeschool.`data`.local.dao.PlanAulaDao_Impl
import com.sigeschool.`data`.local.dao.PlanDao
import com.sigeschool.`data`.local.dao.PlanDao_Impl
import com.sigeschool.`data`.local.dao.PlanEstudiosDao
import com.sigeschool.`data`.local.dao.PlanEstudiosDao_Impl
import com.sigeschool.`data`.local.dao.PlanEstudiosDetalleDao
import com.sigeschool.`data`.local.dao.PlanEstudiosDetalleDao_Impl
import com.sigeschool.`data`.local.dao.PrestamoDao
import com.sigeschool.`data`.local.dao.PrestamoDao_Impl
import com.sigeschool.`data`.local.dao.ProgramDao
import com.sigeschool.`data`.local.dao.ProgramDao_Impl
import com.sigeschool.`data`.local.dao.ProgramaMappingDao
import com.sigeschool.`data`.local.dao.ProgramaMappingDao_Impl
import com.sigeschool.`data`.local.dao.ProgramaOfertaMappingDao
import com.sigeschool.`data`.local.dao.ProgramaOfertaMappingDao_Impl
import com.sigeschool.`data`.local.dao.PucAccountDao
import com.sigeschool.`data`.local.dao.PucAccountDao_Impl
import com.sigeschool.`data`.local.dao.RetiroAnticipadoDao
import com.sigeschool.`data`.local.dao.RetiroAnticipadoDao_Impl
import com.sigeschool.`data`.local.dao.RiskAnalysisDao
import com.sigeschool.`data`.local.dao.RiskAnalysisDao_Impl
import com.sigeschool.`data`.local.dao.RiskSummaryDao
import com.sigeschool.`data`.local.dao.RiskSummaryDao_Impl
import com.sigeschool.`data`.local.dao.RoleDao
import com.sigeschool.`data`.local.dao.RoleDao_Impl
import com.sigeschool.`data`.local.dao.SalaryDao
import com.sigeschool.`data`.local.dao.SalaryDao_Impl
import com.sigeschool.`data`.local.dao.ScheduleDao
import com.sigeschool.`data`.local.dao.ScheduleDao_Impl
import com.sigeschool.`data`.local.dao.SedeDao
import com.sigeschool.`data`.local.dao.SedeDao_Impl
import com.sigeschool.`data`.local.dao.SeguimientoInasistenciaDao
import com.sigeschool.`data`.local.dao.SeguimientoInasistenciaDao_Impl
import com.sigeschool.`data`.local.dao.ServicioDao
import com.sigeschool.`data`.local.dao.ServicioDao_Impl
import com.sigeschool.`data`.local.dao.ServicioLogDao
import com.sigeschool.`data`.local.dao.ServicioLogDao_Impl
import com.sigeschool.`data`.local.dao.StudentDao
import com.sigeschool.`data`.local.dao.StudentDao_Impl
import com.sigeschool.`data`.local.dao.StudentProgramDao
import com.sigeschool.`data`.local.dao.StudentProgramDao_Impl
import com.sigeschool.`data`.local.dao.SubmissionDao
import com.sigeschool.`data`.local.dao.SubmissionDao_Impl
import com.sigeschool.`data`.local.dao.SuscripcionDao
import com.sigeschool.`data`.local.dao.SuscripcionDao_Impl
import com.sigeschool.`data`.local.dao.TaskDao
import com.sigeschool.`data`.local.dao.TaskDao_Impl
import com.sigeschool.`data`.local.dao.UserApprovalDao
import com.sigeschool.`data`.local.dao.UserApprovalDao_Impl
import com.sigeschool.`data`.local.dao.UserDao
import com.sigeschool.`data`.local.dao.UserDao_Impl
import com.sigeschool.`data`.local.dao.billing.BankAccountDao
import com.sigeschool.`data`.local.dao.billing.BankAccountDao_Impl
import com.sigeschool.`data`.local.dao.billing.BillingDao
import com.sigeschool.`data`.local.dao.billing.BillingDao_Impl
import com.sigeschool.`data`.local.dao.billing.FeeCategoryDao
import com.sigeschool.`data`.local.dao.billing.FeeCategoryDao_Impl
import com.sigeschool.`data`.local.dao.sie.AcademicDao
import com.sigeschool.`data`.local.dao.sie.AcademicDao_Impl
import com.sigeschool.`data`.local.dao.sie.PromotionDao
import com.sigeschool.`data`.local.dao.sie.PromotionDao_Impl
import com.sigeschool.`data`.local.dao.sie.SieDao
import com.sigeschool.`data`.local.dao.sie.SieDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _studentDao: Lazy<StudentDao> = lazy {
    StudentDao_Impl(this)
  }

  private val _attendanceDao: Lazy<AttendanceDao> = lazy {
    AttendanceDao_Impl(this)
  }

  private val _gradeDao: Lazy<GradeDao> = lazy {
    GradeDao_Impl(this)
  }

  private val _classDao: Lazy<ClassDao> = lazy {
    ClassDao_Impl(this)
  }

  private val _employeeDao: Lazy<EmployeeDao> = lazy {
    EmployeeDao_Impl(this)
  }

  private val _salaryDao: Lazy<SalaryDao> = lazy {
    SalaryDao_Impl(this)
  }

  private val _taskDao: Lazy<TaskDao> = lazy {
    TaskDao_Impl(this)
  }

  private val _examDao: Lazy<ExamDao> = lazy {
    ExamDao_Impl(this)
  }

  private val _announcementDao: Lazy<AnnouncementDao> = lazy {
    AnnouncementDao_Impl(this)
  }

  private val _pucAccountDao: Lazy<PucAccountDao> = lazy {
    PucAccountDao_Impl(this)
  }

  private val _accountingEntryDao: Lazy<AccountingEntryDao> = lazy {
    AccountingEntryDao_Impl(this)
  }

  private val _feePaymentDao: Lazy<FeePaymentDao> = lazy {
    FeePaymentDao_Impl(this)
  }

  private val _laboralDao: Lazy<LaboralDao> = lazy {
    LaboralDao_Impl(this)
  }

  private val _cashClosingDao: Lazy<CashClosingDao> = lazy {
    CashClosingDao_Impl(this)
  }

  private val _curricularDao: Lazy<CurricularDao> = lazy {
    CurricularDao_Impl(this)
  }

  private val _billingDao: Lazy<BillingDao> = lazy {
    BillingDao_Impl(this)
  }

  private val _sieDao: Lazy<SieDao> = lazy {
    SieDao_Impl(this)
  }

  private val _academicDao: Lazy<AcademicDao> = lazy {
    AcademicDao_Impl(this)
  }

  private val _feeCategoryDao: Lazy<FeeCategoryDao> = lazy {
    FeeCategoryDao_Impl(this)
  }

  private val _cashDao: Lazy<CashDao> = lazy {
    CashDao_Impl(this)
  }

  private val _consentDao: Lazy<ConsentDao> = lazy {
    ConsentDao_Impl(this)
  }

  private val _auditDao: Lazy<AuditDao> = lazy {
    AuditDao_Impl(this)
  }

  private val _roleDao: Lazy<RoleDao> = lazy {
    RoleDao_Impl(this)
  }

  private val _personalProfileDao: Lazy<PersonalProfileDao> = lazy {
    PersonalProfileDao_Impl(this)
  }

  private val _parentDao: Lazy<ParentDao> = lazy {
    ParentDao_Impl(this)
  }

  private val _notificationDao: Lazy<NotificationDao> = lazy {
    NotificationDao_Impl(this)
  }

  private val _importDao: Lazy<ImportDao> = lazy {
    ImportDao_Impl(this)
  }

  private val _backupDao: Lazy<BackupDao> = lazy {
    BackupDao_Impl(this)
  }

  private val _bankAccountDao: Lazy<BankAccountDao> = lazy {
    BankAccountDao_Impl(this)
  }

  private val _promotionDao: Lazy<PromotionDao> = lazy {
    PromotionDao_Impl(this)
  }

  private val _nivelEducativoDao: Lazy<NivelEducativoDao> = lazy {
    NivelEducativoDao_Impl(this)
  }

  private val _academicGradoDao: Lazy<AcademicGradoDao> = lazy {
    AcademicGradoDao_Impl(this)
  }

  private val _periodoAcademicoDao: Lazy<PeriodoAcademicoDao> = lazy {
    PeriodoAcademicoDao_Impl(this)
  }

  private val _periodoConfiguracionDao: Lazy<PeriodoConfiguracionDao> = lazy {
    PeriodoConfiguracionDao_Impl(this)
  }

  private val _areaConocimientoDao: Lazy<AreaConocimientoDao> = lazy {
    AreaConocimientoDao_Impl(this)
  }

  private val _asignaturaDao: Lazy<AsignaturaDao> = lazy {
    AsignaturaDao_Impl(this)
  }

  private val _ofertaAcademicaDao: Lazy<OfertaAcademicaDao> = lazy {
    OfertaAcademicaDao_Impl(this)
  }

  private val _detalleOfertaAcademicaDao: Lazy<DetalleOfertaAcademicaDao> = lazy {
    DetalleOfertaAcademicaDao_Impl(this)
  }

  private val _claseDao: Lazy<ClaseDao> = lazy {
    ClaseDao_Impl(this)
  }

  private val _matriculaDao: Lazy<MatriculaDao> = lazy {
    MatriculaDao_Impl(this)
  }

  private val _planEstudiosDao: Lazy<PlanEstudiosDao> = lazy {
    PlanEstudiosDao_Impl(this)
  }

  private val _planEstudiosDetalleDao: Lazy<PlanEstudiosDetalleDao> = lazy {
    PlanEstudiosDetalleDao_Impl(this)
  }

  private val _planAulaDao: Lazy<PlanAulaDao> = lazy {
    PlanAulaDao_Impl(this)
  }

  private val _aulaDao: Lazy<AulaDao> = lazy {
    AulaDao_Impl(this)
  }

  private val _horarioDao: Lazy<HorarioDao> = lazy {
    HorarioDao_Impl(this)
  }

  private val _calificacionDao: Lazy<CalificacionDao> = lazy {
    CalificacionDao_Impl(this)
  }

  private val _auditLogDao: Lazy<AuditLogDao> = lazy {
    AuditLogDao_Impl(this)
  }

  private val _docenteSyncConfigDao: Lazy<DocenteSyncConfigDao> = lazy {
    DocenteSyncConfigDao_Impl(this)
  }

  private val _documentoInstitucionalDao: Lazy<DocumentoInstitucionalDao> = lazy {
    DocumentoInstitucionalDao_Impl(this)
  }

  private val _institutionDao: Lazy<InstitutionDao> = lazy {
    InstitutionDao_Impl(this)
  }

  private val _institutionSettingsDao: Lazy<InstitutionSettingsDao> = lazy {
    InstitutionSettingsDao_Impl(this)
  }

  private val _institutionThemeDao: Lazy<InstitutionThemeDao> = lazy {
    InstitutionThemeDao_Impl(this)
  }

  private val _listadoConfigDao: Lazy<ListadoConfigDao> = lazy {
    ListadoConfigDao_Impl(this)
  }

  private val _planDao: Lazy<PlanDao> = lazy {
    PlanDao_Impl(this)
  }

  private val _suscripcionDao: Lazy<SuscripcionDao> = lazy {
    SuscripcionDao_Impl(this)
  }

  private val _accessLogDao: Lazy<AccessLogDao> = lazy {
    AccessLogDao_Impl(this)
  }

  private val _alertaInasistenciaDao: Lazy<AlertaInasistenciaDao> = lazy {
    AlertaInasistenciaDao_Impl(this)
  }

  private val _alertaTempranaDao: Lazy<AlertaTempranaDao> = lazy {
    AlertaTempranaDao_Impl(this)
  }

  private val _citaDao: Lazy<CitaDao> = lazy {
    CitaDao_Impl(this)
  }

  private val _configuracionAlertaDao: Lazy<ConfiguracionAlertaDao> = lazy {
    ConfiguracionAlertaDao_Impl(this)
  }

  private val _configuracionAlertasDao: Lazy<ConfiguracionAlertasDao> = lazy {
    ConfiguracionAlertasDao_Impl(this)
  }

  private val _retiroAnticipadoDao: Lazy<RetiroAnticipadoDao> = lazy {
    RetiroAnticipadoDao_Impl(this)
  }

  private val _seguimientoInasistenciaDao: Lazy<SeguimientoInasistenciaDao> = lazy {
    SeguimientoInasistenciaDao_Impl(this)
  }

  private val _servicioDao: Lazy<ServicioDao> = lazy {
    ServicioDao_Impl(this)
  }

  private val _servicioLogDao: Lazy<ServicioLogDao> = lazy {
    ServicioLogDao_Impl(this)
  }

  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }

  private val _userApprovalDao: Lazy<UserApprovalDao> = lazy {
    UserApprovalDao_Impl(this)
  }

  private val _convivenciaDao: Lazy<ConvivenciaDao> = lazy {
    ConvivenciaDao_Impl(this)
  }

  private val _expenseDao: Lazy<ExpenseDao> = lazy {
    ExpenseDao_Impl(this)
  }

  private val _facturaDao: Lazy<FacturaDao> = lazy {
    FacturaDao_Impl(this)
  }

  private val _paymentDao: Lazy<PaymentDao> = lazy {
    PaymentDao_Impl(this)
  }

  private val _ordenPagoDao: Lazy<OrdenPagoDao> = lazy {
    OrdenPagoDao_Impl(this)
  }

  private val _conceptoPagoDao: Lazy<ConceptoPagoDao> = lazy {
    ConceptoPagoDao_Impl(this)
  }

  private val _confirmacionPagoDao: Lazy<ConfirmacionPagoDao> = lazy {
    ConfirmacionPagoDao_Impl(this)
  }

  private val _academicRecordDao: Lazy<AcademicRecordDao> = lazy {
    AcademicRecordDao_Impl(this)
  }

  private val _certificadoDao: Lazy<CertificadoDao> = lazy {
    CertificadoDao_Impl(this)
  }

  private val _managedDocumentDao: Lazy<ManagedDocumentDao> = lazy {
    ManagedDocumentDao_Impl(this)
  }

  private val _docenteDao: Lazy<DocenteDao> = lazy {
    DocenteDao_Impl(this)
  }

  private val _docenteCursoDao: Lazy<DocenteCursoDao> = lazy {
    DocenteCursoDao_Impl(this)
  }

  private val _horarioAtencionDao: Lazy<HorarioAtencionDao> = lazy {
    HorarioAtencionDao_Impl(this)
  }

  private val _nominaDao: Lazy<NominaDao> = lazy {
    NominaDao_Impl(this)
  }

  private val _libroDao: Lazy<LibroDao> = lazy {
    LibroDao_Impl(this)
  }

  private val _prestamoDao: Lazy<PrestamoDao> = lazy {
    PrestamoDao_Impl(this)
  }

  private val _scheduleDao: Lazy<ScheduleDao> = lazy {
    ScheduleDao_Impl(this)
  }

  private val _firmaUsuarioDao: Lazy<FirmaUsuarioDao> = lazy {
    FirmaUsuarioDao_Impl(this)
  }

  private val _programDao: Lazy<ProgramDao> = lazy {
    ProgramDao_Impl(this)
  }

  private val _riskAnalysisDao: Lazy<RiskAnalysisDao> = lazy {
    RiskAnalysisDao_Impl(this)
  }

  private val _riskSummaryDao: Lazy<RiskSummaryDao> = lazy {
    RiskSummaryDao_Impl(this)
  }

  private val _studentProgramDao: Lazy<StudentProgramDao> = lazy {
    StudentProgramDao_Impl(this)
  }

  private val _submissionDao: Lazy<SubmissionDao> = lazy {
    SubmissionDao_Impl(this)
  }

  private val _sedeDao: Lazy<SedeDao> = lazy {
    SedeDao_Impl(this)
  }

  private val _jornadaDao: Lazy<JornadaDao> = lazy {
    JornadaDao_Impl(this)
  }

  private val _cursoDao: Lazy<CursoDao> = lazy {
    CursoDao_Impl(this)
  }

  private val _notificacionCalificacionDao: Lazy<NotificacionCalificacionDao> = lazy {
    NotificacionCalificacionDao_Impl(this)
  }

  private val _programaMappingDao: Lazy<ProgramaMappingDao> = lazy {
    ProgramaMappingDao_Impl(this)
  }

  private val _programaOfertaMappingDao: Lazy<ProgramaOfertaMappingDao> = lazy {
    ProgramaOfertaMappingDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(60,
        "f2d85ae8ff423d3ccb0f97f887482c94", "31b375e00c8efb5490f4e495c73dbcf6") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `students` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `userId` TEXT, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `documentId` TEXT NOT NULL, `gender` TEXT, `ethnicity` TEXT, `ethnicCommunity` TEXT, `disability` TEXT, `disabilityAdjustments` TEXT, `photoPath` TEXT, `qrCode` TEXT NOT NULL, `cursoId` INTEGER, `consentAcceptedAt` INTEGER, `consentVersion` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, `isDuplicate` INTEGER NOT NULL DEFAULT 0, `mergedIntoId` TEXT, `deletedAt` INTEGER, `deletedReason` TEXT, `deletedByUserId` TEXT, `documentType` TEXT, `birthDate` TEXT, `age` INTEGER, `email` TEXT, `phone` TEXT, `address` TEXT, `neighborhood` TEXT, `stratum` INTEGER, `educationLevel` TEXT, `previousSchool` TEXT, `selectedPrograms` TEXT, `howDidYouHear` TEXT, `status` TEXT NOT NULL DEFAULT 'ENROLLED', `withdrawalReason` TEXT, `withdrawalDate` INTEGER, `statusUpdatedAt` INTEGER NOT NULL, `photoUpdatedAt` INTEGER, `estadoMatricula` TEXT NOT NULL DEFAULT 'MATRICULADO', `fechaRetiro` INTEGER, `motivoRetiro` TEXT, `diasInasistenciaConsecutiva` INTEGER NOT NULL DEFAULT 0, `ultimaFechaAsistencia` INTEGER, `alertaEnviada30Dias` INTEGER NOT NULL DEFAULT 0, `guardianFirstName` TEXT, `guardianLastName` TEXT, `guardianDocumentId` TEXT, `guardianRelationship` TEXT, `guardianPhone` TEXT, `guardianEmail` TEXT, `esExterno` INTEGER NOT NULL DEFAULT 0, `institucionOrigen` TEXT, `externoId` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `attendance` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `type` TEXT NOT NULL, `claseId` INTEGER, `observacion` TEXT, `justificacionUrl` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `grades` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `courseId` TEXT NOT NULL, `subjectId` TEXT NOT NULL, `score` REAL NOT NULL, `date` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `classes` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `level` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `teacherId` TEXT, `createdAt` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `employees` (`id` TEXT NOT NULL, `authUserId` TEXT, `institutionId` TEXT NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `role` TEXT NOT NULL, `email` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `salary_records` (`id` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `amount` REAL NOT NULL, `date` INTEGER NOT NULL, `type` TEXT NOT NULL, `status` TEXT NOT NULL, `observation` TEXT NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `tasks` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `dueDate` INTEGER NOT NULL, `classId` TEXT NOT NULL, `subjectId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `exams` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `date` INTEGER NOT NULL, `classId` TEXT NOT NULL, `subjectId` TEXT NOT NULL, `maxScore` REAL NOT NULL, `institutionId` TEXT NOT NULL, `durationMinutes` INTEGER NOT NULL, `questions` TEXT NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `announcements` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `content` TEXT NOT NULL, `date` INTEGER NOT NULL, `authorId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `target` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `puc_accounts` (`id` TEXT NOT NULL, `code` TEXT NOT NULL, `name` TEXT NOT NULL, `level` INTEGER NOT NULL, `parentCode` TEXT, `accountType` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `isCustom` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `accounting_entries` (`id` TEXT NOT NULL, `date` TEXT NOT NULL, `description` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `type` TEXT NOT NULL, `centerId` TEXT, `entriesJson` TEXT NOT NULL, `totalDebit` REAL NOT NULL, `totalCredit` REAL NOT NULL, `isElectronicInvoiced` INTEGER NOT NULL, `synchronized` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `employee_attendance` (`id` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `checkIn` TEXT, `checkOut` TEXT, `totalHours` REAL NOT NULL, `extraHours` REAL NOT NULL, `isExtraApproved` INTEGER NOT NULL, `approvedBy` TEXT, `date` TEXT NOT NULL, `status` TEXT NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `fee_payments` (`id` TEXT NOT NULL, `studentId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `monto` REAL NOT NULL, `concepto` TEXT NOT NULL, `fecha` TEXT NOT NULL, `usuarioRecibe` TEXT NOT NULL, `metodoPago` TEXT NOT NULL, `receiptUrl` TEXT, `sincronizado` INTEGER NOT NULL, `version` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `syncAttempts` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `vacation_requests` (`id` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `startDate` INTEGER NOT NULL, `endDate` INTEGER NOT NULL, `days` INTEGER NOT NULL, `status` TEXT NOT NULL, `observations` TEXT NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `advance_requests` (`id` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `amountRequested` REAL NOT NULL, `reason` TEXT NOT NULL, `status` TEXT NOT NULL, `requestDate` INTEGER NOT NULL, `maxAllowed` REAL NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `payroll_calculations` (`id` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `basicSalary` TEXT NOT NULL, `daysWorked` INTEGER NOT NULL, `transportAllowance` TEXT NOT NULL, `healthDeduction` TEXT NOT NULL, `pensionDeduction` TEXT NOT NULL, `advances` TEXT NOT NULL, `extraHours` TEXT NOT NULL, `totalDevengado` TEXT NOT NULL, `totalDeducciones` TEXT NOT NULL, `netPay` TEXT NOT NULL, `date` INTEGER NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `cash_closings` (`id` TEXT NOT NULL, `date` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `totalCash` REAL NOT NULL, `totalTransfer` REAL NOT NULL, `totalOther` REAL NOT NULL, `totalGeneral` REAL NOT NULL, `closedBy` TEXT NOT NULL, `closingTimestamp` INTEGER NOT NULL, `observations` TEXT NOT NULL, `isSynced` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `institutional_documents` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `type` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `grade` TEXT, `subject` TEXT, `teacherId` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `document_blocks` (`id` TEXT NOT NULL, `documentId` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, `title` TEXT NOT NULL, `contentHtml` TEXT NOT NULL, `updatedAt` INTEGER NOT NULL, `modifiedBy` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `block_history` (`id` TEXT NOT NULL, `blockId` TEXT NOT NULL, `contentHtml` TEXT NOT NULL, `modifiedAt` INTEGER NOT NULL, `modifiedBy` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `invoices` (`id` TEXT NOT NULL, `pagoId` TEXT NOT NULL, `number` TEXT NOT NULL, `studentId` TEXT NOT NULL, `studentName` TEXT NOT NULL, `parentName` TEXT NOT NULL, `parentId` TEXT NOT NULL, `grade` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `date` INTEGER NOT NULL, `dueDate` INTEGER NOT NULL, `status` TEXT NOT NULL, `type` TEXT NOT NULL, `totalAmount` REAL NOT NULL, `paidAmount` REAL NOT NULL, `balance` REAL NOT NULL, `concept` TEXT NOT NULL, `observations` TEXT, `cufe` TEXT, `qrCode` TEXT, `xmlUrl` TEXT, `digitalSignatureUrl` TEXT, `isSynced` INTEGER NOT NULL, `version` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `syncAttempts` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `invoice_items` (`id` TEXT NOT NULL, `invoiceId` TEXT NOT NULL, `categoryId` TEXT NOT NULL, `description` TEXT NOT NULL, `quantity` INTEGER NOT NULL, `unitPrice` REAL NOT NULL, `discount` REAL NOT NULL, `tax` REAL NOT NULL, `total` REAL NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `payment_records` (`id` TEXT NOT NULL, `invoiceId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `amount` REAL NOT NULL, `date` INTEGER NOT NULL, `paymentMethod` TEXT NOT NULL, `reference` TEXT, `registrarId` TEXT NOT NULL, `isSynced` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `grading_scales` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `name` TEXT NOT NULL, `minScore` REAL NOT NULL, `maxScore` REAL NOT NULL, `isDefault` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `scale_ranges` (`id` TEXT NOT NULL, `gradingScaleId` TEXT NOT NULL, `name` TEXT NOT NULL, `minLimit` REAL NOT NULL, `maxLimit` REAL NOT NULL, `description` TEXT, `color` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `grade_categories` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `name` TEXT NOT NULL, `weightPercentage` REAL NOT NULL, `periodId` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `rubrics` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `rubric_criteria` (`id` TEXT NOT NULL, `rubricId` TEXT NOT NULL, `name` TEXT NOT NULL, `weight` REAL NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `criterion_levels` (`id` TEXT NOT NULL, `criterionId` TEXT NOT NULL, `name` TEXT NOT NULL, `score` REAL NOT NULL, `description` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `competencies` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `code` TEXT NOT NULL, `description` TEXT NOT NULL, `area` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `achievement_indicators` (`id` TEXT NOT NULL, `competencyId` TEXT NOT NULL, `rangeId` TEXT NOT NULL, `description` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `rubric_evaluations` (`id` TEXT NOT NULL, `gradeId` TEXT NOT NULL, `rubricId` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `rubric_selections` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `evaluationId` TEXT NOT NULL, `criterionId` TEXT NOT NULL, `levelId` TEXT NOT NULL, `score` REAL NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `cash_transactions` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `type` TEXT NOT NULL, `concept` TEXT NOT NULL, `category` TEXT NOT NULL, `amount` REAL NOT NULL, `paymentMethod` TEXT NOT NULL, `personName` TEXT, `reference` TEXT, `timestamp` INTEGER NOT NULL, `observations` TEXT, `registradoPorId` TEXT NOT NULL, `isSynced` INTEGER NOT NULL, `version` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `syncAttempts` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `politicas_privacidad` (`id` TEXT NOT NULL, `version` INTEGER NOT NULL, `fechaPublicacion` INTEGER NOT NULL, `contenidoHash` TEXT NOT NULL, `contenidoTexto` TEXT NOT NULL, `es_activa` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `consentimientos` (`id` TEXT NOT NULL, `studentId` TEXT NOT NULL, `acudienteNombre` TEXT NOT NULL, `acudienteDni` TEXT NOT NULL, `acudienteParentesco` TEXT NOT NULL, `acudienteEmail` TEXT NOT NULL, `acudienteTelefono` TEXT NOT NULL, `politicaId` TEXT NOT NULL, `fechaAceptacion` INTEGER NOT NULL, `fecha_revocacion` INTEGER, `motivo_revocacion` TEXT, `device_info` TEXT NOT NULL, `hash_firma_digital` TEXT NOT NULL, `granularConsent` TEXT NOT NULL, `version` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `syncAttempts` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`studentId`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`politicaId`) REFERENCES `politicas_privacidad`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_consentimientos_studentId` ON `consentimientos` (`studentId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_consentimientos_politicaId` ON `consentimientos` (`politicaId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `consentimiento_historial` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `consentId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `action` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `details` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `fee_categories` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `basePrice` REAL NOT NULL, `isRecurring` INTEGER NOT NULL, `appliesToGrades` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `achievements` (`id` TEXT NOT NULL, `subjectId` TEXT NOT NULL, `gradeId` TEXT NOT NULL, `period` INTEGER NOT NULL, `description` TEXT NOT NULL, `type` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_grades` (`id` TEXT NOT NULL, `studentId` TEXT NOT NULL, `subjectId` TEXT NOT NULL, `period` INTEGER NOT NULL, `value` REAL NOT NULL, `achievementIds` TEXT NOT NULL, `observations` TEXT, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `discipline_records` (`id` TEXT NOT NULL, `studentId` TEXT NOT NULL, `type` TEXT NOT NULL, `description` TEXT NOT NULL, `date` INTEGER NOT NULL, `teacherId` TEXT NOT NULL, `impactOnGrade` REAL NOT NULL, `parentNotified` INTEGER NOT NULL, `parentAttended` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `study_plans` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `version` TEXT NOT NULL, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `area_plans` (`id` TEXT NOT NULL, `studyPlanId` TEXT NOT NULL, `name` TEXT NOT NULL, `intensity` INTEGER NOT NULL, `subjectIds` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `audit_ledger` (`ledgerIndex` INTEGER NOT NULL, `previousHash` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `data` TEXT NOT NULL, `nonce` TEXT NOT NULL, `hash` TEXT NOT NULL, PRIMARY KEY(`ledgerIndex`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `roles` (`idRol` TEXT NOT NULL, `nombre` TEXT NOT NULL, `nivel` INTEGER NOT NULL, `descripcion` TEXT, `permisosJson` TEXT NOT NULL, `esSistema` INTEGER NOT NULL, `fechaCreacion` INTEGER NOT NULL, `fechaActualizacion` INTEGER NOT NULL, PRIMARY KEY(`idRol`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `permisos` (`idPermiso` TEXT NOT NULL, `nombre` TEXT NOT NULL, `recurso` TEXT NOT NULL, `accion` TEXT NOT NULL, `descripcion` TEXT, PRIMARY KEY(`idPermiso`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `roles_permisos` (`idRol` TEXT NOT NULL, `idPermiso` TEXT NOT NULL, PRIMARY KEY(`idRol`, `idPermiso`), FOREIGN KEY(`idRol`) REFERENCES `roles`(`idRol`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`idPermiso`) REFERENCES `permisos`(`idPermiso`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `perfiles_personal` (`idPerfil` TEXT NOT NULL, `idUsuario` TEXT NOT NULL, `idRol` TEXT NOT NULL, `datosJson` TEXT NOT NULL, `documentoOriginalPath` TEXT, `documentoOriginalHash` TEXT, `estado` TEXT NOT NULL, `fechaCarga` INTEGER NOT NULL, `fechaActualizacion` INTEGER NOT NULL, `version` INTEGER NOT NULL, PRIMARY KEY(`idPerfil`), FOREIGN KEY(`idRol`) REFERENCES `roles`(`idRol`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `historial_cv` (`idHistorial` TEXT NOT NULL, `idPerfil` TEXT NOT NULL, `version` INTEGER NOT NULL, `datosJson` TEXT NOT NULL, `fechaModificacion` INTEGER NOT NULL, `idUsuarioModificador` TEXT NOT NULL, PRIMARY KEY(`idHistorial`), FOREIGN KEY(`idPerfil`) REFERENCES `perfiles_personal`(`idPerfil`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `acudientes` (`idAcudiente` TEXT NOT NULL, `nombreCompleto` TEXT NOT NULL, `tipoDocumento` TEXT NOT NULL, `numeroDocumento` TEXT NOT NULL, `correoElectronico` TEXT NOT NULL, `telefono` TEXT NOT NULL, `whatsapp` TEXT, `direccion` TEXT, `parentesco` TEXT NOT NULL, `estado` INTEGER NOT NULL, `fechaRegistro` INTEGER NOT NULL, `fechaActualizacion` INTEGER NOT NULL, `passwordHash` TEXT, `preferenciasJson` TEXT, PRIMARY KEY(`idAcudiente`))")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_acudientes_numeroDocumento` ON `acudientes` (`numeroDocumento`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_acudientes_correoElectronico` ON `acudientes` (`correoElectronico`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_acudientes_telefono` ON `acudientes` (`telefono`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `estudiantes_acudientes` (`idRelacion` TEXT NOT NULL, `idEstudiante` TEXT NOT NULL, `idAcudiente` TEXT NOT NULL, `esPrincipal` INTEGER NOT NULL, `puedeRetirar` INTEGER NOT NULL, `recibeNotificaciones` INTEGER NOT NULL, PRIMARY KEY(`idEstudiante`, `idAcudiente`), FOREIGN KEY(`idEstudiante`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`idAcudiente`) REFERENCES `acudientes`(`idAcudiente`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `preferencias_notificaciones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `usuarioId` TEXT NOT NULL, `tipoEvento` TEXT NOT NULL, `push` INTEGER NOT NULL, `inApp` INTEGER NOT NULL, `email` INTEGER NOT NULL, `sms` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `notificaciones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `usuarioId` TEXT NOT NULL, `tipo` TEXT NOT NULL, `titulo` TEXT NOT NULL, `mensaje` TEXT NOT NULL, `canal` TEXT NOT NULL, `leida` INTEGER NOT NULL DEFAULT 0, `fecha` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_notificaciones_usuario` ON `notificaciones` (`usuarioId`, `institutionId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_notificaciones_leida` ON `notificaciones` (`leida`, `institutionId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `institutional_notificaciones` (`idNotificacion` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `idEstudiante` INTEGER, `idAcudiente` TEXT, `idUsuarioRemitente` TEXT NOT NULL, `tipoNotificacion` TEXT NOT NULL, `asunto` TEXT NOT NULL, `mensaje` TEXT NOT NULL, `mensajeWhatsapp` TEXT, `mensajeEmail` TEXT, `fechaEnvio` INTEGER NOT NULL, `canales` TEXT NOT NULL, `estadoEnvioEmail` TEXT, `estadoEnvioWhatsapp` TEXT, `estadoEnvioSms` TEXT, `estadoEnvioPush` TEXT, `idRespuesta` TEXT, `prioridad` TEXT NOT NULL, `fechaLecturaAcudiente` INTEGER, `metadata` TEXT, PRIMARY KEY(`idNotificacion`), FOREIGN KEY(`idAcudiente`) REFERENCES `acudientes`(`idAcudiente`) ON UPDATE NO ACTION ON DELETE SET NULL )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `circulares` (`idCircular` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `titulo` TEXT NOT NULL, `contenido` TEXT NOT NULL, `contenidoWhatsapp` TEXT, `contenidoEmail` TEXT, `idUsuarioCreador` TEXT NOT NULL, `fechaCreacion` INTEGER NOT NULL, `fechaProgramacion` INTEGER, `estado` TEXT NOT NULL, `destinatarios` TEXT, `archivosAdjuntos` TEXT, `fechaEnvio` INTEGER, PRIMARY KEY(`idCircular`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `log_notificaciones` (`idLog` TEXT NOT NULL, `idNotificacion` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `canal` TEXT NOT NULL, `fechaIntento` INTEGER NOT NULL, `codigoRespuesta` INTEGER, `mensajeRespuesta` TEXT, `exito` INTEGER NOT NULL, `intentos` INTEGER NOT NULL, PRIMARY KEY(`idLog`), FOREIGN KEY(`idNotificacion`) REFERENCES `institutional_notificaciones`(`idNotificacion`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `importaciones` (`idImportacion` TEXT NOT NULL, `tipo` TEXT NOT NULL, `nombreArchivo` TEXT NOT NULL, `fechaImportacion` INTEGER NOT NULL, `idUsuarioImporto` TEXT NOT NULL, `totalRegistros` INTEGER NOT NULL, `registrosCreados` INTEGER NOT NULL, `registrosActualizados` INTEGER NOT NULL, `errores` INTEGER NOT NULL, `duplicados` INTEGER NOT NULL, `usuariosCreados` INTEGER NOT NULL, `notificacionesEnviadas` INTEGER NOT NULL, `estado` TEXT NOT NULL, `detalleJson` TEXT, PRIMARY KEY(`idImportacion`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `importaciones_detalle` (`idDetalle` TEXT NOT NULL, `idImportacion` TEXT NOT NULL, `fila` INTEGER NOT NULL, `documento` TEXT, `accion` TEXT NOT NULL, `mensaje` TEXT, PRIMARY KEY(`idDetalle`), FOREIGN KEY(`idImportacion`) REFERENCES `importaciones`(`idImportacion`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `logs_backup` (`idLog` TEXT NOT NULL, `fechaInicio` INTEGER NOT NULL, `fechaFin` INTEGER, `estado` TEXT NOT NULL, `tamanioBytes` INTEGER NOT NULL, `rutaArchivo` TEXT, `errorMensaje` TEXT, `esManual` INTEGER NOT NULL, `metadata` TEXT, PRIMARY KEY(`idLog`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `logs_backup_llaves` (`idLog` TEXT NOT NULL, `accion` TEXT NOT NULL, `fecha` INTEGER NOT NULL, `usuarioId` TEXT NOT NULL, `exito` INTEGER NOT NULL, `mensajeError` TEXT, `metadata` TEXT, PRIMARY KEY(`idLog`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `bank_accounts` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `bankName` TEXT NOT NULL, `accountType` TEXT NOT NULL, `accountNumber` TEXT NOT NULL, `holderName` TEXT NOT NULL, `holderDni` TEXT NOT NULL, `notificationEmail` TEXT, `status` TEXT NOT NULL, `createdAt` INTEGER, `updatedAt` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `bank_account_history` (`id` TEXT NOT NULL, `accountId` TEXT NOT NULL, `userId` TEXT NOT NULL, `action` TEXT NOT NULL, `previousData` TEXT, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `autoevaluaciones` (`id` TEXT NOT NULL, `studentId` TEXT NOT NULL, `subjectId` TEXT NOT NULL, `periodId` TEXT NOT NULL, `score` REAL NOT NULL, `registrationDate` INTEGER NOT NULL, `metadata` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`studentId`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `configuracion_promocion` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `maxFailedSubjects` INTEGER NOT NULL, `maxInattendancePercentage` REAL NOT NULL, `minimumPassingScore` REAL NOT NULL, `autoevaluacionWeight` REAL NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_niveles_educativos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT, `orden` INTEGER NOT NULL, `icono` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_grados` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nivelEducativoId` INTEGER NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT, `orden` INTEGER NOT NULL, `esActivo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_periodos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `tipo` TEXT NOT NULL, `fechaInicio` INTEGER NOT NULL, `fechaFin` INTEGER NOT NULL, `duracionMeses` INTEGER NOT NULL, `numeroCortes` INTEGER NOT NULL, `esActivo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `periodo_configuracion` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `periodoAcademicoId` INTEGER NOT NULL, `tipoConcepto` TEXT NOT NULL, `conceptoId` INTEGER NOT NULL, `aplicarCada` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_areas_conocimiento` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_asignaturas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `areaConocimientoId` INTEGER NOT NULL, `nombre` TEXT NOT NULL, `codigo` TEXT, `descripcion` TEXT, `intensidadHoraria` INTEGER NOT NULL, `esElectiva` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_ofertas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `gradoId` INTEGER NOT NULL, `periodoAcademicoId` INTEGER NOT NULL, `nombre` TEXT NOT NULL, `fechaInicio` INTEGER NOT NULL, `fechaFin` INTEGER NOT NULL, `estado` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_detalles_oferta` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `ofertaAcademicaId` INTEGER NOT NULL, `asignaturaId` INTEGER NOT NULL, `docenteId` TEXT, `intensidadHoraria` INTEGER NOT NULL, `aula` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_clases` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `ofertaAcademicaId` INTEGER NOT NULL, `detalleOfertaId` INTEGER NOT NULL, `nombre` TEXT NOT NULL, `horario` TEXT, `capacidadMaxima` INTEGER NOT NULL, `estudiantesInscritos` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_matriculas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `estudianteId` TEXT NOT NULL, `claseId` INTEGER NOT NULL, `fechaMatricula` INTEGER NOT NULL, `estado` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_planes_estudios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT, `version` TEXT NOT NULL, `vigente` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_planes_estudios_detalle` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `planEstudiosId` INTEGER NOT NULL, `gradoId` INTEGER NOT NULL, `asignaturaId` INTEGER NOT NULL, `intensidadHorariaMinima` INTEGER NOT NULL, `esObligatoria` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_planes_aula` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `claseId` INTEGER NOT NULL, `docenteId` TEXT NOT NULL, `competencias` TEXT, `logros` TEXT, `indicadores` TEXT, `recursos` TEXT, `metodologia` TEXT, `evaluacion` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_aulas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `capacidad` INTEGER NOT NULL, `descripcion` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_horarios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `claseId` INTEGER NOT NULL, `diaSemana` INTEGER NOT NULL, `horaInicio` TEXT NOT NULL, `horaFin` TEXT NOT NULL, `aulaId` INTEGER, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_calificaciones` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `estudianteId` TEXT NOT NULL, `claseId` INTEGER NOT NULL, `periodoAcademicoId` INTEGER NOT NULL, `corte` INTEGER NOT NULL, `nota` REAL NOT NULL, `observacion` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `audit_logs` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `userId` TEXT NOT NULL, `userName` TEXT NOT NULL, `userRole` TEXT NOT NULL, `action` TEXT NOT NULL, `entityName` TEXT NOT NULL, `entityId` TEXT NOT NULL, `details` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `docente_sync_configs` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `docenteId` TEXT NOT NULL, `claseId` INTEGER NOT NULL, `type` TEXT NOT NULL, `url` TEXT, `classroomCourseId` TEXT, `classroomCourseWorkId` TEXT, `syncIntervalHours` INTEGER NOT NULL, `lastSyncTimestamp` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `docente_sync_logs` (`id` TEXT NOT NULL, `configId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `result` TEXT NOT NULL, `message` TEXT, `itemsProcessed` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `documentos_institucionales` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `titulo` TEXT NOT NULL, `tipo` TEXT NOT NULL, `rutaArchivo` TEXT NOT NULL, `fechaSubida` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `institutions` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `address` TEXT, `phone` TEXT, `email` TEXT, `website` TEXT, `slogan` TEXT, `logoUri` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER, `isActive` INTEGER NOT NULL DEFAULT 1, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, `planId` INTEGER, `estudiantesActivos` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `institution_settings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `syncUrl` TEXT, `isSyncEnabled` INTEGER NOT NULL, `syncFrequencyHours` INTEGER NOT NULL, `lastSyncTimestamp` INTEGER, `lastSyncStatus` TEXT, `lastSyncMessage` TEXT, `downloadUrl` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `institution_themes` (`institutionId` TEXT NOT NULL, `primaryColor` INTEGER NOT NULL, `secondaryColor` INTEGER NOT NULL, `accentColor` INTEGER NOT NULL, `backgroundColor` INTEGER NOT NULL, `textColor` INTEGER NOT NULL, `isDarkMode` INTEGER NOT NULL, `themeMode` TEXT NOT NULL, `presetName` TEXT, `extractedFromLogo` INTEGER NOT NULL, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`institutionId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `listado_config` (`institutionId` TEXT NOT NULL, `tamanoPapel` TEXT NOT NULL, `incluirLogo` INTEGER NOT NULL, `incluirFirmas` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`institutionId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `planes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT, `limiteEstudiantes` INTEGER NOT NULL, `precioMensual` REAL NOT NULL, `precioAnual` REAL NOT NULL, `incluyeNomina` INTEGER NOT NULL, `incluyeCarnets` INTEGER NOT NULL, `incluyeBI` INTEGER NOT NULL, `incluyeSoportePrioritario` INTEGER NOT NULL, `incluyeAPI` INTEGER NOT NULL, `incluyeGestorDedicado` INTEGER NOT NULL, `incluyeImplementacionGuiada` INTEGER NOT NULL, `capacitaciones` INTEGER NOT NULL, `activo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_planes_nombre` ON `planes` (`nombre`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `suscripciones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `planId` INTEGER NOT NULL, `fechaInicio` INTEGER NOT NULL, `fechaFin` INTEGER, `estado` TEXT NOT NULL, `periodoFacturacion` TEXT NOT NULL, `ultimoPagoFecha` INTEGER, `proximoPagoFecha` INTEGER, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_suscripciones_institution` ON `suscripciones` (`institutionId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `access_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `scannedByUserId` TEXT NOT NULL, `scannedByUserName` TEXT NOT NULL, `accessTime` INTEGER NOT NULL, `tipo` TEXT NOT NULL, `result` TEXT NOT NULL, `reason` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_access_student` ON `access_logs` (`studentId`, `institutionId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_access_time` ON `access_logs` (`accessTime`, `institutionId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `alertas_inasistencia` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `estudianteId` TEXT NOT NULL, `acudienteId` TEXT NOT NULL, `directorCursoId` TEXT, `jefeAreaId` TEXT, `coordinadorId` TEXT, `inasistenciasConsecutivas` INTEGER NOT NULL, `diasSemana` INTEGER NOT NULL, `semanaInicio` INTEGER NOT NULL, `semanaFin` INTEGER NOT NULL, `nivelAlerta` INTEGER NOT NULL, `estado` TEXT NOT NULL, `fechaAlerta` INTEGER NOT NULL, `fechaResolucion` INTEGER, `observaciones` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_alertas_estudiante` ON `alertas_inasistencia` (`estudianteId`, `institutionId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_alertas_estado` ON `alertas_inasistencia` (`estado`, `institutionId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `alertas_tempranas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `tipo` TEXT NOT NULL, `nivel` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `fechaDeteccion` INTEGER NOT NULL, `estado` TEXT NOT NULL, `atendidaPor` TEXT, `fechaAtencion` INTEGER, `observaciones` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `citas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `docenteId` TEXT NOT NULL, `acudienteId` TEXT NOT NULL, `estudianteId` TEXT NOT NULL, `fechaCita` INTEGER NOT NULL, `estado` TEXT NOT NULL, `motivo` TEXT, `observaciones` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `configuracion_alerta` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `inasistenciasConsecutivasParaAlerta` INTEGER NOT NULL, `diasSemanaUmbral` INTEGER NOT NULL, `semanasConsecutivasPatron` INTEGER NOT NULL, `nivelAlertaAcudiente` INTEGER NOT NULL, `nivelAlertaDirector` INTEGER NOT NULL, `nivelAlertaJefeArea` INTEGER NOT NULL, `nivelAlertaCoordinador` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `configuracion_alertas` (`institutionId` TEXT NOT NULL, `umbralInasistenciaConsecutiva` INTEGER NOT NULL, `umbralAsistenciaSemanal` INTEGER NOT NULL, `umbralServiciosExcesivos` INTEGER NOT NULL, `umbralTardanzaMensual` INTEGER NOT NULL, `activo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`institutionId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `retiros_anticipados` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `docenteId` TEXT NOT NULL, `fechaSalida` INTEGER NOT NULL, `motivo` TEXT NOT NULL, `motivoOtro` TEXT, `tipoFirmante` TEXT NOT NULL, `firmanteNombre` TEXT NOT NULL, `firmanteDocumento` TEXT NOT NULL, `firmaDigitalPath` TEXT NOT NULL, `observaciones` TEXT, `notificadoAcudiente` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `seguimiento_inasistencia` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `alertaId` INTEGER NOT NULL, `usuarioId` TEXT NOT NULL, `accion` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `fechaSeguimiento` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_seguimiento_alerta` ON `seguimiento_inasistencia` (`alertaId`, `institutionId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `servicios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT, `tipo` TEXT NOT NULL, `responsable` TEXT, `ubicacion` TEXT, `horario` TEXT, `notificaAcudiente` INTEGER NOT NULL, `activo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `servicio_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `servicioId` INTEGER NOT NULL, `docenteId` TEXT, `fechaHoraSalida` INTEGER NOT NULL, `fechaHoraLlegada` INTEGER, `fechaHoraRegreso` INTEGER, `motivo` TEXT, `estado` TEXT NOT NULL, `notificadoAcudiente` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_serv_logs_student` ON `servicio_logs` (`studentId`, `institutionId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_serv_logs_servicio` ON `servicio_logs` (`servicioId`, `institutionId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, `role` TEXT NOT NULL, `fullName` TEXT NOT NULL, `email` TEXT, `profilePictureUri` TEXT, `fcmToken` TEXT, `isFirstLogin` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `user_approvals` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `userId` TEXT NOT NULL, `status` TEXT NOT NULL, `requestedAt` INTEGER NOT NULL, `approvedAt` INTEGER, `approvedByUserId` TEXT, `rejectedReason` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `convivencia_cases` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `teacherId` TEXT NOT NULL, `createdByUserId` TEXT NOT NULL, `openingDate` INTEGER NOT NULL, `status` TEXT NOT NULL, `description` TEXT NOT NULL, `resolution` TEXT, `resolutionDate` INTEGER, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`studentId`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `case_testimonies` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `caseId` TEXT NOT NULL, `authorName` TEXT NOT NULL, `authorRole` TEXT NOT NULL, `content` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`caseId`) REFERENCES `convivencia_cases`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `behavioral_competencies` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `behavioral_scores` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `competencyId` TEXT NOT NULL, `periodId` TEXT NOT NULL, `scoreType` TEXT NOT NULL, `feedback` TEXT, `evaluationDate` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`studentId`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`competencyId`) REFERENCES `behavioral_competencies`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `family_attendance` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `parentName` TEXT NOT NULL, `citationDate` INTEGER NOT NULL, `attendanceDate` INTEGER, `status` TEXT NOT NULL, `meetingNotes` TEXT, `behavioralImpact` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`studentId`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `amount` REAL NOT NULL, `date` INTEGER NOT NULL, `description` TEXT NOT NULL, `category` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `cashier_facturas` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `numeroFactura` TEXT NOT NULL, `fechaEmision` INTEGER NOT NULL, `fechaVencimiento` INTEGER NOT NULL, `subtotal` REAL NOT NULL, `impuestos` REAL NOT NULL, `total` REAL NOT NULL, `saldoPendiente` REAL NOT NULL, `estado` TEXT NOT NULL, `concepto` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_factura_fecha_vencimiento` ON `cashier_facturas` (`fechaVencimiento`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `payments` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `amount` REAL NOT NULL, `date` INTEGER NOT NULL, `concept` TEXT NOT NULL, `paymentMethod` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `ordenes_pago` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `facturaId` TEXT NOT NULL, `estudianteId` TEXT NOT NULL, `referencia` TEXT NOT NULL, `monto` REAL NOT NULL, `fechaGeneracion` INTEGER NOT NULL, `fechaVencimiento` INTEGER NOT NULL, `estado` TEXT NOT NULL, `metodoPago` TEXT NOT NULL, `datosPago` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `cashier_conceptos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `montoBase` REAL NOT NULL, `descripcion` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `confirmaciones_pago` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `ordenPagoId` INTEGER NOT NULL, `referenciaIngresada` TEXT NOT NULL, `valorIngresado` REAL, `fechaConfirmacion` INTEGER NOT NULL, `estadoValidacion` TEXT NOT NULL, `observacion` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_records` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `year` INTEGER NOT NULL, `grade` TEXT NOT NULL, `gpa` REAL NOT NULL, `status` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `cashier_certificados` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `studentId` TEXT NOT NULL, `tipo` TEXT NOT NULL, `fechaEmision` INTEGER NOT NULL, `numeroSerie` TEXT NOT NULL, `rutaArchivo` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `managed_documents` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `type` TEXT NOT NULL, `content` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `salaries` (`id` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `amount` REAL NOT NULL, `concept` TEXT NOT NULL, `paymentDate` INTEGER NOT NULL, `periodMonth` INTEGER NOT NULL, `periodYear` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `employee_docentes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `especialidad` TEXT, `tipoContrato` TEXT, `fechaIngreso` INTEGER NOT NULL, `estado` TEXT NOT NULL, `sedePrincipalId` INTEGER, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, FOREIGN KEY(`userId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `employee_docente_cursos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `docenteId` INTEGER NOT NULL, `cursoId` INTEGER NOT NULL, `asignaturaId` INTEGER NOT NULL, `cargaHorariaSemanal` INTEGER NOT NULL, `esDirectorGrupo` INTEGER NOT NULL, `activo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, FOREIGN KEY(`docenteId`) REFERENCES `employee_docentes`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`cursoId`) REFERENCES `academic_cursos`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`asignaturaId`) REFERENCES `academic_asignaturas`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `horarios_atencion` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `docenteId` TEXT NOT NULL, `diaSemana` INTEGER NOT NULL, `horaInicio` TEXT NOT NULL, `horaFin` TEXT NOT NULL, `activo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `payroll_nominas` (`id` TEXT NOT NULL, `employeeId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `fechaEmision` INTEGER NOT NULL, `periodoInicio` INTEGER NOT NULL, `periodoFin` INTEGER NOT NULL, `salarioBase` REAL NOT NULL, `bonificaciones` REAL NOT NULL, `deducciones` REAL NOT NULL, `totalNeto` REAL NOT NULL, `estado` TEXT NOT NULL, `metodoPago` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `library_libros` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `isbn` TEXT, `titulo` TEXT NOT NULL, `autor` TEXT NOT NULL, `editorial` TEXT, `anioPublicacion` INTEGER, `categoria` TEXT, `descripcion` TEXT, `ejemplaresTotales` INTEGER NOT NULL, `ejemplaresDisponibles` INTEGER NOT NULL, `ubicacionFisica` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `library_prestamos` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `libroId` TEXT NOT NULL, `estudianteId` TEXT, `docenteId` TEXT, `fechaPrestamo` INTEGER NOT NULL, `fechaDevolucionPrevista` INTEGER NOT NULL, `fechaDevolucionReal` INTEGER, `estado` TEXT NOT NULL, `observaciones` TEXT, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `schedules` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `classroomId` TEXT NOT NULL, `subjectId` TEXT NOT NULL, `teacherId` TEXT NOT NULL, `dayOfWeek` INTEGER NOT NULL, `startTime` TEXT NOT NULL, `endTime` TEXT NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `classrooms` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `name` TEXT NOT NULL, `capacity` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `firmas_usuarios` (`userId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `firmaPath` TEXT NOT NULL, `fechaGuardado` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`userId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `programs` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `codigo` TEXT NOT NULL DEFAULT '', `name` TEXT NOT NULL, `description` TEXT, `nivelEducativoId` INTEGER, `gradoId` INTEGER, `activo` INTEGER NOT NULL DEFAULT 1, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `risk_analysis` (`studentId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `riskLevel` TEXT NOT NULL, `riskScore` REAL NOT NULL, `factors` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, PRIMARY KEY(`studentId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `risk_summary` (`institutionId` TEXT NOT NULL, `totalStudents` INTEGER NOT NULL, `critical` INTEGER NOT NULL, `high` INTEGER NOT NULL, `medium` INTEGER NOT NULL, `low` INTEGER NOT NULL, `averageRisk` REAL NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, PRIMARY KEY(`institutionId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `student_programs` (`studentId` TEXT NOT NULL, `programId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `enrollmentDate` INTEGER NOT NULL, `status` TEXT NOT NULL, PRIMARY KEY(`studentId`, `programId`), FOREIGN KEY(`studentId`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`programId`) REFERENCES `programs`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `parents_guardians` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `userId` TEXT, `fullName` TEXT NOT NULL, `documentId` TEXT NOT NULL, `phoneNumber` TEXT NOT NULL, `email` TEXT, `relationToStudent` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `tareas` (`id` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `claseId` INTEGER NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `deadline` INTEGER NOT NULL, `createdBy` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`claseId`) REFERENCES `academic_clases`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_tareas_institutionId` ON `tareas` (`institutionId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_tareas_claseId` ON `tareas` (`claseId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_tareas_createdBy` ON `tareas` (`createdBy`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `task_submissions` (`id` TEXT NOT NULL, `tareaId` TEXT NOT NULL, `estudianteId` TEXT NOT NULL, `status` TEXT NOT NULL, `submissionDate` INTEGER NOT NULL, `comment` TEXT, `grade` REAL, `feedback` TEXT, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`tareaId`) REFERENCES `tareas`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`estudianteId`) REFERENCES `students`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_task_submissions_tareaId` ON `task_submissions` (`tareaId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_task_submissions_estudianteId` ON `task_submissions` (`estudianteId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `task_attachments` (`id` TEXT NOT NULL, `parentId` TEXT NOT NULL, `fileName` TEXT NOT NULL, `fileUrl` TEXT NOT NULL, `fileType` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_task_attachments_parentId` ON `task_attachments` (`parentId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_sedes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `direccion` TEXT, `telefono` TEXT, `activa` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_jornadas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `nombre` TEXT NOT NULL, `horaInicio` TEXT, `horaFin` TEXT, `activa` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `academic_cursos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `gradoId` INTEGER NOT NULL, `sedeId` INTEGER NOT NULL, `jornadaId` INTEGER NOT NULL, `nombre` TEXT NOT NULL, `codigo` TEXT, `capacidad` INTEGER NOT NULL, `estudiantesInscritos` INTEGER NOT NULL, `activo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `notificaciones_calificaciones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `calificacionId` TEXT NOT NULL, `institutionId` TEXT NOT NULL, `enviadoEstudiante` INTEGER NOT NULL, `enviadoAcudiente` INTEGER NOT NULL, `enviadoDocente` INTEGER NOT NULL, `enviadoCoordinador` INTEGER NOT NULL, `fechaEnvioEstudiante` INTEGER, `fechaEnvioAcudiente` INTEGER, `fechaEnvioDocente` INTEGER, `fechaEnvioCoordinador` INTEGER, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `programa_mapping` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `codigoFormulario` TEXT NOT NULL, `ofertaAcademicaId` INTEGER NOT NULL, `gradoId` INTEGER NOT NULL, `activo` INTEGER NOT NULL, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `programa_oferta_mapping` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `institutionId` TEXT NOT NULL, `codigoFormulario` TEXT NOT NULL, `ofertaAcademicaId` INTEGER NOT NULL, `gradoId` INTEGER NOT NULL, `activo` INTEGER NOT NULL DEFAULT 1, `syncStatus` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `idx_prog_mapping_codigo` ON `programa_oferta_mapping` (`codigoFormulario`, `institutionId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f2d85ae8ff423d3ccb0f97f887482c94')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `students`")
        connection.execSQL("DROP TABLE IF EXISTS `attendance`")
        connection.execSQL("DROP TABLE IF EXISTS `grades`")
        connection.execSQL("DROP TABLE IF EXISTS `classes`")
        connection.execSQL("DROP TABLE IF EXISTS `employees`")
        connection.execSQL("DROP TABLE IF EXISTS `salary_records`")
        connection.execSQL("DROP TABLE IF EXISTS `tasks`")
        connection.execSQL("DROP TABLE IF EXISTS `exams`")
        connection.execSQL("DROP TABLE IF EXISTS `announcements`")
        connection.execSQL("DROP TABLE IF EXISTS `puc_accounts`")
        connection.execSQL("DROP TABLE IF EXISTS `accounting_entries`")
        connection.execSQL("DROP TABLE IF EXISTS `employee_attendance`")
        connection.execSQL("DROP TABLE IF EXISTS `fee_payments`")
        connection.execSQL("DROP TABLE IF EXISTS `vacation_requests`")
        connection.execSQL("DROP TABLE IF EXISTS `advance_requests`")
        connection.execSQL("DROP TABLE IF EXISTS `payroll_calculations`")
        connection.execSQL("DROP TABLE IF EXISTS `cash_closings`")
        connection.execSQL("DROP TABLE IF EXISTS `institutional_documents`")
        connection.execSQL("DROP TABLE IF EXISTS `document_blocks`")
        connection.execSQL("DROP TABLE IF EXISTS `block_history`")
        connection.execSQL("DROP TABLE IF EXISTS `invoices`")
        connection.execSQL("DROP TABLE IF EXISTS `invoice_items`")
        connection.execSQL("DROP TABLE IF EXISTS `payment_records`")
        connection.execSQL("DROP TABLE IF EXISTS `grading_scales`")
        connection.execSQL("DROP TABLE IF EXISTS `scale_ranges`")
        connection.execSQL("DROP TABLE IF EXISTS `grade_categories`")
        connection.execSQL("DROP TABLE IF EXISTS `rubrics`")
        connection.execSQL("DROP TABLE IF EXISTS `rubric_criteria`")
        connection.execSQL("DROP TABLE IF EXISTS `criterion_levels`")
        connection.execSQL("DROP TABLE IF EXISTS `competencies`")
        connection.execSQL("DROP TABLE IF EXISTS `achievement_indicators`")
        connection.execSQL("DROP TABLE IF EXISTS `rubric_evaluations`")
        connection.execSQL("DROP TABLE IF EXISTS `rubric_selections`")
        connection.execSQL("DROP TABLE IF EXISTS `cash_transactions`")
        connection.execSQL("DROP TABLE IF EXISTS `politicas_privacidad`")
        connection.execSQL("DROP TABLE IF EXISTS `consentimientos`")
        connection.execSQL("DROP TABLE IF EXISTS `consentimiento_historial`")
        connection.execSQL("DROP TABLE IF EXISTS `fee_categories`")
        connection.execSQL("DROP TABLE IF EXISTS `achievements`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_grades`")
        connection.execSQL("DROP TABLE IF EXISTS `discipline_records`")
        connection.execSQL("DROP TABLE IF EXISTS `study_plans`")
        connection.execSQL("DROP TABLE IF EXISTS `area_plans`")
        connection.execSQL("DROP TABLE IF EXISTS `audit_ledger`")
        connection.execSQL("DROP TABLE IF EXISTS `roles`")
        connection.execSQL("DROP TABLE IF EXISTS `permisos`")
        connection.execSQL("DROP TABLE IF EXISTS `roles_permisos`")
        connection.execSQL("DROP TABLE IF EXISTS `perfiles_personal`")
        connection.execSQL("DROP TABLE IF EXISTS `historial_cv`")
        connection.execSQL("DROP TABLE IF EXISTS `acudientes`")
        connection.execSQL("DROP TABLE IF EXISTS `estudiantes_acudientes`")
        connection.execSQL("DROP TABLE IF EXISTS `preferencias_notificaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `notificaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `institutional_notificaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `circulares`")
        connection.execSQL("DROP TABLE IF EXISTS `log_notificaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `importaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `importaciones_detalle`")
        connection.execSQL("DROP TABLE IF EXISTS `logs_backup`")
        connection.execSQL("DROP TABLE IF EXISTS `logs_backup_llaves`")
        connection.execSQL("DROP TABLE IF EXISTS `bank_accounts`")
        connection.execSQL("DROP TABLE IF EXISTS `bank_account_history`")
        connection.execSQL("DROP TABLE IF EXISTS `autoevaluaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `configuracion_promocion`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_niveles_educativos`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_grados`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_periodos`")
        connection.execSQL("DROP TABLE IF EXISTS `periodo_configuracion`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_areas_conocimiento`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_asignaturas`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_ofertas`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_detalles_oferta`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_clases`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_matriculas`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_planes_estudios`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_planes_estudios_detalle`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_planes_aula`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_aulas`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_horarios`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_calificaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `audit_logs`")
        connection.execSQL("DROP TABLE IF EXISTS `docente_sync_configs`")
        connection.execSQL("DROP TABLE IF EXISTS `docente_sync_logs`")
        connection.execSQL("DROP TABLE IF EXISTS `documentos_institucionales`")
        connection.execSQL("DROP TABLE IF EXISTS `institutions`")
        connection.execSQL("DROP TABLE IF EXISTS `institution_settings`")
        connection.execSQL("DROP TABLE IF EXISTS `institution_themes`")
        connection.execSQL("DROP TABLE IF EXISTS `listado_config`")
        connection.execSQL("DROP TABLE IF EXISTS `planes`")
        connection.execSQL("DROP TABLE IF EXISTS `suscripciones`")
        connection.execSQL("DROP TABLE IF EXISTS `access_logs`")
        connection.execSQL("DROP TABLE IF EXISTS `alertas_inasistencia`")
        connection.execSQL("DROP TABLE IF EXISTS `alertas_tempranas`")
        connection.execSQL("DROP TABLE IF EXISTS `citas`")
        connection.execSQL("DROP TABLE IF EXISTS `configuracion_alerta`")
        connection.execSQL("DROP TABLE IF EXISTS `configuracion_alertas`")
        connection.execSQL("DROP TABLE IF EXISTS `retiros_anticipados`")
        connection.execSQL("DROP TABLE IF EXISTS `seguimiento_inasistencia`")
        connection.execSQL("DROP TABLE IF EXISTS `servicios`")
        connection.execSQL("DROP TABLE IF EXISTS `servicio_logs`")
        connection.execSQL("DROP TABLE IF EXISTS `users`")
        connection.execSQL("DROP TABLE IF EXISTS `user_approvals`")
        connection.execSQL("DROP TABLE IF EXISTS `convivencia_cases`")
        connection.execSQL("DROP TABLE IF EXISTS `case_testimonies`")
        connection.execSQL("DROP TABLE IF EXISTS `behavioral_competencies`")
        connection.execSQL("DROP TABLE IF EXISTS `behavioral_scores`")
        connection.execSQL("DROP TABLE IF EXISTS `family_attendance`")
        connection.execSQL("DROP TABLE IF EXISTS `expenses`")
        connection.execSQL("DROP TABLE IF EXISTS `cashier_facturas`")
        connection.execSQL("DROP TABLE IF EXISTS `payments`")
        connection.execSQL("DROP TABLE IF EXISTS `ordenes_pago`")
        connection.execSQL("DROP TABLE IF EXISTS `cashier_conceptos`")
        connection.execSQL("DROP TABLE IF EXISTS `confirmaciones_pago`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_records`")
        connection.execSQL("DROP TABLE IF EXISTS `cashier_certificados`")
        connection.execSQL("DROP TABLE IF EXISTS `managed_documents`")
        connection.execSQL("DROP TABLE IF EXISTS `salaries`")
        connection.execSQL("DROP TABLE IF EXISTS `employee_docentes`")
        connection.execSQL("DROP TABLE IF EXISTS `employee_docente_cursos`")
        connection.execSQL("DROP TABLE IF EXISTS `horarios_atencion`")
        connection.execSQL("DROP TABLE IF EXISTS `payroll_nominas`")
        connection.execSQL("DROP TABLE IF EXISTS `library_libros`")
        connection.execSQL("DROP TABLE IF EXISTS `library_prestamos`")
        connection.execSQL("DROP TABLE IF EXISTS `schedules`")
        connection.execSQL("DROP TABLE IF EXISTS `classrooms`")
        connection.execSQL("DROP TABLE IF EXISTS `firmas_usuarios`")
        connection.execSQL("DROP TABLE IF EXISTS `programs`")
        connection.execSQL("DROP TABLE IF EXISTS `risk_analysis`")
        connection.execSQL("DROP TABLE IF EXISTS `risk_summary`")
        connection.execSQL("DROP TABLE IF EXISTS `student_programs`")
        connection.execSQL("DROP TABLE IF EXISTS `parents_guardians`")
        connection.execSQL("DROP TABLE IF EXISTS `tareas`")
        connection.execSQL("DROP TABLE IF EXISTS `task_submissions`")
        connection.execSQL("DROP TABLE IF EXISTS `task_attachments`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_sedes`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_jornadas`")
        connection.execSQL("DROP TABLE IF EXISTS `academic_cursos`")
        connection.execSQL("DROP TABLE IF EXISTS `notificaciones_calificaciones`")
        connection.execSQL("DROP TABLE IF EXISTS `programa_mapping`")
        connection.execSQL("DROP TABLE IF EXISTS `programa_oferta_mapping`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsStudents: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsStudents.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("userId", TableInfo.Column("userId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("firstName", TableInfo.Column("firstName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("lastName", TableInfo.Column("lastName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("documentId", TableInfo.Column("documentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("gender", TableInfo.Column("gender", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("ethnicity", TableInfo.Column("ethnicity", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("ethnicCommunity", TableInfo.Column("ethnicCommunity", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("disability", TableInfo.Column("disability", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("disabilityAdjustments", TableInfo.Column("disabilityAdjustments",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("photoPath", TableInfo.Column("photoPath", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("qrCode", TableInfo.Column("qrCode", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("cursoId", TableInfo.Column("cursoId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("consentAcceptedAt", TableInfo.Column("consentAcceptedAt", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("consentVersion", TableInfo.Column("consentVersion", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("isDuplicate", TableInfo.Column("isDuplicate", "INTEGER", true, 0, "0",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("mergedIntoId", TableInfo.Column("mergedIntoId", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("deletedAt", TableInfo.Column("deletedAt", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("deletedReason", TableInfo.Column("deletedReason", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("deletedByUserId", TableInfo.Column("deletedByUserId", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("documentType", TableInfo.Column("documentType", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("birthDate", TableInfo.Column("birthDate", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("age", TableInfo.Column("age", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("email", TableInfo.Column("email", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("phone", TableInfo.Column("phone", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("address", TableInfo.Column("address", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("neighborhood", TableInfo.Column("neighborhood", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("stratum", TableInfo.Column("stratum", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("educationLevel", TableInfo.Column("educationLevel", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("previousSchool", TableInfo.Column("previousSchool", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("selectedPrograms", TableInfo.Column("selectedPrograms", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("howDidYouHear", TableInfo.Column("howDidYouHear", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("status", TableInfo.Column("status", "TEXT", true, 0, "'ENROLLED'",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("withdrawalReason", TableInfo.Column("withdrawalReason", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("withdrawalDate", TableInfo.Column("withdrawalDate", "INTEGER", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("statusUpdatedAt", TableInfo.Column("statusUpdatedAt", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("photoUpdatedAt", TableInfo.Column("photoUpdatedAt", "INTEGER", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("estadoMatricula", TableInfo.Column("estadoMatricula", "TEXT", true, 0,
            "'MATRICULADO'", TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("fechaRetiro", TableInfo.Column("fechaRetiro", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("motivoRetiro", TableInfo.Column("motivoRetiro", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("diasInasistenciaConsecutiva",
            TableInfo.Column("diasInasistenciaConsecutiva", "INTEGER", true, 0, "0",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("ultimaFechaAsistencia", TableInfo.Column("ultimaFechaAsistencia",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("alertaEnviada30Dias", TableInfo.Column("alertaEnviada30Dias",
            "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("guardianFirstName", TableInfo.Column("guardianFirstName", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("guardianLastName", TableInfo.Column("guardianLastName", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("guardianDocumentId", TableInfo.Column("guardianDocumentId", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("guardianRelationship", TableInfo.Column("guardianRelationship",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("guardianPhone", TableInfo.Column("guardianPhone", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("guardianEmail", TableInfo.Column("guardianEmail", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("esExterno", TableInfo.Column("esExterno", "INTEGER", true, 0, "0",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("institucionOrigen", TableInfo.Column("institucionOrigen", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudents.put("externoId", TableInfo.Column("externoId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysStudents: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesStudents: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoStudents: TableInfo = TableInfo("students", _columnsStudents, _foreignKeysStudents,
            _indicesStudents)
        val _existingStudents: TableInfo = read(connection, "students")
        if (!_infoStudents.equals(_existingStudents)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |students(com.sigeschool.data.local.entity.StudentEntity).
              | Expected:
              |""".trimMargin() + _infoStudents + """
              |
              | Found:
              |""".trimMargin() + _existingStudents)
        }
        val _columnsAttendance: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAttendance.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("claseId", TableInfo.Column("claseId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("observacion", TableInfo.Column("observacion", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("justificacionUrl", TableInfo.Column("justificacionUrl", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAttendance.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAttendance: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAttendance: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAttendance: TableInfo = TableInfo("attendance", _columnsAttendance,
            _foreignKeysAttendance, _indicesAttendance)
        val _existingAttendance: TableInfo = read(connection, "attendance")
        if (!_infoAttendance.equals(_existingAttendance)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |attendance(com.sigeschool.data.local.entity.AttendanceEntity).
              | Expected:
              |""".trimMargin() + _infoAttendance + """
              |
              | Found:
              |""".trimMargin() + _existingAttendance)
        }
        val _columnsGrades: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGrades.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("courseId", TableInfo.Column("courseId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("subjectId", TableInfo.Column("subjectId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("score", TableInfo.Column("score", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGrades.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGrades: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesGrades: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoGrades: TableInfo = TableInfo("grades", _columnsGrades, _foreignKeysGrades,
            _indicesGrades)
        val _existingGrades: TableInfo = read(connection, "grades")
        if (!_infoGrades.equals(_existingGrades)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |grades(com.sigeschool.data.local.entity.GradeEntity).
              | Expected:
              |""".trimMargin() + _infoGrades + """
              |
              | Found:
              |""".trimMargin() + _existingGrades)
        }
        val _columnsClasses: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsClasses.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClasses.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClasses.put("level", TableInfo.Column("level", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClasses.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsClasses.put("teacherId", TableInfo.Column("teacherId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClasses.put("createdAt", TableInfo.Column("createdAt", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysClasses: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesClasses: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoClasses: TableInfo = TableInfo("classes", _columnsClasses, _foreignKeysClasses,
            _indicesClasses)
        val _existingClasses: TableInfo = read(connection, "classes")
        if (!_infoClasses.equals(_existingClasses)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |classes(com.sigeschool.data.local.entity.ClassEntity).
              | Expected:
              |""".trimMargin() + _infoClasses + """
              |
              | Found:
              |""".trimMargin() + _existingClasses)
        }
        val _columnsEmployees: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEmployees.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("authUserId", TableInfo.Column("authUserId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("firstName", TableInfo.Column("firstName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("lastName", TableInfo.Column("lastName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("role", TableInfo.Column("role", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployees.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEmployees: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesEmployees: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoEmployees: TableInfo = TableInfo("employees", _columnsEmployees,
            _foreignKeysEmployees, _indicesEmployees)
        val _existingEmployees: TableInfo = read(connection, "employees")
        if (!_infoEmployees.equals(_existingEmployees)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |employees(com.sigeschool.data.local.entity.EmployeeEntity).
              | Expected:
              |""".trimMargin() + _infoEmployees + """
              |
              | Found:
              |""".trimMargin() + _existingEmployees)
        }
        val _columnsSalaryRecords: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSalaryRecords.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("employeeId", TableInfo.Column("employeeId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("amount", TableInfo.Column("amount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("observation", TableInfo.Column("observation", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaryRecords.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSalaryRecords: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSalaryRecords: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSalaryRecords: TableInfo = TableInfo("salary_records", _columnsSalaryRecords,
            _foreignKeysSalaryRecords, _indicesSalaryRecords)
        val _existingSalaryRecords: TableInfo = read(connection, "salary_records")
        if (!_infoSalaryRecords.equals(_existingSalaryRecords)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |salary_records(com.sigeschool.data.local.entity.SalaryRecordEntity).
              | Expected:
              |""".trimMargin() + _infoSalaryRecords + """
              |
              | Found:
              |""".trimMargin() + _existingSalaryRecords)
        }
        val _columnsTasks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTasks.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTasks.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTasks.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTasks.put("dueDate", TableInfo.Column("dueDate", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTasks.put("classId", TableInfo.Column("classId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTasks.put("subjectId", TableInfo.Column("subjectId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTasks.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTasks.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTasks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesTasks: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoTasks: TableInfo = TableInfo("tasks", _columnsTasks, _foreignKeysTasks,
            _indicesTasks)
        val _existingTasks: TableInfo = read(connection, "tasks")
        if (!_infoTasks.equals(_existingTasks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |tasks(com.sigeschool.data.local.entity.TaskEntity).
              | Expected:
              |""".trimMargin() + _infoTasks + """
              |
              | Found:
              |""".trimMargin() + _existingTasks)
        }
        val _columnsExams: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExams.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("classId", TableInfo.Column("classId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("subjectId", TableInfo.Column("subjectId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("maxScore", TableInfo.Column("maxScore", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("durationMinutes", TableInfo.Column("durationMinutes", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("questions", TableInfo.Column("questions", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExams.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExams: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesExams: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExams: TableInfo = TableInfo("exams", _columnsExams, _foreignKeysExams,
            _indicesExams)
        val _existingExams: TableInfo = read(connection, "exams")
        if (!_infoExams.equals(_existingExams)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |exams(com.sigeschool.data.local.entity.ExamEntity).
              | Expected:
              |""".trimMargin() + _infoExams + """
              |
              | Found:
              |""".trimMargin() + _existingExams)
        }
        val _columnsAnnouncements: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAnnouncements.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAnnouncements.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAnnouncements.put("content", TableInfo.Column("content", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAnnouncements.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAnnouncements.put("authorId", TableInfo.Column("authorId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAnnouncements.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAnnouncements.put("target", TableInfo.Column("target", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAnnouncements: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAnnouncements: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAnnouncements: TableInfo = TableInfo("announcements", _columnsAnnouncements,
            _foreignKeysAnnouncements, _indicesAnnouncements)
        val _existingAnnouncements: TableInfo = read(connection, "announcements")
        if (!_infoAnnouncements.equals(_existingAnnouncements)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |announcements(com.sigeschool.data.local.entity.AnnouncementEntity).
              | Expected:
              |""".trimMargin() + _infoAnnouncements + """
              |
              | Found:
              |""".trimMargin() + _existingAnnouncements)
        }
        val _columnsPucAccounts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPucAccounts.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("code", TableInfo.Column("code", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("level", TableInfo.Column("level", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("parentCode", TableInfo.Column("parentCode", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("accountType", TableInfo.Column("accountType", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("isCustom", TableInfo.Column("isCustom", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPucAccounts.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPucAccounts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPucAccounts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPucAccounts: TableInfo = TableInfo("puc_accounts", _columnsPucAccounts,
            _foreignKeysPucAccounts, _indicesPucAccounts)
        val _existingPucAccounts: TableInfo = read(connection, "puc_accounts")
        if (!_infoPucAccounts.equals(_existingPucAccounts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |puc_accounts(com.sigeschool.data.local.entity.PucAccountEntity).
              | Expected:
              |""".trimMargin() + _infoPucAccounts + """
              |
              | Found:
              |""".trimMargin() + _existingPucAccounts)
        }
        val _columnsAccountingEntries: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAccountingEntries.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("date", TableInfo.Column("date", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("description", TableInfo.Column("description", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("centerId", TableInfo.Column("centerId", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("entriesJson", TableInfo.Column("entriesJson", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("totalDebit", TableInfo.Column("totalDebit", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("totalCredit", TableInfo.Column("totalCredit", "REAL", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("isElectronicInvoiced",
            TableInfo.Column("isElectronicInvoiced", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountingEntries.put("synchronized", TableInfo.Column("synchronized", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAccountingEntries: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAccountingEntries: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAccountingEntries: TableInfo = TableInfo("accounting_entries",
            _columnsAccountingEntries, _foreignKeysAccountingEntries, _indicesAccountingEntries)
        val _existingAccountingEntries: TableInfo = read(connection, "accounting_entries")
        if (!_infoAccountingEntries.equals(_existingAccountingEntries)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |accounting_entries(com.sigeschool.data.local.entity.AccountingEntryEntity).
              | Expected:
              |""".trimMargin() + _infoAccountingEntries + """
              |
              | Found:
              |""".trimMargin() + _existingAccountingEntries)
        }
        val _columnsEmployeeAttendance: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEmployeeAttendance.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("employeeId", TableInfo.Column("employeeId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("checkIn", TableInfo.Column("checkIn", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("checkOut", TableInfo.Column("checkOut", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("totalHours", TableInfo.Column("totalHours", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("extraHours", TableInfo.Column("extraHours", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("isExtraApproved", TableInfo.Column("isExtraApproved",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("approvedBy", TableInfo.Column("approvedBy", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("date", TableInfo.Column("date", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeAttendance.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEmployeeAttendance: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesEmployeeAttendance: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoEmployeeAttendance: TableInfo = TableInfo("employee_attendance",
            _columnsEmployeeAttendance, _foreignKeysEmployeeAttendance, _indicesEmployeeAttendance)
        val _existingEmployeeAttendance: TableInfo = read(connection, "employee_attendance")
        if (!_infoEmployeeAttendance.equals(_existingEmployeeAttendance)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |employee_attendance(com.sigeschool.data.local.entity.EmployeeAttendanceEntity).
              | Expected:
              |""".trimMargin() + _infoEmployeeAttendance + """
              |
              | Found:
              |""".trimMargin() + _existingEmployeeAttendance)
        }
        val _columnsFeePayments: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFeePayments.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("monto", TableInfo.Column("monto", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("concepto", TableInfo.Column("concepto", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("fecha", TableInfo.Column("fecha", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("usuarioRecibe", TableInfo.Column("usuarioRecibe", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("metodoPago", TableInfo.Column("metodoPago", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("receiptUrl", TableInfo.Column("receiptUrl", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("version", TableInfo.Column("version", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("deviceId", TableInfo.Column("deviceId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFeePayments.put("syncAttempts", TableInfo.Column("syncAttempts", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFeePayments: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesFeePayments: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoFeePayments: TableInfo = TableInfo("fee_payments", _columnsFeePayments,
            _foreignKeysFeePayments, _indicesFeePayments)
        val _existingFeePayments: TableInfo = read(connection, "fee_payments")
        if (!_infoFeePayments.equals(_existingFeePayments)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |fee_payments(com.sigeschool.data.local.entity.FeePaymentEntity).
              | Expected:
              |""".trimMargin() + _infoFeePayments + """
              |
              | Found:
              |""".trimMargin() + _existingFeePayments)
        }
        val _columnsVacationRequests: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsVacationRequests.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsVacationRequests.put("employeeId", TableInfo.Column("employeeId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVacationRequests.put("startDate", TableInfo.Column("startDate", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVacationRequests.put("endDate", TableInfo.Column("endDate", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVacationRequests.put("days", TableInfo.Column("days", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsVacationRequests.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsVacationRequests.put("observations", TableInfo.Column("observations", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVacationRequests.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysVacationRequests: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesVacationRequests: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoVacationRequests: TableInfo = TableInfo("vacation_requests",
            _columnsVacationRequests, _foreignKeysVacationRequests, _indicesVacationRequests)
        val _existingVacationRequests: TableInfo = read(connection, "vacation_requests")
        if (!_infoVacationRequests.equals(_existingVacationRequests)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |vacation_requests(com.sigeschool.data.local.entity.VacationRequestEntity).
              | Expected:
              |""".trimMargin() + _infoVacationRequests + """
              |
              | Found:
              |""".trimMargin() + _existingVacationRequests)
        }
        val _columnsAdvanceRequests: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAdvanceRequests.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAdvanceRequests.put("employeeId", TableInfo.Column("employeeId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAdvanceRequests.put("amountRequested", TableInfo.Column("amountRequested", "REAL",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAdvanceRequests.put("reason", TableInfo.Column("reason", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAdvanceRequests.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAdvanceRequests.put("requestDate", TableInfo.Column("requestDate", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAdvanceRequests.put("maxAllowed", TableInfo.Column("maxAllowed", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAdvanceRequests.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAdvanceRequests: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAdvanceRequests: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAdvanceRequests: TableInfo = TableInfo("advance_requests", _columnsAdvanceRequests,
            _foreignKeysAdvanceRequests, _indicesAdvanceRequests)
        val _existingAdvanceRequests: TableInfo = read(connection, "advance_requests")
        if (!_infoAdvanceRequests.equals(_existingAdvanceRequests)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |advance_requests(com.sigeschool.data.local.entity.AdvanceRequestEntity).
              | Expected:
              |""".trimMargin() + _infoAdvanceRequests + """
              |
              | Found:
              |""".trimMargin() + _existingAdvanceRequests)
        }
        val _columnsPayrollCalculations: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPayrollCalculations.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("employeeId", TableInfo.Column("employeeId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("basicSalary", TableInfo.Column("basicSalary", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("daysWorked", TableInfo.Column("daysWorked", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("transportAllowance", TableInfo.Column("transportAllowance",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("healthDeduction", TableInfo.Column("healthDeduction",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("pensionDeduction", TableInfo.Column("pensionDeduction",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("advances", TableInfo.Column("advances", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("extraHours", TableInfo.Column("extraHours", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("totalDevengado", TableInfo.Column("totalDevengado", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("totalDeducciones", TableInfo.Column("totalDeducciones",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("netPay", TableInfo.Column("netPay", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollCalculations.put("sincronizado", TableInfo.Column("sincronizado", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPayrollCalculations: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPayrollCalculations: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPayrollCalculations: TableInfo = TableInfo("payroll_calculations",
            _columnsPayrollCalculations, _foreignKeysPayrollCalculations,
            _indicesPayrollCalculations)
        val _existingPayrollCalculations: TableInfo = read(connection, "payroll_calculations")
        if (!_infoPayrollCalculations.equals(_existingPayrollCalculations)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |payroll_calculations(com.sigeschool.data.local.entity.PayrollCalculationEntity).
              | Expected:
              |""".trimMargin() + _infoPayrollCalculations + """
              |
              | Found:
              |""".trimMargin() + _existingPayrollCalculations)
        }
        val _columnsCashClosings: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCashClosings.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("date", TableInfo.Column("date", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("totalCash", TableInfo.Column("totalCash", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("totalTransfer", TableInfo.Column("totalTransfer", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("totalOther", TableInfo.Column("totalOther", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("totalGeneral", TableInfo.Column("totalGeneral", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("closedBy", TableInfo.Column("closedBy", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("closingTimestamp", TableInfo.Column("closingTimestamp", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("observations", TableInfo.Column("observations", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashClosings.put("isSynced", TableInfo.Column("isSynced", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCashClosings: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCashClosings: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCashClosings: TableInfo = TableInfo("cash_closings", _columnsCashClosings,
            _foreignKeysCashClosings, _indicesCashClosings)
        val _existingCashClosings: TableInfo = read(connection, "cash_closings")
        if (!_infoCashClosings.equals(_existingCashClosings)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |cash_closings(com.sigeschool.data.local.entity.CashClosingEntity).
              | Expected:
              |""".trimMargin() + _infoCashClosings + """
              |
              | Found:
              |""".trimMargin() + _existingCashClosings)
        }
        val _columnsInstitutionalDocuments: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsInstitutionalDocuments.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("grade", TableInfo.Column("grade", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("subject", TableInfo.Column("subject", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("teacherId", TableInfo.Column("teacherId", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("createdAt", TableInfo.Column("createdAt", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalDocuments.put("updatedAt", TableInfo.Column("updatedAt", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysInstitutionalDocuments: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesInstitutionalDocuments: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoInstitutionalDocuments: TableInfo = TableInfo("institutional_documents",
            _columnsInstitutionalDocuments, _foreignKeysInstitutionalDocuments,
            _indicesInstitutionalDocuments)
        val _existingInstitutionalDocuments: TableInfo = read(connection, "institutional_documents")
        if (!_infoInstitutionalDocuments.equals(_existingInstitutionalDocuments)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |institutional_documents(com.sigeschool.data.local.entity.InstitutionalDocumentEntity).
              | Expected:
              |""".trimMargin() + _infoInstitutionalDocuments + """
              |
              | Found:
              |""".trimMargin() + _existingInstitutionalDocuments)
        }
        val _columnsDocumentBlocks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDocumentBlocks.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentBlocks.put("documentId", TableInfo.Column("documentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentBlocks.put("orderIndex", TableInfo.Column("orderIndex", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentBlocks.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentBlocks.put("contentHtml", TableInfo.Column("contentHtml", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentBlocks.put("updatedAt", TableInfo.Column("updatedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentBlocks.put("modifiedBy", TableInfo.Column("modifiedBy", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDocumentBlocks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDocumentBlocks: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDocumentBlocks: TableInfo = TableInfo("document_blocks", _columnsDocumentBlocks,
            _foreignKeysDocumentBlocks, _indicesDocumentBlocks)
        val _existingDocumentBlocks: TableInfo = read(connection, "document_blocks")
        if (!_infoDocumentBlocks.equals(_existingDocumentBlocks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |document_blocks(com.sigeschool.data.local.entity.DocumentBlockEntity).
              | Expected:
              |""".trimMargin() + _infoDocumentBlocks + """
              |
              | Found:
              |""".trimMargin() + _existingDocumentBlocks)
        }
        val _columnsBlockHistory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBlockHistory.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBlockHistory.put("blockId", TableInfo.Column("blockId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBlockHistory.put("contentHtml", TableInfo.Column("contentHtml", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBlockHistory.put("modifiedAt", TableInfo.Column("modifiedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBlockHistory.put("modifiedBy", TableInfo.Column("modifiedBy", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBlockHistory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBlockHistory: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBlockHistory: TableInfo = TableInfo("block_history", _columnsBlockHistory,
            _foreignKeysBlockHistory, _indicesBlockHistory)
        val _existingBlockHistory: TableInfo = read(connection, "block_history")
        if (!_infoBlockHistory.equals(_existingBlockHistory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |block_history(com.sigeschool.data.local.entity.BlockHistoryEntity).
              | Expected:
              |""".trimMargin() + _infoBlockHistory + """
              |
              | Found:
              |""".trimMargin() + _existingBlockHistory)
        }
        val _columnsInvoices: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsInvoices.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("pagoId", TableInfo.Column("pagoId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("number", TableInfo.Column("number", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("studentName", TableInfo.Column("studentName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("parentName", TableInfo.Column("parentName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("parentId", TableInfo.Column("parentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("grade", TableInfo.Column("grade", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("dueDate", TableInfo.Column("dueDate", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("totalAmount", TableInfo.Column("totalAmount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("paidAmount", TableInfo.Column("paidAmount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("balance", TableInfo.Column("balance", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("concept", TableInfo.Column("concept", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("observations", TableInfo.Column("observations", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("cufe", TableInfo.Column("cufe", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("qrCode", TableInfo.Column("qrCode", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("xmlUrl", TableInfo.Column("xmlUrl", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("digitalSignatureUrl", TableInfo.Column("digitalSignatureUrl", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("isSynced", TableInfo.Column("isSynced", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("version", TableInfo.Column("version", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("deviceId", TableInfo.Column("deviceId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoices.put("syncAttempts", TableInfo.Column("syncAttempts", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysInvoices: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesInvoices: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoInvoices: TableInfo = TableInfo("invoices", _columnsInvoices, _foreignKeysInvoices,
            _indicesInvoices)
        val _existingInvoices: TableInfo = read(connection, "invoices")
        if (!_infoInvoices.equals(_existingInvoices)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |invoices(com.sigeschool.data.local.entity.billing.InvoiceEntity).
              | Expected:
              |""".trimMargin() + _infoInvoices + """
              |
              | Found:
              |""".trimMargin() + _existingInvoices)
        }
        val _columnsInvoiceItems: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsInvoiceItems.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("invoiceId", TableInfo.Column("invoiceId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("categoryId", TableInfo.Column("categoryId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("description", TableInfo.Column("description", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("quantity", TableInfo.Column("quantity", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("unitPrice", TableInfo.Column("unitPrice", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("discount", TableInfo.Column("discount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("tax", TableInfo.Column("tax", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInvoiceItems.put("total", TableInfo.Column("total", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysInvoiceItems: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesInvoiceItems: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoInvoiceItems: TableInfo = TableInfo("invoice_items", _columnsInvoiceItems,
            _foreignKeysInvoiceItems, _indicesInvoiceItems)
        val _existingInvoiceItems: TableInfo = read(connection, "invoice_items")
        if (!_infoInvoiceItems.equals(_existingInvoiceItems)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |invoice_items(com.sigeschool.data.local.entity.billing.InvoiceItemEntity).
              | Expected:
              |""".trimMargin() + _infoInvoiceItems + """
              |
              | Found:
              |""".trimMargin() + _existingInvoiceItems)
        }
        val _columnsPaymentRecords: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPaymentRecords.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("invoiceId", TableInfo.Column("invoiceId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("amount", TableInfo.Column("amount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("paymentMethod", TableInfo.Column("paymentMethod", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("reference", TableInfo.Column("reference", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("registrarId", TableInfo.Column("registrarId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPaymentRecords.put("isSynced", TableInfo.Column("isSynced", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPaymentRecords: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPaymentRecords: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPaymentRecords: TableInfo = TableInfo("payment_records", _columnsPaymentRecords,
            _foreignKeysPaymentRecords, _indicesPaymentRecords)
        val _existingPaymentRecords: TableInfo = read(connection, "payment_records")
        if (!_infoPaymentRecords.equals(_existingPaymentRecords)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |payment_records(com.sigeschool.data.local.entity.billing.PaymentRecordEntity).
              | Expected:
              |""".trimMargin() + _infoPaymentRecords + """
              |
              | Found:
              |""".trimMargin() + _existingPaymentRecords)
        }
        val _columnsGradingScales: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGradingScales.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGradingScales.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGradingScales.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGradingScales.put("minScore", TableInfo.Column("minScore", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGradingScales.put("maxScore", TableInfo.Column("maxScore", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGradingScales.put("isDefault", TableInfo.Column("isDefault", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGradingScales: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesGradingScales: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoGradingScales: TableInfo = TableInfo("grading_scales", _columnsGradingScales,
            _foreignKeysGradingScales, _indicesGradingScales)
        val _existingGradingScales: TableInfo = read(connection, "grading_scales")
        if (!_infoGradingScales.equals(_existingGradingScales)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |grading_scales(com.sigeschool.data.local.entity.sie.GradingScaleEntity).
              | Expected:
              |""".trimMargin() + _infoGradingScales + """
              |
              | Found:
              |""".trimMargin() + _existingGradingScales)
        }
        val _columnsScaleRanges: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsScaleRanges.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsScaleRanges.put("gradingScaleId", TableInfo.Column("gradingScaleId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsScaleRanges.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsScaleRanges.put("minLimit", TableInfo.Column("minLimit", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsScaleRanges.put("maxLimit", TableInfo.Column("maxLimit", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsScaleRanges.put("description", TableInfo.Column("description", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsScaleRanges.put("color", TableInfo.Column("color", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysScaleRanges: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesScaleRanges: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoScaleRanges: TableInfo = TableInfo("scale_ranges", _columnsScaleRanges,
            _foreignKeysScaleRanges, _indicesScaleRanges)
        val _existingScaleRanges: TableInfo = read(connection, "scale_ranges")
        if (!_infoScaleRanges.equals(_existingScaleRanges)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |scale_ranges(com.sigeschool.data.local.entity.sie.ScaleRangeEntity).
              | Expected:
              |""".trimMargin() + _infoScaleRanges + """
              |
              | Found:
              |""".trimMargin() + _existingScaleRanges)
        }
        val _columnsGradeCategories: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGradeCategories.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGradeCategories.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGradeCategories.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsGradeCategories.put("weightPercentage", TableInfo.Column("weightPercentage", "REAL",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGradeCategories.put("periodId", TableInfo.Column("periodId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGradeCategories: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesGradeCategories: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoGradeCategories: TableInfo = TableInfo("grade_categories", _columnsGradeCategories,
            _foreignKeysGradeCategories, _indicesGradeCategories)
        val _existingGradeCategories: TableInfo = read(connection, "grade_categories")
        if (!_infoGradeCategories.equals(_existingGradeCategories)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |grade_categories(com.sigeschool.data.local.entity.sie.GradeCategoryEntity).
              | Expected:
              |""".trimMargin() + _infoGradeCategories + """
              |
              | Found:
              |""".trimMargin() + _existingGradeCategories)
        }
        val _columnsRubrics: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRubrics.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubrics.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRubrics.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubrics.put("description", TableInfo.Column("description", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRubrics: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRubrics: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRubrics: TableInfo = TableInfo("rubrics", _columnsRubrics, _foreignKeysRubrics,
            _indicesRubrics)
        val _existingRubrics: TableInfo = read(connection, "rubrics")
        if (!_infoRubrics.equals(_existingRubrics)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |rubrics(com.sigeschool.data.local.entity.sie.RubricEntity).
              | Expected:
              |""".trimMargin() + _infoRubrics + """
              |
              | Found:
              |""".trimMargin() + _existingRubrics)
        }
        val _columnsRubricCriteria: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRubricCriteria.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricCriteria.put("rubricId", TableInfo.Column("rubricId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricCriteria.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricCriteria.put("weight", TableInfo.Column("weight", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRubricCriteria: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRubricCriteria: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRubricCriteria: TableInfo = TableInfo("rubric_criteria", _columnsRubricCriteria,
            _foreignKeysRubricCriteria, _indicesRubricCriteria)
        val _existingRubricCriteria: TableInfo = read(connection, "rubric_criteria")
        if (!_infoRubricCriteria.equals(_existingRubricCriteria)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |rubric_criteria(com.sigeschool.data.local.entity.sie.RubricCriterionEntity).
              | Expected:
              |""".trimMargin() + _infoRubricCriteria + """
              |
              | Found:
              |""".trimMargin() + _existingRubricCriteria)
        }
        val _columnsCriterionLevels: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCriterionLevels.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCriterionLevels.put("criterionId", TableInfo.Column("criterionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCriterionLevels.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCriterionLevels.put("score", TableInfo.Column("score", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCriterionLevels.put("description", TableInfo.Column("description", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCriterionLevels: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCriterionLevels: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCriterionLevels: TableInfo = TableInfo("criterion_levels", _columnsCriterionLevels,
            _foreignKeysCriterionLevels, _indicesCriterionLevels)
        val _existingCriterionLevels: TableInfo = read(connection, "criterion_levels")
        if (!_infoCriterionLevels.equals(_existingCriterionLevels)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |criterion_levels(com.sigeschool.data.local.entity.sie.CriterionLevelEntity).
              | Expected:
              |""".trimMargin() + _infoCriterionLevels + """
              |
              | Found:
              |""".trimMargin() + _existingCriterionLevels)
        }
        val _columnsCompetencies: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCompetencies.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCompetencies.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCompetencies.put("code", TableInfo.Column("code", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCompetencies.put("description", TableInfo.Column("description", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCompetencies.put("area", TableInfo.Column("area", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCompetencies: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCompetencies: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCompetencies: TableInfo = TableInfo("competencies", _columnsCompetencies,
            _foreignKeysCompetencies, _indicesCompetencies)
        val _existingCompetencies: TableInfo = read(connection, "competencies")
        if (!_infoCompetencies.equals(_existingCompetencies)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |competencies(com.sigeschool.data.local.entity.sie.CompetencyEntity).
              | Expected:
              |""".trimMargin() + _infoCompetencies + """
              |
              | Found:
              |""".trimMargin() + _existingCompetencies)
        }
        val _columnsAchievementIndicators: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAchievementIndicators.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievementIndicators.put("competencyId", TableInfo.Column("competencyId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievementIndicators.put("rangeId", TableInfo.Column("rangeId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievementIndicators.put("description", TableInfo.Column("description", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAchievementIndicators: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAchievementIndicators: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAchievementIndicators: TableInfo = TableInfo("achievement_indicators",
            _columnsAchievementIndicators, _foreignKeysAchievementIndicators,
            _indicesAchievementIndicators)
        val _existingAchievementIndicators: TableInfo = read(connection, "achievement_indicators")
        if (!_infoAchievementIndicators.equals(_existingAchievementIndicators)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |achievement_indicators(com.sigeschool.data.local.entity.sie.AchievementIndicatorEntity).
              | Expected:
              |""".trimMargin() + _infoAchievementIndicators + """
              |
              | Found:
              |""".trimMargin() + _existingAchievementIndicators)
        }
        val _columnsRubricEvaluations: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRubricEvaluations.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricEvaluations.put("gradeId", TableInfo.Column("gradeId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricEvaluations.put("rubricId", TableInfo.Column("rubricId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRubricEvaluations: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRubricEvaluations: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRubricEvaluations: TableInfo = TableInfo("rubric_evaluations",
            _columnsRubricEvaluations, _foreignKeysRubricEvaluations, _indicesRubricEvaluations)
        val _existingRubricEvaluations: TableInfo = read(connection, "rubric_evaluations")
        if (!_infoRubricEvaluations.equals(_existingRubricEvaluations)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |rubric_evaluations(com.sigeschool.data.local.entity.sie.RubricEvaluationEntity).
              | Expected:
              |""".trimMargin() + _infoRubricEvaluations + """
              |
              | Found:
              |""".trimMargin() + _existingRubricEvaluations)
        }
        val _columnsRubricSelections: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRubricSelections.put("localId", TableInfo.Column("localId", "INTEGER", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricSelections.put("evaluationId", TableInfo.Column("evaluationId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricSelections.put("criterionId", TableInfo.Column("criterionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricSelections.put("levelId", TableInfo.Column("levelId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRubricSelections.put("score", TableInfo.Column("score", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRubricSelections: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRubricSelections: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRubricSelections: TableInfo = TableInfo("rubric_selections",
            _columnsRubricSelections, _foreignKeysRubricSelections, _indicesRubricSelections)
        val _existingRubricSelections: TableInfo = read(connection, "rubric_selections")
        if (!_infoRubricSelections.equals(_existingRubricSelections)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |rubric_selections(com.sigeschool.data.local.entity.sie.CriterionSelectionEntity).
              | Expected:
              |""".trimMargin() + _infoRubricSelections + """
              |
              | Found:
              |""".trimMargin() + _existingRubricSelections)
        }
        val _columnsCashTransactions: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCashTransactions.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("concept", TableInfo.Column("concept", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("category", TableInfo.Column("category", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("amount", TableInfo.Column("amount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("paymentMethod", TableInfo.Column("paymentMethod", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("personName", TableInfo.Column("personName", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("reference", TableInfo.Column("reference", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("observations", TableInfo.Column("observations", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("registradoPorId", TableInfo.Column("registradoPorId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("isSynced", TableInfo.Column("isSynced", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("version", TableInfo.Column("version", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("deviceId", TableInfo.Column("deviceId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashTransactions.put("syncAttempts", TableInfo.Column("syncAttempts", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCashTransactions: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCashTransactions: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCashTransactions: TableInfo = TableInfo("cash_transactions",
            _columnsCashTransactions, _foreignKeysCashTransactions, _indicesCashTransactions)
        val _existingCashTransactions: TableInfo = read(connection, "cash_transactions")
        if (!_infoCashTransactions.equals(_existingCashTransactions)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |cash_transactions(com.sigeschool.data.local.entity.CashTransactionEntity).
              | Expected:
              |""".trimMargin() + _infoCashTransactions + """
              |
              | Found:
              |""".trimMargin() + _existingCashTransactions)
        }
        val _columnsPoliticasPrivacidad: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPoliticasPrivacidad.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPoliticasPrivacidad.put("version", TableInfo.Column("version", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPoliticasPrivacidad.put("fechaPublicacion", TableInfo.Column("fechaPublicacion",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPoliticasPrivacidad.put("contenidoHash", TableInfo.Column("contenidoHash", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPoliticasPrivacidad.put("contenidoTexto", TableInfo.Column("contenidoTexto", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPoliticasPrivacidad.put("es_activa", TableInfo.Column("es_activa", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPoliticasPrivacidad: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPoliticasPrivacidad: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPoliticasPrivacidad: TableInfo = TableInfo("politicas_privacidad",
            _columnsPoliticasPrivacidad, _foreignKeysPoliticasPrivacidad,
            _indicesPoliticasPrivacidad)
        val _existingPoliticasPrivacidad: TableInfo = read(connection, "politicas_privacidad")
        if (!_infoPoliticasPrivacidad.equals(_existingPoliticasPrivacidad)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |politicas_privacidad(com.sigeschool.data.local.entity.PrivacyPolicyEntity).
              | Expected:
              |""".trimMargin() + _infoPoliticasPrivacidad + """
              |
              | Found:
              |""".trimMargin() + _existingPoliticasPrivacidad)
        }
        val _columnsConsentimientos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConsentimientos.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("acudienteNombre", TableInfo.Column("acudienteNombre", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("acudienteDni", TableInfo.Column("acudienteDni", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("acudienteParentesco", TableInfo.Column("acudienteParentesco",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("acudienteEmail", TableInfo.Column("acudienteEmail", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("acudienteTelefono", TableInfo.Column("acudienteTelefono",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("politicaId", TableInfo.Column("politicaId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("fechaAceptacion", TableInfo.Column("fechaAceptacion",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("fecha_revocacion", TableInfo.Column("fecha_revocacion",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("motivo_revocacion", TableInfo.Column("motivo_revocacion",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("device_info", TableInfo.Column("device_info", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("hash_firma_digital", TableInfo.Column("hash_firma_digital",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("granularConsent", TableInfo.Column("granularConsent", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("version", TableInfo.Column("version", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("deviceId", TableInfo.Column("deviceId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientos.put("syncAttempts", TableInfo.Column("syncAttempts", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConsentimientos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysConsentimientos.add(TableInfo.ForeignKey("students", "CASCADE", "NO ACTION",
            listOf("studentId"), listOf("id")))
        _foreignKeysConsentimientos.add(TableInfo.ForeignKey("politicas_privacidad", "NO ACTION",
            "NO ACTION", listOf("politicaId"), listOf("id")))
        val _indicesConsentimientos: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesConsentimientos.add(TableInfo.Index("index_consentimientos_studentId", false,
            listOf("studentId"), listOf("ASC")))
        _indicesConsentimientos.add(TableInfo.Index("index_consentimientos_politicaId", false,
            listOf("politicaId"), listOf("ASC")))
        val _infoConsentimientos: TableInfo = TableInfo("consentimientos", _columnsConsentimientos,
            _foreignKeysConsentimientos, _indicesConsentimientos)
        val _existingConsentimientos: TableInfo = read(connection, "consentimientos")
        if (!_infoConsentimientos.equals(_existingConsentimientos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |consentimientos(com.sigeschool.data.local.entity.ConsentEntity).
              | Expected:
              |""".trimMargin() + _infoConsentimientos + """
              |
              | Found:
              |""".trimMargin() + _existingConsentimientos)
        }
        val _columnsConsentimientoHistorial: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConsentimientoHistorial.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientoHistorial.put("consentId", TableInfo.Column("consentId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientoHistorial.put("studentId", TableInfo.Column("studentId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientoHistorial.put("action", TableInfo.Column("action", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientoHistorial.put("timestamp", TableInfo.Column("timestamp", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConsentimientoHistorial.put("details", TableInfo.Column("details", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConsentimientoHistorial: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesConsentimientoHistorial: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoConsentimientoHistorial: TableInfo = TableInfo("consentimiento_historial",
            _columnsConsentimientoHistorial, _foreignKeysConsentimientoHistorial,
            _indicesConsentimientoHistorial)
        val _existingConsentimientoHistorial: TableInfo = read(connection,
            "consentimiento_historial")
        if (!_infoConsentimientoHistorial.equals(_existingConsentimientoHistorial)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |consentimiento_historial(com.sigeschool.data.local.entity.ConsentHistoryEntity).
              | Expected:
              |""".trimMargin() + _infoConsentimientoHistorial + """
              |
              | Found:
              |""".trimMargin() + _existingConsentimientoHistorial)
        }
        val _columnsFeeCategories: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFeeCategories.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeCategories.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeCategories.put("basePrice", TableInfo.Column("basePrice", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeCategories.put("isRecurring", TableInfo.Column("isRecurring", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeCategories.put("appliesToGrades", TableInfo.Column("appliesToGrades", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFeeCategories: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesFeeCategories: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoFeeCategories: TableInfo = TableInfo("fee_categories", _columnsFeeCategories,
            _foreignKeysFeeCategories, _indicesFeeCategories)
        val _existingFeeCategories: TableInfo = read(connection, "fee_categories")
        if (!_infoFeeCategories.equals(_existingFeeCategories)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |fee_categories(com.sigeschool.data.local.entity.billing.FeeCategoryEntity).
              | Expected:
              |""".trimMargin() + _infoFeeCategories + """
              |
              | Found:
              |""".trimMargin() + _existingFeeCategories)
        }
        val _columnsAchievements: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAchievements.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievements.put("subjectId", TableInfo.Column("subjectId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievements.put("gradeId", TableInfo.Column("gradeId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievements.put("period", TableInfo.Column("period", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievements.put("description", TableInfo.Column("description", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAchievements.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAchievements: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAchievements: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAchievements: TableInfo = TableInfo("achievements", _columnsAchievements,
            _foreignKeysAchievements, _indicesAchievements)
        val _existingAchievements: TableInfo = read(connection, "achievements")
        if (!_infoAchievements.equals(_existingAchievements)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |achievements(com.sigeschool.data.local.entity.sie.AchievementEntity).
              | Expected:
              |""".trimMargin() + _infoAchievements + """
              |
              | Found:
              |""".trimMargin() + _existingAchievements)
        }
        val _columnsAcademicGrades: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicGrades.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrades.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrades.put("subjectId", TableInfo.Column("subjectId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrades.put("period", TableInfo.Column("period", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrades.put("value", TableInfo.Column("value", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrades.put("achievementIds", TableInfo.Column("achievementIds", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrades.put("observations", TableInfo.Column("observations", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrades.put("updatedAt", TableInfo.Column("updatedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicGrades: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicGrades: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicGrades: TableInfo = TableInfo("academic_grades", _columnsAcademicGrades,
            _foreignKeysAcademicGrades, _indicesAcademicGrades)
        val _existingAcademicGrades: TableInfo = read(connection, "academic_grades")
        if (!_infoAcademicGrades.equals(_existingAcademicGrades)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_grades(com.sigeschool.data.local.entity.sie.AcademicGradeEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicGrades + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicGrades)
        }
        val _columnsDisciplineRecords: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDisciplineRecords.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("description", TableInfo.Column("description", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("teacherId", TableInfo.Column("teacherId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("impactOnGrade", TableInfo.Column("impactOnGrade", "REAL",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("parentNotified", TableInfo.Column("parentNotified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDisciplineRecords.put("parentAttended", TableInfo.Column("parentAttended",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDisciplineRecords: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDisciplineRecords: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDisciplineRecords: TableInfo = TableInfo("discipline_records",
            _columnsDisciplineRecords, _foreignKeysDisciplineRecords, _indicesDisciplineRecords)
        val _existingDisciplineRecords: TableInfo = read(connection, "discipline_records")
        if (!_infoDisciplineRecords.equals(_existingDisciplineRecords)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |discipline_records(com.sigeschool.data.local.entity.sie.DisciplineRecordEntity).
              | Expected:
              |""".trimMargin() + _infoDisciplineRecords + """
              |
              | Found:
              |""".trimMargin() + _existingDisciplineRecords)
        }
        val _columnsStudyPlans: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsStudyPlans.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudyPlans.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudyPlans.put("version", TableInfo.Column("version", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsStudyPlans.put("lastUpdated", TableInfo.Column("lastUpdated", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysStudyPlans: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesStudyPlans: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoStudyPlans: TableInfo = TableInfo("study_plans", _columnsStudyPlans,
            _foreignKeysStudyPlans, _indicesStudyPlans)
        val _existingStudyPlans: TableInfo = read(connection, "study_plans")
        if (!_infoStudyPlans.equals(_existingStudyPlans)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |study_plans(com.sigeschool.data.local.entity.sie.StudyPlanEntity).
              | Expected:
              |""".trimMargin() + _infoStudyPlans + """
              |
              | Found:
              |""".trimMargin() + _existingStudyPlans)
        }
        val _columnsAreaPlans: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAreaPlans.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAreaPlans.put("studyPlanId", TableInfo.Column("studyPlanId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAreaPlans.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAreaPlans.put("intensity", TableInfo.Column("intensity", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAreaPlans.put("subjectIds", TableInfo.Column("subjectIds", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAreaPlans: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAreaPlans: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAreaPlans: TableInfo = TableInfo("area_plans", _columnsAreaPlans,
            _foreignKeysAreaPlans, _indicesAreaPlans)
        val _existingAreaPlans: TableInfo = read(connection, "area_plans")
        if (!_infoAreaPlans.equals(_existingAreaPlans)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |area_plans(com.sigeschool.data.local.entity.sie.AreaPlanEntity).
              | Expected:
              |""".trimMargin() + _infoAreaPlans + """
              |
              | Found:
              |""".trimMargin() + _existingAreaPlans)
        }
        val _columnsAuditLedger: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAuditLedger.put("ledgerIndex", TableInfo.Column("ledgerIndex", "INTEGER", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLedger.put("previousHash", TableInfo.Column("previousHash", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLedger.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLedger.put("data", TableInfo.Column("data", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLedger.put("nonce", TableInfo.Column("nonce", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLedger.put("hash", TableInfo.Column("hash", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAuditLedger: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAuditLedger: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAuditLedger: TableInfo = TableInfo("audit_ledger", _columnsAuditLedger,
            _foreignKeysAuditLedger, _indicesAuditLedger)
        val _existingAuditLedger: TableInfo = read(connection, "audit_ledger")
        if (!_infoAuditLedger.equals(_existingAuditLedger)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |audit_ledger(com.sigeschool.data.local.entity.AuditEntryEntity).
              | Expected:
              |""".trimMargin() + _infoAuditLedger + """
              |
              | Found:
              |""".trimMargin() + _existingAuditLedger)
        }
        val _columnsRoles: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRoles.put("idRol", TableInfo.Column("idRol", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRoles.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRoles.put("nivel", TableInfo.Column("nivel", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRoles.put("descripcion", TableInfo.Column("descripcion", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRoles.put("permisosJson", TableInfo.Column("permisosJson", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRoles.put("esSistema", TableInfo.Column("esSistema", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRoles.put("fechaCreacion", TableInfo.Column("fechaCreacion", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRoles.put("fechaActualizacion", TableInfo.Column("fechaActualizacion", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRoles: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRoles: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRoles: TableInfo = TableInfo("roles", _columnsRoles, _foreignKeysRoles,
            _indicesRoles)
        val _existingRoles: TableInfo = read(connection, "roles")
        if (!_infoRoles.equals(_existingRoles)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |roles(com.sigeschool.data.local.entity.RoleEntity).
              | Expected:
              |""".trimMargin() + _infoRoles + """
              |
              | Found:
              |""".trimMargin() + _existingRoles)
        }
        val _columnsPermisos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPermisos.put("idPermiso", TableInfo.Column("idPermiso", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPermisos.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPermisos.put("recurso", TableInfo.Column("recurso", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPermisos.put("accion", TableInfo.Column("accion", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPermisos.put("descripcion", TableInfo.Column("descripcion", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPermisos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPermisos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPermisos: TableInfo = TableInfo("permisos", _columnsPermisos, _foreignKeysPermisos,
            _indicesPermisos)
        val _existingPermisos: TableInfo = read(connection, "permisos")
        if (!_infoPermisos.equals(_existingPermisos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |permisos(com.sigeschool.data.local.entity.PermisoEntity).
              | Expected:
              |""".trimMargin() + _infoPermisos + """
              |
              | Found:
              |""".trimMargin() + _existingPermisos)
        }
        val _columnsRolesPermisos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRolesPermisos.put("idRol", TableInfo.Column("idRol", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRolesPermisos.put("idPermiso", TableInfo.Column("idPermiso", "TEXT", true, 2, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRolesPermisos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysRolesPermisos.add(TableInfo.ForeignKey("roles", "CASCADE", "NO ACTION",
            listOf("idRol"), listOf("idRol")))
        _foreignKeysRolesPermisos.add(TableInfo.ForeignKey("permisos", "CASCADE", "NO ACTION",
            listOf("idPermiso"), listOf("idPermiso")))
        val _indicesRolesPermisos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRolesPermisos: TableInfo = TableInfo("roles_permisos", _columnsRolesPermisos,
            _foreignKeysRolesPermisos, _indicesRolesPermisos)
        val _existingRolesPermisos: TableInfo = read(connection, "roles_permisos")
        if (!_infoRolesPermisos.equals(_existingRolesPermisos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |roles_permisos(com.sigeschool.data.local.entity.RolePermisoCrossReference).
              | Expected:
              |""".trimMargin() + _infoRolesPermisos + """
              |
              | Found:
              |""".trimMargin() + _existingRolesPermisos)
        }
        val _columnsPerfilesPersonal: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPerfilesPersonal.put("idPerfil", TableInfo.Column("idPerfil", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("idUsuario", TableInfo.Column("idUsuario", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("idRol", TableInfo.Column("idRol", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("datosJson", TableInfo.Column("datosJson", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("documentoOriginalPath",
            TableInfo.Column("documentoOriginalPath", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("documentoOriginalHash",
            TableInfo.Column("documentoOriginalHash", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("fechaCarga", TableInfo.Column("fechaCarga", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("fechaActualizacion", TableInfo.Column("fechaActualizacion",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPerfilesPersonal.put("version", TableInfo.Column("version", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPerfilesPersonal: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysPerfilesPersonal.add(TableInfo.ForeignKey("roles", "NO ACTION", "NO ACTION",
            listOf("idRol"), listOf("idRol")))
        val _indicesPerfilesPersonal: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPerfilesPersonal: TableInfo = TableInfo("perfiles_personal",
            _columnsPerfilesPersonal, _foreignKeysPerfilesPersonal, _indicesPerfilesPersonal)
        val _existingPerfilesPersonal: TableInfo = read(connection, "perfiles_personal")
        if (!_infoPerfilesPersonal.equals(_existingPerfilesPersonal)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |perfiles_personal(com.sigeschool.data.local.entity.PerfilPersonalEntity).
              | Expected:
              |""".trimMargin() + _infoPerfilesPersonal + """
              |
              | Found:
              |""".trimMargin() + _existingPerfilesPersonal)
        }
        val _columnsHistorialCv: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsHistorialCv.put("idHistorial", TableInfo.Column("idHistorial", "TEXT", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHistorialCv.put("idPerfil", TableInfo.Column("idPerfil", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsHistorialCv.put("version", TableInfo.Column("version", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsHistorialCv.put("datosJson", TableInfo.Column("datosJson", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsHistorialCv.put("fechaModificacion", TableInfo.Column("fechaModificacion",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHistorialCv.put("idUsuarioModificador", TableInfo.Column("idUsuarioModificador",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysHistorialCv: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysHistorialCv.add(TableInfo.ForeignKey("perfiles_personal", "CASCADE",
            "NO ACTION", listOf("idPerfil"), listOf("idPerfil")))
        val _indicesHistorialCv: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoHistorialCv: TableInfo = TableInfo("historial_cv", _columnsHistorialCv,
            _foreignKeysHistorialCv, _indicesHistorialCv)
        val _existingHistorialCv: TableInfo = read(connection, "historial_cv")
        if (!_infoHistorialCv.equals(_existingHistorialCv)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |historial_cv(com.sigeschool.data.local.entity.HistorialCvEntity).
              | Expected:
              |""".trimMargin() + _infoHistorialCv + """
              |
              | Found:
              |""".trimMargin() + _existingHistorialCv)
        }
        val _columnsAcudientes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcudientes.put("idAcudiente", TableInfo.Column("idAcudiente", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("nombreCompleto", TableInfo.Column("nombreCompleto", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("tipoDocumento", TableInfo.Column("tipoDocumento", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("numeroDocumento", TableInfo.Column("numeroDocumento", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("correoElectronico", TableInfo.Column("correoElectronico", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("telefono", TableInfo.Column("telefono", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("whatsapp", TableInfo.Column("whatsapp", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("direccion", TableInfo.Column("direccion", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("parentesco", TableInfo.Column("parentesco", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("estado", TableInfo.Column("estado", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("fechaRegistro", TableInfo.Column("fechaRegistro", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("fechaActualizacion", TableInfo.Column("fechaActualizacion",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("passwordHash", TableInfo.Column("passwordHash", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcudientes.put("preferenciasJson", TableInfo.Column("preferenciasJson", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcudientes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcudientes: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesAcudientes.add(TableInfo.Index("index_acudientes_numeroDocumento", true,
            listOf("numeroDocumento"), listOf("ASC")))
        _indicesAcudientes.add(TableInfo.Index("index_acudientes_correoElectronico", true,
            listOf("correoElectronico"), listOf("ASC")))
        _indicesAcudientes.add(TableInfo.Index("index_acudientes_telefono", true,
            listOf("telefono"), listOf("ASC")))
        val _infoAcudientes: TableInfo = TableInfo("acudientes", _columnsAcudientes,
            _foreignKeysAcudientes, _indicesAcudientes)
        val _existingAcudientes: TableInfo = read(connection, "acudientes")
        if (!_infoAcudientes.equals(_existingAcudientes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |acudientes(com.sigeschool.data.local.entity.AcudienteEntity).
              | Expected:
              |""".trimMargin() + _infoAcudientes + """
              |
              | Found:
              |""".trimMargin() + _existingAcudientes)
        }
        val _columnsEstudiantesAcudientes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEstudiantesAcudientes.put("idRelacion", TableInfo.Column("idRelacion", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEstudiantesAcudientes.put("idEstudiante", TableInfo.Column("idEstudiante", "TEXT",
            true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEstudiantesAcudientes.put("idAcudiente", TableInfo.Column("idAcudiente", "TEXT",
            true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEstudiantesAcudientes.put("esPrincipal", TableInfo.Column("esPrincipal", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEstudiantesAcudientes.put("puedeRetirar", TableInfo.Column("puedeRetirar",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEstudiantesAcudientes.put("recibeNotificaciones",
            TableInfo.Column("recibeNotificaciones", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEstudiantesAcudientes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysEstudiantesAcudientes.add(TableInfo.ForeignKey("students", "CASCADE",
            "NO ACTION", listOf("idEstudiante"), listOf("id")))
        _foreignKeysEstudiantesAcudientes.add(TableInfo.ForeignKey("acudientes", "CASCADE",
            "NO ACTION", listOf("idAcudiente"), listOf("idAcudiente")))
        val _indicesEstudiantesAcudientes: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoEstudiantesAcudientes: TableInfo = TableInfo("estudiantes_acudientes",
            _columnsEstudiantesAcudientes, _foreignKeysEstudiantesAcudientes,
            _indicesEstudiantesAcudientes)
        val _existingEstudiantesAcudientes: TableInfo = read(connection, "estudiantes_acudientes")
        if (!_infoEstudiantesAcudientes.equals(_existingEstudiantesAcudientes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |estudiantes_acudientes(com.sigeschool.data.local.entity.EstudianteAcudienteEntity).
              | Expected:
              |""".trimMargin() + _infoEstudiantesAcudientes + """
              |
              | Found:
              |""".trimMargin() + _existingEstudiantesAcudientes)
        }
        val _columnsPreferenciasNotificaciones: MutableMap<String, TableInfo.Column> =
            mutableMapOf()
        _columnsPreferenciasNotificaciones.put("id", TableInfo.Column("id", "INTEGER", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("usuarioId", TableInfo.Column("usuarioId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("tipoEvento", TableInfo.Column("tipoEvento", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("push", TableInfo.Column("push", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("inApp", TableInfo.Column("inApp", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("email", TableInfo.Column("email", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("sms", TableInfo.Column("sms", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("syncStatus", TableInfo.Column("syncStatus",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPreferenciasNotificaciones.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPreferenciasNotificaciones: MutableSet<TableInfo.ForeignKey> =
            mutableSetOf()
        val _indicesPreferenciasNotificaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPreferenciasNotificaciones: TableInfo = TableInfo("preferencias_notificaciones",
            _columnsPreferenciasNotificaciones, _foreignKeysPreferenciasNotificaciones,
            _indicesPreferenciasNotificaciones)
        val _existingPreferenciasNotificaciones: TableInfo = read(connection,
            "preferencias_notificaciones")
        if (!_infoPreferenciasNotificaciones.equals(_existingPreferenciasNotificaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |preferencias_notificaciones(com.sigeschool.data.local.entity.PreferenciaNotificacionEntity).
              | Expected:
              |""".trimMargin() + _infoPreferenciasNotificaciones + """
              |
              | Found:
              |""".trimMargin() + _existingPreferenciasNotificaciones)
        }
        val _columnsNotificaciones: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsNotificaciones.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("usuarioId", TableInfo.Column("usuarioId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("titulo", TableInfo.Column("titulo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("mensaje", TableInfo.Column("mensaje", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("canal", TableInfo.Column("canal", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("leida", TableInfo.Column("leida", "INTEGER", true, 0, "0",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("fecha", TableInfo.Column("fecha", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificaciones.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysNotificaciones: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesNotificaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesNotificaciones.add(TableInfo.Index("idx_notificaciones_usuario", false,
            listOf("usuarioId", "institutionId"), listOf("ASC", "ASC")))
        _indicesNotificaciones.add(TableInfo.Index("idx_notificaciones_leida", false,
            listOf("leida", "institutionId"), listOf("ASC", "ASC")))
        val _infoNotificaciones: TableInfo = TableInfo("notificaciones", _columnsNotificaciones,
            _foreignKeysNotificaciones, _indicesNotificaciones)
        val _existingNotificaciones: TableInfo = read(connection, "notificaciones")
        if (!_infoNotificaciones.equals(_existingNotificaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |notificaciones(com.sigeschool.data.local.entity.NotificacionEntity).
              | Expected:
              |""".trimMargin() + _infoNotificaciones + """
              |
              | Found:
              |""".trimMargin() + _existingNotificaciones)
        }
        val _columnsInstitutionalNotificaciones: MutableMap<String, TableInfo.Column> =
            mutableMapOf()
        _columnsInstitutionalNotificaciones.put("idNotificacion", TableInfo.Column("idNotificacion",
            "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("idEstudiante", TableInfo.Column("idEstudiante",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("idAcudiente", TableInfo.Column("idAcudiente",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("idUsuarioRemitente",
            TableInfo.Column("idUsuarioRemitente", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("tipoNotificacion",
            TableInfo.Column("tipoNotificacion", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("asunto", TableInfo.Column("asunto", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("mensaje", TableInfo.Column("mensaje", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("mensajeWhatsapp",
            TableInfo.Column("mensajeWhatsapp", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("mensajeEmail", TableInfo.Column("mensajeEmail",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("fechaEnvio", TableInfo.Column("fechaEnvio",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("canales", TableInfo.Column("canales", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("estadoEnvioEmail",
            TableInfo.Column("estadoEnvioEmail", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("estadoEnvioWhatsapp",
            TableInfo.Column("estadoEnvioWhatsapp", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("estadoEnvioSms", TableInfo.Column("estadoEnvioSms",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("estadoEnvioPush",
            TableInfo.Column("estadoEnvioPush", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("idRespuesta", TableInfo.Column("idRespuesta",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("prioridad", TableInfo.Column("prioridad", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("fechaLecturaAcudiente",
            TableInfo.Column("fechaLecturaAcudiente", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionalNotificaciones.put("metadata", TableInfo.Column("metadata", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysInstitutionalNotificaciones: MutableSet<TableInfo.ForeignKey> =
            mutableSetOf()
        _foreignKeysInstitutionalNotificaciones.add(TableInfo.ForeignKey("acudientes", "SET NULL",
            "NO ACTION", listOf("idAcudiente"), listOf("idAcudiente")))
        val _indicesInstitutionalNotificaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoInstitutionalNotificaciones: TableInfo = TableInfo("institutional_notificaciones",
            _columnsInstitutionalNotificaciones, _foreignKeysInstitutionalNotificaciones,
            _indicesInstitutionalNotificaciones)
        val _existingInstitutionalNotificaciones: TableInfo = read(connection,
            "institutional_notificaciones")
        if (!_infoInstitutionalNotificaciones.equals(_existingInstitutionalNotificaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |institutional_notificaciones(com.sigeschool.data.local.entity.InstitutionalNotificationEntity).
              | Expected:
              |""".trimMargin() + _infoInstitutionalNotificaciones + """
              |
              | Found:
              |""".trimMargin() + _existingInstitutionalNotificaciones)
        }
        val _columnsCirculares: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCirculares.put("idCircular", TableInfo.Column("idCircular", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("titulo", TableInfo.Column("titulo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("contenido", TableInfo.Column("contenido", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("contenidoWhatsapp", TableInfo.Column("contenidoWhatsapp", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("contenidoEmail", TableInfo.Column("contenidoEmail", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("idUsuarioCreador", TableInfo.Column("idUsuarioCreador", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("fechaCreacion", TableInfo.Column("fechaCreacion", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("fechaProgramacion", TableInfo.Column("fechaProgramacion", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("destinatarios", TableInfo.Column("destinatarios", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("archivosAdjuntos", TableInfo.Column("archivosAdjuntos", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCirculares.put("fechaEnvio", TableInfo.Column("fechaEnvio", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCirculares: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCirculares: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCirculares: TableInfo = TableInfo("circulares", _columnsCirculares,
            _foreignKeysCirculares, _indicesCirculares)
        val _existingCirculares: TableInfo = read(connection, "circulares")
        if (!_infoCirculares.equals(_existingCirculares)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |circulares(com.sigeschool.data.local.entity.CircularEntity).
              | Expected:
              |""".trimMargin() + _infoCirculares + """
              |
              | Found:
              |""".trimMargin() + _existingCirculares)
        }
        val _columnsLogNotificaciones: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLogNotificaciones.put("idLog", TableInfo.Column("idLog", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("idNotificacion", TableInfo.Column("idNotificacion", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("canal", TableInfo.Column("canal", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("fechaIntento", TableInfo.Column("fechaIntento", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("codigoRespuesta", TableInfo.Column("codigoRespuesta",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("mensajeRespuesta", TableInfo.Column("mensajeRespuesta",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("exito", TableInfo.Column("exito", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogNotificaciones.put("intentos", TableInfo.Column("intentos", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLogNotificaciones: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysLogNotificaciones.add(TableInfo.ForeignKey("institutional_notificaciones",
            "CASCADE", "NO ACTION", listOf("idNotificacion"), listOf("idNotificacion")))
        val _indicesLogNotificaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLogNotificaciones: TableInfo = TableInfo("log_notificaciones",
            _columnsLogNotificaciones, _foreignKeysLogNotificaciones, _indicesLogNotificaciones)
        val _existingLogNotificaciones: TableInfo = read(connection, "log_notificaciones")
        if (!_infoLogNotificaciones.equals(_existingLogNotificaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |log_notificaciones(com.sigeschool.data.local.entity.LogNotificacionEntity).
              | Expected:
              |""".trimMargin() + _infoLogNotificaciones + """
              |
              | Found:
              |""".trimMargin() + _existingLogNotificaciones)
        }
        val _columnsImportaciones: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsImportaciones.put("idImportacion", TableInfo.Column("idImportacion", "TEXT", true,
            1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("nombreArchivo", TableInfo.Column("nombreArchivo", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("fechaImportacion", TableInfo.Column("fechaImportacion",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("idUsuarioImporto", TableInfo.Column("idUsuarioImporto", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("totalRegistros", TableInfo.Column("totalRegistros", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("registrosCreados", TableInfo.Column("registrosCreados",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("registrosActualizados", TableInfo.Column("registrosActualizados",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("errores", TableInfo.Column("errores", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("duplicados", TableInfo.Column("duplicados", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("usuariosCreados", TableInfo.Column("usuariosCreados", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("notificacionesEnviadas",
            TableInfo.Column("notificacionesEnviadas", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsImportaciones.put("detalleJson", TableInfo.Column("detalleJson", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysImportaciones: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesImportaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoImportaciones: TableInfo = TableInfo("importaciones", _columnsImportaciones,
            _foreignKeysImportaciones, _indicesImportaciones)
        val _existingImportaciones: TableInfo = read(connection, "importaciones")
        if (!_infoImportaciones.equals(_existingImportaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |importaciones(com.sigeschool.data.local.entity.ImportEntity).
              | Expected:
              |""".trimMargin() + _infoImportaciones + """
              |
              | Found:
              |""".trimMargin() + _existingImportaciones)
        }
        val _columnsImportacionesDetalle: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsImportacionesDetalle.put("idDetalle", TableInfo.Column("idDetalle", "TEXT", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportacionesDetalle.put("idImportacion", TableInfo.Column("idImportacion", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportacionesDetalle.put("fila", TableInfo.Column("fila", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsImportacionesDetalle.put("documento", TableInfo.Column("documento", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsImportacionesDetalle.put("accion", TableInfo.Column("accion", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsImportacionesDetalle.put("mensaje", TableInfo.Column("mensaje", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysImportacionesDetalle: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysImportacionesDetalle.add(TableInfo.ForeignKey("importaciones", "CASCADE",
            "NO ACTION", listOf("idImportacion"), listOf("idImportacion")))
        val _indicesImportacionesDetalle: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoImportacionesDetalle: TableInfo = TableInfo("importaciones_detalle",
            _columnsImportacionesDetalle, _foreignKeysImportacionesDetalle,
            _indicesImportacionesDetalle)
        val _existingImportacionesDetalle: TableInfo = read(connection, "importaciones_detalle")
        if (!_infoImportacionesDetalle.equals(_existingImportacionesDetalle)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |importaciones_detalle(com.sigeschool.data.local.entity.ImportDetailEntity).
              | Expected:
              |""".trimMargin() + _infoImportacionesDetalle + """
              |
              | Found:
              |""".trimMargin() + _existingImportacionesDetalle)
        }
        val _columnsLogsBackup: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLogsBackup.put("idLog", TableInfo.Column("idLog", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("fechaInicio", TableInfo.Column("fechaInicio", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("fechaFin", TableInfo.Column("fechaFin", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("tamanioBytes", TableInfo.Column("tamanioBytes", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("rutaArchivo", TableInfo.Column("rutaArchivo", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("errorMensaje", TableInfo.Column("errorMensaje", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("esManual", TableInfo.Column("esManual", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackup.put("metadata", TableInfo.Column("metadata", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLogsBackup: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLogsBackup: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLogsBackup: TableInfo = TableInfo("logs_backup", _columnsLogsBackup,
            _foreignKeysLogsBackup, _indicesLogsBackup)
        val _existingLogsBackup: TableInfo = read(connection, "logs_backup")
        if (!_infoLogsBackup.equals(_existingLogsBackup)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |logs_backup(com.sigeschool.data.local.entity.BackupLogEntity).
              | Expected:
              |""".trimMargin() + _infoLogsBackup + """
              |
              | Found:
              |""".trimMargin() + _existingLogsBackup)
        }
        val _columnsLogsBackupLlaves: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLogsBackupLlaves.put("idLog", TableInfo.Column("idLog", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackupLlaves.put("accion", TableInfo.Column("accion", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackupLlaves.put("fecha", TableInfo.Column("fecha", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackupLlaves.put("usuarioId", TableInfo.Column("usuarioId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackupLlaves.put("exito", TableInfo.Column("exito", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackupLlaves.put("mensajeError", TableInfo.Column("mensajeError", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLogsBackupLlaves.put("metadata", TableInfo.Column("metadata", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLogsBackupLlaves: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLogsBackupLlaves: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLogsBackupLlaves: TableInfo = TableInfo("logs_backup_llaves",
            _columnsLogsBackupLlaves, _foreignKeysLogsBackupLlaves, _indicesLogsBackupLlaves)
        val _existingLogsBackupLlaves: TableInfo = read(connection, "logs_backup_llaves")
        if (!_infoLogsBackupLlaves.equals(_existingLogsBackupLlaves)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |logs_backup_llaves(com.sigeschool.data.local.entity.KeyBackupLogEntity).
              | Expected:
              |""".trimMargin() + _infoLogsBackupLlaves + """
              |
              | Found:
              |""".trimMargin() + _existingLogsBackupLlaves)
        }
        val _columnsBankAccounts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBankAccounts.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("bankName", TableInfo.Column("bankName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("accountType", TableInfo.Column("accountType", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("accountNumber", TableInfo.Column("accountNumber", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("holderName", TableInfo.Column("holderName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("holderDni", TableInfo.Column("holderDni", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("notificationEmail", TableInfo.Column("notificationEmail", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("createdAt", TableInfo.Column("createdAt", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccounts.put("updatedAt", TableInfo.Column("updatedAt", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBankAccounts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBankAccounts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBankAccounts: TableInfo = TableInfo("bank_accounts", _columnsBankAccounts,
            _foreignKeysBankAccounts, _indicesBankAccounts)
        val _existingBankAccounts: TableInfo = read(connection, "bank_accounts")
        if (!_infoBankAccounts.equals(_existingBankAccounts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |bank_accounts(com.sigeschool.data.local.entity.billing.BankAccountEntity).
              | Expected:
              |""".trimMargin() + _infoBankAccounts + """
              |
              | Found:
              |""".trimMargin() + _existingBankAccounts)
        }
        val _columnsBankAccountHistory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBankAccountHistory.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccountHistory.put("accountId", TableInfo.Column("accountId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccountHistory.put("userId", TableInfo.Column("userId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccountHistory.put("action", TableInfo.Column("action", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccountHistory.put("previousData", TableInfo.Column("previousData", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBankAccountHistory.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBankAccountHistory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBankAccountHistory: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBankAccountHistory: TableInfo = TableInfo("bank_account_history",
            _columnsBankAccountHistory, _foreignKeysBankAccountHistory, _indicesBankAccountHistory)
        val _existingBankAccountHistory: TableInfo = read(connection, "bank_account_history")
        if (!_infoBankAccountHistory.equals(_existingBankAccountHistory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |bank_account_history(com.sigeschool.data.local.entity.billing.BankAccountHistoryEntity).
              | Expected:
              |""".trimMargin() + _infoBankAccountHistory + """
              |
              | Found:
              |""".trimMargin() + _existingBankAccountHistory)
        }
        val _columnsAutoevaluaciones: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAutoevaluaciones.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAutoevaluaciones.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutoevaluaciones.put("subjectId", TableInfo.Column("subjectId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutoevaluaciones.put("periodId", TableInfo.Column("periodId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAutoevaluaciones.put("score", TableInfo.Column("score", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAutoevaluaciones.put("registrationDate", TableInfo.Column("registrationDate",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutoevaluaciones.put("metadata", TableInfo.Column("metadata", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAutoevaluaciones: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysAutoevaluaciones.add(TableInfo.ForeignKey("students", "CASCADE", "NO ACTION",
            listOf("studentId"), listOf("id")))
        val _indicesAutoevaluaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAutoevaluaciones: TableInfo = TableInfo("autoevaluaciones",
            _columnsAutoevaluaciones, _foreignKeysAutoevaluaciones, _indicesAutoevaluaciones)
        val _existingAutoevaluaciones: TableInfo = read(connection, "autoevaluaciones")
        if (!_infoAutoevaluaciones.equals(_existingAutoevaluaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |autoevaluaciones(com.sigeschool.data.local.entity.sie.AutoevaluacionEntity).
              | Expected:
              |""".trimMargin() + _infoAutoevaluaciones + """
              |
              | Found:
              |""".trimMargin() + _existingAutoevaluaciones)
        }
        val _columnsConfiguracionPromocion: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConfiguracionPromocion.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionPromocion.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionPromocion.put("maxFailedSubjects",
            TableInfo.Column("maxFailedSubjects", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionPromocion.put("maxInattendancePercentage",
            TableInfo.Column("maxInattendancePercentage", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionPromocion.put("minimumPassingScore",
            TableInfo.Column("minimumPassingScore", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionPromocion.put("autoevaluacionWeight",
            TableInfo.Column("autoevaluacionWeight", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConfiguracionPromocion: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesConfiguracionPromocion: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoConfiguracionPromocion: TableInfo = TableInfo("configuracion_promocion",
            _columnsConfiguracionPromocion, _foreignKeysConfiguracionPromocion,
            _indicesConfiguracionPromocion)
        val _existingConfiguracionPromocion: TableInfo = read(connection, "configuracion_promocion")
        if (!_infoConfiguracionPromocion.equals(_existingConfiguracionPromocion)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |configuracion_promocion(com.sigeschool.data.local.entity.sie.PromotionConfigEntity).
              | Expected:
              |""".trimMargin() + _infoConfiguracionPromocion + """
              |
              | Found:
              |""".trimMargin() + _existingConfiguracionPromocion)
        }
        var _result: RoomOpenDelegate.ValidationResult
        _result = onValidateSchema2(connection)
        if (!_result.isValid) {
          return _result
        }
        _result = onValidateSchema3(connection)
        if (!_result.isValid) {
          return _result
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }

      private fun onValidateSchema2(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsAcademicNivelesEducativos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicNivelesEducativos.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicNivelesEducativos.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicNivelesEducativos.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicNivelesEducativos.put("descripcion", TableInfo.Column("descripcion", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicNivelesEducativos.put("orden", TableInfo.Column("orden", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicNivelesEducativos.put("icono", TableInfo.Column("icono", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicNivelesEducativos.put("syncStatus", TableInfo.Column("syncStatus",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicNivelesEducativos.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicNivelesEducativos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicNivelesEducativos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicNivelesEducativos: TableInfo = TableInfo("academic_niveles_educativos",
            _columnsAcademicNivelesEducativos, _foreignKeysAcademicNivelesEducativos,
            _indicesAcademicNivelesEducativos)
        val _existingAcademicNivelesEducativos: TableInfo = read(connection,
            "academic_niveles_educativos")
        if (!_infoAcademicNivelesEducativos.equals(_existingAcademicNivelesEducativos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_niveles_educativos(com.sigeschool.data.local.entity.NivelEducativoEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicNivelesEducativos + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicNivelesEducativos)
        }
        val _columnsAcademicGrados: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicGrados.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("nivelEducativoId", TableInfo.Column("nivelEducativoId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("descripcion", TableInfo.Column("descripcion", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("orden", TableInfo.Column("orden", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("esActivo", TableInfo.Column("esActivo", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicGrados.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicGrados: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicGrados: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicGrados: TableInfo = TableInfo("academic_grados", _columnsAcademicGrados,
            _foreignKeysAcademicGrados, _indicesAcademicGrados)
        val _existingAcademicGrados: TableInfo = read(connection, "academic_grados")
        if (!_infoAcademicGrados.equals(_existingAcademicGrados)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_grados(com.sigeschool.data.local.entity.AcademicGradoEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicGrados + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicGrados)
        }
        val _columnsAcademicPeriodos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicPeriodos.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("fechaInicio", TableInfo.Column("fechaInicio", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("fechaFin", TableInfo.Column("fechaFin", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("duracionMeses", TableInfo.Column("duracionMeses", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("numeroCortes", TableInfo.Column("numeroCortes", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("esActivo", TableInfo.Column("esActivo", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPeriodos.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicPeriodos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicPeriodos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicPeriodos: TableInfo = TableInfo("academic_periodos",
            _columnsAcademicPeriodos, _foreignKeysAcademicPeriodos, _indicesAcademicPeriodos)
        val _existingAcademicPeriodos: TableInfo = read(connection, "academic_periodos")
        if (!_infoAcademicPeriodos.equals(_existingAcademicPeriodos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_periodos(com.sigeschool.data.local.entity.PeriodoAcademicoEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicPeriodos + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicPeriodos)
        }
        val _columnsPeriodoConfiguracion: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPeriodoConfiguracion.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPeriodoConfiguracion.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeriodoConfiguracion.put("periodoAcademicoId",
            TableInfo.Column("periodoAcademicoId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPeriodoConfiguracion.put("tipoConcepto", TableInfo.Column("tipoConcepto", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeriodoConfiguracion.put("conceptoId", TableInfo.Column("conceptoId", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeriodoConfiguracion.put("aplicarCada", TableInfo.Column("aplicarCada", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeriodoConfiguracion.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeriodoConfiguracion.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPeriodoConfiguracion: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPeriodoConfiguracion: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPeriodoConfiguracion: TableInfo = TableInfo("periodo_configuracion",
            _columnsPeriodoConfiguracion, _foreignKeysPeriodoConfiguracion,
            _indicesPeriodoConfiguracion)
        val _existingPeriodoConfiguracion: TableInfo = read(connection, "periodo_configuracion")
        if (!_infoPeriodoConfiguracion.equals(_existingPeriodoConfiguracion)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |periodo_configuracion(com.sigeschool.data.local.entity.PeriodoConfiguracionEntity).
              | Expected:
              |""".trimMargin() + _infoPeriodoConfiguracion + """
              |
              | Found:
              |""".trimMargin() + _existingPeriodoConfiguracion)
        }
        val _columnsAcademicAreasConocimiento: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicAreasConocimiento.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAreasConocimiento.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAreasConocimiento.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAreasConocimiento.put("descripcion", TableInfo.Column("descripcion", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAreasConocimiento.put("syncStatus", TableInfo.Column("syncStatus",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAreasConocimiento.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicAreasConocimiento: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicAreasConocimiento: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicAreasConocimiento: TableInfo = TableInfo("academic_areas_conocimiento",
            _columnsAcademicAreasConocimiento, _foreignKeysAcademicAreasConocimiento,
            _indicesAcademicAreasConocimiento)
        val _existingAcademicAreasConocimiento: TableInfo = read(connection,
            "academic_areas_conocimiento")
        if (!_infoAcademicAreasConocimiento.equals(_existingAcademicAreasConocimiento)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_areas_conocimiento(com.sigeschool.data.local.entity.AreaConocimientoEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicAreasConocimiento + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicAreasConocimiento)
        }
        val _columnsAcademicAsignaturas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicAsignaturas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("areaConocimientoId", TableInfo.Column("areaConocimientoId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("codigo", TableInfo.Column("codigo", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("descripcion", TableInfo.Column("descripcion", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("intensidadHoraria", TableInfo.Column("intensidadHoraria",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("esElectiva", TableInfo.Column("esElectiva", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAsignaturas.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicAsignaturas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicAsignaturas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicAsignaturas: TableInfo = TableInfo("academic_asignaturas",
            _columnsAcademicAsignaturas, _foreignKeysAcademicAsignaturas,
            _indicesAcademicAsignaturas)
        val _existingAcademicAsignaturas: TableInfo = read(connection, "academic_asignaturas")
        if (!_infoAcademicAsignaturas.equals(_existingAcademicAsignaturas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_asignaturas(com.sigeschool.data.local.entity.AsignaturaEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicAsignaturas + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicAsignaturas)
        }
        val _columnsAcademicOfertas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicOfertas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("gradoId", TableInfo.Column("gradoId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("periodoAcademicoId", TableInfo.Column("periodoAcademicoId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("fechaInicio", TableInfo.Column("fechaInicio", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("fechaFin", TableInfo.Column("fechaFin", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicOfertas.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicOfertas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicOfertas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicOfertas: TableInfo = TableInfo("academic_ofertas", _columnsAcademicOfertas,
            _foreignKeysAcademicOfertas, _indicesAcademicOfertas)
        val _existingAcademicOfertas: TableInfo = read(connection, "academic_ofertas")
        if (!_infoAcademicOfertas.equals(_existingAcademicOfertas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_ofertas(com.sigeschool.data.local.entity.OfertaAcademicaEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicOfertas + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicOfertas)
        }
        val _columnsAcademicDetallesOferta: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicDetallesOferta.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("ofertaAcademicaId",
            TableInfo.Column("ofertaAcademicaId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("asignaturaId", TableInfo.Column("asignaturaId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("docenteId", TableInfo.Column("docenteId", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("intensidadHoraria",
            TableInfo.Column("intensidadHoraria", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("aula", TableInfo.Column("aula", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicDetallesOferta.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicDetallesOferta: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicDetallesOferta: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicDetallesOferta: TableInfo = TableInfo("academic_detalles_oferta",
            _columnsAcademicDetallesOferta, _foreignKeysAcademicDetallesOferta,
            _indicesAcademicDetallesOferta)
        val _existingAcademicDetallesOferta: TableInfo = read(connection,
            "academic_detalles_oferta")
        if (!_infoAcademicDetallesOferta.equals(_existingAcademicDetallesOferta)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_detalles_oferta(com.sigeschool.data.local.entity.DetalleOfertaAcademicaEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicDetallesOferta + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicDetallesOferta)
        }
        val _columnsAcademicClases: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicClases.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("ofertaAcademicaId", TableInfo.Column("ofertaAcademicaId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("detalleOfertaId", TableInfo.Column("detalleOfertaId", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("horario", TableInfo.Column("horario", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("capacidadMaxima", TableInfo.Column("capacidadMaxima", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("estudiantesInscritos", TableInfo.Column("estudiantesInscritos",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicClases.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicClases: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicClases: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicClases: TableInfo = TableInfo("academic_clases", _columnsAcademicClases,
            _foreignKeysAcademicClases, _indicesAcademicClases)
        val _existingAcademicClases: TableInfo = read(connection, "academic_clases")
        if (!_infoAcademicClases.equals(_existingAcademicClases)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_clases(com.sigeschool.data.local.entity.ClaseEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicClases + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicClases)
        }
        val _columnsAcademicMatriculas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicMatriculas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicMatriculas.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicMatriculas.put("estudianteId", TableInfo.Column("estudianteId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicMatriculas.put("claseId", TableInfo.Column("claseId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicMatriculas.put("fechaMatricula", TableInfo.Column("fechaMatricula",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicMatriculas.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicMatriculas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicMatriculas.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicMatriculas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicMatriculas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicMatriculas: TableInfo = TableInfo("academic_matriculas",
            _columnsAcademicMatriculas, _foreignKeysAcademicMatriculas, _indicesAcademicMatriculas)
        val _existingAcademicMatriculas: TableInfo = read(connection, "academic_matriculas")
        if (!_infoAcademicMatriculas.equals(_existingAcademicMatriculas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_matriculas(com.sigeschool.data.local.entity.MatriculaEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicMatriculas + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicMatriculas)
        }
        val _columnsAcademicPlanesEstudios: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicPlanesEstudios.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudios.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudios.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudios.put("descripcion", TableInfo.Column("descripcion", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudios.put("version", TableInfo.Column("version", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudios.put("vigente", TableInfo.Column("vigente", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudios.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudios.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicPlanesEstudios: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicPlanesEstudios: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicPlanesEstudios: TableInfo = TableInfo("academic_planes_estudios",
            _columnsAcademicPlanesEstudios, _foreignKeysAcademicPlanesEstudios,
            _indicesAcademicPlanesEstudios)
        val _existingAcademicPlanesEstudios: TableInfo = read(connection,
            "academic_planes_estudios")
        if (!_infoAcademicPlanesEstudios.equals(_existingAcademicPlanesEstudios)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_planes_estudios(com.sigeschool.data.local.entity.PlanEstudiosEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicPlanesEstudios + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicPlanesEstudios)
        }
        val _columnsAcademicPlanesEstudiosDetalle: MutableMap<String, TableInfo.Column> =
            mutableMapOf()
        _columnsAcademicPlanesEstudiosDetalle.put("id", TableInfo.Column("id", "INTEGER", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("planEstudiosId",
            TableInfo.Column("planEstudiosId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("gradoId", TableInfo.Column("gradoId", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("asignaturaId", TableInfo.Column("asignaturaId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("intensidadHorariaMinima",
            TableInfo.Column("intensidadHorariaMinima", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("esObligatoria", TableInfo.Column("esObligatoria",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("syncStatus", TableInfo.Column("syncStatus",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesEstudiosDetalle.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicPlanesEstudiosDetalle: MutableSet<TableInfo.ForeignKey> =
            mutableSetOf()
        val _indicesAcademicPlanesEstudiosDetalle: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicPlanesEstudiosDetalle: TableInfo =
            TableInfo("academic_planes_estudios_detalle", _columnsAcademicPlanesEstudiosDetalle,
            _foreignKeysAcademicPlanesEstudiosDetalle, _indicesAcademicPlanesEstudiosDetalle)
        val _existingAcademicPlanesEstudiosDetalle: TableInfo = read(connection,
            "academic_planes_estudios_detalle")
        if (!_infoAcademicPlanesEstudiosDetalle.equals(_existingAcademicPlanesEstudiosDetalle)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_planes_estudios_detalle(com.sigeschool.data.local.entity.PlanEstudiosDetalleEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicPlanesEstudiosDetalle + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicPlanesEstudiosDetalle)
        }
        val _columnsAcademicPlanesAula: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicPlanesAula.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("claseId", TableInfo.Column("claseId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("docenteId", TableInfo.Column("docenteId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("competencias", TableInfo.Column("competencias", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("logros", TableInfo.Column("logros", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("indicadores", TableInfo.Column("indicadores", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("recursos", TableInfo.Column("recursos", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("metodologia", TableInfo.Column("metodologia", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("evaluacion", TableInfo.Column("evaluacion", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicPlanesAula.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicPlanesAula: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicPlanesAula: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicPlanesAula: TableInfo = TableInfo("academic_planes_aula",
            _columnsAcademicPlanesAula, _foreignKeysAcademicPlanesAula, _indicesAcademicPlanesAula)
        val _existingAcademicPlanesAula: TableInfo = read(connection, "academic_planes_aula")
        if (!_infoAcademicPlanesAula.equals(_existingAcademicPlanesAula)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_planes_aula(com.sigeschool.data.local.entity.PlanAulaEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicPlanesAula + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicPlanesAula)
        }
        val _columnsAcademicAulas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicAulas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAulas.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAulas.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAulas.put("capacidad", TableInfo.Column("capacidad", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAulas.put("descripcion", TableInfo.Column("descripcion", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAulas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicAulas.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicAulas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicAulas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicAulas: TableInfo = TableInfo("academic_aulas", _columnsAcademicAulas,
            _foreignKeysAcademicAulas, _indicesAcademicAulas)
        val _existingAcademicAulas: TableInfo = read(connection, "academic_aulas")
        if (!_infoAcademicAulas.equals(_existingAcademicAulas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_aulas(com.sigeschool.data.local.entity.AulaEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicAulas + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicAulas)
        }
        val _columnsAcademicHorarios: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicHorarios.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("claseId", TableInfo.Column("claseId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("diaSemana", TableInfo.Column("diaSemana", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("horaInicio", TableInfo.Column("horaInicio", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("horaFin", TableInfo.Column("horaFin", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("aulaId", TableInfo.Column("aulaId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicHorarios.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicHorarios: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicHorarios: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicHorarios: TableInfo = TableInfo("academic_horarios",
            _columnsAcademicHorarios, _foreignKeysAcademicHorarios, _indicesAcademicHorarios)
        val _existingAcademicHorarios: TableInfo = read(connection, "academic_horarios")
        if (!_infoAcademicHorarios.equals(_existingAcademicHorarios)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_horarios(com.sigeschool.data.local.entity.HorarioEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicHorarios + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicHorarios)
        }
        val _columnsAcademicCalificaciones: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicCalificaciones.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("estudianteId", TableInfo.Column("estudianteId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("claseId", TableInfo.Column("claseId", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("periodoAcademicoId",
            TableInfo.Column("periodoAcademicoId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("corte", TableInfo.Column("corte", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("nota", TableInfo.Column("nota", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("observacion", TableInfo.Column("observacion", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCalificaciones.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicCalificaciones: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicCalificaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicCalificaciones: TableInfo = TableInfo("academic_calificaciones",
            _columnsAcademicCalificaciones, _foreignKeysAcademicCalificaciones,
            _indicesAcademicCalificaciones)
        val _existingAcademicCalificaciones: TableInfo = read(connection, "academic_calificaciones")
        if (!_infoAcademicCalificaciones.equals(_existingAcademicCalificaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_calificaciones(com.sigeschool.data.local.entity.CalificacionEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicCalificaciones + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicCalificaciones)
        }
        val _columnsAuditLogs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAuditLogs.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("userId", TableInfo.Column("userId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("userName", TableInfo.Column("userName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("userRole", TableInfo.Column("userRole", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("action", TableInfo.Column("action", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("entityName", TableInfo.Column("entityName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("entityId", TableInfo.Column("entityId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("details", TableInfo.Column("details", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAuditLogs.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAuditLogs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAuditLogs: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAuditLogs: TableInfo = TableInfo("audit_logs", _columnsAuditLogs,
            _foreignKeysAuditLogs, _indicesAuditLogs)
        val _existingAuditLogs: TableInfo = read(connection, "audit_logs")
        if (!_infoAuditLogs.equals(_existingAuditLogs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |audit_logs(com.sigeschool.data.local.entity.AuditLogEntity).
              | Expected:
              |""".trimMargin() + _infoAuditLogs + """
              |
              | Found:
              |""".trimMargin() + _existingAuditLogs)
        }
        val _columnsDocenteSyncConfigs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDocenteSyncConfigs.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("docenteId", TableInfo.Column("docenteId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("claseId", TableInfo.Column("claseId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("url", TableInfo.Column("url", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("classroomCourseId", TableInfo.Column("classroomCourseId",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("classroomCourseWorkId",
            TableInfo.Column("classroomCourseWorkId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("syncIntervalHours", TableInfo.Column("syncIntervalHours",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("lastSyncTimestamp", TableInfo.Column("lastSyncTimestamp",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncConfigs.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDocenteSyncConfigs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDocenteSyncConfigs: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDocenteSyncConfigs: TableInfo = TableInfo("docente_sync_configs",
            _columnsDocenteSyncConfigs, _foreignKeysDocenteSyncConfigs, _indicesDocenteSyncConfigs)
        val _existingDocenteSyncConfigs: TableInfo = read(connection, "docente_sync_configs")
        if (!_infoDocenteSyncConfigs.equals(_existingDocenteSyncConfigs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |docente_sync_configs(com.sigeschool.data.local.entity.DocenteSyncConfigEntity).
              | Expected:
              |""".trimMargin() + _infoDocenteSyncConfigs + """
              |
              | Found:
              |""".trimMargin() + _existingDocenteSyncConfigs)
        }
        val _columnsDocenteSyncLogs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDocenteSyncLogs.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncLogs.put("configId", TableInfo.Column("configId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncLogs.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncLogs.put("result", TableInfo.Column("result", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncLogs.put("message", TableInfo.Column("message", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocenteSyncLogs.put("itemsProcessed", TableInfo.Column("itemsProcessed", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDocenteSyncLogs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDocenteSyncLogs: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDocenteSyncLogs: TableInfo = TableInfo("docente_sync_logs",
            _columnsDocenteSyncLogs, _foreignKeysDocenteSyncLogs, _indicesDocenteSyncLogs)
        val _existingDocenteSyncLogs: TableInfo = read(connection, "docente_sync_logs")
        if (!_infoDocenteSyncLogs.equals(_existingDocenteSyncLogs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |docente_sync_logs(com.sigeschool.data.local.entity.DocenteSyncLogEntity).
              | Expected:
              |""".trimMargin() + _infoDocenteSyncLogs + """
              |
              | Found:
              |""".trimMargin() + _existingDocenteSyncLogs)
        }
        val _columnsDocumentosInstitucionales: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDocumentosInstitucionales.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentosInstitucionales.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentosInstitucionales.put("titulo", TableInfo.Column("titulo", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentosInstitucionales.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentosInstitucionales.put("rutaArchivo", TableInfo.Column("rutaArchivo", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentosInstitucionales.put("fechaSubida", TableInfo.Column("fechaSubida",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentosInstitucionales.put("syncStatus", TableInfo.Column("syncStatus",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDocumentosInstitucionales.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDocumentosInstitucionales: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDocumentosInstitucionales: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDocumentosInstitucionales: TableInfo = TableInfo("documentos_institucionales",
            _columnsDocumentosInstitucionales, _foreignKeysDocumentosInstitucionales,
            _indicesDocumentosInstitucionales)
        val _existingDocumentosInstitucionales: TableInfo = read(connection,
            "documentos_institucionales")
        if (!_infoDocumentosInstitucionales.equals(_existingDocumentosInstitucionales)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |documentos_institucionales(com.sigeschool.data.local.entity.DocumentoInstitucionalEntity).
              | Expected:
              |""".trimMargin() + _infoDocumentosInstitucionales + """
              |
              | Found:
              |""".trimMargin() + _existingDocumentosInstitucionales)
        }
        val _columnsInstitutions: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsInstitutions.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("address", TableInfo.Column("address", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("phone", TableInfo.Column("phone", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("email", TableInfo.Column("email", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("website", TableInfo.Column("website", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("slogan", TableInfo.Column("slogan", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("logoUri", TableInfo.Column("logoUri", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("updatedAt", TableInfo.Column("updatedAt", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, "1",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("planId", TableInfo.Column("planId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutions.put("estudiantesActivos", TableInfo.Column("estudiantesActivos",
            "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysInstitutions: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesInstitutions: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoInstitutions: TableInfo = TableInfo("institutions", _columnsInstitutions,
            _foreignKeysInstitutions, _indicesInstitutions)
        val _existingInstitutions: TableInfo = read(connection, "institutions")
        if (!_infoInstitutions.equals(_existingInstitutions)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |institutions(com.sigeschool.data.local.entity.InstitutionEntity).
              | Expected:
              |""".trimMargin() + _infoInstitutions + """
              |
              | Found:
              |""".trimMargin() + _existingInstitutions)
        }
        val _columnsInstitutionSettings: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsInstitutionSettings.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("syncUrl", TableInfo.Column("syncUrl", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("isSyncEnabled", TableInfo.Column("isSyncEnabled",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("syncFrequencyHours", TableInfo.Column("syncFrequencyHours",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("lastSyncTimestamp", TableInfo.Column("lastSyncTimestamp",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("lastSyncStatus", TableInfo.Column("lastSyncStatus", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("lastSyncMessage", TableInfo.Column("lastSyncMessage",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionSettings.put("downloadUrl", TableInfo.Column("downloadUrl", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysInstitutionSettings: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesInstitutionSettings: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoInstitutionSettings: TableInfo = TableInfo("institution_settings",
            _columnsInstitutionSettings, _foreignKeysInstitutionSettings,
            _indicesInstitutionSettings)
        val _existingInstitutionSettings: TableInfo = read(connection, "institution_settings")
        if (!_infoInstitutionSettings.equals(_existingInstitutionSettings)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |institution_settings(com.sigeschool.data.local.entity.InstitutionSettingsEntity).
              | Expected:
              |""".trimMargin() + _infoInstitutionSettings + """
              |
              | Found:
              |""".trimMargin() + _existingInstitutionSettings)
        }
        val _columnsInstitutionThemes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsInstitutionThemes.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("primaryColor", TableInfo.Column("primaryColor", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("secondaryColor", TableInfo.Column("secondaryColor",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("accentColor", TableInfo.Column("accentColor", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("backgroundColor", TableInfo.Column("backgroundColor",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("textColor", TableInfo.Column("textColor", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("isDarkMode", TableInfo.Column("isDarkMode", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("themeMode", TableInfo.Column("themeMode", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("presetName", TableInfo.Column("presetName", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("extractedFromLogo", TableInfo.Column("extractedFromLogo",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsInstitutionThemes.put("lastUpdated", TableInfo.Column("lastUpdated", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysInstitutionThemes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesInstitutionThemes: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoInstitutionThemes: TableInfo = TableInfo("institution_themes",
            _columnsInstitutionThemes, _foreignKeysInstitutionThemes, _indicesInstitutionThemes)
        val _existingInstitutionThemes: TableInfo = read(connection, "institution_themes")
        if (!_infoInstitutionThemes.equals(_existingInstitutionThemes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |institution_themes(com.sigeschool.data.local.entity.InstitutionThemeEntity).
              | Expected:
              |""".trimMargin() + _infoInstitutionThemes + """
              |
              | Found:
              |""".trimMargin() + _existingInstitutionThemes)
        }
        val _columnsListadoConfig: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsListadoConfig.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsListadoConfig.put("tamanoPapel", TableInfo.Column("tamanoPapel", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsListadoConfig.put("incluirLogo", TableInfo.Column("incluirLogo", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsListadoConfig.put("incluirFirmas", TableInfo.Column("incluirFirmas", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsListadoConfig.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsListadoConfig.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysListadoConfig: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesListadoConfig: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoListadoConfig: TableInfo = TableInfo("listado_config", _columnsListadoConfig,
            _foreignKeysListadoConfig, _indicesListadoConfig)
        val _existingListadoConfig: TableInfo = read(connection, "listado_config")
        if (!_infoListadoConfig.equals(_existingListadoConfig)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |listado_config(com.sigeschool.data.local.entity.ListadoConfigEntity).
              | Expected:
              |""".trimMargin() + _infoListadoConfig + """
              |
              | Found:
              |""".trimMargin() + _existingListadoConfig)
        }
        val _columnsPlanes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlanes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("descripcion", TableInfo.Column("descripcion", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("limiteEstudiantes", TableInfo.Column("limiteEstudiantes", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("precioMensual", TableInfo.Column("precioMensual", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("precioAnual", TableInfo.Column("precioAnual", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("incluyeNomina", TableInfo.Column("incluyeNomina", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("incluyeCarnets", TableInfo.Column("incluyeCarnets", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("incluyeBI", TableInfo.Column("incluyeBI", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("incluyeSoportePrioritario",
            TableInfo.Column("incluyeSoportePrioritario", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("incluyeAPI", TableInfo.Column("incluyeAPI", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("incluyeGestorDedicado", TableInfo.Column("incluyeGestorDedicado",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("incluyeImplementacionGuiada",
            TableInfo.Column("incluyeImplementacionGuiada", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("capacitaciones", TableInfo.Column("capacitaciones", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("activo", TableInfo.Column("activo", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlanes.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlanes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlanes: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPlanes.add(TableInfo.Index("idx_planes_nombre", false, listOf("nombre"),
            listOf("ASC")))
        val _infoPlanes: TableInfo = TableInfo("planes", _columnsPlanes, _foreignKeysPlanes,
            _indicesPlanes)
        val _existingPlanes: TableInfo = read(connection, "planes")
        if (!_infoPlanes.equals(_existingPlanes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |planes(com.sigeschool.data.local.entity.PlanEntity).
              | Expected:
              |""".trimMargin() + _infoPlanes + """
              |
              | Found:
              |""".trimMargin() + _existingPlanes)
        }
        val _columnsSuscripciones: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSuscripciones.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("planId", TableInfo.Column("planId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("fechaInicio", TableInfo.Column("fechaInicio", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("fechaFin", TableInfo.Column("fechaFin", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("periodoFacturacion", TableInfo.Column("periodoFacturacion",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("ultimoPagoFecha", TableInfo.Column("ultimoPagoFecha", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("proximoPagoFecha", TableInfo.Column("proximoPagoFecha",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSuscripciones.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSuscripciones: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSuscripciones: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSuscripciones.add(TableInfo.Index("idx_suscripciones_institution", false,
            listOf("institutionId"), listOf("ASC")))
        val _infoSuscripciones: TableInfo = TableInfo("suscripciones", _columnsSuscripciones,
            _foreignKeysSuscripciones, _indicesSuscripciones)
        val _existingSuscripciones: TableInfo = read(connection, "suscripciones")
        if (!_infoSuscripciones.equals(_existingSuscripciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |suscripciones(com.sigeschool.data.local.entity.SuscripcionEntity).
              | Expected:
              |""".trimMargin() + _infoSuscripciones + """
              |
              | Found:
              |""".trimMargin() + _existingSuscripciones)
        }
        val _columnsAccessLogs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAccessLogs.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("scannedByUserId", TableInfo.Column("scannedByUserId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("scannedByUserName", TableInfo.Column("scannedByUserName", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("accessTime", TableInfo.Column("accessTime", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("result", TableInfo.Column("result", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("reason", TableInfo.Column("reason", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccessLogs.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAccessLogs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAccessLogs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesAccessLogs.add(TableInfo.Index("idx_access_student", false, listOf("studentId",
            "institutionId"), listOf("ASC", "ASC")))
        _indicesAccessLogs.add(TableInfo.Index("idx_access_time", false, listOf("accessTime",
            "institutionId"), listOf("ASC", "ASC")))
        val _infoAccessLogs: TableInfo = TableInfo("access_logs", _columnsAccessLogs,
            _foreignKeysAccessLogs, _indicesAccessLogs)
        val _existingAccessLogs: TableInfo = read(connection, "access_logs")
        if (!_infoAccessLogs.equals(_existingAccessLogs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |access_logs(com.sigeschool.data.local.entity.AccessLogEntity).
              | Expected:
              |""".trimMargin() + _infoAccessLogs + """
              |
              | Found:
              |""".trimMargin() + _existingAccessLogs)
        }
        val _columnsAlertasInasistencia: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAlertasInasistencia.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("estudianteId", TableInfo.Column("estudianteId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("acudienteId", TableInfo.Column("acudienteId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("directorCursoId", TableInfo.Column("directorCursoId",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("jefeAreaId", TableInfo.Column("jefeAreaId", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("coordinadorId", TableInfo.Column("coordinadorId", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("inasistenciasConsecutivas",
            TableInfo.Column("inasistenciasConsecutivas", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("diasSemana", TableInfo.Column("diasSemana", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("semanaInicio", TableInfo.Column("semanaInicio", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("semanaFin", TableInfo.Column("semanaFin", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("nivelAlerta", TableInfo.Column("nivelAlerta", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("fechaAlerta", TableInfo.Column("fechaAlerta", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("fechaResolucion", TableInfo.Column("fechaResolucion",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("observaciones", TableInfo.Column("observaciones", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasInasistencia.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAlertasInasistencia: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAlertasInasistencia: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesAlertasInasistencia.add(TableInfo.Index("idx_alertas_estudiante", false,
            listOf("estudianteId", "institutionId"), listOf("ASC", "ASC")))
        _indicesAlertasInasistencia.add(TableInfo.Index("idx_alertas_estado", false,
            listOf("estado", "institutionId"), listOf("ASC", "ASC")))
        val _infoAlertasInasistencia: TableInfo = TableInfo("alertas_inasistencia",
            _columnsAlertasInasistencia, _foreignKeysAlertasInasistencia,
            _indicesAlertasInasistencia)
        val _existingAlertasInasistencia: TableInfo = read(connection, "alertas_inasistencia")
        if (!_infoAlertasInasistencia.equals(_existingAlertasInasistencia)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |alertas_inasistencia(com.sigeschool.data.local.entity.AlertaInasistenciaEntity).
              | Expected:
              |""".trimMargin() + _infoAlertasInasistencia + """
              |
              | Found:
              |""".trimMargin() + _existingAlertasInasistencia)
        }
        val _columnsAlertasTempranas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAlertasTempranas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("nivel", TableInfo.Column("nivel", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("descripcion", TableInfo.Column("descripcion", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("fechaDeteccion", TableInfo.Column("fechaDeteccion", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("atendidaPor", TableInfo.Column("atendidaPor", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("fechaAtencion", TableInfo.Column("fechaAtencion", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("observaciones", TableInfo.Column("observaciones", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlertasTempranas.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAlertasTempranas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAlertasTempranas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAlertasTempranas: TableInfo = TableInfo("alertas_tempranas",
            _columnsAlertasTempranas, _foreignKeysAlertasTempranas, _indicesAlertasTempranas)
        val _existingAlertasTempranas: TableInfo = read(connection, "alertas_tempranas")
        if (!_infoAlertasTempranas.equals(_existingAlertasTempranas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |alertas_tempranas(com.sigeschool.data.local.entity.AlertaTempranaEntity).
              | Expected:
              |""".trimMargin() + _infoAlertasTempranas + """
              |
              | Found:
              |""".trimMargin() + _existingAlertasTempranas)
        }
        val _columnsCitas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCitas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("docenteId", TableInfo.Column("docenteId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("acudienteId", TableInfo.Column("acudienteId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("estudianteId", TableInfo.Column("estudianteId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("fechaCita", TableInfo.Column("fechaCita", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("motivo", TableInfo.Column("motivo", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("observaciones", TableInfo.Column("observaciones", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCitas.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCitas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCitas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCitas: TableInfo = TableInfo("citas", _columnsCitas, _foreignKeysCitas,
            _indicesCitas)
        val _existingCitas: TableInfo = read(connection, "citas")
        if (!_infoCitas.equals(_existingCitas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |citas(com.sigeschool.data.local.entity.CitaEntity).
              | Expected:
              |""".trimMargin() + _infoCitas + """
              |
              | Found:
              |""".trimMargin() + _existingCitas)
        }
        val _columnsConfiguracionAlerta: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConfiguracionAlerta.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("inasistenciasConsecutivasParaAlerta",
            TableInfo.Column("inasistenciasConsecutivasParaAlerta", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("diasSemanaUmbral", TableInfo.Column("diasSemanaUmbral",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("semanasConsecutivasPatron",
            TableInfo.Column("semanasConsecutivasPatron", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("nivelAlertaAcudiente",
            TableInfo.Column("nivelAlertaAcudiente", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("nivelAlertaDirector",
            TableInfo.Column("nivelAlertaDirector", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("nivelAlertaJefeArea",
            TableInfo.Column("nivelAlertaJefeArea", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("nivelAlertaCoordinador",
            TableInfo.Column("nivelAlertaCoordinador", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlerta.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConfiguracionAlerta: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesConfiguracionAlerta: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoConfiguracionAlerta: TableInfo = TableInfo("configuracion_alerta",
            _columnsConfiguracionAlerta, _foreignKeysConfiguracionAlerta,
            _indicesConfiguracionAlerta)
        val _existingConfiguracionAlerta: TableInfo = read(connection, "configuracion_alerta")
        if (!_infoConfiguracionAlerta.equals(_existingConfiguracionAlerta)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |configuracion_alerta(com.sigeschool.data.local.entity.ConfiguracionAlertaEntity).
              | Expected:
              |""".trimMargin() + _infoConfiguracionAlerta + """
              |
              | Found:
              |""".trimMargin() + _existingConfiguracionAlerta)
        }
        val _columnsConfiguracionAlertas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConfiguracionAlertas.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlertas.put("umbralInasistenciaConsecutiva",
            TableInfo.Column("umbralInasistenciaConsecutiva", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlertas.put("umbralAsistenciaSemanal",
            TableInfo.Column("umbralAsistenciaSemanal", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlertas.put("umbralServiciosExcesivos",
            TableInfo.Column("umbralServiciosExcesivos", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlertas.put("umbralTardanzaMensual",
            TableInfo.Column("umbralTardanzaMensual", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlertas.put("activo", TableInfo.Column("activo", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlertas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfiguracionAlertas.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConfiguracionAlertas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesConfiguracionAlertas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoConfiguracionAlertas: TableInfo = TableInfo("configuracion_alertas",
            _columnsConfiguracionAlertas, _foreignKeysConfiguracionAlertas,
            _indicesConfiguracionAlertas)
        val _existingConfiguracionAlertas: TableInfo = read(connection, "configuracion_alertas")
        if (!_infoConfiguracionAlertas.equals(_existingConfiguracionAlertas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |configuracion_alertas(com.sigeschool.data.local.entity.ConfiguracionAlertasEntity).
              | Expected:
              |""".trimMargin() + _infoConfiguracionAlertas + """
              |
              | Found:
              |""".trimMargin() + _existingConfiguracionAlertas)
        }
        val _columnsRetirosAnticipados: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRetirosAnticipados.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("docenteId", TableInfo.Column("docenteId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("fechaSalida", TableInfo.Column("fechaSalida", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("motivo", TableInfo.Column("motivo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("motivoOtro", TableInfo.Column("motivoOtro", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("tipoFirmante", TableInfo.Column("tipoFirmante", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("firmanteNombre", TableInfo.Column("firmanteNombre", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("firmanteDocumento", TableInfo.Column("firmanteDocumento",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("firmaDigitalPath", TableInfo.Column("firmaDigitalPath",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("observaciones", TableInfo.Column("observaciones", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("notificadoAcudiente",
            TableInfo.Column("notificadoAcudiente", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRetirosAnticipados.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRetirosAnticipados: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRetirosAnticipados: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRetirosAnticipados: TableInfo = TableInfo("retiros_anticipados",
            _columnsRetirosAnticipados, _foreignKeysRetirosAnticipados, _indicesRetirosAnticipados)
        val _existingRetirosAnticipados: TableInfo = read(connection, "retiros_anticipados")
        if (!_infoRetirosAnticipados.equals(_existingRetirosAnticipados)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |retiros_anticipados(com.sigeschool.data.local.entity.RetiroAnticipadoEntity).
              | Expected:
              |""".trimMargin() + _infoRetirosAnticipados + """
              |
              | Found:
              |""".trimMargin() + _existingRetirosAnticipados)
        }
        val _columnsSeguimientoInasistencia: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSeguimientoInasistencia.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("alertaId", TableInfo.Column("alertaId", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("usuarioId", TableInfo.Column("usuarioId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("accion", TableInfo.Column("accion", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("descripcion", TableInfo.Column("descripcion", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("fechaSeguimiento", TableInfo.Column("fechaSeguimiento",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeguimientoInasistencia.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSeguimientoInasistencia: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSeguimientoInasistencia: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSeguimientoInasistencia.add(TableInfo.Index("idx_seguimiento_alerta", false,
            listOf("alertaId", "institutionId"), listOf("ASC", "ASC")))
        val _infoSeguimientoInasistencia: TableInfo = TableInfo("seguimiento_inasistencia",
            _columnsSeguimientoInasistencia, _foreignKeysSeguimientoInasistencia,
            _indicesSeguimientoInasistencia)
        val _existingSeguimientoInasistencia: TableInfo = read(connection,
            "seguimiento_inasistencia")
        if (!_infoSeguimientoInasistencia.equals(_existingSeguimientoInasistencia)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |seguimiento_inasistencia(com.sigeschool.data.local.entity.SeguimientoInasistenciaEntity).
              | Expected:
              |""".trimMargin() + _infoSeguimientoInasistencia + """
              |
              | Found:
              |""".trimMargin() + _existingSeguimientoInasistencia)
        }
        val _columnsServicios: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsServicios.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("descripcion", TableInfo.Column("descripcion", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("responsable", TableInfo.Column("responsable", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("ubicacion", TableInfo.Column("ubicacion", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("horario", TableInfo.Column("horario", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("notificaAcudiente", TableInfo.Column("notificaAcudiente", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("activo", TableInfo.Column("activo", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicios.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysServicios: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesServicios: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoServicios: TableInfo = TableInfo("servicios", _columnsServicios,
            _foreignKeysServicios, _indicesServicios)
        val _existingServicios: TableInfo = read(connection, "servicios")
        if (!_infoServicios.equals(_existingServicios)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |servicios(com.sigeschool.data.local.entity.ServicioEntity).
              | Expected:
              |""".trimMargin() + _infoServicios + """
              |
              | Found:
              |""".trimMargin() + _existingServicios)
        }
        val _columnsServicioLogs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsServicioLogs.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("servicioId", TableInfo.Column("servicioId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("docenteId", TableInfo.Column("docenteId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("fechaHoraSalida", TableInfo.Column("fechaHoraSalida", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("fechaHoraLlegada", TableInfo.Column("fechaHoraLlegada", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("fechaHoraRegreso", TableInfo.Column("fechaHoraRegreso", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("motivo", TableInfo.Column("motivo", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("notificadoAcudiente", TableInfo.Column("notificadoAcudiente",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsServicioLogs.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysServicioLogs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesServicioLogs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesServicioLogs.add(TableInfo.Index("idx_serv_logs_student", false, listOf("studentId",
            "institutionId"), listOf("ASC", "ASC")))
        _indicesServicioLogs.add(TableInfo.Index("idx_serv_logs_servicio", false,
            listOf("servicioId", "institutionId"), listOf("ASC", "ASC")))
        val _infoServicioLogs: TableInfo = TableInfo("servicio_logs", _columnsServicioLogs,
            _foreignKeysServicioLogs, _indicesServicioLogs)
        val _existingServicioLogs: TableInfo = read(connection, "servicio_logs")
        if (!_infoServicioLogs.equals(_existingServicioLogs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |servicio_logs(com.sigeschool.data.local.entity.ServicioLogEntity).
              | Expected:
              |""".trimMargin() + _infoServicioLogs + """
              |
              | Found:
              |""".trimMargin() + _existingServicioLogs)
        }
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("username", TableInfo.Column("username", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("password", TableInfo.Column("password", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("role", TableInfo.Column("role", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("fullName", TableInfo.Column("fullName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("profilePictureUri", TableInfo.Column("profilePictureUri", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("fcmToken", TableInfo.Column("fcmToken", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("isFirstLogin", TableInfo.Column("isFirstLogin", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("isActive", TableInfo.Column("isActive", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers,
            _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(com.sigeschool.data.local.entity.UserEntity).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        val _columnsUserApprovals: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUserApprovals.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("userId", TableInfo.Column("userId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("requestedAt", TableInfo.Column("requestedAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("approvedAt", TableInfo.Column("approvedAt", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("approvedByUserId", TableInfo.Column("approvedByUserId", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("rejectedReason", TableInfo.Column("rejectedReason", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUserApprovals.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUserApprovals: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUserApprovals: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUserApprovals: TableInfo = TableInfo("user_approvals", _columnsUserApprovals,
            _foreignKeysUserApprovals, _indicesUserApprovals)
        val _existingUserApprovals: TableInfo = read(connection, "user_approvals")
        if (!_infoUserApprovals.equals(_existingUserApprovals)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |user_approvals(com.sigeschool.data.local.entity.UserApprovalEntity).
              | Expected:
              |""".trimMargin() + _infoUserApprovals + """
              |
              | Found:
              |""".trimMargin() + _existingUserApprovals)
        }
        val _columnsConvivenciaCases: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConvivenciaCases.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("teacherId", TableInfo.Column("teacherId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("createdByUserId", TableInfo.Column("createdByUserId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("openingDate", TableInfo.Column("openingDate", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("description", TableInfo.Column("description", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("resolution", TableInfo.Column("resolution", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("resolutionDate", TableInfo.Column("resolutionDate", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConvivenciaCases.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConvivenciaCases: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysConvivenciaCases.add(TableInfo.ForeignKey("students", "CASCADE", "NO ACTION",
            listOf("studentId"), listOf("id")))
        val _indicesConvivenciaCases: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoConvivenciaCases: TableInfo = TableInfo("convivencia_cases",
            _columnsConvivenciaCases, _foreignKeysConvivenciaCases, _indicesConvivenciaCases)
        val _existingConvivenciaCases: TableInfo = read(connection, "convivencia_cases")
        if (!_infoConvivenciaCases.equals(_existingConvivenciaCases)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |convivencia_cases(com.sigeschool.data.local.entity.ConvivenciaCaseEntity).
              | Expected:
              |""".trimMargin() + _infoConvivenciaCases + """
              |
              | Found:
              |""".trimMargin() + _existingConvivenciaCases)
        }
        val _columnsCaseTestimonies: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCaseTestimonies.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCaseTestimonies.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCaseTestimonies.put("caseId", TableInfo.Column("caseId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCaseTestimonies.put("authorName", TableInfo.Column("authorName", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCaseTestimonies.put("authorRole", TableInfo.Column("authorRole", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCaseTestimonies.put("content", TableInfo.Column("content", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCaseTestimonies.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCaseTestimonies: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysCaseTestimonies.add(TableInfo.ForeignKey("convivencia_cases", "CASCADE",
            "NO ACTION", listOf("caseId"), listOf("id")))
        val _indicesCaseTestimonies: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCaseTestimonies: TableInfo = TableInfo("case_testimonies", _columnsCaseTestimonies,
            _foreignKeysCaseTestimonies, _indicesCaseTestimonies)
        val _existingCaseTestimonies: TableInfo = read(connection, "case_testimonies")
        if (!_infoCaseTestimonies.equals(_existingCaseTestimonies)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |case_testimonies(com.sigeschool.data.local.entity.TestimonyEntity).
              | Expected:
              |""".trimMargin() + _infoCaseTestimonies + """
              |
              | Found:
              |""".trimMargin() + _existingCaseTestimonies)
        }
        val _columnsBehavioralCompetencies: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBehavioralCompetencies.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralCompetencies.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralCompetencies.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralCompetencies.put("description", TableInfo.Column("description", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBehavioralCompetencies: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBehavioralCompetencies: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBehavioralCompetencies: TableInfo = TableInfo("behavioral_competencies",
            _columnsBehavioralCompetencies, _foreignKeysBehavioralCompetencies,
            _indicesBehavioralCompetencies)
        val _existingBehavioralCompetencies: TableInfo = read(connection, "behavioral_competencies")
        if (!_infoBehavioralCompetencies.equals(_existingBehavioralCompetencies)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |behavioral_competencies(com.sigeschool.data.local.entity.BehavioralCompetencyEntity).
              | Expected:
              |""".trimMargin() + _infoBehavioralCompetencies + """
              |
              | Found:
              |""".trimMargin() + _existingBehavioralCompetencies)
        }
        val _columnsBehavioralScores: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBehavioralScores.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralScores.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralScores.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralScores.put("competencyId", TableInfo.Column("competencyId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralScores.put("periodId", TableInfo.Column("periodId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralScores.put("scoreType", TableInfo.Column("scoreType", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralScores.put("feedback", TableInfo.Column("feedback", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBehavioralScores.put("evaluationDate", TableInfo.Column("evaluationDate", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBehavioralScores: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysBehavioralScores.add(TableInfo.ForeignKey("students", "CASCADE", "NO ACTION",
            listOf("studentId"), listOf("id")))
        _foreignKeysBehavioralScores.add(TableInfo.ForeignKey("behavioral_competencies", "CASCADE",
            "NO ACTION", listOf("competencyId"), listOf("id")))
        val _indicesBehavioralScores: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBehavioralScores: TableInfo = TableInfo("behavioral_scores",
            _columnsBehavioralScores, _foreignKeysBehavioralScores, _indicesBehavioralScores)
        val _existingBehavioralScores: TableInfo = read(connection, "behavioral_scores")
        if (!_infoBehavioralScores.equals(_existingBehavioralScores)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |behavioral_scores(com.sigeschool.data.local.entity.BehavioralScoreEntity).
              | Expected:
              |""".trimMargin() + _infoBehavioralScores + """
              |
              | Found:
              |""".trimMargin() + _existingBehavioralScores)
        }
        val _columnsFamilyAttendance: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFamilyAttendance.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("parentName", TableInfo.Column("parentName", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("citationDate", TableInfo.Column("citationDate", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("attendanceDate", TableInfo.Column("attendanceDate", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("meetingNotes", TableInfo.Column("meetingNotes", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFamilyAttendance.put("behavioralImpact", TableInfo.Column("behavioralImpact",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFamilyAttendance: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysFamilyAttendance.add(TableInfo.ForeignKey("students", "CASCADE", "NO ACTION",
            listOf("studentId"), listOf("id")))
        val _indicesFamilyAttendance: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoFamilyAttendance: TableInfo = TableInfo("family_attendance",
            _columnsFamilyAttendance, _foreignKeysFamilyAttendance, _indicesFamilyAttendance)
        val _existingFamilyAttendance: TableInfo = read(connection, "family_attendance")
        if (!_infoFamilyAttendance.equals(_existingFamilyAttendance)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |family_attendance(com.sigeschool.data.local.entity.FamilyAttendanceEntity).
              | Expected:
              |""".trimMargin() + _infoFamilyAttendance + """
              |
              | Found:
              |""".trimMargin() + _existingFamilyAttendance)
        }
        val _columnsExpenses: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExpenses.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("amount", TableInfo.Column("amount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("category", TableInfo.Column("category", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExpenses: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesExpenses: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExpenses: TableInfo = TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses,
            _indicesExpenses)
        val _existingExpenses: TableInfo = read(connection, "expenses")
        if (!_infoExpenses.equals(_existingExpenses)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |expenses(com.sigeschool.data.local.entity.ExpenseEntity).
              | Expected:
              |""".trimMargin() + _infoExpenses + """
              |
              | Found:
              |""".trimMargin() + _existingExpenses)
        }
        val _columnsCashierFacturas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCashierFacturas.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("numeroFactura", TableInfo.Column("numeroFactura", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("fechaEmision", TableInfo.Column("fechaEmision", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("fechaVencimiento", TableInfo.Column("fechaVencimiento",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("subtotal", TableInfo.Column("subtotal", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("impuestos", TableInfo.Column("impuestos", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("total", TableInfo.Column("total", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("saldoPendiente", TableInfo.Column("saldoPendiente", "REAL",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("concepto", TableInfo.Column("concepto", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierFacturas.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCashierFacturas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCashierFacturas: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesCashierFacturas.add(TableInfo.Index("idx_factura_fecha_vencimiento", false,
            listOf("fechaVencimiento"), listOf("ASC")))
        val _infoCashierFacturas: TableInfo = TableInfo("cashier_facturas", _columnsCashierFacturas,
            _foreignKeysCashierFacturas, _indicesCashierFacturas)
        val _existingCashierFacturas: TableInfo = read(connection, "cashier_facturas")
        if (!_infoCashierFacturas.equals(_existingCashierFacturas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |cashier_facturas(com.sigeschool.data.local.entity.FacturaEntity).
              | Expected:
              |""".trimMargin() + _infoCashierFacturas + """
              |
              | Found:
              |""".trimMargin() + _existingCashierFacturas)
        }
        val _columnsPayments: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPayments.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("amount", TableInfo.Column("amount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("concept", TableInfo.Column("concept", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("paymentMethod", TableInfo.Column("paymentMethod", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayments.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPayments: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPayments: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPayments: TableInfo = TableInfo("payments", _columnsPayments, _foreignKeysPayments,
            _indicesPayments)
        val _existingPayments: TableInfo = read(connection, "payments")
        if (!_infoPayments.equals(_existingPayments)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |payments(com.sigeschool.data.local.entity.PaymentEntity).
              | Expected:
              |""".trimMargin() + _infoPayments + """
              |
              | Found:
              |""".trimMargin() + _existingPayments)
        }
        val _columnsOrdenesPago: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsOrdenesPago.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("facturaId", TableInfo.Column("facturaId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("estudianteId", TableInfo.Column("estudianteId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("referencia", TableInfo.Column("referencia", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("monto", TableInfo.Column("monto", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("fechaGeneracion", TableInfo.Column("fechaGeneracion", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("fechaVencimiento", TableInfo.Column("fechaVencimiento", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("metodoPago", TableInfo.Column("metodoPago", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("datosPago", TableInfo.Column("datosPago", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsOrdenesPago.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysOrdenesPago: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesOrdenesPago: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoOrdenesPago: TableInfo = TableInfo("ordenes_pago", _columnsOrdenesPago,
            _foreignKeysOrdenesPago, _indicesOrdenesPago)
        val _existingOrdenesPago: TableInfo = read(connection, "ordenes_pago")
        if (!_infoOrdenesPago.equals(_existingOrdenesPago)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |ordenes_pago(com.sigeschool.data.local.entity.OrdenPagoEntity).
              | Expected:
              |""".trimMargin() + _infoOrdenesPago + """
              |
              | Found:
              |""".trimMargin() + _existingOrdenesPago)
        }
        val _columnsCashierConceptos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCashierConceptos.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierConceptos.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierConceptos.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierConceptos.put("montoBase", TableInfo.Column("montoBase", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierConceptos.put("descripcion", TableInfo.Column("descripcion", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierConceptos.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierConceptos.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCashierConceptos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCashierConceptos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCashierConceptos: TableInfo = TableInfo("cashier_conceptos",
            _columnsCashierConceptos, _foreignKeysCashierConceptos, _indicesCashierConceptos)
        val _existingCashierConceptos: TableInfo = read(connection, "cashier_conceptos")
        if (!_infoCashierConceptos.equals(_existingCashierConceptos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |cashier_conceptos(com.sigeschool.data.local.entity.ConceptoPagoEntity).
              | Expected:
              |""".trimMargin() + _infoCashierConceptos + """
              |
              | Found:
              |""".trimMargin() + _existingCashierConceptos)
        }
        val _columnsConfirmacionesPago: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsConfirmacionesPago.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("ordenPagoId", TableInfo.Column("ordenPagoId", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("referenciaIngresada",
            TableInfo.Column("referenciaIngresada", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("valorIngresado", TableInfo.Column("valorIngresado", "REAL",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("fechaConfirmacion", TableInfo.Column("fechaConfirmacion",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("estadoValidacion", TableInfo.Column("estadoValidacion",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("observacion", TableInfo.Column("observacion", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsConfirmacionesPago.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysConfirmacionesPago: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesConfirmacionesPago: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoConfirmacionesPago: TableInfo = TableInfo("confirmaciones_pago",
            _columnsConfirmacionesPago, _foreignKeysConfirmacionesPago, _indicesConfirmacionesPago)
        val _existingConfirmacionesPago: TableInfo = read(connection, "confirmaciones_pago")
        if (!_infoConfirmacionesPago.equals(_existingConfirmacionesPago)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |confirmaciones_pago(com.sigeschool.data.local.entity.ConfirmacionPagoEntity).
              | Expected:
              |""".trimMargin() + _infoConfirmacionesPago + """
              |
              | Found:
              |""".trimMargin() + _existingConfirmacionesPago)
        }
        val _columnsAcademicRecords: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicRecords.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("year", TableInfo.Column("year", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("grade", TableInfo.Column("grade", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("gpa", TableInfo.Column("gpa", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicRecords.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicRecords: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicRecords: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicRecords: TableInfo = TableInfo("academic_records", _columnsAcademicRecords,
            _foreignKeysAcademicRecords, _indicesAcademicRecords)
        val _existingAcademicRecords: TableInfo = read(connection, "academic_records")
        if (!_infoAcademicRecords.equals(_existingAcademicRecords)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_records(com.sigeschool.data.local.entity.AcademicRecordEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicRecords + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicRecords)
        }
        val _columnsCashierCertificados: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCashierCertificados.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("studentId", TableInfo.Column("studentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("fechaEmision", TableInfo.Column("fechaEmision", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("numeroSerie", TableInfo.Column("numeroSerie", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("rutaArchivo", TableInfo.Column("rutaArchivo", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCashierCertificados.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCashierCertificados: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCashierCertificados: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCashierCertificados: TableInfo = TableInfo("cashier_certificados",
            _columnsCashierCertificados, _foreignKeysCashierCertificados,
            _indicesCashierCertificados)
        val _existingCashierCertificados: TableInfo = read(connection, "cashier_certificados")
        if (!_infoCashierCertificados.equals(_existingCashierCertificados)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |cashier_certificados(com.sigeschool.data.local.entity.CertificadoEntity).
              | Expected:
              |""".trimMargin() + _infoCashierCertificados + """
              |
              | Found:
              |""".trimMargin() + _existingCashierCertificados)
        }
        val _columnsManagedDocuments: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsManagedDocuments.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsManagedDocuments.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsManagedDocuments.put("type", TableInfo.Column("type", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsManagedDocuments.put("content", TableInfo.Column("content", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsManagedDocuments.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsManagedDocuments.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysManagedDocuments: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesManagedDocuments: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoManagedDocuments: TableInfo = TableInfo("managed_documents",
            _columnsManagedDocuments, _foreignKeysManagedDocuments, _indicesManagedDocuments)
        val _existingManagedDocuments: TableInfo = read(connection, "managed_documents")
        if (!_infoManagedDocuments.equals(_existingManagedDocuments)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |managed_documents(com.sigeschool.data.local.entity.ManagedDocumentEntity).
              | Expected:
              |""".trimMargin() + _infoManagedDocuments + """
              |
              | Found:
              |""".trimMargin() + _existingManagedDocuments)
        }
        val _columnsSalaries: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSalaries.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("employeeId", TableInfo.Column("employeeId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("amount", TableInfo.Column("amount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("concept", TableInfo.Column("concept", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("paymentDate", TableInfo.Column("paymentDate", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("periodMonth", TableInfo.Column("periodMonth", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("periodYear", TableInfo.Column("periodYear", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSalaries.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSalaries: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSalaries: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSalaries: TableInfo = TableInfo("salaries", _columnsSalaries, _foreignKeysSalaries,
            _indicesSalaries)
        val _existingSalaries: TableInfo = read(connection, "salaries")
        if (!_infoSalaries.equals(_existingSalaries)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |salaries(com.sigeschool.data.local.entity.SalaryEntity).
              | Expected:
              |""".trimMargin() + _infoSalaries + """
              |
              | Found:
              |""".trimMargin() + _existingSalaries)
        }
        val _columnsEmployeeDocentes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEmployeeDocentes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("userId", TableInfo.Column("userId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("especialidad", TableInfo.Column("especialidad", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("tipoContrato", TableInfo.Column("tipoContrato", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("fechaIngreso", TableInfo.Column("fechaIngreso", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("sedePrincipalId", TableInfo.Column("sedePrincipalId",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocentes.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEmployeeDocentes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysEmployeeDocentes.add(TableInfo.ForeignKey("users", "CASCADE", "NO ACTION",
            listOf("userId"), listOf("id")))
        val _indicesEmployeeDocentes: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoEmployeeDocentes: TableInfo = TableInfo("employee_docentes",
            _columnsEmployeeDocentes, _foreignKeysEmployeeDocentes, _indicesEmployeeDocentes)
        val _existingEmployeeDocentes: TableInfo = read(connection, "employee_docentes")
        if (!_infoEmployeeDocentes.equals(_existingEmployeeDocentes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |employee_docentes(com.sigeschool.data.local.entity.DocenteEntity).
              | Expected:
              |""".trimMargin() + _infoEmployeeDocentes + """
              |
              | Found:
              |""".trimMargin() + _existingEmployeeDocentes)
        }
        val _columnsEmployeeDocenteCursos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEmployeeDocenteCursos.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("docenteId", TableInfo.Column("docenteId", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("cursoId", TableInfo.Column("cursoId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("asignaturaId", TableInfo.Column("asignaturaId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("cargaHorariaSemanal",
            TableInfo.Column("cargaHorariaSemanal", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("esDirectorGrupo", TableInfo.Column("esDirectorGrupo",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("activo", TableInfo.Column("activo", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEmployeeDocenteCursos.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEmployeeDocenteCursos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysEmployeeDocenteCursos.add(TableInfo.ForeignKey("employee_docentes", "CASCADE",
            "NO ACTION", listOf("docenteId"), listOf("id")))
        _foreignKeysEmployeeDocenteCursos.add(TableInfo.ForeignKey("academic_cursos", "CASCADE",
            "NO ACTION", listOf("cursoId"), listOf("id")))
        _foreignKeysEmployeeDocenteCursos.add(TableInfo.ForeignKey("academic_asignaturas",
            "CASCADE", "NO ACTION", listOf("asignaturaId"), listOf("id")))
        val _indicesEmployeeDocenteCursos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoEmployeeDocenteCursos: TableInfo = TableInfo("employee_docente_cursos",
            _columnsEmployeeDocenteCursos, _foreignKeysEmployeeDocenteCursos,
            _indicesEmployeeDocenteCursos)
        val _existingEmployeeDocenteCursos: TableInfo = read(connection, "employee_docente_cursos")
        if (!_infoEmployeeDocenteCursos.equals(_existingEmployeeDocenteCursos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |employee_docente_cursos(com.sigeschool.data.local.entity.DocenteCursoEntity).
              | Expected:
              |""".trimMargin() + _infoEmployeeDocenteCursos + """
              |
              | Found:
              |""".trimMargin() + _existingEmployeeDocenteCursos)
        }
        val _columnsHorariosAtencion: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsHorariosAtencion.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("docenteId", TableInfo.Column("docenteId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("diaSemana", TableInfo.Column("diaSemana", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("horaInicio", TableInfo.Column("horaInicio", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("horaFin", TableInfo.Column("horaFin", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("activo", TableInfo.Column("activo", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsHorariosAtencion.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysHorariosAtencion: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesHorariosAtencion: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoHorariosAtencion: TableInfo = TableInfo("horarios_atencion",
            _columnsHorariosAtencion, _foreignKeysHorariosAtencion, _indicesHorariosAtencion)
        val _existingHorariosAtencion: TableInfo = read(connection, "horarios_atencion")
        if (!_infoHorariosAtencion.equals(_existingHorariosAtencion)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |horarios_atencion(com.sigeschool.data.local.entity.HorarioAtencionEntity).
              | Expected:
              |""".trimMargin() + _infoHorariosAtencion + """
              |
              | Found:
              |""".trimMargin() + _existingHorariosAtencion)
        }
        val _columnsPayrollNominas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPayrollNominas.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("employeeId", TableInfo.Column("employeeId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("fechaEmision", TableInfo.Column("fechaEmision", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("periodoInicio", TableInfo.Column("periodoInicio", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("periodoFin", TableInfo.Column("periodoFin", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("salarioBase", TableInfo.Column("salarioBase", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("bonificaciones", TableInfo.Column("bonificaciones", "REAL",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("deducciones", TableInfo.Column("deducciones", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("totalNeto", TableInfo.Column("totalNeto", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("metodoPago", TableInfo.Column("metodoPago", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPayrollNominas.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPayrollNominas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPayrollNominas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPayrollNominas: TableInfo = TableInfo("payroll_nominas", _columnsPayrollNominas,
            _foreignKeysPayrollNominas, _indicesPayrollNominas)
        val _existingPayrollNominas: TableInfo = read(connection, "payroll_nominas")
        if (!_infoPayrollNominas.equals(_existingPayrollNominas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |payroll_nominas(com.sigeschool.data.local.entity.NominaEntity).
              | Expected:
              |""".trimMargin() + _infoPayrollNominas + """
              |
              | Found:
              |""".trimMargin() + _existingPayrollNominas)
        }
        val _columnsLibraryLibros: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLibraryLibros.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("isbn", TableInfo.Column("isbn", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("titulo", TableInfo.Column("titulo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("autor", TableInfo.Column("autor", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("editorial", TableInfo.Column("editorial", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("anioPublicacion", TableInfo.Column("anioPublicacion", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("categoria", TableInfo.Column("categoria", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("descripcion", TableInfo.Column("descripcion", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("ejemplaresTotales", TableInfo.Column("ejemplaresTotales",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("ejemplaresDisponibles", TableInfo.Column("ejemplaresDisponibles",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("ubicacionFisica", TableInfo.Column("ubicacionFisica", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryLibros.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLibraryLibros: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLibraryLibros: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLibraryLibros: TableInfo = TableInfo("library_libros", _columnsLibraryLibros,
            _foreignKeysLibraryLibros, _indicesLibraryLibros)
        val _existingLibraryLibros: TableInfo = read(connection, "library_libros")
        if (!_infoLibraryLibros.equals(_existingLibraryLibros)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |library_libros(com.sigeschool.data.local.entity.LibroEntity).
              | Expected:
              |""".trimMargin() + _infoLibraryLibros + """
              |
              | Found:
              |""".trimMargin() + _existingLibraryLibros)
        }
        val _columnsLibraryPrestamos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLibraryPrestamos.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("libroId", TableInfo.Column("libroId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("estudianteId", TableInfo.Column("estudianteId", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("docenteId", TableInfo.Column("docenteId", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("fechaPrestamo", TableInfo.Column("fechaPrestamo", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("fechaDevolucionPrevista",
            TableInfo.Column("fechaDevolucionPrevista", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("fechaDevolucionReal", TableInfo.Column("fechaDevolucionReal",
            "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("observaciones", TableInfo.Column("observaciones", "TEXT",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLibraryPrestamos.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLibraryPrestamos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLibraryPrestamos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLibraryPrestamos: TableInfo = TableInfo("library_prestamos",
            _columnsLibraryPrestamos, _foreignKeysLibraryPrestamos, _indicesLibraryPrestamos)
        val _existingLibraryPrestamos: TableInfo = read(connection, "library_prestamos")
        if (!_infoLibraryPrestamos.equals(_existingLibraryPrestamos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |library_prestamos(com.sigeschool.data.local.entity.PrestamoEntity).
              | Expected:
              |""".trimMargin() + _infoLibraryPrestamos + """
              |
              | Found:
              |""".trimMargin() + _existingLibraryPrestamos)
        }
        val _columnsSchedules: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSchedules.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("classroomId", TableInfo.Column("classroomId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("subjectId", TableInfo.Column("subjectId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("teacherId", TableInfo.Column("teacherId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("dayOfWeek", TableInfo.Column("dayOfWeek", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("startTime", TableInfo.Column("startTime", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("endTime", TableInfo.Column("endTime", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsSchedules.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSchedules: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSchedules: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSchedules: TableInfo = TableInfo("schedules", _columnsSchedules,
            _foreignKeysSchedules, _indicesSchedules)
        val _existingSchedules: TableInfo = read(connection, "schedules")
        if (!_infoSchedules.equals(_existingSchedules)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |schedules(com.sigeschool.data.local.entity.ScheduleEntity).
              | Expected:
              |""".trimMargin() + _infoSchedules + """
              |
              | Found:
              |""".trimMargin() + _existingSchedules)
        }
        val _columnsClassrooms: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsClassrooms.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClassrooms.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsClassrooms.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClassrooms.put("capacity", TableInfo.Column("capacity", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClassrooms.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsClassrooms.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysClassrooms: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesClassrooms: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoClassrooms: TableInfo = TableInfo("classrooms", _columnsClassrooms,
            _foreignKeysClassrooms, _indicesClassrooms)
        val _existingClassrooms: TableInfo = read(connection, "classrooms")
        if (!_infoClassrooms.equals(_existingClassrooms)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |classrooms(com.sigeschool.data.local.entity.ClassroomEntity).
              | Expected:
              |""".trimMargin() + _infoClassrooms + """
              |
              | Found:
              |""".trimMargin() + _existingClassrooms)
        }
        val _columnsFirmasUsuarios: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFirmasUsuarios.put("userId", TableInfo.Column("userId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFirmasUsuarios.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFirmasUsuarios.put("firmaPath", TableInfo.Column("firmaPath", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFirmasUsuarios.put("fechaGuardado", TableInfo.Column("fechaGuardado", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFirmasUsuarios.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFirmasUsuarios.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFirmasUsuarios: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesFirmasUsuarios: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoFirmasUsuarios: TableInfo = TableInfo("firmas_usuarios", _columnsFirmasUsuarios,
            _foreignKeysFirmasUsuarios, _indicesFirmasUsuarios)
        val _existingFirmasUsuarios: TableInfo = read(connection, "firmas_usuarios")
        if (!_infoFirmasUsuarios.equals(_existingFirmasUsuarios)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |firmas_usuarios(com.sigeschool.data.local.entity.FirmaUsuarioEntity).
              | Expected:
              |""".trimMargin() + _infoFirmasUsuarios + """
              |
              | Found:
              |""".trimMargin() + _existingFirmasUsuarios)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }

      private fun onValidateSchema3(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsPrograms: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPrograms.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("codigo", TableInfo.Column("codigo", "TEXT", true, 0, "''",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("description", TableInfo.Column("description", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("nivelEducativoId", TableInfo.Column("nivelEducativoId", "INTEGER",
            false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("gradoId", TableInfo.Column("gradoId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("activo", TableInfo.Column("activo", "INTEGER", true, 0, "1",
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPrograms: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPrograms: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPrograms: TableInfo = TableInfo("programs", _columnsPrograms, _foreignKeysPrograms,
            _indicesPrograms)
        val _existingPrograms: TableInfo = read(connection, "programs")
        if (!_infoPrograms.equals(_existingPrograms)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |programs(com.sigeschool.data.local.entity.ProgramEntity).
              | Expected:
              |""".trimMargin() + _infoPrograms + """
              |
              | Found:
              |""".trimMargin() + _existingPrograms)
        }
        val _columnsRiskAnalysis: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRiskAnalysis.put("studentId", TableInfo.Column("studentId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskAnalysis.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskAnalysis.put("riskLevel", TableInfo.Column("riskLevel", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskAnalysis.put("riskScore", TableInfo.Column("riskScore", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskAnalysis.put("factors", TableInfo.Column("factors", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskAnalysis.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskAnalysis.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRiskAnalysis: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRiskAnalysis: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRiskAnalysis: TableInfo = TableInfo("risk_analysis", _columnsRiskAnalysis,
            _foreignKeysRiskAnalysis, _indicesRiskAnalysis)
        val _existingRiskAnalysis: TableInfo = read(connection, "risk_analysis")
        if (!_infoRiskAnalysis.equals(_existingRiskAnalysis)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |risk_analysis(com.sigeschool.data.local.entity.RiskAnalysisEntity).
              | Expected:
              |""".trimMargin() + _infoRiskAnalysis + """
              |
              | Found:
              |""".trimMargin() + _existingRiskAnalysis)
        }
        val _columnsRiskSummary: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRiskSummary.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("totalStudents", TableInfo.Column("totalStudents", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("critical", TableInfo.Column("critical", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("high", TableInfo.Column("high", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("medium", TableInfo.Column("medium", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("low", TableInfo.Column("low", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("averageRisk", TableInfo.Column("averageRisk", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRiskSummary.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRiskSummary: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRiskSummary: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRiskSummary: TableInfo = TableInfo("risk_summary", _columnsRiskSummary,
            _foreignKeysRiskSummary, _indicesRiskSummary)
        val _existingRiskSummary: TableInfo = read(connection, "risk_summary")
        if (!_infoRiskSummary.equals(_existingRiskSummary)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |risk_summary(com.sigeschool.data.local.entity.RiskSummaryEntity).
              | Expected:
              |""".trimMargin() + _infoRiskSummary + """
              |
              | Found:
              |""".trimMargin() + _existingRiskSummary)
        }
        val _columnsStudentPrograms: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsStudentPrograms.put("studentId", TableInfo.Column("studentId", "TEXT", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudentPrograms.put("programId", TableInfo.Column("programId", "TEXT", true, 2,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudentPrograms.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudentPrograms.put("enrollmentDate", TableInfo.Column("enrollmentDate", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsStudentPrograms.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysStudentPrograms: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysStudentPrograms.add(TableInfo.ForeignKey("students", "CASCADE", "NO ACTION",
            listOf("studentId"), listOf("id")))
        _foreignKeysStudentPrograms.add(TableInfo.ForeignKey("programs", "CASCADE", "NO ACTION",
            listOf("programId"), listOf("id")))
        val _indicesStudentPrograms: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoStudentPrograms: TableInfo = TableInfo("student_programs", _columnsStudentPrograms,
            _foreignKeysStudentPrograms, _indicesStudentPrograms)
        val _existingStudentPrograms: TableInfo = read(connection, "student_programs")
        if (!_infoStudentPrograms.equals(_existingStudentPrograms)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |student_programs(com.sigeschool.data.local.entity.StudentProgramEntity).
              | Expected:
              |""".trimMargin() + _infoStudentPrograms + """
              |
              | Found:
              |""".trimMargin() + _existingStudentPrograms)
        }
        val _columnsParentsGuardians: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsParentsGuardians.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("userId", TableInfo.Column("userId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("fullName", TableInfo.Column("fullName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("documentId", TableInfo.Column("documentId", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("phoneNumber", TableInfo.Column("phoneNumber", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("email", TableInfo.Column("email", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("relationToStudent", TableInfo.Column("relationToStudent",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsParentsGuardians.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysParentsGuardians: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesParentsGuardians: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoParentsGuardians: TableInfo = TableInfo("parents_guardians",
            _columnsParentsGuardians, _foreignKeysParentsGuardians, _indicesParentsGuardians)
        val _existingParentsGuardians: TableInfo = read(connection, "parents_guardians")
        if (!_infoParentsGuardians.equals(_existingParentsGuardians)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |parents_guardians(com.sigeschool.data.local.entity.ParentGuardianEntity).
              | Expected:
              |""".trimMargin() + _infoParentsGuardians + """
              |
              | Found:
              |""".trimMargin() + _existingParentsGuardians)
        }
        val _columnsTareas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTareas.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("institutionId", TableInfo.Column("institutionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("claseId", TableInfo.Column("claseId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("deadline", TableInfo.Column("deadline", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("createdBy", TableInfo.Column("createdBy", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTareas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTareas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysTareas.add(TableInfo.ForeignKey("academic_clases", "CASCADE", "NO ACTION",
            listOf("claseId"), listOf("id")))
        val _indicesTareas: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesTareas.add(TableInfo.Index("index_tareas_institutionId", false,
            listOf("institutionId"), listOf("ASC")))
        _indicesTareas.add(TableInfo.Index("index_tareas_claseId", false, listOf("claseId"),
            listOf("ASC")))
        _indicesTareas.add(TableInfo.Index("index_tareas_createdBy", false, listOf("createdBy"),
            listOf("ASC")))
        val _infoTareas: TableInfo = TableInfo("tareas", _columnsTareas, _foreignKeysTareas,
            _indicesTareas)
        val _existingTareas: TableInfo = read(connection, "tareas")
        if (!_infoTareas.equals(_existingTareas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |tareas(com.sigeschool.data.local.entity.TareaEntity).
              | Expected:
              |""".trimMargin() + _infoTareas + """
              |
              | Found:
              |""".trimMargin() + _existingTareas)
        }
        val _columnsTaskSubmissions: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTaskSubmissions.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("tareaId", TableInfo.Column("tareaId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("estudianteId", TableInfo.Column("estudianteId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("submissionDate", TableInfo.Column("submissionDate", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("comment", TableInfo.Column("comment", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("grade", TableInfo.Column("grade", "REAL", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("feedback", TableInfo.Column("feedback", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskSubmissions.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTaskSubmissions: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysTaskSubmissions.add(TableInfo.ForeignKey("tareas", "CASCADE", "NO ACTION",
            listOf("tareaId"), listOf("id")))
        _foreignKeysTaskSubmissions.add(TableInfo.ForeignKey("students", "CASCADE", "NO ACTION",
            listOf("estudianteId"), listOf("id")))
        val _indicesTaskSubmissions: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesTaskSubmissions.add(TableInfo.Index("index_task_submissions_tareaId", false,
            listOf("tareaId"), listOf("ASC")))
        _indicesTaskSubmissions.add(TableInfo.Index("index_task_submissions_estudianteId", false,
            listOf("estudianteId"), listOf("ASC")))
        val _infoTaskSubmissions: TableInfo = TableInfo("task_submissions", _columnsTaskSubmissions,
            _foreignKeysTaskSubmissions, _indicesTaskSubmissions)
        val _existingTaskSubmissions: TableInfo = read(connection, "task_submissions")
        if (!_infoTaskSubmissions.equals(_existingTaskSubmissions)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |task_submissions(com.sigeschool.data.local.entity.EntregaEntity).
              | Expected:
              |""".trimMargin() + _infoTaskSubmissions + """
              |
              | Found:
              |""".trimMargin() + _existingTaskSubmissions)
        }
        val _columnsTaskAttachments: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTaskAttachments.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskAttachments.put("parentId", TableInfo.Column("parentId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskAttachments.put("fileName", TableInfo.Column("fileName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskAttachments.put("fileUrl", TableInfo.Column("fileUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskAttachments.put("fileType", TableInfo.Column("fileType", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskAttachments.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTaskAttachments.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTaskAttachments: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesTaskAttachments: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesTaskAttachments.add(TableInfo.Index("index_task_attachments_parentId", false,
            listOf("parentId"), listOf("ASC")))
        val _infoTaskAttachments: TableInfo = TableInfo("task_attachments", _columnsTaskAttachments,
            _foreignKeysTaskAttachments, _indicesTaskAttachments)
        val _existingTaskAttachments: TableInfo = read(connection, "task_attachments")
        if (!_infoTaskAttachments.equals(_existingTaskAttachments)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |task_attachments(com.sigeschool.data.local.entity.TareaAdjuntoEntity).
              | Expected:
              |""".trimMargin() + _infoTaskAttachments + """
              |
              | Found:
              |""".trimMargin() + _existingTaskAttachments)
        }
        val _columnsAcademicSedes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicSedes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicSedes.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicSedes.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicSedes.put("direccion", TableInfo.Column("direccion", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicSedes.put("telefono", TableInfo.Column("telefono", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicSedes.put("activa", TableInfo.Column("activa", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicSedes.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicSedes.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicSedes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicSedes: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicSedes: TableInfo = TableInfo("academic_sedes", _columnsAcademicSedes,
            _foreignKeysAcademicSedes, _indicesAcademicSedes)
        val _existingAcademicSedes: TableInfo = read(connection, "academic_sedes")
        if (!_infoAcademicSedes.equals(_existingAcademicSedes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_sedes(com.sigeschool.data.local.entity.SedeEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicSedes + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicSedes)
        }
        val _columnsAcademicJornadas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicJornadas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicJornadas.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicJornadas.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicJornadas.put("horaInicio", TableInfo.Column("horaInicio", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicJornadas.put("horaFin", TableInfo.Column("horaFin", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicJornadas.put("activa", TableInfo.Column("activa", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicJornadas.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicJornadas.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicJornadas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicJornadas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicJornadas: TableInfo = TableInfo("academic_jornadas",
            _columnsAcademicJornadas, _foreignKeysAcademicJornadas, _indicesAcademicJornadas)
        val _existingAcademicJornadas: TableInfo = read(connection, "academic_jornadas")
        if (!_infoAcademicJornadas.equals(_existingAcademicJornadas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_jornadas(com.sigeschool.data.local.entity.JornadaEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicJornadas + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicJornadas)
        }
        val _columnsAcademicCursos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAcademicCursos.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("gradoId", TableInfo.Column("gradoId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("sedeId", TableInfo.Column("sedeId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("jornadaId", TableInfo.Column("jornadaId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("codigo", TableInfo.Column("codigo", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("capacidad", TableInfo.Column("capacidad", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("estudiantesInscritos", TableInfo.Column("estudiantesInscritos",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("activo", TableInfo.Column("activo", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAcademicCursos.put("lastModified", TableInfo.Column("lastModified", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAcademicCursos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAcademicCursos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAcademicCursos: TableInfo = TableInfo("academic_cursos", _columnsAcademicCursos,
            _foreignKeysAcademicCursos, _indicesAcademicCursos)
        val _existingAcademicCursos: TableInfo = read(connection, "academic_cursos")
        if (!_infoAcademicCursos.equals(_existingAcademicCursos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |academic_cursos(com.sigeschool.data.local.entity.CursoEntity).
              | Expected:
              |""".trimMargin() + _infoAcademicCursos + """
              |
              | Found:
              |""".trimMargin() + _existingAcademicCursos)
        }
        val _columnsNotificacionesCalificaciones: MutableMap<String, TableInfo.Column> =
            mutableMapOf()
        _columnsNotificacionesCalificaciones.put("id", TableInfo.Column("id", "INTEGER", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("calificacionId",
            TableInfo.Column("calificacionId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("institutionId", TableInfo.Column("institutionId",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("enviadoEstudiante",
            TableInfo.Column("enviadoEstudiante", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("enviadoAcudiente",
            TableInfo.Column("enviadoAcudiente", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("enviadoDocente",
            TableInfo.Column("enviadoDocente", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("enviadoCoordinador",
            TableInfo.Column("enviadoCoordinador", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("fechaEnvioEstudiante",
            TableInfo.Column("fechaEnvioEstudiante", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("fechaEnvioAcudiente",
            TableInfo.Column("fechaEnvioAcudiente", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("fechaEnvioDocente",
            TableInfo.Column("fechaEnvioDocente", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("fechaEnvioCoordinador",
            TableInfo.Column("fechaEnvioCoordinador", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("syncStatus", TableInfo.Column("syncStatus",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsNotificacionesCalificaciones.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysNotificacionesCalificaciones: MutableSet<TableInfo.ForeignKey> =
            mutableSetOf()
        val _indicesNotificacionesCalificaciones: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoNotificacionesCalificaciones: TableInfo =
            TableInfo("notificaciones_calificaciones", _columnsNotificacionesCalificaciones,
            _foreignKeysNotificacionesCalificaciones, _indicesNotificacionesCalificaciones)
        val _existingNotificacionesCalificaciones: TableInfo = read(connection,
            "notificaciones_calificaciones")
        if (!_infoNotificacionesCalificaciones.equals(_existingNotificacionesCalificaciones)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |notificaciones_calificaciones(com.sigeschool.data.local.entity.NotificacionCalificacionEntity).
              | Expected:
              |""".trimMargin() + _infoNotificacionesCalificaciones + """
              |
              | Found:
              |""".trimMargin() + _existingNotificacionesCalificaciones)
        }
        val _columnsProgramaMapping: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProgramaMapping.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaMapping.put("institutionId", TableInfo.Column("institutionId", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaMapping.put("codigoFormulario", TableInfo.Column("codigoFormulario", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaMapping.put("ofertaAcademicaId", TableInfo.Column("ofertaAcademicaId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaMapping.put("gradoId", TableInfo.Column("gradoId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaMapping.put("activo", TableInfo.Column("activo", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaMapping.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaMapping.put("lastModified", TableInfo.Column("lastModified", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProgramaMapping: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProgramaMapping: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoProgramaMapping: TableInfo = TableInfo("programa_mapping", _columnsProgramaMapping,
            _foreignKeysProgramaMapping, _indicesProgramaMapping)
        val _existingProgramaMapping: TableInfo = read(connection, "programa_mapping")
        if (!_infoProgramaMapping.equals(_existingProgramaMapping)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |programa_mapping(com.sigeschool.data.local.entity.ProgramaMappingEntity).
              | Expected:
              |""".trimMargin() + _infoProgramaMapping + """
              |
              | Found:
              |""".trimMargin() + _existingProgramaMapping)
        }
        val _columnsProgramaOfertaMapping: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProgramaOfertaMapping.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaOfertaMapping.put("institutionId", TableInfo.Column("institutionId", "TEXT",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaOfertaMapping.put("codigoFormulario", TableInfo.Column("codigoFormulario",
            "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaOfertaMapping.put("ofertaAcademicaId", TableInfo.Column("ofertaAcademicaId",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaOfertaMapping.put("gradoId", TableInfo.Column("gradoId", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaOfertaMapping.put("activo", TableInfo.Column("activo", "INTEGER", true, 0,
            "1", TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaOfertaMapping.put("syncStatus", TableInfo.Column("syncStatus", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramaOfertaMapping.put("lastModified", TableInfo.Column("lastModified",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProgramaOfertaMapping: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProgramaOfertaMapping: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesProgramaOfertaMapping.add(TableInfo.Index("idx_prog_mapping_codigo", false,
            listOf("codigoFormulario", "institutionId"), listOf("ASC", "ASC")))
        val _infoProgramaOfertaMapping: TableInfo = TableInfo("programa_oferta_mapping",
            _columnsProgramaOfertaMapping, _foreignKeysProgramaOfertaMapping,
            _indicesProgramaOfertaMapping)
        val _existingProgramaOfertaMapping: TableInfo = read(connection, "programa_oferta_mapping")
        if (!_infoProgramaOfertaMapping.equals(_existingProgramaOfertaMapping)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |programa_oferta_mapping(com.sigeschool.data.local.entity.ProgramaOfertaMappingEntity).
              | Expected:
              |""".trimMargin() + _infoProgramaOfertaMapping + """
              |
              | Found:
              |""".trimMargin() + _existingProgramaOfertaMapping)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "students", "attendance",
        "grades", "classes", "employees", "salary_records", "tasks", "exams", "announcements",
        "puc_accounts", "accounting_entries", "employee_attendance", "fee_payments",
        "vacation_requests", "advance_requests", "payroll_calculations", "cash_closings",
        "institutional_documents", "document_blocks", "block_history", "invoices", "invoice_items",
        "payment_records", "grading_scales", "scale_ranges", "grade_categories", "rubrics",
        "rubric_criteria", "criterion_levels", "competencies", "achievement_indicators",
        "rubric_evaluations", "rubric_selections", "cash_transactions", "politicas_privacidad",
        "consentimientos", "consentimiento_historial", "fee_categories", "achievements",
        "academic_grades", "discipline_records", "study_plans", "area_plans", "audit_ledger",
        "roles", "permisos", "roles_permisos", "perfiles_personal", "historial_cv", "acudientes",
        "estudiantes_acudientes", "preferencias_notificaciones", "notificaciones",
        "institutional_notificaciones", "circulares", "log_notificaciones", "importaciones",
        "importaciones_detalle", "logs_backup", "logs_backup_llaves", "bank_accounts",
        "bank_account_history", "autoevaluaciones", "configuracion_promocion",
        "academic_niveles_educativos", "academic_grados", "academic_periodos",
        "periodo_configuracion", "academic_areas_conocimiento", "academic_asignaturas",
        "academic_ofertas", "academic_detalles_oferta", "academic_clases", "academic_matriculas",
        "academic_planes_estudios", "academic_planes_estudios_detalle", "academic_planes_aula",
        "academic_aulas", "academic_horarios", "academic_calificaciones", "audit_logs",
        "docente_sync_configs", "docente_sync_logs", "documentos_institucionales", "institutions",
        "institution_settings", "institution_themes", "listado_config", "planes", "suscripciones",
        "access_logs", "alertas_inasistencia", "alertas_tempranas", "citas", "configuracion_alerta",
        "configuracion_alertas", "retiros_anticipados", "seguimiento_inasistencia", "servicios",
        "servicio_logs", "users", "user_approvals", "convivencia_cases", "case_testimonies",
        "behavioral_competencies", "behavioral_scores", "family_attendance", "expenses",
        "cashier_facturas", "payments", "ordenes_pago", "cashier_conceptos", "confirmaciones_pago",
        "academic_records", "cashier_certificados", "managed_documents", "salaries",
        "employee_docentes", "employee_docente_cursos", "horarios_atencion", "payroll_nominas",
        "library_libros", "library_prestamos", "schedules", "classrooms", "firmas_usuarios",
        "programs", "risk_analysis", "risk_summary", "student_programs", "parents_guardians",
        "tareas", "task_submissions", "task_attachments", "academic_sedes", "academic_jornadas",
        "academic_cursos", "notificaciones_calificaciones", "programa_mapping",
        "programa_oferta_mapping")
  }

  public override fun clearAllTables() {
    super.performClear(true, "students", "attendance", "grades", "classes", "employees",
        "salary_records", "tasks", "exams", "announcements", "puc_accounts", "accounting_entries",
        "employee_attendance", "fee_payments", "vacation_requests", "advance_requests",
        "payroll_calculations", "cash_closings", "institutional_documents", "document_blocks",
        "block_history", "invoices", "invoice_items", "payment_records", "grading_scales",
        "scale_ranges", "grade_categories", "rubrics", "rubric_criteria", "criterion_levels",
        "competencies", "achievement_indicators", "rubric_evaluations", "rubric_selections",
        "cash_transactions", "consentimientos", "politicas_privacidad", "consentimiento_historial",
        "fee_categories", "achievements", "academic_grades", "discipline_records", "study_plans",
        "area_plans", "audit_ledger", "roles", "permisos", "roles_permisos", "perfiles_personal",
        "historial_cv", "acudientes", "estudiantes_acudientes", "preferencias_notificaciones",
        "notificaciones", "institutional_notificaciones", "circulares", "log_notificaciones",
        "importaciones", "importaciones_detalle", "logs_backup", "logs_backup_llaves",
        "bank_accounts", "bank_account_history", "autoevaluaciones", "configuracion_promocion",
        "academic_niveles_educativos", "academic_grados", "academic_periodos",
        "periodo_configuracion", "academic_areas_conocimiento", "academic_asignaturas",
        "academic_ofertas", "academic_detalles_oferta", "academic_clases", "academic_matriculas",
        "academic_planes_estudios", "academic_planes_estudios_detalle", "academic_planes_aula",
        "academic_aulas", "academic_horarios", "academic_calificaciones", "audit_logs",
        "docente_sync_configs", "docente_sync_logs", "documentos_institucionales", "institutions",
        "institution_settings", "institution_themes", "listado_config", "planes", "suscripciones",
        "access_logs", "alertas_inasistencia", "alertas_tempranas", "citas", "configuracion_alerta",
        "configuracion_alertas", "retiros_anticipados", "seguimiento_inasistencia", "servicios",
        "servicio_logs", "users", "user_approvals", "convivencia_cases", "case_testimonies",
        "behavioral_competencies", "behavioral_scores", "family_attendance", "expenses",
        "cashier_facturas", "payments", "ordenes_pago", "cashier_conceptos", "confirmaciones_pago",
        "academic_records", "cashier_certificados", "managed_documents", "salaries",
        "employee_docentes", "employee_docente_cursos", "horarios_atencion", "payroll_nominas",
        "library_libros", "library_prestamos", "schedules", "classrooms", "firmas_usuarios",
        "programs", "risk_analysis", "risk_summary", "student_programs", "parents_guardians",
        "tareas", "task_submissions", "task_attachments", "academic_sedes", "academic_jornadas",
        "academic_cursos", "notificaciones_calificaciones", "programa_mapping",
        "programa_oferta_mapping")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(StudentDao::class, StudentDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AttendanceDao::class, AttendanceDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(GradeDao::class, GradeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ClassDao::class, ClassDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(EmployeeDao::class, EmployeeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SalaryDao::class, SalaryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(TaskDao::class, TaskDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ExamDao::class, ExamDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AnnouncementDao::class, AnnouncementDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PucAccountDao::class, PucAccountDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AccountingEntryDao::class,
        AccountingEntryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FeePaymentDao::class, FeePaymentDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(LaboralDao::class, LaboralDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CashClosingDao::class, CashClosingDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CurricularDao::class, CurricularDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BillingDao::class, BillingDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SieDao::class, SieDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AcademicDao::class, AcademicDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FeeCategoryDao::class, FeeCategoryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CashDao::class, CashDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ConsentDao::class, ConsentDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AuditDao::class, AuditDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RoleDao::class, RoleDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PersonalProfileDao::class,
        PersonalProfileDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ParentDao::class, ParentDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(NotificationDao::class, NotificationDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ImportDao::class, ImportDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BackupDao::class, BackupDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BankAccountDao::class, BankAccountDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PromotionDao::class, PromotionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(NivelEducativoDao::class, NivelEducativoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AcademicGradoDao::class, AcademicGradoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PeriodoAcademicoDao::class,
        PeriodoAcademicoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PeriodoConfiguracionDao::class,
        PeriodoConfiguracionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AreaConocimientoDao::class,
        AreaConocimientoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AsignaturaDao::class, AsignaturaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(OfertaAcademicaDao::class,
        OfertaAcademicaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DetalleOfertaAcademicaDao::class,
        DetalleOfertaAcademicaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ClaseDao::class, ClaseDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(MatriculaDao::class, MatriculaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlanEstudiosDao::class, PlanEstudiosDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlanEstudiosDetalleDao::class,
        PlanEstudiosDetalleDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlanAulaDao::class, PlanAulaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AulaDao::class, AulaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(HorarioDao::class, HorarioDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CalificacionDao::class, CalificacionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AuditLogDao::class, AuditLogDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DocenteSyncConfigDao::class,
        DocenteSyncConfigDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DocumentoInstitucionalDao::class,
        DocumentoInstitucionalDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(InstitutionDao::class, InstitutionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(InstitutionSettingsDao::class,
        InstitutionSettingsDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(InstitutionThemeDao::class,
        InstitutionThemeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ListadoConfigDao::class, ListadoConfigDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlanDao::class, PlanDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SuscripcionDao::class, SuscripcionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AccessLogDao::class, AccessLogDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AlertaInasistenciaDao::class,
        AlertaInasistenciaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AlertaTempranaDao::class, AlertaTempranaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CitaDao::class, CitaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ConfiguracionAlertaDao::class,
        ConfiguracionAlertaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ConfiguracionAlertasDao::class,
        ConfiguracionAlertasDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RetiroAnticipadoDao::class,
        RetiroAnticipadoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SeguimientoInasistenciaDao::class,
        SeguimientoInasistenciaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ServicioDao::class, ServicioDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ServicioLogDao::class, ServicioLogDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(UserApprovalDao::class, UserApprovalDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ConvivenciaDao::class, ConvivenciaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ExpenseDao::class, ExpenseDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FacturaDao::class, FacturaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PaymentDao::class, PaymentDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(OrdenPagoDao::class, OrdenPagoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ConceptoPagoDao::class, ConceptoPagoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ConfirmacionPagoDao::class,
        ConfirmacionPagoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AcademicRecordDao::class, AcademicRecordDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CertificadoDao::class, CertificadoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ManagedDocumentDao::class,
        ManagedDocumentDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DocenteDao::class, DocenteDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DocenteCursoDao::class, DocenteCursoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(HorarioAtencionDao::class,
        HorarioAtencionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(NominaDao::class, NominaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(LibroDao::class, LibroDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PrestamoDao::class, PrestamoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ScheduleDao::class, ScheduleDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FirmaUsuarioDao::class, FirmaUsuarioDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProgramDao::class, ProgramDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RiskAnalysisDao::class, RiskAnalysisDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RiskSummaryDao::class, RiskSummaryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(StudentProgramDao::class, StudentProgramDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SubmissionDao::class, SubmissionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SedeDao::class, SedeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(JornadaDao::class, JornadaDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CursoDao::class, CursoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(NotificacionCalificacionDao::class,
        NotificacionCalificacionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProgramaMappingDao::class,
        ProgramaMappingDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProgramaOfertaMappingDao::class,
        ProgramaOfertaMappingDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun studentDao(): StudentDao = _studentDao.value

  public override fun attendanceDao(): AttendanceDao = _attendanceDao.value

  public override fun gradeDao(): GradeDao = _gradeDao.value

  public override fun classDao(): ClassDao = _classDao.value

  public override fun employeeDao(): EmployeeDao = _employeeDao.value

  public override fun salaryDao(): SalaryDao = _salaryDao.value

  public override fun taskDao(): TaskDao = _taskDao.value

  public override fun examDao(): ExamDao = _examDao.value

  public override fun announcementDao(): AnnouncementDao = _announcementDao.value

  public override fun pucAccountDao(): PucAccountDao = _pucAccountDao.value

  public override fun accountingEntryDao(): AccountingEntryDao = _accountingEntryDao.value

  public override fun feePaymentDao(): FeePaymentDao = _feePaymentDao.value

  public override fun laboralDao(): LaboralDao = _laboralDao.value

  public override fun cashClosingDao(): CashClosingDao = _cashClosingDao.value

  public override fun curricularDao(): CurricularDao = _curricularDao.value

  public override fun billingDao(): BillingDao = _billingDao.value

  public override fun sieDao(): SieDao = _sieDao.value

  public override fun academicDao(): AcademicDao = _academicDao.value

  public override fun feeCategoryDao(): FeeCategoryDao = _feeCategoryDao.value

  public override fun cashDao(): CashDao = _cashDao.value

  public override fun consentDao(): ConsentDao = _consentDao.value

  public override fun auditDao(): AuditDao = _auditDao.value

  public override fun roleDao(): RoleDao = _roleDao.value

  public override fun personalProfileDao(): PersonalProfileDao = _personalProfileDao.value

  public override fun parentDao(): ParentDao = _parentDao.value

  public override fun notificationDao(): NotificationDao = _notificationDao.value

  public override fun importDao(): ImportDao = _importDao.value

  public override fun backupDao(): BackupDao = _backupDao.value

  public override fun bankAccountDao(): BankAccountDao = _bankAccountDao.value

  public override fun promotionDao(): PromotionDao = _promotionDao.value

  public override fun nivelEducativoDao(): NivelEducativoDao = _nivelEducativoDao.value

  public override fun academicGradoDao(): AcademicGradoDao = _academicGradoDao.value

  public override fun periodoAcademicoDao(): PeriodoAcademicoDao = _periodoAcademicoDao.value

  public override fun periodoConfiguracionDao(): PeriodoConfiguracionDao =
      _periodoConfiguracionDao.value

  public override fun areaConocimientoDao(): AreaConocimientoDao = _areaConocimientoDao.value

  public override fun asignaturaDao(): AsignaturaDao = _asignaturaDao.value

  public override fun ofertaAcademicaDao(): OfertaAcademicaDao = _ofertaAcademicaDao.value

  public override fun detalleOfertaAcademicaDao(): DetalleOfertaAcademicaDao =
      _detalleOfertaAcademicaDao.value

  public override fun claseDao(): ClaseDao = _claseDao.value

  public override fun matriculaDao(): MatriculaDao = _matriculaDao.value

  public override fun planEstudiosDao(): PlanEstudiosDao = _planEstudiosDao.value

  public override fun planEstudiosDetalleDao(): PlanEstudiosDetalleDao =
      _planEstudiosDetalleDao.value

  public override fun planAulaDao(): PlanAulaDao = _planAulaDao.value

  public override fun aulaDao(): AulaDao = _aulaDao.value

  public override fun horarioDao(): HorarioDao = _horarioDao.value

  public override fun calificacionDao(): CalificacionDao = _calificacionDao.value

  public override fun auditLogDao(): AuditLogDao = _auditLogDao.value

  public override fun docenteSyncConfigDao(): DocenteSyncConfigDao = _docenteSyncConfigDao.value

  public override fun documentoInstitucionalDao(): DocumentoInstitucionalDao =
      _documentoInstitucionalDao.value

  public override fun institutionDao(): InstitutionDao = _institutionDao.value

  public override fun institutionSettingsDao(): InstitutionSettingsDao =
      _institutionSettingsDao.value

  public override fun institutionThemeDao(): InstitutionThemeDao = _institutionThemeDao.value

  public override fun listadoConfigDao(): ListadoConfigDao = _listadoConfigDao.value

  public override fun planDao(): PlanDao = _planDao.value

  public override fun suscripcionDao(): SuscripcionDao = _suscripcionDao.value

  public override fun accessLogDao(): AccessLogDao = _accessLogDao.value

  public override fun alertaInasistenciaDao(): AlertaInasistenciaDao = _alertaInasistenciaDao.value

  public override fun alertaTempranaDao(): AlertaTempranaDao = _alertaTempranaDao.value

  public override fun citaDao(): CitaDao = _citaDao.value

  public override fun configuracionAlertaDao(): ConfiguracionAlertaDao =
      _configuracionAlertaDao.value

  public override fun configuracionAlertasDao(): ConfiguracionAlertasDao =
      _configuracionAlertasDao.value

  public override fun retiroAnticipadoDao(): RetiroAnticipadoDao = _retiroAnticipadoDao.value

  public override fun seguimientoInasistenciaDao(): SeguimientoInasistenciaDao =
      _seguimientoInasistenciaDao.value

  public override fun servicioDao(): ServicioDao = _servicioDao.value

  public override fun servicioLogDao(): ServicioLogDao = _servicioLogDao.value

  public override fun userDao(): UserDao = _userDao.value

  public override fun userApprovalDao(): UserApprovalDao = _userApprovalDao.value

  public override fun convivenciaDao(): ConvivenciaDao = _convivenciaDao.value

  public override fun expenseDao(): ExpenseDao = _expenseDao.value

  public override fun facturaDao(): FacturaDao = _facturaDao.value

  public override fun paymentDao(): PaymentDao = _paymentDao.value

  public override fun ordenPagoDao(): OrdenPagoDao = _ordenPagoDao.value

  public override fun conceptoPagoDao(): ConceptoPagoDao = _conceptoPagoDao.value

  public override fun confirmacionPagoDao(): ConfirmacionPagoDao = _confirmacionPagoDao.value

  public override fun academicRecordDao(): AcademicRecordDao = _academicRecordDao.value

  public override fun certificadoDao(): CertificadoDao = _certificadoDao.value

  public override fun managedDocumentDao(): ManagedDocumentDao = _managedDocumentDao.value

  public override fun docenteDao(): DocenteDao = _docenteDao.value

  public override fun docenteCursoDao(): DocenteCursoDao = _docenteCursoDao.value

  public override fun horarioAtencionDao(): HorarioAtencionDao = _horarioAtencionDao.value

  public override fun nominaDao(): NominaDao = _nominaDao.value

  public override fun libroDao(): LibroDao = _libroDao.value

  public override fun prestamoDao(): PrestamoDao = _prestamoDao.value

  public override fun scheduleDao(): ScheduleDao = _scheduleDao.value

  public override fun firmaUsuarioDao(): FirmaUsuarioDao = _firmaUsuarioDao.value

  public override fun programDao(): ProgramDao = _programDao.value

  public override fun riskAnalysisDao(): RiskAnalysisDao = _riskAnalysisDao.value

  public override fun riskSummaryDao(): RiskSummaryDao = _riskSummaryDao.value

  public override fun studentProgramDao(): StudentProgramDao = _studentProgramDao.value

  public override fun submissionDao(): SubmissionDao = _submissionDao.value

  public override fun sedeDao(): SedeDao = _sedeDao.value

  public override fun jornadaDao(): JornadaDao = _jornadaDao.value

  public override fun cursoDao(): CursoDao = _cursoDao.value

  public override fun notificacionCalificacionDao(): NotificacionCalificacionDao =
      _notificacionCalificacionDao.value

  public override fun programaMappingDao(): ProgramaMappingDao = _programaMappingDao.value

  public override fun programaOfertaMappingDao(): ProgramaOfertaMappingDao =
      _programaOfertaMappingDao.value
}
