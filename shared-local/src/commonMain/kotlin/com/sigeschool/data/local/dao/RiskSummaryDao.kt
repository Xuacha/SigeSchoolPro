package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.RiskSummaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RiskSummaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(summary: RiskSummaryEntity)

    @Query("SELECT * FROM risk_summary WHERE institutionId = :instId")
    suspend fun getByInstitution(instId: String): RiskSummaryEntity?

    @Query("SELECT * FROM risk_summary WHERE institutionId = :instId")
    fun getByInstitutionFlow(instId: String): Flow<RiskSummaryEntity?>

    @Query("SELECT * FROM risk_summary WHERE institutionId = :instId AND syncStatus = 2")
    suspend fun getPendingSync(instId: String): List<RiskSummaryEntity>

    @Query("UPDATE risk_summary SET syncStatus = 0, lastModified = :timestamp WHERE institutionId = :instId")
    suspend fun markAsSynced(instId: String, timestamp: Long)
}
