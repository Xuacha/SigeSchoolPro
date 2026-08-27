package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import com.sigeschool.domain.model.EntryType

@Entity(tableName = "accounting_entries")
@Serializable
data class AccountingEntryEntity(
    @PrimaryKey
    val id: String,
    val date: String,
    val description: String,
    val institutionId: String,
    val type: EntryType,
    val centerId: String?,
    val entriesJson: String, // Lista de EntryDetail serializada
    val totalDebit: Double,
    val totalCredit: Double,
    val isElectronicInvoiced: Boolean = false,
    val synchronized: Boolean = false
)
