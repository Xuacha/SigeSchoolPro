package com.sigeschool.data.local.entity.billing

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_accounts")
data class BankAccountEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val bankName: String,
    val accountType: String,
    val accountNumber: String, // Encrypted
    val holderName: String,
    val holderDni: String,
    val notificationEmail: String?,
    val status: String,
    val createdAt: Long?,
    val updatedAt: Long?
)

@Entity(tableName = "bank_account_history")
data class BankAccountHistoryEntity(
    @PrimaryKey val id: String,
    val accountId: String,
    val userId: String,
    val action: String,
    val previousData: String?, // JSON
    val createdAt: Long
)
