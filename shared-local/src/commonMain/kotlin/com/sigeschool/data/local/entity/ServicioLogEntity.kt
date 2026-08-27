package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "servicio_logs",
    indices = [
        Index(value = ["studentId", "institutionId"], name = "idx_serv_logs_student"),
        Index(value = ["servicioId", "institutionId"], name = "idx_serv_logs_servicio")
    ]
)
data class ServicioLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val studentId: String,
    val servicioId: Long,
    val docenteId: String? = null,
    val fechaHoraSalida: Long = 0,
    val fechaHoraLlegada: Long? = null,
    val fechaHoraRegreso: Long? = null,
    val motivo: String? = null,
    val estado: String = "EN_CURSO",
    val notificadoAcudiente: Boolean = false,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
