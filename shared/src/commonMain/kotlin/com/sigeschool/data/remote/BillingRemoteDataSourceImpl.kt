package com.sigeschool.data.remote

import io.github.jan.supabase.postgrest.Postgrest
import com.sigeschool.domain.model.billing.*

class BillingRemoteDataSourceImpl(
    private val postgrest: Postgrest
) : BillingRemoteDataSource {
    override suspend fun getInvoices(institutionId: String): List<Invoice> {
        return emptyList()
    }

    override suspend fun upsertInvoice(invoice: Invoice): Boolean {
        return true
    }

    override suspend fun savePayment(payment: PaymentRecord): Boolean {
        return true
    }

    override suspend fun createPaymentTransaction(transaction: PaymentTransaction): String {
        return "pending_ref"
    }

    override suspend fun updateTransactionStatus(reference: String, status: String): Boolean {
        return true
    }
}
