package com.sigeschool.services.pdf

import com.sigeschool.domain.model.sie.AcademicReport
import com.sigeschool.domain.model.billing.Invoice
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.font.Standard14Fonts
import org.apache.pdfbox.pdmodel.common.PDRectangle
import java.io.ByteArrayOutputStream

actual class PlatformPdfGenerator actual constructor() : PdfGenerator {
    actual override suspend fun generateAcademicReport(report: AcademicReport): ByteArray {
        return generateBulkAcademicReports(listOf(report))
    }

    actual override suspend fun generateBulkAcademicReports(reports: List<AcademicReport>): ByteArray {
        val document = PDDocument()
        val boldFont = PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
        val regularFont = PDType1Font(Standard14Fonts.FontName.HELVETICA)

        reports.forEach { report ->
            val page = PDPage(PDRectangle.A4)
            document.addPage(page)

            PDPageContentStream(document, page).use { contentStream ->
                contentStream.beginText()
                contentStream.setFont(boldFont, 18f)
                contentStream.newLineAtOffset(50f, 750f)
                contentStream.showText("BOLETÍN ACADÉMICO - PERIODO ${report.period}")
                contentStream.endText()

                contentStream.beginText()
                contentStream.setFont(regularFont, 12f)
                contentStream.newLineAtOffset(50f, 720f)
                contentStream.showText("Estudiante: ${report.student.nombreCompleto}")
                contentStream.newLineAtOffset(0f, -20f)
                contentStream.showText("Grado: ${report.student.grado}")
                contentStream.endText()
                
                var yPos = 650f
                report.grades.forEach { grade ->
                    contentStream.beginText()
                    contentStream.setFont(regularFont, 11f)
                    contentStream.newLineAtOffset(50f, yPos)
                    contentStream.showText("${grade.subject}: ${grade.finalGrade} (${grade.performanceLevel})")
                    contentStream.endText()
                    yPos -= 20f
                }

                // Discipline & Conduct
                contentStream.beginText()
                contentStream.setFont(boldFont, 12f)
                contentStream.newLineAtOffset(50f, yPos - 20f)
                contentStream.showText("CONDUCTA: ${report.conductScore.score}")
                contentStream.endText()
            }
        }

        val outputStream = ByteArrayOutputStream()
        document.save(outputStream)
        document.close()
        return outputStream.toByteArray()
    }

    actual override suspend fun generateInvoice(invoice: Invoice): ByteArray {
        val document = PDDocument()
        val page = PDPage(PDRectangle.A4)
        document.addPage(page)

        val boldFont = PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
        val regularFont = PDType1Font(Standard14Fonts.FontName.HELVETICA)

        PDPageContentStream(document, page).use { contentStream ->
            contentStream.beginText()
            contentStream.setFont(boldFont, 20f)
            contentStream.newLineAtOffset(50f, 750f)
            contentStream.showText("RECIBO DE CAJA: ${invoice.number}")
            contentStream.endText()
            
            var yPos = 700f
            invoice.items.forEach { item ->
                contentStream.beginText()
                contentStream.setFont(regularFont, 12f)
                contentStream.newLineAtOffset(50f, yPos)
                contentStream.showText("${item.description} x ${item.quantity}  --- $ ${item.total}")
                contentStream.endText()
                yPos -= 20f
            }
            
            contentStream.beginText()
            contentStream.setFont(boldFont, 14f)
            contentStream.newLineAtOffset(400f, yPos - 20f)
            contentStream.showText("TOTAL: $ ${invoice.totalAmount}")
            contentStream.endText()
        }

        val outputStream = ByteArrayOutputStream()
        document.save(outputStream)
        document.close()
        return outputStream.toByteArray()
    }

    actual override suspend fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray {
        val document = PDDocument()
        val page = PDPage(PDRectangle.A4)
        document.addPage(page)
        
        val boldFont = PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
        val regularFont = PDType1Font(Standard14Fonts.FontName.HELVETICA)

        PDPageContentStream(document, page).use { contentStream ->
            contentStream.beginText()
            contentStream.setFont(boldFont, 24f)
            contentStream.newLineAtOffset(150f, 750f)
            contentStream.showText("CERTIFICADO DE ESTUDIOS")
            contentStream.endText()

            contentStream.beginText()
            contentStream.setFont(regularFont, 14f)
            contentStream.newLineAtOffset(50f, 650f)
            contentStream.showText("La institución educativa con ID $institutionId,")
            contentStream.newLineAtOffset(0f, -25f)
            contentStream.showText("hace constar que el estudiante: $studentName")
            contentStream.newLineAtOffset(0f, -25f)
            contentStream.showText("Identificado con documento No. $documentNumber")
            contentStream.newLineAtOffset(0f, -25f)
            contentStream.showText("Se encuentra legalmente matriculado en: $program")
            contentStream.endText()
        }

        val outputStream = ByteArrayOutputStream()
        document.save(outputStream)
        document.close()
        return outputStream.toByteArray()
    }
}
