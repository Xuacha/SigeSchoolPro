package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "periodo_configuracion")
data class PeriodoConfiguracionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val periodoAcademicoId: Long,
    val tipoConcepto: String,
    val conceptoId: Long,
    val aplicarCada: Int,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
