package com.sigeschool.data.repository

import com.sigeschool.data.datasource.CashLocalDataSource
import com.sigeschool.domain.model.billing.CashArqueo
import com.sigeschool.domain.model.billing.CashTransaction
import com.sigeschool.domain.repository.CashRepository
import kotlinx.coroutines.flow.Flow

class CashRepositoryImpl(
    private val localDataSource: CashLocalDataSource
) : CashRepository {
    override fun getTransactions(institutionId: String, start: Long, end: Long): Flow<List<CashTransaction>> {
        return localDataSource.getTransactions(institutionId, start, end)
    }

    override suspend fun registerTransaction(transaction: CashTransaction) {
        localDataSource.insertTransaction(transaction)
    }

    override fun getArqueo(institutionId: String, start: Long, end: Long): Flow<CashArqueo> {
        return localDataSource.getArqueo(institutionId, start, end)
    }
}
