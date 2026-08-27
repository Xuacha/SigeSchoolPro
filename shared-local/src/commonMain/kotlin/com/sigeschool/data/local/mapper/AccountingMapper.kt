package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.AccountingEntryEntity
import com.sigeschool.domain.model.AccountingEntry
import com.sigeschool.domain.model.EntryDetail
import com.sigeschool.domain.model.EntryType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.datetime.Clock

fun AccountingEntryEntity.toDomain(): AccountingEntry {
    val entries = try {
        Json.decodeFromString<List<EntryDetail>>(entriesJson)
    } catch (e: Exception) {
        emptyList()
    }
    return AccountingEntry(
        id = id,
        date = date,
        description = description,
        institutionId = institutionId,
        type = type,
        centerId = centerId,
        entries = entries,
        totalDebit = totalDebit,
        totalCredit = totalCredit,
        isElectronicInvoiced = isElectronicInvoiced
    )
}

fun AccountingEntry.toEntity(isSynced: Boolean = false): AccountingEntryEntity {
    return AccountingEntryEntity(
        id = id.ifEmpty { "entry_${Clock.System.now().toEpochMilliseconds()}" },
        date = date,
        description = description,
        institutionId = institutionId,
        type = type,
        centerId = centerId,
        entriesJson = Json.encodeToString(entries),
        totalDebit = totalDebit,
        totalCredit = totalCredit,
        isElectronicInvoiced = isElectronicInvoiced,
        synchronized = isSynced
    )
}
