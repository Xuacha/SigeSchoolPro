package com.sigeschool.data.repository.billing

import com.sigeschool.data.remote.BankAccountRemoteDataSource
import com.sigeschool.domain.model.billing.BankAccount
import com.sigeschool.domain.repository.billing.BankAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BankAccountRepositoryImpl(
    private val remoteDataSource: BankAccountRemoteDataSource
) : BankAccountRepository {

    override fun getAccountByInstitution(institutionId: String): Flow<BankAccount?> = flow {
        emit(remoteDataSource.getAccountByInstitution(institutionId))
    }

    override suspend fun saveAccount(account: BankAccount): Result<Unit> = runCatching {
        remoteDataSource.upsertAccount(account)
    }

    override suspend fun updateAccount(account: BankAccount): Result<Unit> = runCatching {
        remoteDataSource.upsertAccount(account)
    }

    override suspend fun deactivateAccount(accountId: String, reason: String): Result<Unit> = runCatching {
        // Implementation for deactivation logic
    }
}
