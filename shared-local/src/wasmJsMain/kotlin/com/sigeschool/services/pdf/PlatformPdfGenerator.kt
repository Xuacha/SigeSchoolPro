package com.sigeschool.services.pdf

import com.sigeschool.domain.model.sie.AcademicReport
import com.sigeschool.domain.model.billing.Invoice
import com.sigeschool.util.jsPDF
import kotlinx.browser.window

/**
 * Implementación de generación de PDF para WasmJs usando jsPDF.
 * Cumple con la interfaz PdfGenerator para la fase Gold.
 */
fun createJsPdf(): jsPDF = js("new jspdf.jsPDF()")

actual class PlatformPdfGenerator actual constructor() : PdfGenerator {
    
    actual override suspend fun generateAcademicReport(report: AcademicReport): ByteArray {
        val doc = createJsPdf()
        drawReport(doc, report)
        doc.save("${report.student.nombreCompleto}_Boletin.pdf")
        return ByteArray(0)
    }

    actual override suspend fun generateBulkAcademicReports(reports: List<AcademicReport>): ByteArray {
        val doc = createJsPdf()
        reports.forEachIndexed { index, report ->
            if (index > 0) doc.addPage()
            drawReport(doc, report)
        }
        doc.save("Reporte_Academico_Masivo.pdf")
        return ByteArray(0)
    }

    actual override suspend fun generateInvoice(invoice: Invoice): ByteArray {
        val doc = createJsPdf()
        doc.setFontSize(20.0)
        doc.text("FACTURA DE VENTA: ${invoice.number}", 20.0, 20.0)
        doc.setFontSize(12.0)
        doc.text("Cliente: ${invoice.parentName}", 20.0, 40.0)
        doc.text("Total: $${invoice.totalAmount}", 20.0, 50.0)
        doc.save("Factura_${invoice.number}.pdf")
        return ByteArray(0)
    }

    private fun drawReport(doc: jsPDF, report: AcademicReport) {
        doc.setFontSize(18.0)
        doc.text("SigeSchool Pro - Informe Académico", 20.0, 20.0)
        
        doc.setFontSize(12.0)
        doc.text("Estudiante: ${report.student.nombreCompleto}", 20.0, 35.0)
        doc.text("Periodo: ${report.period}", 20.0, 45.0)
        doc.text("Promedio: ${report.overallAverage}", 20.0, 55.0)
        
        doc.line(20.0, 60.0, 190.0, 60.0)
        
        var y = 70.0
        report.grades.forEach { subjectGrade ->
            if (y > 270) {
                doc.addPage()
                y = 20.0
            }
            doc.text(subjectGrade.subject, 20.0, y)
            doc.text(subjectGrade.finalGrade.toString(), 150.0, y)
            doc.text(subjectGrade.performanceLevel.name, 170.0, y)
            y += 10.0
        }
    }

    actual override suspend fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray {
        val doc = createJsPdf()
        doc.setFontSize(22.0)
        doc.text("CERTIFICADO DE ESTUDIOS", 105.0, 40.0) // Centrado aprox
        
        doc.setFontSize(14.0)
        doc.text("La institución con ID $institutionId,", 20.0, 80.0)
        doc.text("Certifica que el estudiante $studentName,", 20.0, 95.0)
        doc.text("con documento $documentNumber, está matriculado en $program.", 20.0, 110.0)
        
        doc.save("Certificado_$studentId.pdf")
        return ByteArray(0)
    }
}
