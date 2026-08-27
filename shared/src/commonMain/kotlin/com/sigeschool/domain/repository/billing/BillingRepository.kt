package com.sigeschool.domain.repository.billing

import com.sigeschool.domain.model.billing.*
import kotlinx.coroutines.flow.Flow

interface BillingRepository {
    // Invoices & Payments
    fun getInvoices(institutionId: String): Flow<List<Invoice>>
    fun getInvoiceById(id: String): Flow<Invoice?>
    suspend fun saveInvoice(invoice: Invoice)
    suspend fun savePayment(payment: PaymentRecord)
    
    // Fee Management (Conceptos y Tarifas)
    fun getFeeCategories(): Flow<List<FeeCategory>>
    suspend fun saveFeeCategory(category: FeeCategory)
    
    // Arqueo y Tesorería
    suspend fun openCashBox(registrarId: String, initialAmount: Double): String
    suspend fun closeCashBox(arqueoId: String, actualFinal: Double): Result<Unit>
    fun getCashTransactions(start: Long, end: Long): Flow<List<CashTransaction>>
    
    // Integración Académica
    suspend fun hasPendingDebts(studentId: String): Boolean
    
    suspend fun generateMassiveInvoices(
        institutionId: String,
        studentIds: List<String>,
        concept: String,
        amount: Double,
        month: Int
    ): List<Invoice>

    suspend fun updatePaymentTransactionStatus(reference: String, status: String): Result<Unit>
    suspend fun createPaymentTransaction(transaction: PaymentTransaction): Result<String>
}
