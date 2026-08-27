package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "institution_settings")
data class InstitutionSettingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val syncUrl: String? = null,
    val isSyncEnabled: Boolean = false,
    val syncFrequencyHours: Int = 24,
    val lastSyncTimestamp: Long? = null,
    val lastSyncStatus: String? = null,
    val lastSyncMessage: String? = null,
    val downloadUrl: String? = "https://gestionescolar.app/download"
)
