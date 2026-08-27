package com.sigeschool.data.service

actual class PlatformBackupHelper {
    actual suspend fun getDatabaseFile(): ByteArray? {
        // En Web, no podemos acceder al sistema de archivos directamente
        // Se podría implementar exportando el estado de IndexedDB si se usa Room con Driver específico
        return null
    }

    actual suspend fun restoreDatabase(bytes: ByteArray): Boolean {
        return false
    }
}
