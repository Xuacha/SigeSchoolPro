package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "retiros_anticipados")
data class RetiroAnticipadoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val studentId: String,
    val docenteId: String,
    val fechaSalida: Long = 0,
    val motivo: String,
    val motivoOtro: String? = null,
    val tipoFirmante: String,
    val firmanteNombre: String,
    val firmanteDocumento: String,
    val firmaDigitalPath: String,
    val observaciones: String? = null,
    val notificadoAcudiente: Boolean = false,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
