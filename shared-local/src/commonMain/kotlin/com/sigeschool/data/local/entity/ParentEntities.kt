package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "acudientes",
    indices = [
        Index(value = ["numeroDocumento"], unique = true),
        Index(value = ["correoElectronico"], unique = true),
        Index(value = ["telefono"], unique = true)
    ]
)
data class AcudienteEntity(
    @PrimaryKey val idAcudiente: String,
    val nombreCompleto: String,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val correoElectronico: String,
    val telefono: String,
    val whatsapp: String?,
    val direccion: String?,
    val parentesco: String,
    val estado: Boolean = true,
    val fechaRegistro: Long,
    val fechaActualizacion: Long,
    val passwordHash: String?,
    val preferenciasJson: String? // JSON con preferencias de notificación completo
)

@Entity(
    tableName = "estudiantes_acudientes",
    primaryKeys = ["idEstudiante", "idAcudiente"],
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["idEstudiante"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AcudienteEntity::class,
            parentColumns = ["idAcudiente"],
            childColumns = ["idAcudiente"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EstudianteAcudienteEntity(
    val idRelacion: String,
    val idEstudiante: String, // Cambiado de Long a String
    val idAcudiente: String,
    val esPrincipal: Boolean = false,
    val puedeRetirar: Boolean = false,
    val recibeNotificaciones: Boolean = true
)

@Entity(
    tableName = "acudiente_preferencias",
    foreignKeys = [
        ForeignKey(
            entity = AcudienteEntity::class,
            parentColumns = ["idAcudiente"],
            childColumns = ["idAcudiente"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PreferenciaNotificacionEntity(
    @PrimaryKey val idPreferencia: String,
    val idAcudiente: String,
    val canalesPreferidos: String, // EMAIL, WHATSAPP, SMS, PUSH
    val recibeAsistencia: Boolean = true,
    val recibeAcademico: Boolean = true,
    val recibeDisciplinario: Boolean = true,
    val recibePagos: Boolean = true,
    val recibeCirculares: Boolean = true,
    val frecuenciaResumen: String = "INMEDIATO",
    val horaResumen: String = "18:00",
    val idioma: String = "es"
)
