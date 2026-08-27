package com.sigeschool.data.datasource

import com.sigeschool.domain.model.billing.CashArqueo
import com.sigeschool.domain.model.billing.CashTransaction
import kotlinx.coroutines.flow.Flow

interface CashLocalDataSource {
    fun getTransactions(institutionId: String, start: Long, end: Long): Flow<List<CashTransaction>>
    suspend fun insertTransaction(transaction: CashTransaction)
    fun getArqueo(institutionId: String, start: Long, end: Long): Flow<CashArqueo>
}
