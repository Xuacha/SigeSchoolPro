package com.sigeschool.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.sigeschool.domain.model.FinancialStatement
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.IdCard
import com.sigeschool.domain.model.Student
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import java.io.ByteArrayOutputStream

actual object PdfPlatformGenerator {

    actual fun generateStudentReport(student: Student, grades: List<Grade>): ByteArray {
        return generateBulkReport(listOf(student to grades))
    }

    actual fun generateBulkReport(data: List<Pair<Student, List<Grade>>>): ByteArray {
        val pdfDocument = PdfDocument()
        val paint = Paint()

        data.forEachIndexed { index, pair ->
            val student = pair.first
            val grades = pair.second
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, index + 1).create()
            val page = pdfDocument.startPage(pageInfo)
            val canvas: Canvas = page.canvas
            var y = 50f

            // Header
            paint.textSize = 24f
            paint.isFakeBoldText = true
            canvas.drawText("SIGESCHOOL PRO", 50f, y, paint)
            y += 40f

            paint.textSize = 18f
            canvas.drawText("Boletín de Calificaciones", 50f, y, paint)
            y += 50f

            // Student Info
            paint.textSize = 14f
            paint.isFakeBoldText = false
            canvas.drawText("Estudiante: ${student.nombreCompleto}", 50f, y, paint)
            y += 20f
            canvas.drawText("Grado: ${student.grado} - ${student.seccion}", 50f, y, paint)
            y += 20f
            canvas.drawText("DNI: ${student.dni}", 50f, y, paint)
            y += 40f

            // Table Header
            paint.isFakeBoldText = true
            canvas.drawText("Materia", 50f, y, paint)
            canvas.drawText("Periodo", 300f, y, paint)
            canvas.drawText("Nota", 500f, y, paint)
            y += 10f
            canvas.drawLine(50f, y, 550f, y, paint)
            y += 25f

            // Grades
            paint.isFakeBoldText = false
            grades.forEach { grade ->
                if (y > 780) return@forEach 
                canvas.drawText(grade.subjectId, 50f, y, paint)
                canvas.drawText(grade.periodId, 300f, y, paint)
                canvas.drawText(grade.score.toString(), 500f, y, paint)
                y += 20f
            }

            y += 20f
            canvas.drawLine(50f, y, 550f, y, paint)
            y += 30f
            
            // Average
            val average = if (grades.isEmpty()) 0.0 else grades.map { it.score }.average()
            paint.isFakeBoldText = true
            canvas.drawText("PROMEDIO GENERAL: ${average.toString().take(4)}", 50f, y, paint)

            pdfDocument.finishPage(page)
        }

        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual fun generateFinancialReport(statement: FinancialStatement): ByteArray {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas
        val paint = Paint()
        var y = 50f

        // Header
        paint.textSize = 20f
        paint.isFakeBoldText = true
        canvas.drawText(statement.institutionName, 50f, y, paint)
        y += 30f
        paint.textSize = 18f
        canvas.drawText(statement.title, 50f, y, paint)
        y += 25f
        paint.textSize = 14f
        paint.isFakeBoldText = false
        canvas.drawText(statement.period, 50f, y, paint)
        y += 40f

        statement.sections.forEach { section ->
            paint.textSize = 16f
            paint.isFakeBoldText = true
            canvas.drawText(section.title, 50f, y, paint)
            y += 10f
            canvas.drawLine(50f, y, 550f, y, paint)
            y += 25f

            paint.textSize = 12f
            paint.isFakeBoldText = false
            section.items.forEach { item ->
                canvas.drawText("${item.code} ${item.name}", 50f, y, paint)
                canvas.drawText("$${item.balance}", 450f, y, paint)
                y += 20f
            }
            
            paint.isFakeBoldText = true
            canvas.drawText("SUBTOTAL ${section.title}", 50f, y, paint)
            canvas.drawText("$${section.subtotal}", 450f, y, paint)
            y += 40f
        }

        paint.textSize = 16f
        paint.isFakeBoldText = true
        canvas.drawLine(50f, y - 20f, 550f, y - 20f, paint)
        canvas.drawText(statement.totalLabel, 50f, y, paint)
        canvas.drawText("$${statement.totalValue}", 450f, y, paint)

        pdfDocument.finishPage(page)
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual fun generateTrialBalanceReport(report: com.sigeschool.domain.model.TrialBalanceReport): ByteArray {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas
        val paint = Paint()
        var y = 50f

        // Header
        paint.textSize = 20f
        paint.isFakeBoldText = true
        canvas.drawText(report.institutionName, 50f, y, paint)
        y += 30f
        paint.textSize = 18f
        canvas.drawText("BALANCE DE PRUEBA", 50f, y, paint)
        y += 25f
        paint.textSize = 14f
        paint.isFakeBoldText = false
        canvas.drawText("Fecha: ${report.date}", 50f, y, paint)
        y += 40f

        // Table Header
        paint.textSize = 10f
        paint.isFakeBoldText = true
        canvas.drawText("CÓDIGO / NOMBRE", 50f, y, paint)
        canvas.drawText("DÉBITO", 300f, y, paint)
        canvas.drawText("CRÉDITO", 400f, y, paint)
        canvas.drawText("SALDO", 500f, y, paint)
        y += 10f
        canvas.drawLine(50f, y, 550f, y, paint)
        y += 20f

        paint.isFakeBoldText = false
        report.items.forEach { item ->
            val shortName = if (item.name.length > 25) item.name.take(22) + "..." else item.name
            canvas.drawText("${item.code} $shortName", 50f, y, paint)
            canvas.drawText("$${item.debits}", 300f, y, paint)
            canvas.drawText("$${item.credits}", 400f, y, paint)
            canvas.drawText("$${item.finalBalance}", 500f, y, paint)
            y += 15f
        }

        y += 10f
        canvas.drawLine(50f, y, 550f, y, paint)
        y += 25f
        paint.isFakeBoldText = true
        canvas.drawText("TOTALES", 50f, y, paint)
        canvas.drawText("$${report.totalDebits}", 300f, y, paint)
        canvas.drawText("$${report.totalCredits}", 400f, y, paint)

        pdfDocument.finishPage(page)
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual fun generateIdCards(cards: List<IdCard>): ByteArray {
        val pdfDocument = PdfDocument()
        val paint = Paint()
        val cardWidth = 242
        val cardHeight = 153
        val cardsPerRow = 2
        val cardsPerCol = 4
        val margin = 40f
        
        var currentCardIndex = 0
        while (currentCardIndex < cards.size) {
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, (currentCardIndex / (cardsPerRow * cardsPerCol)) + 1).create()
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            
            for (row in 0 until cardsPerCol) {
                for (col in 0 until cardsPerRow) {
                    if (currentCardIndex >= cards.size) break
                    
                    val card = cards[currentCardIndex]
                    val x = margin + col * (cardWidth + 20)
                    val y = margin + row * (cardHeight + 20)
                    
                    paint.color = Color.BLACK
                    paint.style = Paint.Style.STROKE
                    canvas.drawRect(x, y, x + cardWidth, y + cardHeight, paint)
                    
                    paint.style = Paint.Style.FILL
                    paint.color = Color.parseColor("#1976D2")
                    canvas.drawRect(x, y, x + 80, y + cardHeight, paint)
                    
                    paint.color = Color.BLACK
                    paint.textSize = 12f
                    canvas.drawText(card.ownerName, x + 90, y + 30, paint)
                    
                    try {
                        val matrix: BitMatrix = MultiFormatWriter().encode(card.identifier, BarcodeFormat.CODE_128, 200, 50)
                        // ... bitmap drawing ...
                    } catch (e: Exception) {}

                    currentCardIndex++
                }
            }
            pdfDocument.finishPage(page)
        }
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual fun generatePayrollReceipt(
        calculation: com.sigeschool.domain.model.PayrollCalculation,
        employeeName: String,
        institutionName: String
    ): ByteArray {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 421, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        canvas.drawText("$institutionName - PAGO NÓMINA", 50f, 50f, paint)
        canvas.drawText("Empleado: $employeeName", 50f, 80f, paint)
        canvas.drawText("Neto: $${calculation.netPay}", 50f, 110f, paint)
        pdfDocument.finishPage(page)
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual fun generateBulkPayrollReport(data: List<Triple<com.sigeschool.domain.model.PayrollCalculation, String, String>>): ByteArray {
        val pdfDocument = PdfDocument()
        data.forEachIndexed { index, triple ->
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, index + 1).create()
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            val paint = Paint()
            canvas.drawText("REPORTE NÓMINA - ${triple.third}", 50f, 50f, paint)
            canvas.drawText("Empleado: ${triple.second}", 50f, 80f, paint)
            canvas.drawText("Neto: $${triple.first.netPay}", 50f, 110f, paint)
            pdfDocument.finishPage(page)
        }
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual fun generateConsentAuditReport(
        consents: List<com.sigeschool.domain.model.Consent>,
        studentName: String
    ): ByteArray {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        canvas.drawText("AUDITORÍA CONSENTIMIENTO - $studentName", 50f, 50f, paint)
        pdfDocument.finishPage(page)
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }

    actual fun generateStudyCertificate(
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
        canvas.drawText("CERTIFICADO DE ESTUDIOS", 200f, 50f, paint)
        canvas.drawText("Estudiante: $studentName", 50f, 100f, paint)
        canvas.drawText("Documento: $documentNumber", 50f, 130f, paint)
        canvas.drawText("Programa: $program", 50f, 160f, paint)
        pdfDocument.finishPage(page)
        val outputStream = ByteArrayOutputStream()
        pdfDocument.writeTo(outputStream)
        pdfDocument.close()
        return outputStream.toByteArray()
    }
}
