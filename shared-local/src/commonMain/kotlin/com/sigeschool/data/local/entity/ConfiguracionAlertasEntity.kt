package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "configuracion_alertas")
data class ConfiguracionAlertasEntity(
    @PrimaryKey
    val institutionId: String,
    val umbralInasistenciaConsecutiva: Int = 3,
    val umbralAsistenciaSemanal: Int = 3,
    val umbralServiciosExcesivos: Int = 3,
    val umbralTardanzaMensual: Int = 3,
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
