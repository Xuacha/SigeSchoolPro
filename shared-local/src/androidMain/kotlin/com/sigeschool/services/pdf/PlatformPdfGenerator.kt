package com.sigeschool.services.pdf

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.sigeschool.domain.model.sie.AcademicReport
import com.sigeschool.domain.model.billing.Invoice
import java.io.ByteArrayOutputStream
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class PlatformPdfGenerator actual constructor() : PdfGenerator, KoinComponent {
    private val context: Context by inject()

    actual override suspend fun generateAcademicReport(report: AcademicReport): ByteArray {
        return generateBulkAcademicReports(listOf(report))
    }

    actual override suspend fun generateBulkAcademicReports(reports: List<AcademicReport>): ByteArray {
        val pdfDocument = PdfDocument()
        reports.forEachIndexed { index, report ->
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, index + 1).create() // A4
            val page = pdfDocument.startPage(pageInfo)
            val canvas: Canvas = page.canvas
            val paint = Paint()

            // Header
            paint.color = Color.BLACK
            paint.textSize = 18f
            paint.isFakeBoldText = true
            canvas.drawText("BOLETÍN ACADÉMICO - PERIODO ${report.period}", 50f, 50f, paint)

            // Student Info
            paint.textSize = 12f
            paint.isFakeBoldText = false
            canvas.drawText("Estudiante: ${report.student.nombreCompleto}", 50f, 80f, paint)
            canvas.drawText("Grado: ${report.student.grado}", 50f, 100f, paint)

            // Grades Table Header
            var yPos = 140f
            paint.isFakeBoldText = true
            canvas.drawText("Asignatura", 50f, yPos, paint)
            canvas.drawText("Nota Final", 400f, yPos, paint)
            canvas.drawText("Desempeño", 500f, yPos, paint)
            
            yPos += 20f
            paint.isFakeBoldText = false
            
            report.grades.forEach { grade ->
                canvas.drawText(grade.subject, 50f, yPos, paint)
                canvas.drawText("%.2f".format(grade.finalGrade), 400f, yPos, paint)
                canvas.drawText(grade.performanceLevel.name, 500f, yPos, paint)
                yPos += 15f
            }

            // Discipline
            yPos += 20f
            paint.isFakeBoldText = true
            canvas.drawText("CONDUCTA: ${report.conductScore.score}", 50f, yPos, paint)
            
            pdfDocument.finishPage(page)
        }

        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual override suspend fun generateInvoice(invoice: Invoice): ByteArray {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()

        paint.textSize = 20f
        canvas.drawText("RECIBO DE CAJA: ${invoice.number}", 50f, 50f, paint)
        
        paint.textSize = 12f
        canvas.drawText("Fecha: ${invoice.date}", 50f, 80f, paint)
        canvas.drawText("Estudiante: ${invoice.studentName}", 50f, 100f, paint)
        
        var yPos = 140f
        invoice.items.forEach { item ->
            canvas.drawText("${item.description} x ${item.quantity}", 50f, yPos, paint)
            canvas.drawText("$ ${item.total}", 450f, yPos, paint)
            yPos += 20f
        }

        canvas.drawText("TOTAL: $ ${invoice.totalAmount}", 400f, yPos + 20f, paint)

        pdfDocument.finishPage(page)
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual override suspend fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()

        // Dibujar borde decorativo
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        canvas.drawRect(20f, 20f, 575f, 822f, paint)

        paint.style = Paint.Style.FILL
        paint.textSize = 24f
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("CERTIFICADO DE ESTUDIOS", 297f, 100f, paint)

        paint.textSize = 14f
        paint.textAlign = Paint.Align.LEFT
        val bodyText = """
            La institución educativa identificada con ID $institutionId,
            hace constar que el estudiante:
            
            $studentName
            Identificado con el documento No. $documentNumber
            
            Se encuentra legalmente matriculado en el programa:
            $program
            
            Para constancia se firma a la fecha de hoy.
        """.trimIndent()

        var yPos = 180f
        bodyText.split("\n").forEach { line ->
            canvas.drawText(line, 50f, yPos, paint)
            yPos += 25f
        }

        pdfDocument.finishPage(page)
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }
}
