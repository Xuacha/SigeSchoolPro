package com.sigeschool.services.sync

import com.sigeschool.domain.repository.StudentRepository
import com.sigeschool.domain.repository.AttendanceRepository
import com.sigeschool.domain.repository.GradeRepository
import com.sigeschool.domain.repository.LaboralRepository
import com.sigeschool.domain.repository.PaymentRepository
import com.sigeschool.domain.repository.ExpenseRepository
import com.sigeschool.domain.repository.EmployeeRepository
import com.sigeschool.domain.repository.SalaryRepository
import com.sigeschool.domain.repository.TaskRepository
import com.sigeschool.domain.repository.ExamRepository
import com.sigeschool.domain.repository.AnnouncementRepository
import com.sigeschool.domain.repository.PucRepository
import com.sigeschool.domain.repository.FeeRepository
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * SyncManager orchestrates the synchronization of all core features.
 * It provides methods to sync all features in parallel or individual features.
 */
class SyncManager(
    private val studentRepository: StudentRepository,
    private val attendanceRepository: AttendanceRepository,
    private val gradeRepository: GradeRepository,
    private val employeeRepository: EmployeeRepository,
    private val salaryRepository: SalaryRepository,
    private val paymentRepository: PaymentRepository,
    private val expenseRepository: ExpenseRepository,
    private val taskRepository: TaskRepository,
    private val examRepository: ExamRepository,
    private val announcementRepository: AnnouncementRepository,
    private val pucRepository: PucRepository,
    private val laboralRepository: LaboralRepository,
    private val feeRepository: FeeRepository
) {
    /**
     * Synchronizes all core features in parallel using a supervisorScope.
     * This ensures that a failure in one feature sync does not cancel the others.
     */
    suspend fun syncAll(institutionId: String) = supervisorScope {
        launch { safeSync("Students") { studentRepository.syncStudents() } }
        launch { safeSync("Attendance") { attendanceRepository.syncAttendance() } }
        launch { safeSync("Grades") { gradeRepository.syncGrades() } }
        launch { safeSync("Employees") { employeeRepository.syncEmployees(institutionId) } }
        launch { safeSync("Salary") { salaryRepository.syncWithCloud() } }
        launch { safeSync("Payments") { paymentRepository.syncPayments(institutionId) } }
        launch { safeSync("Expenses") { expenseRepository.syncExpenses(institutionId) } }
        launch { safeSync("Tasks") { taskRepository.syncWithCloud() } }
        launch { safeSync("Exams") { examRepository.syncWithCloud() } }
        launch { safeSync("Announcements") { announcementRepository.syncAnnouncements(institutionId) } }
        launch { safeSync("Puc") { pucRepository.syncWithCloud() } }
        launch { safeSync("Laboral") { laboralRepository.syncWithCloud() } }
        launch { safeSync("Fees") { feeRepository.syncWithCloud() } }
    }

    /**
     * Helper method to execute sync blocks safely.
     */
    private suspend fun safeSync(featureName: String, block: suspend () -> Any?) {
        try {
            val result = block()
            if (result is Resource.Error<*>) {
                println("SyncManager: Error syncing $featureName: ${result.message}")
            } else {
                println("SyncManager: Successfully synced $featureName")
            }
        } catch (e: Exception) {
            println("SyncManager: Exception syncing $featureName: ${e.message}")
            e.printStackTrace()
        }
    }

    // Individual sync methods for fine-grained control
    suspend fun syncStudents() = studentRepository.syncStudents()
    suspend fun syncAttendance() = attendanceRepository.syncAttendance()
    suspend fun syncGrades() = gradeRepository.syncGrades()
    suspend fun syncEmployees(institutionId: String) = employeeRepository.syncEmployees(institutionId)
    suspend fun syncSalary() = salaryRepository.syncWithCloud()
    suspend fun syncPayments(institutionId: String) = paymentRepository.syncPayments(institutionId)
    suspend fun syncExpenses(institutionId: String) = expenseRepository.syncExpenses(institutionId)
    suspend fun syncTasks() = taskRepository.syncWithCloud()
    suspend fun syncExams() = examRepository.syncWithCloud()
    suspend fun syncAnnouncements(institutionId: String) = announcementRepository.syncAnnouncements(institutionId)
    suspend fun syncPuc(institutionId: String) = pucRepository.syncWithCloud()
    suspend fun syncLaboral() = laboralRepository.syncWithCloud()
    suspend fun syncFees() = feeRepository.syncWithCloud()
}
