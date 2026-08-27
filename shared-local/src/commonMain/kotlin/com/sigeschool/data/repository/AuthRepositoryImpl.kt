package com.sigeschool.data.repository

import com.sigeschool.data.datasource.UserLocalDataSource
import com.sigeschool.data.datasource.InstitutionLocalDataSource
import com.sigeschool.domain.model.User
import com.sigeschool.domain.model.Institution
import com.sigeschool.domain.repository.AuthRepository
import com.sigeschool.domain.util.SessionManager
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val sessionManager: SessionManager,
    private val userLocalDataSource: UserLocalDataSource,
    private val institutionLocalDataSource: InstitutionLocalDataSource
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<User> {
        return try {
            supabaseClient.auth.signInWith(Email) {
                email = username
                this.password = password
            }

            val localUsers = userLocalDataSource.findByEmailGlobal(username)
            var authenticatedUser: User? = localUsers.firstOrNull()

            if (authenticatedUser == null) {
                authenticatedUser = supabaseClient.from("users")
                    .select {
                        filter {
                            eq("email", username)
                        }
                    }
                    .decodeSingleOrNull<User>()
            }

            if (authenticatedUser != null) {
                val userInfo = supabaseClient.auth.currentUserOrNull()
                sessionManager.updateSession(userInfo, authenticatedUser.institutionId)
                Result.success(authenticatedUser)
            } else {
                Result.failure(Exception("Sesión iniciada pero perfil de usuario no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUser(): Flow<User?> {
        return userLocalDataSource.getUsersByRole("ADMIN").map { it.firstOrNull() }
    }

    override suspend fun logout() {
        try {
            supabaseClient.auth.signOut()
        } catch (e: Exception) { /* Ignore */ }
        sessionManager.updateSession(null)
    }

    override suspend fun hasUsers(): Boolean {
        return userLocalDataSource.getUserCount() > 0
    }

    override suspend fun createInitialUsers() { }

    override suspend fun isFirstSetupNeeded(): Boolean {
        return institutionLocalDataSource.count() == 0
    }

    override suspend fun registerFirstAdmin(institution: Institution, adminUser: User): Result<Pair<Institution, User>> {
        return Result.success(Pair(institution, adminUser))
    }

    override suspend fun changePassword(userId: String, oldPassword: String, newPassword: String): Result<Unit> {
        return try {
            supabaseClient.auth.updateUser {
                this.password = newPassword
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun findByEmailGlobal(email: String): List<User> {
        return userLocalDataSource.findByEmailGlobal(email)
    }

    override fun getUsersByRole(role: String): Flow<List<User>> {
        return userLocalDataSource.getUsersByRole(role)
    }

    override suspend fun getInstitutionCount(): Int {
        return institutionLocalDataSource.count()
    }

    override fun getAllInstitutions(): Flow<List<Institution>> {
        return institutionLocalDataSource.getAllActiveInstitutions()
    }

    override suspend fun registerUser(user: User, documentId: String): Result<User> {
        userLocalDataSource.saveUser(user)
        return Result.success(user)
    }

    override suspend fun updateFcmToken(userId: String, token: String): Result<Unit> {
        return Result.success(Unit)
    }

    override fun getCurrentInstitutionId(): String? {
        return sessionManager.getCurrentInstitutionId()
    }
}
