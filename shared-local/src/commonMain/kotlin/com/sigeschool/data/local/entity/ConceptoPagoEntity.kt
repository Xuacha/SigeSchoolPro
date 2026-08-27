package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "cashier_conceptos")
data class ConceptoPagoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val nombre: String,
    val montoBase: Double,
    val descripcion: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
