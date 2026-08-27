package com.sigeschool.data.local.mapper

import com.sigeschool.data.local.entity.AcudienteEntity
import com.sigeschool.data.local.entity.EstudianteAcudienteEntity
import com.sigeschool.data.local.entity.PreferenciaNotificacionEntity
import com.sigeschool.domain.model.Acudiente
import com.sigeschool.domain.model.EstudianteAcudienteRelacion
import com.sigeschool.domain.model.PreferenciasNotificacion
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

private val json = Json { ignoreUnknownKeys = true }

fun AcudienteEntity.toDomain(preferencias: PreferenciaNotificacionEntity? = null): Acudiente {
    return Acudiente(
        idAcudiente = idAcudiente,
        nombreCompleto = nombreCompleto,
        tipoDocumento = tipoDocumento,
        numeroDocumento = numeroDocumento,
        correoElectronico = correoElectronico,
        telefono = telefono,
        whatsapp = whatsapp,
        direccion = direccion,
        parentesco = parentesco,
        estado = estado,
        fechaRegistro = fechaRegistro,
        fechaActualizacion = fechaActualizacion,
        preferencias = preferencias?.toDomain()
    )
}

fun Acudiente.toEntity(passwordHash: String? = null): AcudienteEntity {
    return AcudienteEntity(
        idAcudiente = idAcudiente,
        nombreCompleto = nombreCompleto,
        tipoDocumento = tipoDocumento,
        numeroDocumento = numeroDocumento,
        correoElectronico = correoElectronico,
        telefono = telefono,
        whatsapp = whatsapp,
        direccion = direccion,
        parentesco = parentesco,
        estado = estado,
        fechaRegistro = fechaRegistro ?: System.currentTimeMillis(),
        fechaActualizacion = System.currentTimeMillis(),
        passwordHash = passwordHash,
        preferenciasJson = preferencias?.let { json.encodeToString(it) }
    )
}

fun PreferenciaNotificacionEntity.toDomain(): PreferenciasNotificacion {
    return PreferenciasNotificacion(
        idPreferencia = idPreferencia,
        idAcudiente = idAcudiente,
        canalesPreferidos = canalesPreferidos.split(",").filter { it.isNotBlank() },
        recibeNotificacionesAsistencia = recibeAsistencia,
        recibeNotificacionesAcademicas = recibeAcademico,
        recibeNotificacionesDisciplinarias = recibeDisciplinario,
        recibeNotificacionesPagos = recibePagos,
        recibeCirculares = recibeCirculares,
        frecuenciaResumen = frecuenciaResumen,
        horaResumen = horaResumen,
        idioma = idioma
    )
}

fun PreferenciasNotificacion.toEntity(): PreferenciaNotificacionEntity {
    return PreferenciaNotificacionEntity(
        idPreferencia = idPreferencia,
        idAcudiente = idAcudiente,
        canalesPreferidos = canalesPreferidos.joinToString(","),
        recibeAsistencia = recibeNotificacionesAsistencia,
        recibeAcademico = recibeNotificacionesAcademicas,
        recibeDisciplinario = recibeNotificacionesDisciplinarias,
        recibePagos = recibeNotificacionesPagos,
        recibeCirculares = recibeCirculares,
        frecuenciaResumen = frecuenciaResumen,
        horaResumen = horaResumen,
        idioma = idioma
    )
}

fun EstudianteAcudienteEntity.toDomain(): EstudianteAcudienteRelacion {
    return EstudianteAcudienteRelacion(
        idRelacion = idRelacion,
        idEstudiante = idEstudiante,
        idAcudiente = idAcudiente,
        esPrincipal = esPrincipal,
        puedeRetirar = puedeRetirar,
        recibeNotificaciones = recibeNotificaciones
    )
}

fun EstudianteAcudienteRelacion.toEntity(): EstudianteAcudienteEntity {
    return EstudianteAcudienteEntity(
        idRelacion = idRelacion,
        idEstudiante = idEstudiante,
        idAcudiente = idAcudiente,
        esPrincipal = esPrincipal,
        puedeRetirar = puedeRetirar,
        recibeNotificaciones = recibeNotificaciones
    )
}
