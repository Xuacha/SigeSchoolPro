package com.sigeschool.util

import com.sigeschool.domain.model.FinancialStatement
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.model.Student

actual object PdfPlatformGenerator {
    actual fun generateStudentReport(student: Student, grades: List<Grade>): ByteArray {
        return generateBulkReport(listOf(student to grades))
    }

    actual fun generateBulkReport(data: List<Pair<Student, List<Grade>>>): ByteArray {
        // En desktop podríamos usar Apache FOP o simplemente devolver el texto por ahora
        // hasta integrar una librería multiplataforma real.
        val reportText = data.joinToString("\n\n-- PAGINA --\n\n") { (student, grades) ->
            """
                SIGESCHOOL PRO - REPORTE DESKTOP
                Estudiante: ${student.nombreCompleto}
                Grado: ${student.grado}
                Promedio: ${grades.map { it.score }.average()}
            """.trimIndent()
        }
        return reportText.encodeToByteArray()
    }

    actual fun generateFinancialReport(statement: FinancialStatement): ByteArray {
        return FinancialStatementGenerator.financialToText(statement).encodeToByteArray()
    }

    actual fun generateTrialBalanceReport(report: com.sigeschool.domain.model.TrialBalanceReport): ByteArray {
        val sb = StringBuilder()
        sb.append("==========================================\n")
        sb.append("${report.institutionName}\n")
        sb.append("BALANCE DE PRUEBA\n")
        sb.append("Fecha: ${report.date}\n")
        sb.append("==========================================\n\n")
        sb.append("CÓDIGO".padEnd(10) + "NOMBRE".padEnd(25) + "DÉBITO".padStart(12) + "CRÉDITO".padStart(12) + "SALDO".padStart(12) + "\n")
        sb.append("--------------------------------------------------------------------------------\n")
        report.items.forEach { item ->
            sb.append(item.code.padEnd(10))
            sb.append(item.name.take(24).padEnd(25))
            sb.append(item.debits.toString().padStart(12))
            sb.append(item.credits.toString().padStart(12))
            sb.append(item.finalBalance.toString().padStart(12))
            sb.append("\n")
        }
        sb.append("--------------------------------------------------------------------------------\n")
        sb.append("TOTALES".padEnd(35) + report.totalDebits.toString().padStart(12) + report.totalCredits.toString().padStart(12) + "\n")
        sb.append("==========================================\n")
        return sb.toString().encodeToByteArray()
    }

    actual fun generateIdCards(cards: List<com.sigeschool.domain.model.IdCard>): ByteArray {
        return cards.joinToString("\n\n") { 
            "CARD: ${it.ownerName} [${it.ownerRole}] ID: ${it.identifier}" 
        }.encodeToByteArray()
    }

    actual fun generatePayrollReceipt(
        calculation: com.sigeschool.domain.model.PayrollCalculation,
        employeeName: String,
        institutionName: String
    ): ByteArray {
        val receipt = """
            $institutionName - COMPROBANTE DE NOMINA 2026
            --------------------------------------------
            Empleado: $employeeName
            Dias Trabajados: ${calculation.daysWorked}
            Salario Base: $${calculation.basicSalary}
            
            DEVENGADOS:
            Sueldo: $${(calculation.basicSalary / 30) * calculation.daysWorked}
            Auxilio Transporte: $${calculation.transportAllowance}
            Total Devengado: $${calculation.totalDevengado}
            
            DEDUCCIONES:
            Salud (4%): $${calculation.healthDeduction}
            Pension (4%): $${calculation.pensionDeduction}
            Adelantos: $${calculation.advances}
            Total Deducciones: $${calculation.totalDeducciones}
            
            NETO A PAGAR: $${calculation.netPay}
            --------------------------------------------
        """.trimIndent()
        return receipt.encodeToByteArray()
    }

    actual fun generateBulkPayrollReport(data: List<Triple<com.sigeschool.domain.model.PayrollCalculation, String, String>>): ByteArray {
        return data.joinToString("\n\n-- PAGINA --\n\n") { (calc, name, inst) ->
            "$inst - REPORTE NOMINA\nEmpleado: $name\nNeto: $${calc.netPay}"
        }.encodeToByteArray()
    }

    actual fun generateConsentAuditReport(
        consents: List<com.sigeschool.domain.model.Consent>,
        studentName: String
    ): ByteArray {
        val sb = StringBuilder()
        sb.append("AUDITORÍA DE CONSENTIMIENTO - SIGESCHOOL\n")
        sb.append("Estudiante: $studentName\n")
        sb.append("==========================================\n\n")
        
        consents.forEach { consent ->
            sb.append("ID CONSENTIMIENTO: ${consent.id}\n")
            sb.append("Acudiente: ${consent.acudienteNombre} (${consent.acudienteParentesco})\n")
            sb.append("Documento: ${consent.acudienteDni}\n")
            sb.append("Fecha: ${java.util.Date(consent.fechaAceptacion)}\n")
            sb.append("ID Política: ${consent.politicaId}\n")
            sb.append("Dispositivo: ${consent.deviceInfo}\n")
            sb.append("Finalidades:\n")
            consent.granularConsent.forEach { (key, value) ->
                sb.append("  - $key: ${if (value) "ACEPTADO" else "RECHAZADO"}\n")
            }
            sb.append("HASH FIRMA: ${consent.hashFirmaDigital}\n")
            sb.append("------------------------------------------\n\n")
        }
        
        return sb.toString().encodeToByteArray()
    }

    actual fun generateStudyCertificate(
        studentName: String,
        studentId: String,
        documentNumber: String,
        program: String,
        institutionId: String
    ): ByteArray {
        val cert = """
            CERTIFICADO DE ESTUDIO - $institutionId
            ---------------------------------------
            ESTUDIANTE: $studentName
            ID: $studentId
            DOCUMENTO: $documentNumber
            PROGRAMA: $program
            ---------------------------------------
        """.trimIndent()
        return cert.encodeToByteArray()
    }
}
