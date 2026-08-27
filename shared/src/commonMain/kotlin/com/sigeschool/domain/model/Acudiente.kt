package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Acudiente(
    val idAcudiente: String,
    val nombreCompleto: String,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val correoElectronico: String,
    val telefono: String,
    val whatsapp: String? = null,
    val direccion: String? = null,
    val parentesco: String,
    val estado: Boolean = true,
    val fechaRegistro: Long? = null,
    val fechaActualizacion: Long? = null,
    val preferencias: PreferenciasNotificacion? = null
)

@Serializable
data class PreferenciasNotificacion(
    val idPreferencia: String,
    val idAcudiente: String,
    val canalesPreferidos: List<String>, // EMAIL, WHATSAPP, SMS, PUSH
    val recibeNotificacionesAsistencia: Boolean = true,
    val recibeNotificacionesAcademicas: Boolean = true,
    val recibeNotificacionesDisciplinarias: Boolean = true,
    val recibeNotificacionesPagos: Boolean = true,
    val recibeCirculares: Boolean = true,
    val frecuenciaResumen: String = "INMEDIATO", // DIARIO, SEMANAL, MENSUAL, INMEDIATO
    val horaResumen: String = "18:00",
    val idioma: String = "es"
)

@Serializable
data class EstudianteAcudienteRelacion(
    val idRelacion: String,
    val idEstudiante: Long,
    val idAcudiente: String,
    val esPrincipal: Boolean = false,
    val puedeRetirar: Boolean = false,
    val recibeNotificaciones: Boolean = true
)
