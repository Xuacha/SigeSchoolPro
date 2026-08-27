package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.UserLocalDataSource
import com.sigeschool.data.local.dao.UserDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {
    override suspend fun findByEmailGlobal(email: String): List<User> {
        return userDao.findByEmailGlobal(email).map { it.toDomain() }
    }

    override suspend fun getUserCount(): Int {
        return userDao.getUserCount("")
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(com.sigeschool.data.local.entity.UserEntity(
            id = user.id,
            institutionId = user.institutionId,
            username = user.username,
            password = "", // No guardamos la clave por seguridad
            role = user.role.name,
            fullName = user.fullName,
            email = user.email,
            profilePictureUri = user.profilePictureUrl,
            fcmToken = null,
            isFirstLogin = false,
            isActive = true
        ))
    }

    override fun getUsersByRole(role: String): Flow<List<User>> {
        return userDao.getUsersByRole(role, "").map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)?.toDomain()
    }
}
