package com.sigeschool.data.repository

import com.sigeschool.data.datasource.ConsentLocalDataSource
import com.sigeschool.domain.model.Consent
import com.sigeschool.domain.model.PrivacyPolicy
import com.sigeschool.domain.repository.ConsentRepository
import kotlinx.coroutines.flow.Flow

class ConsentRepositoryImpl(
    private val localDataSource: ConsentLocalDataSource
) : ConsentRepository {

    override suspend fun getActivePolicy(): PrivacyPolicy? {
        return localDataSource.getActivePolicy()
    }

    override suspend fun registerConsent(consent: Consent): Result<Unit> {
        return try {
            localDataSource.registerConsent(consent)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isConsentValid(studentId: String): Boolean {
        return localDataSource.isConsentValid(studentId)
    }

    override suspend fun revokeConsent(consentId: String, reason: String): Result<Unit> {
        return try {
            localDataSource.revokeConsent(consentId, reason)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getConsentHistory(studentId: String): Flow<List<Consent>> {
        return localDataSource.getConsentHistory(studentId)
    }

    override suspend fun insertPolicy(policy: PrivacyPolicy) {
        localDataSource.insertPolicy(policy)
    }
}
