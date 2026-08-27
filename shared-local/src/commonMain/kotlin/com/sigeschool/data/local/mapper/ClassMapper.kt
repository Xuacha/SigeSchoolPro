package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.ClassEntity
import com.sigeschool.domain.model.Class

fun ClassEntity.toDomain() = Class(
    id = id,
    name = name,
    level = level,
    institutionId = institutionId,
    teacherId = teacherId,
    createdAt = createdAt
)

fun Class.toEntity() = ClassEntity(
    id = id,
    name = name,
    level = level,
    institutionId = institutionId,
    teacherId = teacherId,
    createdAt = createdAt
)
