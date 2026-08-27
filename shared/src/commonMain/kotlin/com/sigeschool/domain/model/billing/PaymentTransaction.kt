package com.sigeschool.domain.model.billing

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PaymentTransaction(
    val id: String? = null,
    @SerialName("institution_id")
    val institutionId: String,
    @SerialName("student_id")
    val studentId: String? = null,
    val monto: Double,
    val estado: String = "PENDIENTE",
    @SerialName("referencia_externa")
    val referenciaExterna: String,
    @SerialName("metodo_pago")
    val metodoPago: String? = null,
    val metadata: Map<String, String> = emptyMap()
)

@Serializable
data class PaymentRequest(
    val amount: Double,
    val description: String,
    val studentId: String,
    val conceptId: String,
    val paymentMethod: String // PSE, NEQUI, etc.
)

@Serializable
data class PaymentResponse(
    val success: Boolean,
    val redirectUrl: String? = null,
    val transactionId: String? = null,
    val error: String? = null
)
