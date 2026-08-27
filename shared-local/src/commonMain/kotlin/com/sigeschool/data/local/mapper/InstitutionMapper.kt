package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.InstitutionEntity
import com.sigeschool.domain.model.Institution

fun InstitutionEntity.toDomain() = Institution(
    id = id,
    name = name,
    address = address ?: "",
    phone = phone ?: "",
    email = email ?: ""
)
