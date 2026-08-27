package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.RiskAnalysisEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RiskAnalysisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(analysis: RiskAnalysisEntity)

    @Query("SELECT * FROM risk_analysis WHERE institutionId = :instId")
    fun getByInstitution(instId: String): Flow<List<RiskAnalysisEntity>>

    @Query("SELECT * FROM risk_analysis WHERE studentId = :studentId AND institutionId = :instId")
    suspend fun getByStudent(studentId: String, instId: String): RiskAnalysisEntity?

    @Query("SELECT * FROM risk_analysis WHERE institutionId = :instId AND syncStatus = 2")
    suspend fun getPendingSync(instId: String): List<RiskAnalysisEntity>

    @Query("UPDATE risk_analysis SET syncStatus = 0, lastModified = :timestamp WHERE studentId = :studentId")
    suspend fun markAsSynced(studentId: String, timestamp: Long)

    @Query("UPDATE risk_analysis SET syncStatus = 2 WHERE institutionId = :instId")
    suspend fun markAllPending(instId: String)
}
