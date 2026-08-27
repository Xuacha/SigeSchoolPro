package com.sigeschool.data.datasource

import com.sigeschool.domain.model.*
import com.sigeschool.domain.model.Class
import com.sigeschool.domain.model.billing.*
import com.sigeschool.domain.model.sie.*
import com.sigeschool.data.datasource.billing.BillingLocalDataSource
import com.sigeschool.data.datasource.sie.SieLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.sigeschool.domain.model.billing.CashTransaction

class NoOpClassLocalDataSource : ClassLocalDataSource {
    override fun getAllClasses(institutionId: String): Flow<List<Class>> = flowOf(emptyList())
    override suspend fun addClass(clazz: Class) {}
    override suspend fun updateClass(clazz: Class) {}
    override suspend fun deleteClass(clazz: Class) {}
}

class NoOpPucLocalDataSource : PucLocalDataSource {
    override fun getAccounts(institutionId: String): Flow<List<PucAccount>> = flowOf(emptyList())
    override suspend fun insertAccounts(accounts: List<PucAccount>) {}
    override suspend fun getAccountByCode(code: String, institutionId: String): PucAccount? = null
    override suspend fun saveEntry(entry: AccountingEntry, isSynced: Boolean) {}
    override fun getEntries(institutionId: String): Flow<List<AccountingEntry>> = flowOf(emptyList())
    override suspend fun getUnsyncedEntries(): List<AccountingEntry> = emptyList()
    override suspend fun markEntryAsSynced(entryId: String) {}
}

class NoOpExamLocalDataSource : ExamLocalDataSource {
    override fun getExams(institutionId: String): Flow<List<Exam>> = flowOf(emptyList())
    override fun getExamsByClass(classId: String): Flow<List<Exam>> = flowOf(emptyList())
    override suspend fun insertExam(exam: Exam) {}
    override suspend fun deleteExam(exam: Exam) {}
    override suspend fun getUnsyncedExams(): List<Exam> = emptyList()
}

class NoOpTaskLocalDataSource : TaskLocalDataSource {
    override fun getTasks(institutionId: String): Flow<List<Task>> = flowOf(emptyList())
    override fun getTasksByClass(classId: String): Flow<List<Task>> = flowOf(emptyList())
    override suspend fun insertTask(task: Task) {}
    override suspend fun deleteTask(task: Task) {}
    override suspend fun getUnsyncedTasks(): List<Task> = emptyList()
}

class NoOpGradeLocalDataSource : GradeLocalDataSource {
    override fun getGradesByStudent(studentId: String, institutionId: String): Flow<List<Grade>> = flowOf(emptyList())
    override fun getGradesByStudentList(studentIds: List<String>, institutionId: String): Flow<List<Grade>> = flowOf(emptyList())
    override fun getGradesByInstitution(institutionId: String, periodId: String?): Flow<List<Grade>> = flowOf(emptyList())
    override suspend fun saveGrade(grade: Grade) {}
    override suspend fun deleteGrade(gradeId: String, institutionId: String) {}
    override suspend fun getUnsyncedGrades(institutionId: String): List<Grade> = emptyList()
}

class NoOpSalaryLocalDataSource : SalaryLocalDataSource {
    override fun getSalaryRecords(institutionId: String): Flow<List<SalaryRecord>> = flowOf(emptyList())
    override fun getSalaryRecordsByEmployee(employeeId: String): Flow<List<SalaryRecord>> = flowOf(emptyList())
    override suspend fun insertSalaryRecord(record: SalaryRecord) {}
    override suspend fun deleteSalaryRecord(record: SalaryRecord) {}
    override suspend fun getUnsyncedRecords(): List<SalaryRecord> = emptyList()
}

class NoOpStudentLocalDataSource : StudentLocalDataSource {
    override fun getAllStudents(institutionId: String): Flow<List<Student>> = flowOf(emptyList())
    override fun searchStudents(query: String, institutionId: String): Flow<List<Student>> = flowOf(emptyList())
    override suspend fun insertStudent(student: Student) {}
    override suspend fun updateStudent(student: Student) {}
    override suspend fun deleteStudentById(id: String, institutionId: String) {}
    override suspend fun softDeleteStudentById(id: String, institutionId: String) {}
    override suspend fun getStudentById(id: String, institutionId: String): Student? = null
    override suspend fun getStudentByDni(dni: String, institutionId: String): Student? = null
    override suspend fun getUnsyncedStudents(): List<Student> = emptyList()
    override suspend fun deleteAll(institutionId: String) {}
}

class NoOpEmployeeLocalDataSource : EmployeeLocalDataSource {
    override fun getEmployees(institutionId: String): Flow<List<Employee>> = flowOf(emptyList())
    override fun getActiveEmployees(institutionId: String): Flow<List<Employee>> = flowOf(emptyList())
    override suspend fun insertEmployee(employee: Employee) {}
    override suspend fun insertEmployees(employees: List<Employee>) {}
    override suspend fun getEmployeeById(id: String): Employee? = null
    override suspend fun deleteEmployee(employee: Employee) {}
}

class NoOpAttendanceLocalDataSource : AttendanceLocalDataSource {
    override fun getAttendanceByDate(fecha: String, institutionId: String): Flow<List<Attendance>> = flowOf(emptyList())
    override fun getAttendanceByStudent(studentId: String, institutionId: String): Flow<List<Attendance>> = flowOf(emptyList())
    override suspend fun saveAttendance(attendance: List<Attendance>) {}
    override suspend fun getUnsyncedAttendance(institutionId: String): List<Attendance> = emptyList()
    override suspend fun deleteAll(institutionId: String) {}
    
