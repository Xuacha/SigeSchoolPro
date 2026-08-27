package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.CashLocalDataSource
import com.sigeschool.data.local.dao.CashDao
import com.sigeschool.data.local.entity.CashTransactionEntity
import com.sigeschool.domain.model.billing.CashArqueo
import com.sigeschool.domain.model.billing.CashTransaction
import com.sigeschool.domain.model.billing.CashTransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine

class CashLocalDataSourceImpl(
    private val cashDao: CashDao
) : CashLocalDataSource {

    override fun getTransactions(institutionId: String, start: Long, end: Long): Flow<List<CashTransaction>> {
        return cashDao.getTransactionsByRange(institutionId, start, end).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertTransaction(transaction: CashTransaction) {
        cashDao.insertTransaction(transaction.toEntity())
    }

    override fun getArqueo(institutionId: String, start: Long, end: Long): Flow<CashArqueo> {
        return combine(
            cashDao.getTotalIncomes(institutionId, start, end),
            cashDao.getTotalExpenses(institutionId, start, end)
        ) { incomes, expenses ->
            val totalIncomes = incomes ?: 0.0
            val totalExpenses = expenses ?: 0.0
            CashArqueo(
                initialBalance = 0.0,
                totalIncomes = totalIncomes,
                totalExpenses = totalExpenses,
                finalBalance = totalIncomes - totalExpenses
            )
        }
    }

    private fun CashTransactionEntity.toDomain() = CashTransaction(
        id = id,
        institutionId = institutionId,
        type = CashTransactionType.valueOf(type),
        concept = concept,
        category = category,
        amount = amount,
        paymentMethod = paymentMethod,
        personName = personName,
        reference = reference,
        timestamp = timestamp,
        observations = observations,
        registradoPorId = registradoPorId,
        isSynced = isSynced
    )

    private fun CashTransaction.toEntity() = CashTransactionEntity(
        id = id,
        institutionId = institutionId,
        type = type.name,
        concept = concept,
        category = category,
        amount = amount,
        paymentMethod = paymentMethod,
        personName = personName,
        reference = reference,
        timestamp = timestamp,
        observations = observations,
        registradoPorId = registradoPorId,
        isSynced = isSynced
    )
}
