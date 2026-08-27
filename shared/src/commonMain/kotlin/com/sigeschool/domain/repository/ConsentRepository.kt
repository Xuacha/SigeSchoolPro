package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Consent
import com.sigeschool.domain.model.PrivacyPolicy
import kotlinx.coroutines.flow.Flow

interface ConsentRepository {
    suspend fun getActivePolicy(): PrivacyPolicy?
    suspend fun registerConsent(consent: Consent): Result<Unit>
    suspend fun isConsentValid(studentId: String): Boolean
    suspend fun revokeConsent(consentId: String, reason: String): Result<Unit>
    fun getConsentHistory(studentId: String): Flow<List<Consent>>
    suspend fun insertPolicy(policy: PrivacyPolicy)
}
