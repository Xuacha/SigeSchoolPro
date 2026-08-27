package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val idNotificacion: String,
    val institutionId: String,
    val idEstudiante: Long? = null,
    val idAcudiente: String? = null,
    val idUsuarioRemitente: String,
    val tipoNotificacion: String, // ASISTENCIA, ACADEMICO, DISCIPLINARIO, PAGO, CIRCULAR, SISTEMA
    val asunto: String,
    val mensaje: String,
    val mensajeWhatsapp: String? = null,
    val mensajeEmail: String? = null,
    val fechaEnvio: Long,
    val canales: List<String>, // EMAIL, WHATSAPP, SMS, PUSH
    val estadoEnvioEmail: String? = null, // PENDIENTE, ENVIADO, FALLIDO, LEIDO
    val estadoEnvioWhatsapp: String? = null,
    val estadoEnvioSms: String? = null,
    val estadoEnvioPush: String? = null,
    val idRespuesta: String? = null,
    val prioridad: String = "NORMAL", // NORMAL, ALTA, URGENTE
    val fechaLecturaAcudiente: Long? = null,
    val metadata: Map<String, String>? = null
)

@Serializable
data class Circular(
    val idCircular: String,
    val institutionId: String,
    val titulo: String,
    val contenido: String,
    val contenidoWhatsapp: String? = null,
    val contenidoEmail: String? = null,
    val idUsuarioCreador: String,
    val fechaCreacion: Long,
    val fechaProgramacion: Long? = null,
    val estado: String = "BORRADOR", // BORRADOR, PROGRAMADA, ENVIADA, CANCELADA
    val destinatarios: String? = null, // JSON con filtros
    val archivosAdjuntos: List<String>? = null,
    val fechaEnvio: Long? = null
)

@Serializable
data class LogNotificacion(
    val idLog: String,
    val idNotificacion: String,
    val institutionId: String,
    val canal: String,
    val fechaIntento: Long,
    val codigoRespuesta: Int? = null,
    val mensajeRespuesta: String? = null,
    val exito: Boolean = false,
    val intentos: Int = 0
)
