package com.sigeschool.domain.model.billing

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Expense(
    val id: String,
    val institutionId: String,
    val amount: Double,
    val date: Long = Clock.System.now().toEpochMilliseconds(),
    val description: String,
    val category: String
)
