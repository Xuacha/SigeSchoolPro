package com.sigeschool.domain.model.billing

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Payment(
    val id: String,
    val institutionId: String,
    val studentId: String,
    val amount: Double,
    val date: Long = Clock.System.now().toEpochMilliseconds(),
    val concept: String,
    val paymentMethod: PaymentMethod
)


