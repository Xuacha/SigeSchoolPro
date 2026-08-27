package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "family_attendance",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FamilyAttendanceEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val parentName: String,
    val citationDate: Long,
    val attendanceDate: Long? = null,
    val status: String,
    val meetingNotes: String? = null,
    val behavioralImpact: String? = null
)
