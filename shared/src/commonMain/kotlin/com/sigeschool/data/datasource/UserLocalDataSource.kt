package com.sigeschool.data.datasource

import com.sigeschool.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun findByEmailGlobal(email: String): List<User>
    suspend fun getUserCount(): Int
    suspend fun saveUser(user: User)
    fun getUsersByRole(role: String): Flow<List<User>>
    suspend fun getUserById(userId: String): User?
}
