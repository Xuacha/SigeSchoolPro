package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "configuracion_alerta")
data class ConfiguracionAlertaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val inasistenciasConsecutivasParaAlerta: Int = 3,
    val diasSemanaUmbral: Int = 3,
    val semanasConsecutivasPatron: Int = 2,
    val nivelAlertaAcudiente: Boolean = true,
    val nivelAlertaDirector: Boolean = true,
    val nivelAlertaJefeArea: Boolean = true,
    val nivelAlertaCoordinador: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
