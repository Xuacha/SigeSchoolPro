package com.sigeschool.domain.repository

import com.sigeschool.domain.model.PucAccount
import com.sigeschool.domain.model.AccountingEntry
import kotlinx.coroutines.flow.Flow

interface PucRepository {
    fun getAccounts(institutionId: String): Flow<List<PucAccount>>
    suspend fun getAccountByCode(code: String, institutionId: String): PucAccount?
    suspend fun saveEntry(entry: AccountingEntry)
    suspend fun syncWithCloud()
}
