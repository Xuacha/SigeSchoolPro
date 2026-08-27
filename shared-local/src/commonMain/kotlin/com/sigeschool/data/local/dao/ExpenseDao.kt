package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity): Long

    @Query("SELECT * FROM expenses WHERE institutionId = :institutionId ORDER BY date DESC")
    fun getAllExpenses(institutionId: String): Flow<List<ExpenseEntity>>

    @Query("SELECT SUM(amount) FROM expenses WHERE institutionId = :institutionId")
    suspend fun getTotalExpenses(institutionId: String): Double?

    @Query("SELECT * FROM expenses WHERE institutionId = :institutionId")
    suspend fun getAllExpensesSync(institutionId: String): List<ExpenseEntity>

    @Query("SELECT * FROM expenses WHERE syncStatus != 0 AND institutionId = :institutionId")
    suspend fun getPendingSyncExpenses(institutionId: String): List<ExpenseEntity>

    @Query("UPDATE expenses SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)

    @Query("DELETE FROM expenses WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteExpense(id: String, institutionId: String)
}
