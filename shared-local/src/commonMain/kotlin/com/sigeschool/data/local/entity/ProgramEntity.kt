package com.sigeschool.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "programs")
data class ProgramEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    @ColumnInfo(defaultValue = "")
    val codigo: String,
    val name: String,
    val description: String? = null,
    val nivelEducativoId: Long? = null,
    val gradoId: Long? = null,
    @ColumnInfo(defaultValue = "1")
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
