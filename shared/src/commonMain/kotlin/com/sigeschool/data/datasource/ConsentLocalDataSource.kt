package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Consent
import com.sigeschool.domain.model.PrivacyPolicy
import kotlinx.coroutines.flow.Flow

interface ConsentLocalDataSource {
    suspend fun getActivePolicy(): PrivacyPolicy?
    suspend fun insertPolicy(policy: PrivacyPolicy)
    suspend fun registerConsent(consent: Consent)
    suspend fun isConsentValid(studentId: String): Boolean
    suspend fun revokeConsent(consentId: String, reason: String)
    fun getConsentHistory(studentId: String): Flow<List<Consent>>
}
