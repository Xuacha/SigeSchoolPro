package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "cashier_facturas",
    indices = [Index(value = ["fechaVencimiento"], name = "idx_factura_fecha_vencimiento")]
)
data class FacturaEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val numeroFactura: String,
    val fechaEmision: Long,
    val fechaVencimiento: Long,
    val subtotal: Double,
    val impuestos: Double,
    val total: Double,
    val saldoPendiente: Double,
    val estado: String,
    val concepto: String,
    val syncStatus: Int = 1, // PENDING_INSERT
    val lastModified: Long = 0
)
