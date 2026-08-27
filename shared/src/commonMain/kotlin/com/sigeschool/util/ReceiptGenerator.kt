package com.sigeschool.util

import com.sigeschool.domain.model.FeePayment
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.billing.Invoice

interface ReceiptGenerator {
    suspend fun generateReceiptPdf(payment: FeePayment, student: Student): ByteArray
    suspend fun generateInvoicePdf(invoice: Invoice): ByteArray
}
