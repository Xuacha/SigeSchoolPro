package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "access_logs",
    indices = [
        Index(value = ["studentId", "institutionId"], name = "idx_access_student"),
        Index(value = ["accessTime", "institutionId"], name = "idx_access_time")
    ]
)
data class AccessLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val studentId: String,
    val scannedByUserId: String,
    val scannedByUserName: String,
    val accessTime: Long = 0,
    val tipo: String = "INGRESO",
    val result: String,
    val reason: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
