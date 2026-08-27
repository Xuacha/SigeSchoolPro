package com.sigeschool.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "institutions")
data class InstitutionEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String?,
    val phone: String?,
    val email: String? = null,
    val website: String? = null,
    val slogan: String? = null,
    val logoUri: String? = null,
    val createdAt: Long,
    val updatedAt: Long?,
    @ColumnInfo(defaultValue = "1")
    val isActive: Boolean,
    val syncStatus: Int = 0,
    val lastModified: Long = 0,
    val planId: Long? = null,
    @ColumnInfo(defaultValue = "0")
    val estudiantesActivos: Int = 0
)
