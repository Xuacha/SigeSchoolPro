package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.PucLocalDataSource
import com.sigeschool.data.local.dao.AccountingEntryDao
import com.sigeschool.data.local.dao.PucAccountDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.AccountingEntry
import com.sigeschool.domain.model.PucAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PucLocalDataSourceImpl(
    private val pucDao: PucAccountDao,
    private val entryDao: AccountingEntryDao
) : PucLocalDataSource {

    override fun getAccounts(institutionId: String): Flow<List<PucAccount>> {
        return pucDao.getAllByInstitution(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertAccounts(accounts: List<PucAccount>) {
        pucDao.insert(accounts.map { it.toEntity() })
    }

    override suspend fun getAccountByCode(code: String, institutionId: String): PucAccount? {
        return pucDao.getByCode(code, institutionId)?.toDomain()
    }

    override suspend fun saveEntry(entry: AccountingEntry, isSynced: Boolean) {
        entryDao.insert(entry.toEntity(isSynced))
    }

    override fun getEntries(institutionId: String): Flow<List<AccountingEntry>> {
        return entryDao.getEntries(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUnsyncedEntries(): List<AccountingEntry> {
        return entryDao.getUnsyncedEntries().map { it.toDomain() }
    }

    override suspend fun markEntryAsSynced(entryId: String) {
        entryDao.markAsSynced(entryId)
    }
}
