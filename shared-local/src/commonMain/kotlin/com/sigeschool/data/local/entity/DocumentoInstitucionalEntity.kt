package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "documentos_institucionales")
data class DocumentoInstitucionalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val titulo: String,
    val tipo: String,
    val rutaArchivo: String,
    val fechaSubida: Long = 0,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
