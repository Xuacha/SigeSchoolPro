package com.sigeschool.domain.repository

import com.sigeschool.domain.model.User
import com.sigeschool.domain.model.Institution
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
    fun getCurrentUser(): Flow<User?>
    suspend fun logout()
    suspend fun hasUsers(): Boolean
    suspend fun createInitialUsers()
    suspend fun isFirstSetupNeeded(): Boolean
    suspend fun registerFirstAdmin(institution: Institution, adminUser: User): Result<Pair<Institution, User>>
    suspend fun changePassword(userId: String, oldPassword: String, newPassword: String): Result<Unit>
    suspend fun findByEmailGlobal(email: String): List<User>
    fun getUsersByRole(role: String): Flow<List<User>>
    suspend fun getInstitutionCount(): Int
    fun getAllInstitutions(): Flow<List<Institution>>
    suspend fun registerUser(user: User, documentId: String): Result<User>
    suspend fun updateFcmToken(userId: String, token: String): Result<Unit>
    fun getCurrentInstitutionId(): String?
}
