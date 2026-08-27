package com.sigeschool.domain.repository.billing

import com.sigeschool.domain.model.billing.BankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {
    fun getAccountByInstitution(institutionId: String): Flow<BankAccount?>
    suspend fun saveAccount(account: BankAccount): Result<Unit>
    suspend fun updateAccount(account: BankAccount): Result<Unit>
    suspend fun deactivateAccount(accountId: String, reason: String): Result<Unit>
}
