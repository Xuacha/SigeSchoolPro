package com.sigeschool.services.security

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.random.Random

@Serializable
data class AuditEntry(
    val index: Long,
    val previousHash: String,
    val timestamp: Long,
    val data: String, // JSON con los datos del consentimiento
    val nonce: String,
    val hash: String // SHA-256 del entry
)

/**
 * Sistema de auditoría inmutable basado en cadena de hashes (tipo Ledger).
 * Garantiza la integridad de los consentimientos de la Ley 1581.
 */
class AuditLedger(private val secretKey: String) {

    fun createEntry(index: Long, previousHash: String, data: String): AuditEntry {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        val nonce = Random.nextLong().toString()
        val hash = calculateHash(previousHash, timestamp, data, nonce)
        
        return AuditEntry(
            index = index,
            previousHash = previousHash,
            timestamp = timestamp,
            data = data,
            nonce = nonce,
            hash = hash
        )
    }

    fun verifyChain(entries: List<AuditEntry>): Boolean {
        var prevHash = "0"
        for (entry in entries) {
            if (entry.previousHash != prevHash) return false
            val calculatedHash = calculateHash(entry.previousHash, entry.timestamp, entry.data, entry.nonce)
            if (entry.hash != calculatedHash) return false
            prevHash = entry.hash
        }
        return true
    }

    private fun calculateHash(previousHash: String, timestamp: Long, data: String, nonce: String): String {
        val input = "$previousHash$timestamp$data$nonce$secretKey"
        return com.sigeschool.util.sha256(input)
    }
}
