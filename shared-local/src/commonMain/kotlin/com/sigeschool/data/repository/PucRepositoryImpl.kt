package com.sigeschool.data.repository

import com.sigeschool.data.datasource.PucLocalDataSource
import com.sigeschool.data.seed.PucSeed
import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.model.PucAccount
import com.sigeschool.domain.model.AccountingEntry
import com.sigeschool.data.remote.PucRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PucRepositoryImpl(
    private val localDataSource: PucLocalDataSource,
    private val remoteDataSource: PucRemoteDataSource,
    private val auditRepository: AuditRepository
) : PucRepository {

    override fun getAccounts(institutionId: String): Flow<List<PucAccount>> {
        return localDataSource.getAccounts(institutionId)
    }

    override suspend fun seedInitialPuc(institutionId: String) {
        val current = localDataSource.getAccounts(institutionId).first()
        if (current.isNotEmpty()) return // Ya tiene datos

        val seed = PucSeed.getFullCatalog().map { it.copy(institutionId = institutionId) }
        localDataSource.insertAccounts(seed)
        
        // Sincronización masiva con remoto
        remoteDataSource.upsertAccounts(seed)
    }

    override suspend fun addCustomAccount(account: PucAccount): Boolean {
        localDataSource.insertAccounts(listOf(account))
        return remoteDataSource.upsertAccount(account)
    }

    override suspend fun getAccountByCode(code: String, institutionId: String): PucAccount? {
        return localDataSource.getAccountByCode(code, institutionId)
    }

    override suspend fun saveEntry(entry: AccountingEntry): Boolean {
        // Validación de partida doble y consistencia del catálogo
        if (!entry.isBalanced) {
            throw IllegalArgumentException("La entrada contable no está balanceada. Débitos: ${entry.totalDebit}, Créditos: ${entry.totalCredit}")
        }
        
        if (entry.entries.isEmpty()) {
            throw IllegalArgumentException("La entrada contable debe tener al menos un detalle.")
        }

        localDataSource.saveEntry(entry, isSynced = false)
        
        auditRepository.log(
            action = "SAVE_ACCOUNTING_ENTRY",
            resource = "accounting/${entry.id}",
            payload = mapOf("totalDebit" to entry.totalDebit, "description" to entry.description)
        )

        return remoteDataSource.saveAccountingEntry(entry)
    }

    override fun getEntries(institutionId: String): Flow<List<AccountingEntry>> {
        return localDataSource.getEntries(institutionId)
    }

    override suspend fun syncEntries(institutionId: String) {
        try {
            // 1. Upload local entries
            val unsynced = localDataSource.getUnsyncedEntries()
            unsynced.forEach { entry ->
                val success = remoteDataSource.saveAccountingEntry(entry)
                if (success) {
                    localDataSource.markEntryAsSynced(entry.id)
                }
            }

            // 2. Download remote entries
            val remoteEntries = remoteDataSource.getAccountingEntries(institutionId)
            remoteEntries.forEach { entry ->
                localDataSource.saveEntry(entry, isSynced = true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
