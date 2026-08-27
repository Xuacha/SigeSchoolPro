package com.sigeschool.data.datasource.billing

import com.sigeschool.domain.model.billing.*
import kotlinx.coroutines.flow.Flow

interface BillingLocalDataSource {
    // Invoices & Payments
    fun getInvoices(institutionId: String): Flow<List<Invoice>>
    fun getInvoiceById(id: String): Flow<Invoice?>
    suspend fun saveInvoice(invoice: Invoice)
    suspend fun savePayment(payment: PaymentRecord)
    suspend fun getPaymentById(id: String): PaymentRecord?
    
    // Fee Categories
    fun getFeeCategories(): Flow<List<FeeCategory>>
    suspend fun saveFeeCategory(category: FeeCategory)
    
    // Cash Transactions
    suspend fun insertTransaction(transaction: CashTransaction)
    fun getCashTransactions(start: Long, end: Long): Flow<List<CashTransaction>>
    
    // Arqueos
    suspend fun saveCashClosing(closing: com.sigeschool.domain.model.CashClosing)
}
