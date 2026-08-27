package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "suscripciones",
    indices = [
        Index(value = ["institutionId"], name = "idx_suscripciones_institution")
    ]
)
data class SuscripcionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val planId: Long,
    val fechaInicio: Long,
    val fechaFin: Long? = null,
    val estado: String,
    val periodoFacturacion: String,
    val ultimoPagoFecha: Long? = null,
    val proximoPagoFecha: Long? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
