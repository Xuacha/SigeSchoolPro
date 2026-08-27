package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.CashClosingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CashClosingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(closing: CashClosingEntity)

    @Query("SELECT * FROM cash_closings WHERE institutionId = :institutionId ORDER BY closingTimestamp DESC")
    fun getAllClosings(institutionId: String): Flow<List<CashClosingEntity>>

    @Query("SELECT * FROM cash_closings WHERE date = :date AND institutionId = :institutionId LIMIT 1")
    suspend fun getClosingByDate(date: String, institutionId: String): CashClosingEntity?

    @Query("SELECT * FROM cash_closings WHERE isSynced = 0")
    suspend fun getUnsyncedClosings(): List<CashClosingEntity>
}
