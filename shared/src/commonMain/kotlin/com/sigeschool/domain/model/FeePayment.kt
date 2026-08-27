package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FeePayment(
    val id: String = "",
    @SerialName("studentId")
    val studentId: String,
    @SerialName("institutionId")
    val institutionId: String,
    val monto: Double,
    val concepto: String,
    val fecha: String,
    @SerialName("usuarioRecibe")
    val usuarioRecibe: String,
    @SerialName("metodoPago")
    val metodoPago: String = "EFECTIVO",
    @SerialName("receiptUrl")
    val receiptUrl: String? = null,
    val sincronizado: Boolean = false
)
