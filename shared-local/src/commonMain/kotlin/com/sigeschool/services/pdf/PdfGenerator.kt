package com.sigeschool.services.pdf

import com.sigeschool.domain.model.sie.AcademicReport
import com.sigeschool.domain.model.billing.Invoice

interface PdfGenerator {
    suspend fun generateAcademicReport(report: AcademicReport): ByteArray
    suspend fun generateBulkAcademicReports(reports: List<AcademicReport>): ByteArray
    suspend fun generateInvoice(invoice: Invoice): ByteArray
    suspend fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray
}

expect class PlatformPdfGenerator() : PdfGenerator {
    override suspend fun generateAcademicReport(report: AcademicReport): ByteArray
    override suspend fun generateBulkAcademicReports(reports: List<AcademicReport>): ByteArray
    override suspend fun generateInvoice(invoice: Invoice): ByteArray
    override suspend fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray
}
