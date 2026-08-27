package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "institutional_notificaciones",
    foreignKeys = [
        ForeignKey(
            entity = AcudienteEntity::class,
            parentColumns = ["idAcudiente"],
            childColumns = ["idAcudiente"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class InstitutionalNotificationEntity(
    @PrimaryKey val idNotificacion: String,
    val institutionId: String,
    val idEstudiante: Long?,
    val idAcudiente: String?,
    val idUsuarioRemitente: String,
    val tipoNotificacion: String, // ASISTENCIA, ACADEMICO, DISCIPLINARIO, PAGO, CIRCULAR, SISTEMA
    val asunto: String,
    val mensaje: String,
    val mensajeWhatsapp: String?,
    val mensajeEmail: String?,
    val fechaEnvio: Long,
    val canales: String, // EMAIL, WHATSAPP, SMS, PUSH (comma separated)
    val estadoEnvioEmail: String?, // PENDIENTE, ENVIADO, FALLIDO, LEIDO
    val estadoEnvioWhatsapp: String?,
    val estadoEnvioSms: String?,
    val estadoEnvioPush: String?,
    val idRespuesta: String?,
    val prioridad: String = "NORMAL",
    val fechaLecturaAcudiente: Long?,
    val metadata: String? // JSON
)

@Entity(tableName = "circulares")
data class CircularEntity(
    @PrimaryKey val idCircular: String,
    val institutionId: String,
    val titulo: String,
    val contenido: String,
    val contenidoWhatsapp: String?,
    val contenidoEmail: String?,
    val idUsuarioCreador: String,
    val fechaCreacion: Long,
    val fechaProgramacion: Long?,
    val estado: String = "BORRADOR", // BORRADOR, PROGRAMADA, ENVIADA, CANCELADA
    val destinatarios: String?, // JSON con filtros: grados, grupos, roles, etc.
    val archivosAdjuntos: String?, // JSON con lista de archivos
    val fechaEnvio: Long?
)

@Entity(
    tableName = "log_notificaciones",
    foreignKeys = [
        ForeignKey(
            entity = InstitutionalNotificationEntity::class,
            parentColumns = ["idNotificacion"],
            childColumns = ["idNotificacion"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LogNotificacionEntity(
    @PrimaryKey val idLog: String,
    val idNotificacion: String,
    val institutionId: String,
    val canal: String, // EMAIL, WHATSAPP, SMS, PUSH
    val fechaIntento: Long,
    val codigoRespuesta: Int?,
    val mensajeRespuesta: String?,
    val exito: Boolean = false,
    val intentos: Int = 0
)
