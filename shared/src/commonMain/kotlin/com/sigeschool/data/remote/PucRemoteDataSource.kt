package com.sigeschool.data.remote

import com.sigeschool.domain.model.PucAccount
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.from

class PucRemoteDataSource(
    private val postgrest: Postgrest
) {
    private val table = "puc_accounts"

    suspend fun getAccounts(institutionId: String): List<PucAccount> {
        return postgrest.from(table)
            .select(columns = Columns.raw("*")) {
                filter {
                    eq("institution_id", institutionId)
                }
            }
            .decodeList<PucAccount>()
    }

    suspend fun upsertAccount(account: PucAccount): Boolean {
        return try {
            postgrest.from(table).upsert(account)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun upsertAccounts(accounts: List<PucAccount>): Boolean {
        return try {
            postgrest.from(table).upsert(accounts)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun saveAccountingEntry(entry: com.sigeschool.domain.model.AccountingEntry): Boolean {
        return try {
            postgrest.from("accounting_entries").upsert(entry)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getAccountingEntries(institutionId: String): List<com.sigeschool.domain.model.AccountingEntry> {
        return postgrest.from("accounting_entries")
            .select {
                filter {
                    eq("institution_id", institutionId)
                }
            }
            .decodeList<com.sigeschool.domain.model.AccountingEntry>()
    }
}
