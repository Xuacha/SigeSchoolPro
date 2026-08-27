package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "confirmaciones_pago")
data class ConfirmacionPagoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val ordenPagoId: Long,
    val referenciaIngresada: String,
    val valorIngresado: Double? = null,
    val fechaConfirmacion: Long = 0,
    val estadoValidacion: String,
    val observacion: String? = null,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
