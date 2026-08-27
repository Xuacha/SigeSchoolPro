package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "firmas_usuarios")
data class FirmaUsuarioEntity(
    @PrimaryKey
    val userId: String,
    val institutionId: String,
    val firmaPath: String,
    val fechaGuardado: Long = 0,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
