package com.sigeschool.services.security

import com.sigeschool.data.local.dao.AuditDao
import com.sigeschool.services.security.AuditLedger
import com.sigeschool.services.security.AuditEntry

class LedgerVerificationService(
    private val auditDao: AuditDao,
    private val ledger: AuditLedger
) {
    suspend fun verifyIntegrity(): VerificationResult {
        return try {
            val entries = auditDao.getAllEntries().map { 
                AuditEntry(
                    index = it.ledgerIndex,
                    previousHash = it.previousHash,
                    timestamp = it.timestamp,
                    data = it.data,
                    nonce = it.nonce,
                    hash = it.hash
                )
            }
            if (entries.isEmpty()) return VerificationResult.Success
            
            val isValid = ledger.verifyChain(entries)
            if (isValid) VerificationResult.Success 
            else VerificationResult.Failure("Cadena de auditoría comprometida")
        } catch (e: Exception) {
            VerificationResult.Failure("Error de auditoría: ${e.message}")
        }
    }

    sealed class VerificationResult {
        object Success : VerificationResult()
        data class Failure(val message: String) : VerificationResult()
    }
}
