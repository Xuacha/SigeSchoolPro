package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "employee_docentes",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DocenteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val institutionId: String,
    val especialidad: String? = null,
    val tipoContrato: String? = null,
    val fechaIngreso: Long = 0,
    val estado: String = "ACTIVO",
    val sedePrincipalId: Long? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
