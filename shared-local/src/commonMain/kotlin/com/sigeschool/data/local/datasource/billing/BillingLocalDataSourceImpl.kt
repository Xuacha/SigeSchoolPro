package com.sigeschool.data.local.datasource.billing

import com.sigeschool.data.datasource.billing.BillingLocalDataSource
import com.sigeschool.data.local.dao.CashClosingDao
import com.sigeschool.data.local.dao.CashDao
import com.sigeschool.data.local.dao.billing.BillingDao
import com.sigeschool.data.local.dao.billing.FeeCategoryDao
import com.sigeschool.data.local.mapper.*
import com.sigeschool.domain.model.CashClosing
import com.sigeschool.domain.model.billing.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BillingLocalDataSourceImpl(
    private val billingDao: BillingDao,
    private val feeCategoryDao: FeeCategoryDao,
    private val cashDao: CashDao,
    private val cashClosingDao: CashClosingDao
) : BillingLocalDataSource {

    override fun getInvoices(institutionId: String): Flow<List<Invoice>> {
        return billingDao.getInvoices(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getInvoiceById(id: String): Flow<Invoice?> {
        return billingDao.getInvoiceById(id).map { entity ->
            entity?.let {
                val items = billingDao.getItemsByInvoiceId(it.id)
                it.toDomain(items)
            }
        }
    }

    override suspend fun saveInvoice(invoice: Invoice) {
        billingDao.saveInvoiceWithItems(
            invoice.toEntity(),
            invoice.items.map { it.toEntity(invoice.id) }
        )
    }

    override suspend fun savePayment(payment: PaymentRecord) {
        billingDao.insertPayment(payment.toEntity())
    }

    override suspend fun getPaymentById(id: String): PaymentRecord? {
        return billingDao.getPaymentById(id)?.toDomain()
    }

    override fun getFeeCategories(): Flow<List<FeeCategory>> {
        return feeCategoryDao.getAllCategories().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveFeeCategory(category: FeeCategory) {
        feeCategoryDao.insertCategory(category.toEntity())
    }

    override suspend fun insertTransaction(transaction: CashTransaction) {
        cashDao.insertTransaction(transaction.toEntity())
    }

    override fun getCashTransactions(start: Long, end: Long): Flow<List<CashTransaction>> {
        // FIXME: Assuming institutionId should be handled or passed. For now, matching the interface change if any, 
        // but interface says getCashTransactions(start, end). DAO needs institutionId.
        // We might need to get current institutionId from a session manager.
        return cashDao.getTransactionsByRange("INST-001", start, end).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveCashClosing(closing: CashClosing) {
        cashClosingDao.insert(closing.toEntity())
    }
}
