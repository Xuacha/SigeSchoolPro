package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.PucAccountEntity
import com.sigeschool.domain.model.PucAccount
import com.sigeschool.domain.model.AccountType

fun PucAccountEntity.toDomain(): PucAccount {
    return PucAccount(
        id = id,
        code = code,
        name = name,
        level = level,
        parentCode = parentCode,
        accountType = AccountType.valueOf(accountType),
        institutionId = institutionId,
        isCustom = isCustom,
        isActive = isActive
    )
}

fun PucAccount.toEntity(): PucAccountEntity {
    return PucAccountEntity(
        id = id.ifEmpty { "${institutionId}_$code" },
        code = code,
        name = name,
        level = level,
        parentCode = parentCode,
        accountType = accountType.name,
        institutionId = institutionId,
        isCustom = isCustom,
        isActive = isActive
    )
}
