package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Consent
import com.sigeschool.domain.model.PrivacyPolicy
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class ConsentRemoteDataSource(
    private val supabase: SupabaseClient
) {
    suspend fun getActivePolicy(): PrivacyPolicy? = withContext(Dispatchers.Default) {
        try {
            supabase.from("privacy_policies")
                .select {
                    filter {
                        eq("esActiva", true)
                    }
                    order("version", Order.DESCENDING)
                    limit(1)
                }.decodeSingleOrNull<PrivacyPolicy>()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun registerConsent(consent: Consent): Boolean = withContext(Dispatchers.Default) {
        try {
            supabase.from("student_consents").insert(consent)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getConsentsByStudent(studentId: Long): List<Consent> = withContext(Dispatchers.Default) {
        try {
            supabase.from("student_consents")
                .select {
                    filter {
                        eq("studentId", studentId)
                    }
                }.decodeList<Consent>()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
