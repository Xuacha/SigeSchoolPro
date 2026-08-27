package com.sigeschool.data.remote

import com.sigeschool.domain.model.billing.BankAccount
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Order

class BankAccountRemoteDataSource(private val postgrest: Postgrest) {

    suspend fun getAccountByInstitution(institutionId: String): BankAccount? {
        return postgrest.from("cuentas_bancarias")
            .select {
                filter {
                    eq("institution_id", institutionId)
                    eq("estado", "ACTIVA")
                }
            }
            .decodeSingleOrNull<BankAccount>()
    }

    suspend fun upsertAccount(account: BankAccount) {
        postgrest.from("cuentas_bancarias").upsert(account)
    }

    suspend fun insertHistory(history: Map<String, String>) {
        postgrest.from("historial_cuentas_bancarias").insert(history)
    }
}
