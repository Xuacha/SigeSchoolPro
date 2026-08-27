package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.UserApprovalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserApprovalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(approval: UserApprovalEntity): Long

    @Update
    suspend fun update(approval: UserApprovalEntity)

    @Query("SELECT * FROM user_approvals WHERE institutionId = :instId AND status = 'PENDIENTE' ORDER BY requestedAt DESC")
    fun getPendingByInstitution(instId: String): Flow<List<UserApprovalEntity>>

    @Query("SELECT * FROM user_approvals WHERE userId = :userId LIMIT 1")
    suspend fun getByUserId(userId: String): UserApprovalEntity?
    
    @Query("SELECT * FROM user_approvals WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<UserApprovalEntity>

    @Query("UPDATE user_approvals SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: Long, timestamp: Long)
}
