package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ConsentEntity
import com.sigeschool.data.local.entity.ConsentHistoryEntity
import com.sigeschool.data.local.entity.PrivacyPolicyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConsentDao {
    @Query("SELECT * FROM politicas_privacidad WHERE es_activa = 1 LIMIT 1")
    suspend fun getActivePolicy(): PrivacyPolicyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPolicy(policy: PrivacyPolicyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsent(consent: ConsentEntity)

    @Insert
    suspend fun insertHistory(history: ConsentHistoryEntity)

    @Query("SELECT * FROM consentimientos WHERE studentId = :studentId AND fecha_revocacion IS NULL LIMIT 1")
    suspend fun getValidConsentForStudent(studentId: String): ConsentEntity?

    @Query("SELECT * FROM consentimiento_historial WHERE studentId = :studentId ORDER BY timestamp DESC")
    fun getHistoryForStudent(studentId: String): Flow<List<ConsentHistoryEntity>>

    @Transaction
    suspend fun registerConsentWithHistory(consent: ConsentEntity, history: ConsentHistoryEntity) {
        insertConsent(consent)
        insertHistory(history)
    }

    @Query("UPDATE consentimientos SET fecha_revocacion = :timestamp, motivo_revocacion = :reason WHERE id = :consentId")
    suspend fun revokeConsent(consentId: String, timestamp: Long, reason: String)

    @Query("SELECT * FROM consentimientos WHERE id = :consentId LIMIT 1")
    suspend fun getConsentById(consentId: String): ConsentEntity?
}
