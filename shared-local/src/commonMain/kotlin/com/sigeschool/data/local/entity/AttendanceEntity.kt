package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "attendance")
data class AttendanceEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val timestamp: Long,
    val type: String, // Normalizado a String
    val claseId: Long? = null,
    val observacion: String? = null,
    val justificacionUrl: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
