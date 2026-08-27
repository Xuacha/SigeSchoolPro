package com.sigeschool.data.service

import java.io.File

actual class PlatformBackupHelper {
    private val dbPath = "sigeschool_encrypted.db"

    actual suspend fun getDatabaseFile(): ByteArray? {
        return try {
            val file = File(dbPath)
            if (file.exists()) {
                file.readBytes()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    actual suspend fun restoreDatabase(bytes: ByteArray): Boolean {
        return try {
            val file = File(dbPath)
            file.writeBytes(bytes)
            true
        } catch (e: Exception) {
            false
        }
    }
}
