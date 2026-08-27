package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.BackupLogEntity
import com.sigeschool.data.local.entity.KeyBackupLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BackupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: BackupLogEntity)

    @Update
    suspend fun updateLog(log: BackupLogEntity)

    @Query("SELECT * FROM logs_backup ORDER BY fechaInicio DESC")
    fun getAllLogs(): Flow<List<BackupLogEntity>>

    @Query("SELECT * FROM logs_backup WHERE idLog = :id")
    suspend fun getLogById(id: String): BackupLogEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeyBackupLog(log: KeyBackupLogEntity)

    @Query("SELECT * FROM logs_backup_llaves ORDER BY fecha DESC")
    fun getAllKeyBackupLogs(): Flow<List<KeyBackupLogEntity>>
}
