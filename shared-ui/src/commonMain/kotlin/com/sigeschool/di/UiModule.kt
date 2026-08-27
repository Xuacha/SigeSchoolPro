package com.sigeschool.di

import com.sigeschool.presentation.screens.parent.PaymentPortalViewModel
import com.sigeschool.presentation.screens.parent.ConsentViewModel as ParentConsentViewModel
import com.sigeschool.presentation.screens.students.ConsentViewModel as StudentConsentViewModel
import com.sigeschool.presentation.screens.billing.BankAccountViewModel
import com.sigeschool.presentation.screens.cash.CashViewModel
import com.sigeschool.presentation.screens.reports.ReportsViewModel
import com.sigeschool.presentation.screens.import.ImportViewModel
import com.sigeschool.presentation.screens.parent.ParentDashboardViewModel
import com.sigeschool.presentation.screens.students.StudentViewModel
import com.sigeschool.presentation.screens.students.StudentListViewModel
import com.sigeschool.presentation.screens.students.StudentDetailViewModel
import com.sigeschool.presentation.screens.students.EnrollmentViewModel

import com.sigeschool.presentation.screens.attendance.AttendanceViewModel
import com.sigeschool.presentation.screens.attendance.AttendanceRegisterViewModel
import com.sigeschool.presentation.screens.attendance.ScannerViewModel
import com.sigeschool.presentation.screens.attendance.EmployeeAttendanceViewModel
import com.sigeschool.presentation.screens.grades.GradesViewModel
import com.sigeschool.presentation.screens.grades.MassiveGradeViewModel
import com.sigeschool.presentation.screens.curricular.StudentRecordsViewModel
import com.sigeschool.presentation.screens.admin.AcademicConfigViewModel
import com.sigeschool.presentation.screens.admin.ThemeSettingsViewModel
import com.sigeschool.presentation.theme.ThemeManager
import com.sigeschool.presentation.screens.employees.EmployeeViewModel
import com.sigeschool.presentation.screens.announcements.AnnouncementViewModel
import com.sigeschool.presentation.screens.tasks.TaskViewModel
import com.sigeschool.presentation.screens.exams.ExamViewModel
import com.sigeschool.presentation.screens.dashboard.DashboardViewModel
import com.sigeschool.presentation.viewmodel.billing.BillingViewModel
import com.sigeschool.presentation.screens.salaries.SalaryViewModel
import com.sigeschool.presentation.screens.puc.PucViewModel
import com.sigeschool.presentation.screens.sie.SieViewModel
import com.sigeschool.presentation.screens.sie.AutoevaluacionViewModel
import com.sigeschool.presentation.screens.sie.ConfiguracionPromocionViewModel
import com.sigeschool.presentation.screens.cv.CvUploadViewModel
import com.sigeschool.presentation.screens.cash.PaymentViewModel
import com.sigeschool.presentation.screens.cash.ExpenseViewModel
import com.sigeschool.presentation.viewmodel.admin.BackupViewModel
import com.sigeschool.presentation.viewmodel.admin.BackupSecurityViewModel
import com.sigeschool.presentation.viewmodel.admin.LogsMonitorViewModel
import com.sigeschool.domain.util.SessionManager
import org.koin.dsl.module

val uiModule = module {
    factory { BackupViewModel(get()) }
    factory { BackupSecurityViewModel(get()) }
    factory { LogsMonitorViewModel(get()) }
    factory { PaymentPortalViewModel(get()) }
    factory { ParentConsentViewModel(get()) }
    factory { StudentConsentViewModel(get(), get()) }
    factory { BankAccountViewModel(get(), get()) }
    factory { CashViewModel(get(), get(), get()) }
    factory { BillingViewModel(get(), get(), get(), get()) }
    factory { SalaryViewModel(get(), get(), get(), get()) }
    factory { PucViewModel(get(), get()) }
    factory { ReportsViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    factory { ImportViewModel(get(), get(), get()) }
    factory { ParentDashboardViewModel(get(), get(), get()) }
    factory { StudentViewModel(get(), get(), get()) }
    factory { StudentListViewModel(get(), get()) }
    factory { StudentDetailViewModel(get(), get(), get()) }
    factory { EnrollmentViewModel(get(), get()) }

    factory { AttendanceViewModel(get(), get(), get()) }
    factory { AttendanceRegisterViewModel(get(), get(), get()) }
    factory { ScannerViewModel(get(), get()) }
    factory { EmployeeAttendanceViewModel(get(), get(), get()) }
    factory { PaymentViewModel(get(), get(), get()) }
    factory { ExpenseViewModel(get(), get()) }
    factory { GradesViewModel(get(), get(), get(), get(), get(), get()) }
    factory { MassiveGradeViewModel(get(), get()) }
    factory { params -> StudentRecordsViewModel(params.get(), get(), get(), get(), get()) }
    factory { AcademicConfigViewModel(get(), get()) }
    factory { EmployeeViewModel(get(), get()) }
    factory { AnnouncementViewModel(get(), get()) }
    factory { TaskViewModel(get(), get()) }
    factory { ExamViewModel(get(), get()) }
    factory { SieViewModel(get(), get(), get(), get()) }
    factory { AutoevaluacionViewModel(get(), get(), get()) }
    factory { ConfiguracionPromocionViewModel(get(), get()) }
    factory { CvUploadViewModel(get(), get()) }
    factory { com.sigeschool.presentation.screens.library.LibraryViewModel(get(), get()) }
    factory { com.sigeschool.presentation.screens.behavior.BehaviorViewModel(get(), get()) }
    factory { com.sigeschool.presentation.screens.promotor.PromotorDashboardViewModel(get(), get()) }
    factory { DashboardViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }

    single { ThemeManager() }
    factory { ThemeSettingsViewModel(get(), get(), get(), get(), get()) }
}
