package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_matriculas")
data class MatriculaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val estudianteId: String,
    val claseId: Long,
    val fechaMatricula: Long = 0,
    val estado: String = "ACTIVA",
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
