package com.sigeschool.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.db.SupportSQLiteOpenHelper
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

actual object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase = AppDatabase_Impl()
}

fun getDatabaseBuilder(ctx: Context, passphraseString: String? = null): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
    
    // Inicializar librerías de SQLCipher
    SQLiteDatabase.loadLibs(appContext)

    val dbFile = appContext.getDatabasePath("sigeschool_encrypted.db")
    
    // Usamos una derivación consistente de la clave para evitar el error "file is not a database"
    val finalKey = passphraseString ?: "SigeSchool2026_${"SigeSchoolMasterKey".hashCode()}"
    val factory = SupportFactory(finalKey.toByteArray())

    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
        .openHelperFactory(factory)
        .addMigrations(MIGRATION_18_19)
}
