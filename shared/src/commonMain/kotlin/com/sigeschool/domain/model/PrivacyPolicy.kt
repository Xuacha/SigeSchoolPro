package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PrivacyPolicy(
    val id: String,
    val version: Int,
    val fechaPublicacion: Long,
    val contenidoHash: String,
    val contenidoTexto: String,
    val esActiva: Boolean
)

@Serializable
data class Consent(
    val id: String,
    val studentId: String,
    val acudienteNombre: String,
    val acudienteDni: String,
    val acudienteParentesco: String,
    val acudienteEmail: String,
    val acudienteTelefono: String,
    val politicaId: String,
    val fechaAceptacion: Long,
    val fechaRevocacion: Long? = null,
    val motivoRevocacion: String? = null,
    val deviceInfo: String,
    val hashFirmaDigital: String,
    val granularConsent: Map<String, Boolean>
)
