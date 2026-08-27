package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "cashier_certificados")
data class CertificadoEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val studentId: String,
    val tipo: String,
    val fechaEmision: Long = 0,
    val numeroSerie: String,
    val rutaArchivo: String,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
