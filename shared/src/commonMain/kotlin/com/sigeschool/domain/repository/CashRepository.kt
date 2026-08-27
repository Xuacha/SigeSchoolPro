package com.sigeschool.domain.repository

import com.sigeschool.domain.model.billing.CashArqueo
import com.sigeschool.domain.model.billing.CashTransaction
import kotlinx.coroutines.flow.Flow

interface CashRepository {
    fun getTransactions(institutionId: String, start: Long, end: Long): Flow<List<CashTransaction>>
    suspend fun registerTransaction(transaction: CashTransaction)
    fun getArqueo(institutionId: String, start: Long, end: Long): Flow<CashArqueo>
}
