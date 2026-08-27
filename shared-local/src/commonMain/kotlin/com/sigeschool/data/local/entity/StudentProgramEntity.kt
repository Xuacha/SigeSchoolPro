package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "student_programs",
    primaryKeys = ["studentId", "programId"],
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProgramEntity::class,
            parentColumns = ["id"],
            childColumns = ["programId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudentProgramEntity(
    val studentId: String,
    val programId: String,
    val institutionId: String,
    val enrollmentDate: Long = 0,
    val status: String = "ACTIVE"
)
