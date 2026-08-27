package com.sigeschool.util

import com.sigeschool.domain.model.FinancialStatement
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.IdCard
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.TrialBalanceReport

expect object PdfPlatformGenerator {
    fun generateStudentReport(student: Student, grades: List<Grade>): ByteArray
    fun generateBulkReport(data: List<Pair<Student, List<Grade>>>): ByteArray
    fun generateFinancialReport(statement: FinancialStatement): ByteArray
    fun generateTrialBalanceReport(report: TrialBalanceReport): ByteArray
    fun generateIdCards(cards: List<IdCard>): ByteArray
    fun generatePayrollReceipt(calculation: PayrollCalculation, employeeName: String, institutionName: String): ByteArray
    fun generateBulkPayrollReport(data: List<Triple<PayrollCalculation, String, String>>): ByteArray
    fun generateConsentAuditReport(consents: List<com.sigeschool.domain.model.Consent>, studentName: String): ByteArray
    fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray
}
