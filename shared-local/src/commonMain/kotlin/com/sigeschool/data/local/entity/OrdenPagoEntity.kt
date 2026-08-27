package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "ordenes_pago")
data class OrdenPagoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val facturaId: String,
    val estudianteId: String,
    val referencia: String,
    val monto: Double,
    val fechaGeneracion: Long = 0,
    val fechaVencimiento: Long,
    val estado: String,
    val metodoPago: String,
    val datosPago: String,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
