package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.NotificacionEntity
import com.sigeschool.data.local.entity.CircularEntity
import com.sigeschool.data.local.entity.LogNotificacionEntity
import com.sigeschool.domain.model.Notification
import com.sigeschool.domain.model.Circular
import com.sigeschool.domain.model.LogNotificacion
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

private val json = Json { ignoreUnknownKeys = true }

fun NotificacionEntity.toDomain(): Notification {
    return Notification(
        idNotificacion = this.idNotificacion,
        institutionId = this.institutionId,
        idEstudiante = this.idEstudiante,
        idAcudiente = this.idAcudiente,
        idUsuarioRemitente = this.idUsuarioRemitente,
        tipoNotificacion = this.tipoNotificacion,
        asunto = this.asunto,
        mensaje = this.mensaje,
        mensajeWhatsapp = this.mensajeWhatsapp,
        mensajeEmail = this.mensajeEmail,
        fechaEnvio = this.fechaEnvio,
        canales = this.canales.split(",").filter { it.isNotBlank() },
        estadoEnvioEmail = this.estadoEnvioEmail,
        estadoEnvioWhatsapp = this.estadoEnvioWhatsapp,
        estadoEnvioSms = this.estadoEnvioSms,
        estadoEnvioPush = this.estadoEnvioPush,
        idRespuesta = this.idRespuesta,
        prioridad = this.prioridad,
        fechaLecturaAcudiente = this.fechaLecturaAcudiente,
        metadata = this.metadata?.let { json.decodeFromString<Map<String, String>>(it) }
    )
}

fun Notification.toEntity(): NotificacionEntity {
    return NotificacionEntity(
        idNotificacion = this.idNotificacion,
        institutionId = this.institutionId,
        idEstudiante = this.idEstudiante,
        idAcudiente = this.idAcudiente,
        idUsuarioRemitente = this.idUsuarioRemitente,
        tipoNotificacion = this.tipoNotificacion,
        asunto = this.asunto,
        mensaje = this.mensaje,
        mensajeWhatsapp = this.mensajeWhatsapp,
        mensajeEmail = this.mensajeEmail,
        fechaEnvio = this.fechaEnvio,
        canales = this.canales.joinToString(","),
        estadoEnvioEmail = this.estadoEnvioEmail,
        estadoEnvioWhatsapp = this.estadoEnvioWhatsapp,
        estadoEnvioSms = this.estadoEnvioSms,
        estadoEnvioPush = this.estadoEnvioPush,
        idRespuesta = this.idRespuesta,
        prioridad = this.prioridad,
        fechaLecturaAcudiente = this.fechaLecturaAcudiente,
        metadata = this.metadata?.let { json.encodeToString(it) }
    )
}

fun CircularEntity.toDomain(): Circular {
    return Circular(
        idCircular = this.idCircular,
        institutionId = this.institutionId,
        titulo = this.titulo,
        contenido = this.contenido,
        contenidoWhatsapp = this.contenidoWhatsapp,
        contenidoEmail = this.contenidoEmail,
        idUsuarioCreador = this.idUsuarioCreador,
        fechaCreacion = this.fechaCreacion,
        fechaProgramacion = this.fechaProgramacion,
        estado = this.estado,
        destinatarios = this.destinatarios,
        archivosAdjuntos = this.archivosAdjuntos?.let { json.decodeFromString<List<String>>(it) },
        fechaEnvio = this.fechaEnvio
    )
}

fun Circular.toEntity(): CircularEntity {
    return CircularEntity(
        idCircular = this.idCircular,
        institutionId = this.institutionId,
        titulo = this.titulo,
        contenido = this.contenido,
        contenidoWhatsapp = this.contenidoWhatsapp,
        contenidoEmail = this.contenidoEmail,
        idUsuarioCreador = this.idUsuarioCreador,
        fechaCreacion = this.fechaCreacion,
        fechaProgramacion = this.fechaProgramacion,
        estado = this.estado,
        destinatarios = this.destinatarios,
        archivosAdjuntos = this.archivosAdjuntos?.let { json.encodeToString(it) },
        fechaEnvio = this.fechaEnvio
    )
}

fun LogNotificacionEntity.toDomain(): LogNotificacion {
    return LogNotificacion(
        idLog = this.idLog,
        idNotificacion = this.idNotificacion,
        institutionId = this.institutionId,
        canal = this.canal,
        fechaIntento = this.fechaIntento,
        codigoRespuesta = this.codigoRespuesta,
        mensajeRespuesta = this.mensajeRespuesta,
        exito = this.exito,
        intentos = this.intentos
    )
}

fun LogNotificacion.toEntity(): LogNotificacionEntity {
    return LogNotificacionEntity(
        idLog = this.idLog,
        idNotificacion = this.idNotificacion,
        institutionId = this.institutionId,
        canal = this.canal,
        fechaIntento = this.fechaIntento,
        codigoRespuesta = this.codigoRespuesta,
        mensajeRespuesta = this.mensajeRespuesta,
        exito = this.exito,
        intentos = this.intentos
    )
}
