package com.sigeschool.data.local.dao.billing

import androidx.room.*
import com.sigeschool.data.local.entity.billing.InvoiceEntity
import com.sigeschool.data.local.entity.billing.InvoiceItemEntity
import com.sigeschool.data.local.entity.billing.PaymentRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BillingDao {
    @Query("SELECT * FROM invoices WHERE institutionId = :institutionId ORDER BY date DESC")
    fun getInvoices(institutionId: String): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE id = :id")
    fun getInvoiceById(id: String): Flow<InvoiceEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoice(invoice: InvoiceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoiceItems(items: List<InvoiceItemEntity>)

    @Query("SELECT * FROM invoice_items WHERE invoiceId = :invoiceId")
    suspend fun getItemsByInvoiceId(invoiceId: String): List<InvoiceItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: PaymentRecordEntity)

    @Query("SELECT * FROM payment_records WHERE id = :id")
    suspend fun getPaymentById(id: String): PaymentRecordEntity?

    @Query("SELECT * FROM payment_records WHERE invoiceId = :invoiceId")
    fun getPaymentsByInvoiceId(invoiceId: String): Flow<List<PaymentRecordEntity>>

    @Transaction
    suspend fun saveInvoiceWithItems(invoice: InvoiceEntity, items: List<InvoiceItemEntity>) {
        insertInvoice(invoice)
        insertInvoiceItems(items)
    }

    @Query("DELETE FROM invoices WHERE id = :invoiceId")
    suspend fun deleteInvoice(invoiceId: String)
}
