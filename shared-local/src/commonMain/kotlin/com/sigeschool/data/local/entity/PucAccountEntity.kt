package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puc_accounts")
data class PucAccountEntity(
    @PrimaryKey val id: String,
    val code: String,
    val name: String,
    val level: Int,
    val parentCode: String?,
    val accountType: String,
    val institutionId: String,
    val isCustom: Boolean,
    val isActive: Boolean
)
