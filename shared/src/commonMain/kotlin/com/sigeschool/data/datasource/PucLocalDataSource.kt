package com.sigeschool.data.datasource

import com.sigeschool.domain.model.PucAccount
import com.sigeschool.domain.model.AccountingEntry
import kotlinx.coroutines.flow.Flow

interface PucLocalDataSource {
    fun getAccounts(institutionId: String): Flow<List<PucAccount>>
    suspend fun insertAccounts(accounts: List<PucAccount>)
    suspend fun getAccountByCode(code: String, institutionId: String): PucAccount?
    
    fun getEntries(institutionId: String): Flow<List<AccountingEntry>>
    suspend fun saveEntry(entry: AccountingEntry, isSynced: Boolean)
    suspend fun getUnsyncedEntries(): List<AccountingEntry>
    suspend fun markEntryAsSynced(entryId: String)
}
