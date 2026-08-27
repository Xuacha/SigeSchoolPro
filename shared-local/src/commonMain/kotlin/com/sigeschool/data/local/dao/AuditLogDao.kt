package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.AuditLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuditLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: AuditLogEntity)

    @Query("SELECT * FROM audit_logs WHERE institutionId = :institutionId ORDER BY timestamp DESC")
    fun getLogsByInstitution(institutionId: String): Flow<List<AuditLogEntity>>

    @Query("SELECT * FROM audit_logs WHERE userId = :userId AND institutionId = :institutionId ORDER BY timestamp DESC")
    fun getLogsByUser(userId: String, institutionId: String): Flow<List<AuditLogEntity>>

    @Query("DELETE FROM audit_logs WHERE timestamp < :timestamp AND institutionId = :institutionId")
    suspend fun deleteOldLogs(timestamp: Long, institutionId: String)
}
