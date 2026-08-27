package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AccessLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccessLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: AccessLogEntity): Long

    @Query("SELECT * FROM access_logs WHERE studentId = :studentId AND institutionId = :instId ORDER BY accessTime DESC")
    fun getByStudent(studentId: String, instId: String): Flow<List<AccessLogEntity>>

    @Query("SELECT * FROM access_logs WHERE studentId = :studentId AND accessTime >= :startDate AND accessTime <= :endDate AND institutionId = :instId")
    suspend fun getByStudentAndDateRange(studentId: String, startDate: Long, endDate: Long, instId: String): List<AccessLogEntity>

    @Query("SELECT * FROM access_logs WHERE institutionId = :instId ORDER BY accessTime DESC LIMIT :limit")
    fun getRecent(instId: String, limit: Int = 50): Flow<List<AccessLogEntity>>

    @Query("SELECT * FROM access_logs WHERE institutionId = :instId AND result = 'RECHAZADO' ORDER BY accessTime DESC")
    fun getRejected(instId: String): Flow<List<AccessLogEntity>>

    @Query("SELECT * FROM access_logs WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<AccessLogEntity>

    @Query("UPDATE access_logs SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
