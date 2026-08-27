package com.sigeschool.`data`.local.database

import androidx.room.RoomDatabaseConstructor

public actual object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
  override fun initialize(): AppDatabase = com.sigeschool.`data`.local.database.AppDatabase_Impl()
}
