package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "listado_config")
data class ListadoConfigEntity(
    @PrimaryKey val institutionId: String,
    val tamanoPapel: String = "CARTA",
    val incluirLogo: Boolean = true,
    val incluirFirmas: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
