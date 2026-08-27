package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "politicas_privacidad")
data class PrivacyPolicyEntity(
    @PrimaryKey
    val id: String,
    val version: Int,
    val fechaPublicacion: Long,
    val contenidoHash: String,
    val contenidoTexto: String,
    @androidx.room.ColumnInfo(name = "es_activa")
    val esActiva: Boolean
)

@Entity(
    tableName = "consentimientos",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PrivacyPolicyEntity::class,
            parentColumns = ["id"],
            childColumns = ["politicaId"]
        )
    ],
    indices = [Index("studentId"), Index("politicaId")]
)
data class ConsentEntity(
    @PrimaryKey
    val id: String,
    val studentId: String,
    val acudienteNombre: String,
    val acudienteDni: String,
    val acudienteParentesco: String,
    val acudienteEmail: String,
    val acudienteTelefono: String,
    val politicaId: String,
    val fechaAceptacion: Long,
    @androidx.room.ColumnInfo(name = "fecha_revocacion")
    val fechaRevocacion: Long? = null,
    @androidx.room.ColumnInfo(name = "motivo_revocacion")
    val motivoRevocacion: String? = null,
    @androidx.room.ColumnInfo(name = "device_info")
    val deviceInfo: String,
    @androidx.room.ColumnInfo(name = "hash_firma_digital")
    val hashFirmaDigital: String,
    val granularConsent: Map<String, Boolean>,
    val version: Long = 0,
    val deviceId: String = "",
    val lastModified: Long = 0,
    val syncStatus: Int = 0,
    val syncAttempts: Int = 0
)

@Entity(tableName = "consentimiento_historial")
data class ConsentHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val consentId: String,
    val studentId: String,
    val action: String, // ACEPTACION, REVOCACION, ACTUALIZACION
    val timestamp: Long,
    val details: String // JSON con cambios o metadatos
)
