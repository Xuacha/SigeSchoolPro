package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun getUserByIdSync(id: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username AND institutionId = :institutionId LIMIT 1")
    suspend fun getUserByUsername(username: String, institutionId: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT COUNT(*) FROM users WHERE institutionId = :institutionId")
    suspend fun getUserCount(institutionId: String): Int

    @Query("SELECT * FROM users WHERE username = :email")
    suspend fun findByEmailGlobal(email: String): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :email AND institutionId = :institutionId LIMIT 1")
    suspend fun findByEmail(email: String, institutionId: String): UserEntity?

    @Query("SELECT * FROM users WHERE role = :role AND institutionId = :institutionId")
    fun getUsersByRole(role: String, institutionId: String): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE role = :role AND institutionId = :institutionId")
    suspend fun getUsersByRoleSync(institutionId: String, role: String): List<UserEntity>
}
