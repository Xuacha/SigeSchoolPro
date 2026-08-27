package com.sigeschool.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SalaryRecord(
    val id: String = "",
    @SerialName("employee_id")
    val employeeId: String = "",
    @SerialName("institution_id")
    val institutionId: String = "",
    val amount: Double = 0.0,
    val date: Long = 0,
    val type: String = "HONORARIO", // MENSUAL, BONO, DESCUENTO
    val status: String = "PENDIENTE", // PENDIENTE, PAGADO
    val observation: String = "",
    val sincronizado: Boolean = false
)
