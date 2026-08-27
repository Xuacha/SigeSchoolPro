package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AccountingEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountingEntryDao {
    @Query("SELECT * FROM accounting_entries WHERE institutionId = :institutionId ORDER BY date DESC")
    fun getEntries(institutionId: String): Flow<List<AccountingEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: AccountingEntryEntity)

    @Query("SELECT * FROM accounting_entries WHERE synchronized = 0")
    suspend fun getUnsyncedEntries(): List<AccountingEntryEntity>

    @Query("UPDATE accounting_entries SET synchronized = 1 WHERE id = :id")
    suspend fun markAsSynced(id: String)
}
