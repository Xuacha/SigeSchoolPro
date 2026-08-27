package com.sigeschool.util

import com.sigeschool.domain.model.FeePayment
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.billing.Invoice

class ReceiptGeneratorImpl : ReceiptGenerator {
    
    override suspend fun generateReceiptPdf(payment: FeePayment, student: Student): ByteArray {
        val content = """
            SIGESCHOOL PRO - COMPROBANTE DE PAGO
            ------------------------------------
            ID Recibo: ${payment.id}
            Fecha: ${payment.fecha}
            
            ESTUDIANTE:
            Nombre: ${student.nombreCompleto}
            DNI: ${student.dni}
            Grado: ${student.grado}
            
            DETALLE:
            Concepto: ${payment.concepto}
            Monto: ${payment.monto}
            Método: ${payment.metodoPago}
            
            Recibido por: ${payment.usuarioRecibe}
            ------------------------------------
            Gracias por su pago.
        """.trimIndent()
        
        return content.encodeToByteArray()
    }

    override suspend fun generateInvoicePdf(invoice: Invoice): ByteArray {
        // En una implementación real, aquí se usaría una plantilla HTML o un DSL de PDF.
        // Simulamos un encabezado profesional y el detalle de ítems.
        val itemsDetail = invoice.items.joinToString("\n") { 
            "${it.description.padEnd(30)} | ${it.quantity} | $${it.unitPrice} | $${it.total}" 
        }

        val content = """
            ${"SIGESCHOOL PRO - FACTURA INTERNA".padStart(45)}
            ${"Nit: 900.123.456-1".padStart(40)}
            ------------------------------------------------------------
            Factura Nro: ${invoice.number}
            Fecha Emisión: ${invoice.date}
            Fecha Vencimiento: ${invoice.dueDate}
            ------------------------------------------------------------
            CLIENTE / ESTUDIANTE:
            Nombre: ${invoice.studentName}
            Acudiente: ${invoice.parentName}
            Grado: ${invoice.grade}
            ------------------------------------------------------------
            DESCRIPCIÓN                      | CANT | UNITARIO | TOTAL
            $itemsDetail
            ------------------------------------------------------------
            SUBTOTAL:                                     $${invoice.totalAmount}
            DESCUENTOS:                                   $0.0
            TOTAL A PAGAR:                                $${invoice.totalAmount}
            ------------------------------------------------------------
            ESTADO: ${invoice.status.name}
            SALDO PENDIENTE: $${invoice.balance}
            
            OBSERVACIONES:
            ${invoice.observations ?: "Ninguna"}
            
            ------------------------------------------------------------
            [FIRMA DIGITAL SIMULADA]
            Rectoría / Contabilidad SigeSchool
            
            Este documento es un soporte interno de cobro y no sustituye
            la factura electrónica de venta exigida por la DIAN.
        """.trimIndent()

        return content.encodeToByteArray()
    }
}
