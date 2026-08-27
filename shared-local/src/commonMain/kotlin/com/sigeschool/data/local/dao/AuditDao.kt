package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AuditEntryEntity

@Dao
interface AuditDao {
    @Query("SELECT * FROM audit_ledger ORDER BY ledgerIndex ASC")
    suspend fun getAllEntries(): List<AuditEntryEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertEntry(entry: AuditEntryEntity)

    @Query("SELECT MAX(ledgerIndex) FROM audit_ledger")
    suspend fun getLastIndex(): Long?

    @Query("SELECT hash FROM audit_ledger ORDER BY ledgerIndex DESC LIMIT 1")
    suspend fun getLastHash(): String?
}
