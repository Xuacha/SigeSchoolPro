package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class IdCard(
    val id: String,
    val ownerName: String,
    val ownerRole: String, // ESTUDIANTE, DOCENTE, ADMINISTRATIVO
    val identifier: String, // DNI o ID único para el código de barras
    val institutionName: String,
    val photoUrl: String? = null,
    val barcodeType: String = "CODE_128",
    val qrData: String? = null,
    val expirationDate: String? = null,
    val grade: String? = null
)
