package com.sigeschool.data.remote

import com.sigeschool.domain.model.billing.Invoice
import com.sigeschool.domain.model.billing.PaymentRecord
import com.sigeschool.domain.model.billing.PaymentTransaction
import com.sigeschool.domain.model.billing.PaymentRequest
import com.sigeschool.domain.model.billing.PaymentResponse

interface BillingRemoteDataSource {
    suspend fun getInvoices(institutionId: String): List<Invoice>
    suspend fun upsertInvoice(invoice: Invoice): Boolean
    suspend fun savePayment(payment: PaymentRecord): Boolean
    suspend fun createPaymentTransaction(transaction: PaymentTransaction): String
    suspend fun updateTransactionStatus(reference: String, status: String): Boolean
}
