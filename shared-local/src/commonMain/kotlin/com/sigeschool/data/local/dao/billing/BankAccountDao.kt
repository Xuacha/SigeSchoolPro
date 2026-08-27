package com.sigeschool.data.local.dao.billing

import androidx.room.*
import com.sigeschool.data.local.entity.billing.BankAccountEntity
import com.sigeschool.data.local.entity.billing.BankAccountHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao {
    @Query("SELECT * FROM bank_accounts WHERE institutionId = :institutionId AND status = 'ACTIVA' LIMIT 1")
    fun getAccountByInstitution(institutionId: String): Flow<BankAccountEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: BankAccountEntity)

    @Update
    suspend fun updateAccount(account: BankAccountEntity)

    @Query("UPDATE bank_accounts SET status = 'INACTIVA' WHERE id = :accountId")
    suspend fun deactivateAccount(accountId: String)

    @Insert
    suspend fun insertHistory(history: BankAccountHistoryEntity)

    @Query("SELECT * FROM bank_account_history WHERE accountId = :accountId ORDER BY createdAt DESC")
    fun getHistory(accountId: String): Flow<List<BankAccountHistoryEntity>>
}
