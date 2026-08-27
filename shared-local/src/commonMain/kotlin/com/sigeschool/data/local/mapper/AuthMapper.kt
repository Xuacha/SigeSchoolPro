package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.UserEntity
import com.sigeschool.domain.model.User
import com.sigeschool.domain.model.UserRole

fun UserEntity.toDomain() = User(
    id = id,
    institutionId = institutionId,
    username = username,
    role = UserRole.fromString(role),
    fullName = fullName,
    email = email,
    profilePictureUri = profilePictureUri
)