    override suspend fun saveEmployeeAttendance(attendance: EmployeeAttendance) {}
    override suspend fun getEmployeeAttendance(employeeId: String, date: String, institutionId: String): EmployeeAttendance? = null
    override suspend fun getEmployeeAttendanceById(id: String, institutionId: String): EmployeeAttendance? = null
    override fun getEmployeeAttendanceByDate(date: String, institutionId: String): Flow<List<EmployeeAttendance>> = flowOf(emptyList())
    override suspend fun getUnsyncedEmployeeAttendance(institutionId: String): List<EmployeeAttendance> = emptyList()
}

class NoOpAnnouncementLocalDataSource : AnnouncementLocalDataSource {
    override fun getAnnouncements(institutionId: String): Flow<List<Announcement>> = flowOf(emptyList())
    override suspend fun insertAnnouncement(announcement: Announcement) {}
    override suspend fun deleteAnnouncement(announcement: Announcement) {}
}

class NoOpCurricularLocalDataSource : CurricularLocalDataSource {
    override fun getDocumentsByType(type: com.sigeschool.domain.model.DocumentType): Flow<List<InstitutionalDocument>> = flowOf(emptyList())
    override fun getBlocksByDocumentId(documentId: String): Flow<List<DocumentBlock>> = flowOf(emptyList())
    override suspend fun insertDocument(document: InstitutionalDocument) {}
    override suspend fun insertBlocks(blocks: List<DocumentBlock>) {}
    override suspend fun getDocumentById(documentId: String): InstitutionalDocument? = null
    override fun getBlockHistory(blockId: String): Flow<List<DocumentBlock>> = flowOf(emptyList())
}

class NoOpLaboralLocalDataSource : LaboralLocalDataSource {
    override fun getVacationRequests(employeeId: String): Flow<List<VacationRequest>> = flowOf(emptyList())
    override suspend fun insertVacationRequest(request: VacationRequest) {}
    override suspend fun markVacationSynced(id: String) {}
    override suspend fun getUnsyncedVacations(): List<VacationRequest> = emptyList()
    override fun getAdvanceRequests(employeeId: String): Flow<List<AdvanceRequest>> = flowOf(emptyList())
    override suspend fun insertAdvanceRequest(request: AdvanceRequest) {}
    override suspend fun markAdvanceSynced(id: String) {}
    override suspend fun getUnsyncedAdvances(): List<AdvanceRequest> = emptyList()
    override fun getPayrollCalculations(employeeId: String): Flow<List<PayrollCalculation>> = flowOf(emptyList())
    override suspend fun insertPayrollCalculation(calculation: PayrollCalculation, employeeId: String, date: Long) {}
    override suspend fun markPayrollSynced(id: String) {}
    override suspend fun getUnsyncedPayroll(): List<PayrollCalculation> = emptyList()
    override suspend fun getPayrollHistoryByDateRange(startDate: Long, endDate: Long): List<PayrollCalculation> = emptyList()
}

class NoOpFeeLocalDataSource : FeeLocalDataSource {
    override fun getPaymentsByStudent(studentId: String): Flow<List<FeePayment>> = flowOf(emptyList())
    override fun getAllPayments(institutionId: String): Flow<List<FeePayment>> = flowOf(emptyList())
    override suspend fun insertPayment(payment: FeePayment) {}
    override suspend fun getUnsyncedPayments(): List<FeePayment> = emptyList()
    override suspend fun markAsSynced(id: String, url: String?) {}
}

class NoOpBillingLocalDataSource : BillingLocalDataSource {
    override fun getInvoices(institutionId: String): Flow<List<Invoice>> = flowOf(emptyList())
    override fun getInvoiceById(id: String): Flow<Invoice?> = flowOf(null)
    override suspend fun saveInvoice(invoice: Invoice) {}
    override suspend fun savePayment(payment: PaymentRecord) {}
    override suspend fun getPaymentById(id: String): PaymentRecord? = null
    override fun getFeeCategories(): Flow<List<FeeCategory>> = flowOf(emptyList())
    override suspend fun saveFeeCategory(category: FeeCategory) {}
    override suspend fun insertTransaction(transaction: CashTransaction) {}
    override fun getCashTransactions(start: Long, end: Long): Flow<List<CashTransaction>> = flowOf(emptyList())
    override suspend fun saveCashClosing(closing: com.sigeschool.domain.model.CashClosing) {}
}

class NoOpSieLocalDataSource : SieLocalDataSource {
    override fun getGradingScales(institutionId: String): Flow<List<GradingScale>> = flowOf(emptyList())
    override suspend fun saveGradingScale(scale: GradingScale) {}
    override suspend fun getGradingScaleById(id: String): GradingScale? = null
    override suspend fun calculateEquivalence(score: Double, scaleId: String): String = ""
    override fun getCategories(institutionId: String): Flow<List<GradeCategory>> = flowOf(emptyList())
    override suspend fun saveCategory(category: GradeCategory) {}
    override fun getCompetencies(institutionId: String): Flow<List<Competency>> = flowOf(emptyList())
    override suspend fun saveCompetency(competency: Competency) {}
    override fun getRubrics(institutionId: String): Flow<List<Rubric>> = flowOf(emptyList())
    override suspend fun saveRubric(rubric: Rubric) {}
    override suspend fun saveRubricEvaluation(evaluation: RubricEvaluation) {}
    override fun getRubricEvaluation(gradeId: String): Flow<RubricEvaluation?> = flowOf(null)
}
