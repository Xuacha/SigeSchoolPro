package com.sigeschool.data.datasource

import com.sigeschool.data.local.dao.ConsentDao
import com.sigeschool.data.local.entity.ConsentEntity
import com.sigeschool.data.local.entity.ConsentHistoryEntity
import com.sigeschool.data.local.entity.PrivacyPolicyEntity
import com.sigeschool.domain.model.Consent
import com.sigeschool.domain.model.PrivacyPolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConsentLocalDataSourceImpl(
    private val consentDao: ConsentDao,
    private val auditDao: com.sigeschool.data.local.dao.AuditDao,
    private val ledger: com.sigeschool.services.security.AuditLedger
) : ConsentLocalDataSource {

    override suspend fun getActivePolicy(): PrivacyPolicy? {
        return consentDao.getActivePolicy()?.toDomain()
    }

    override suspend fun insertPolicy(policy: PrivacyPolicy) {
        consentDao.insertPolicy(policy.toEntity())
    }

    override suspend fun registerConsent(consent: Consent) {
        val lastIndex = auditDao.getLastIndex() ?: -1L
        val lastHash = auditDao.getLastHash() ?: "0"

        val auditEntry = ledger.createEntry(
            index = lastIndex + 1,
            previousHash = lastHash,
            data = "CONSENT_REGISTRATION:${consent.id}:${consent.studentId}"
        )

        val entity = consent.toEntity()
        val history = ConsentHistoryEntity(
            consentId = consent.id,
            studentId = consent.studentId,
            action = "ACEPTACION",
            timestamp = consent.fechaAceptacion,
            details = "Versión política: ${consent.politicaId}"
        )

        // En una implementación real, esto debería estar en una transacción de Room.
        // Como AppDatabase no está disponible aquí directamente como objeto de transacción simple en KMP
        // usamos los DAOs de forma secuencial o inyectamos la DB si es necesario.
        consentDao.registerConsentWithHistory(entity, history)
        auditDao.insertEntry(
            com.sigeschool.data.local.entity.AuditEntryEntity(
                ledgerIndex = auditEntry.index,
                previousHash = auditEntry.previousHash,
                timestamp = auditEntry.timestamp,
                data = auditEntry.data,
                nonce = auditEntry.nonce,
                hash = auditEntry.hash
            )
        )
    }

    override suspend fun isConsentValid(studentId: String): Boolean {
        val activePolicy = consentDao.getActivePolicy() ?: return false
        val consent = consentDao.getValidConsentForStudent(studentId) ?: return false
        return consent.politicaId == activePolicy.id && consent.fechaRevocacion == null
    }

    override suspend fun revokeConsent(consentId: String, reason: String) {
        val timestamp = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        
        // 1. Obtener datos para trazabilidad
        val consent = consentDao.getConsentById(consentId) ?: return

        // 2. Ejecutar revocación en DB
        consentDao.revokeConsent(consentId, timestamp, reason)

        // 3. Registrar en Historial
        val history = ConsentHistoryEntity(
            consentId = consentId,
            studentId = consent.studentId,
            action = "REVOCACION",
            timestamp = timestamp,
            details = "Motivo: $reason"
        )
        consentDao.insertHistory(history)

        // 4. Registrar en Audit Ledger para inmutabilidad
        val lastIndex = auditDao.getLastIndex() ?: -1L
        val lastHash = auditDao.getLastHash() ?: "0"

        val auditEntry = ledger.createEntry(
            index = lastIndex + 1,
            previousHash = lastHash,
            data = "CONSENT_REVOCATION:$consentId:$reason"
        )

        auditDao.insertEntry(
            com.sigeschool.data.local.entity.AuditEntryEntity(
                ledgerIndex = auditEntry.index,
                previousHash = auditEntry.previousHash,
                timestamp = auditEntry.timestamp,
                data = auditEntry.data,
                nonce = auditEntry.nonce,
                hash = auditEntry.hash
            )
        )
    }

    override fun getConsentHistory(studentId: String): Flow<List<Consent>> {
        // Nota: El historial en el DAO devuelve ConsentHistoryEntity, 
        // pero la interfaz pide Flow<List<Consent>>. 
        // Para simplificar esta auditoría, mapearemos los consentimientos vigentes e históricos.
        // En un sistema real, se devolvería una clase de auditoría específica.
        return consentDao.getHistoryForStudent(studentId).map { historyList ->
            historyList.map { 
                // Mapeo mínimo para reporte
                Consent(
                    id = it.consentId,
                    studentId = it.studentId,
                    acudienteNombre = "Historial",
                    acudienteDni = "",
                    acudienteParentesco = "",
                    acudienteEmail = "",
                    acudienteTelefono = "",
                    politicaId = "",
                    fechaAceptacion = it.timestamp,
                    deviceInfo = it.details,
                    hashFirmaDigital = "",
                    granularConsent = emptyMap()
                )
            }
        }
    }

    private fun PrivacyPolicyEntity.toDomain() = PrivacyPolicy(id, version, fechaPublicacion, contenidoHash, contenidoTexto, esActiva)
    private fun PrivacyPolicy.toEntity() = PrivacyPolicyEntity(id, version, fechaPublicacion, contenidoHash, contenidoTexto, esActiva)
    
    private fun ConsentEntity.toDomain() = Consent(id, studentId, acudienteNombre, acudienteDni, acudienteParentesco, acudienteEmail, acudienteTelefono, politicaId, fechaAceptacion, fechaRevocacion, motivoRevocacion, deviceInfo, hashFirmaDigital, granularConsent)
    private fun Consent.toEntity() = ConsentEntity(id, studentId, acudienteNombre, acudienteDni, acudienteParentesco, acudienteEmail, acudienteTelefono, politicaId, fechaAceptacion, fechaRevocacion, motivoRevocacion, deviceInfo, hashFirmaDigital, granularConsent)
}
