package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "docente_sync_configs")
data class DocenteSyncConfigEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val docenteId: String,
    val claseId: Long,
    val type: String, // Normalizado a String para evitar enums en Room si no hay convertidor
    val url: String?,
    val classroomCourseId: String?,
    val classroomCourseWorkId: String?,
    val syncIntervalHours: Int = 24,
    val lastSyncTimestamp: Long = 0,
    val isActive: Boolean = true,
    val syncStatus: Int = 1, // PENDING_INSERT
    val lastModified: Long = 0
)

@Serializable
@Entity(tableName = "docente_sync_logs")
data class DocenteSyncLogEntity(
    @PrimaryKey val id: String,
    val configId: String,
    val timestamp: Long = 0,
    val result: String,
    val message: String?,
    val itemsProcessed: Int = 0
)
