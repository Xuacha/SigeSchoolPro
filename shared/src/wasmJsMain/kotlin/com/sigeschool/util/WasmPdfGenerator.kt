@file:OptIn(kotlin.js.ExperimentalJsExport::class)

package com.sigeschool.util

import com.sigeschool.domain.model.FinancialStatement
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student

// Declaraciones externas para interactuar con jsPDF
@JsModule("jspdf")
external class jsPDF(
    orientation: String = definedExternally,
    unit: String = definedExternally,
    format: String = definedExternally
) : JsAny {
    fun text(text: String, x: Double, y: Double)
    fun setFontSize(size: Double)
    fun setFont(fontName: String, fontStyle: String)
    fun line(x1: Double, y1: Double, x2: Double, y2: Double)
    fun addPage()
    fun output(type: String): JsAny?
    fun save(filename: String)
}

// Función external para instanciar jsPDF si el constructor directo falla por el empaquetado
private fun newJsPdf(): jsPDF = js("new jspdf.jsPDF()")

actual object PdfPlatformGenerator {
    
    actual fun generateStudentReport(student: Student, grades: List<Grade>): ByteArray {
        val doc = newJsPdf()
        setupDoc(doc, student, grades)
        doc.save("${student.nombreCompleto}_Boletin.pdf")
        return ByteArray(0)
    }

    actual fun generateBulkReport(data: List<Pair<Student, List<Grade>>>): ByteArray {
        val doc = newJsPdf()
        data.forEachIndexed { index, (student, grades) ->
            if (index > 0) {
                doc.addPage()
            }
            setupDoc(doc, student, grades)
        }
        doc.save("Reporte_Masivo.pdf")
        return ByteArray(0)
    }
    
    private fun setupDoc(doc: jsPDF, student: Student, grades: List<Grade>) {
        doc.setFontSize(22.0)
        doc.text("SIGESCHOOL PRO", 20.0, 20.0)
        
        doc.setFontSize(16.0)
        doc.text("Boletín de Calificaciones", 20.0, 35.0)
        
        doc.setFontSize(12.0)
        doc.text("Estudiante: ${student.nombreCompleto}", 20.0, 55.0)
        doc.text("Grado: ${student.grado} - ${student.seccion}", 20.0, 65.0)
        doc.text("DNI: ${student.dni}", 20.0, 75.0)
        
        doc.setFontSize(14.0)
        doc.text("Materia", 20.0, 95.0)
        doc.text("Nota", 150.0, 95.0)
        doc.line(20.0, 100.0, 190.0, 100.0)
        
        doc.setFontSize(12.0)
        var y = 110.0
        grades.forEach { grade ->
            if (y > 270.0) return@forEach
            doc.text(grade.subjectId, 20.0, y)
            doc.text(grade.score.toString(), 150.0, y)
            y += 10.0
        }
        
        val average = if (grades.isEmpty()) 0.0 else grades.map { it.score }.average()
        doc.setFontSize(14.0)
        doc.text("PROMEDIO GENERAL: ${average.toString().take(5)}", 20.0, y + 10.0)
    }

    actual fun generateFinancialReport(statement: FinancialStatement): ByteArray {
        val doc = newJsPdf()
        doc.setFontSize(20.0)
        doc.text(statement.institutionName, 20.0, 20.0)
        doc.setFontSize(18.0)
        doc.text(statement.title, 20.0, 30.0)
        doc.setFontSize(14.0)
        doc.text(statement.period, 20.0, 40.0)
        
        var y = 60.0
        statement.sections.forEach { section ->
            doc.setFontSize(16.0)
            doc.text(section.title, 20.0, y)
            y += 5.0
            doc.line(20.0, y, 190.0, y)
            y += 10.0
            
            doc.setFontSize(12.0)
            section.items.forEach { item ->
                doc.text("${item.code} ${item.name}", 20.0, y)
                doc.text("$${item.balance}", 150.0, y)
                y += 10.0
                if (y > 270) {
                    doc.addPage()
                    y = 20.0
                }
            }
            
            doc.setFontSize(12.0)
            doc.text("SUBTOTAL ${section.title}: $${section.subtotal}", 20.0, y + 5.0)
            y += 20.0
        }
        
        doc.setFontSize(16.0)
        doc.line(20.0, y, 190.0, y)
        doc.text("${statement.totalLabel}: $${statement.totalValue}", 20.0, y + 10.0)
        
        doc.save("${statement.title}.pdf")
        return ByteArray(0)
    }

    actual fun generateTrialBalanceReport(report: com.sigeschool.domain.model.TrialBalanceReport): ByteArray {
        val doc = newJsPdf()
        doc.setFontSize(20.0)
        doc.text(report.institutionName, 20.0, 20.0)
        doc.setFontSize(18.0)
        doc.text("BALANCE DE PRUEBA", 20.0, 30.0)
        doc.setFontSize(14.0)
        doc.text("Fecha: ${report.date}", 20.0, 40.0)

        doc.setFontSize(10.0)
        doc.text("CÓDIGO / NOMBRE", 20.0, 60.0)
        doc.text("DÉBITO", 100.0, 60.0)
        doc.text("CRÉDITO", 130.0, 60.0)
        doc.text("SALDO", 160.0, 60.0)
        doc.line(20.0, 65.0, 190.0, 65.0)

        var y = 75.0
        report.items.forEach { item ->
            doc.text("${item.code} ${item.name.take(20)}", 20.0, y)
            doc.text(item.debits.toString(), 100.0, y)
            doc.text(item.credits.toString(), 130.0, y)
            doc.text(item.finalBalance.toString(), 160.0, y)
            y += 10.0
            if (y > 270) {
                doc.addPage()
                y = 20.0
            }
        }

        doc.line(20.0, y, 190.0, y)
        doc.text("TOTALES", 20.0, y + 10.0)
        doc.text(report.totalDebits.toString(), 100.0, y + 10.0)
        doc.text(report.totalCredits.toString(), 130.0, y + 10.0)

        doc.save("Balance_Prueba_${report.date}.pdf")
        return ByteArray(0)
    }

    actual fun generateIdCards(cards: List<com.sigeschool.domain.model.IdCard>): ByteArray {
        val doc = newJsPdf()
        cards.forEachIndexed { index, card ->
            if (index > 0) doc.addPage()
            doc.setFontSize(14.0)
            doc.text("ID CARD - ${card.institutionName}", 20.0, 20.0)
            doc.setFontSize(12.0)
            doc.text("Nombre: ${card.ownerName}", 20.0, 40.0)
            doc.text("Rol: ${card.ownerRole}", 20.0, 50.0)
            doc.text("ID: ${card.identifier}", 20.0, 60.0)
            doc.text("|| ||| || |||| ||", 20.0, 80.0)
        }
        doc.save("Carnets.pdf")
        return ByteArray(0)
    }

    actual fun generatePayrollReceipt(
        calculation: com.sigeschool.domain.model.PayrollCalculation,
        employeeName: String,
        institutionName: String
    ): ByteArray {
        val doc = newJsPdf()
        doc.setFontSize(16.0)
        doc.text(institutionName, 20.0, 20.0)
        doc.text("PAGO NOMINA", 20.0, 30.0)
        doc.setFontSize(12.0)
        doc.text("Empleado: $employeeName", 20.0, 45.0)
        doc.text("Neto: $${calculation.netPay}", 20.0, 55.0)
        doc.save("Nomina_$employeeName.pdf")
        return ByteArray(0)
    }

    actual fun generateBulkPayrollReport(data: List<Triple<com.sigeschool.domain.model.PayrollCalculation, String, String>>): ByteArray {
        val doc = newJsPdf()
        data.forEachIndexed { index, (calc, name, inst) ->
            if (index > 0) doc.addPage()
            doc.text("$inst - NOMINA", 20.0, 20.0)
            doc.text("Empleado: $name", 20.0, 40.0)
            doc.text("Neto: $${calc.netPay}", 20.0, 50.0)
        }
        doc.save("Reporte_Nomina_Masivo.pdf")
        return ByteArray(0)
    }

    actual fun generateConsentAuditReport(
        consents: List<com.sigeschool.domain.model.Consent>,
        studentName: String
    ): ByteArray {
        val doc = newJsPdf()
        consents.forEachIndexed { index, consent ->
            if (index > 0) doc.addPage()
            doc.setFontSize(18.0)
            doc.text("AUDITORÍA DE CONSENTIMIENTO", 20.0, 20.0)
            
            doc.setFontSize(12.0)
            doc.text("Estudiante: $studentName", 20.0, 35.0)
            doc.text("ID Consentimiento: ${consent.id}", 20.0, 45.0)
            
            doc.text("Representante: ${consent.acudienteNombre}", 20.0, 60.0)
            doc.text("Documento: ${consent.acudienteDni}", 20.0, 70.0)
            doc.text("Parentesco: ${consent.acudienteParentesco}", 20.0, 80.0)
            
            doc.text("Fecha Aceptación: ${consent.fechaAceptacion}", 20.0, 95.0)
            doc.text("ID Política: ${consent.politicaId}", 20.0, 105.0)
            doc.text("Dispositivo: ${consent.deviceInfo}", 20.0, 115.0)
            
            doc.text("Finalidades:", 20.0, 130.0)
            var y = 140.0
            consent.granularConsent.forEach { (key, value) ->
                doc.text("- $key: ${if (value) "SI" else "NO"}", 30.0, y)
                y += 10.0
            }
            
            doc.setFontSize(8.0)
            doc.text("Hash Firma: ${consent.hashFirmaDigital}", 20.0, 280.0)
        }
        doc.save("Auditoria_Consentimiento_$studentName.pdf")
        return ByteArray(0)
    }

    actual fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray {
        val doc = newJsPdf()
        doc.setFontSize(22.0)
        doc.text("CERTIFICADO DE ESTUDIOS", 20.0, 30.0)
        doc.setFontSize(12.0)
        doc.text("Estudiante: $studentName", 20.0, 50.0)
        doc.text("Documento: $documentNumber", 20.0, 60.0)
        doc.text("Programa: $program", 20.0, 70.0)
        doc.save("Certificado_$studentName.pdf")
        return ByteArray(0)
    }
}

