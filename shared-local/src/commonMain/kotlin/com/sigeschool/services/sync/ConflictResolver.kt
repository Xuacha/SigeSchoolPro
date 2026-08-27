package com.sigeschool.services.sync

/**
 * Interfaz para entidades que soportan sincronización con versionamiento.
 */
interface Syncable {
    val id: String
    val version: Long
    val deviceId: String
    val lastModified: Long
}

/**
 * Motor de resolución de conflictos basado en protocolos de consistencia eventual.
 */
object ConflictResolver {
    /**
     * Resuelve conflictos usando la estrategia "Last Write Wins" (LWW).
     * Compara las versiones y retorna la más reciente.
     */
    fun <T : Syncable> resolve(local: T, remote: T): T {
        return if (remote.version > local.version) {
            remote
        } else if (remote.version == local.version) {
            // Empate de versiones: Se desempata por orden alfabético de deviceId (determinístico)
            if (remote.deviceId > local.deviceId) remote else local
        } else {
            local
        }
    }
}
