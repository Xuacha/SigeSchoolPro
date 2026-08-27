package com.sigeschool.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class StudentWithPrograms(
    @Embedded val student: StudentEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = StudentProgramEntity::class,
            parentColumn = "studentId",
            entityColumn = "programId"
        )
    )
    val programs: List<ProgramEntity>
)
