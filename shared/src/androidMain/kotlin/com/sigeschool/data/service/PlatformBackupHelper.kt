package com.sigeschool.data.service

import android.content.Context
import java.io.File

actual class PlatformBackupHelper(private val context: Context) {
    actual suspend fun getDatabaseFile(): ByteArray? {
        return try {
            val dbFile = context.getDatabasePath("sigeschool_encrypted.db")
            if (dbFile.exists()) {
                dbFile.readBytes()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    actual suspend fun restoreDatabase(bytes: ByteArray): Boolean {
        return try {
            val dbFile = context.getDatabasePath("sigeschool_encrypted.db")
            dbFile.writeBytes(bytes)
            true
        } catch (e: Exception) {
            false
        }
    }
}
