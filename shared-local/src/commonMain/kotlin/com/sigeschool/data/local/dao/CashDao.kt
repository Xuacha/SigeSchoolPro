package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.CashTransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CashDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: CashTransactionEntity)

    @Query("SELECT * FROM cash_transactions WHERE institutionId = :institutionId AND timestamp BETWEEN :start AND :end ORDER BY timestamp DESC")
    fun getTransactionsByRange(institutionId: String, start: Long, end: Long): Flow<List<CashTransactionEntity>>

    @Query("SELECT SUM(amount) FROM cash_transactions WHERE institutionId = :institutionId AND type = 'INCOME' AND timestamp BETWEEN :start AND :end")
    fun getTotalIncomes(institutionId: String, start: Long, end: Long): Flow<Double?>

    @Query("SELECT SUM(amount) FROM cash_transactions WHERE institutionId = :institutionId AND type = 'EXPENSE' AND timestamp BETWEEN :start AND :end")
    fun getTotalExpenses(institutionId: String, start: Long, end: Long): Flow<Double?>
}
