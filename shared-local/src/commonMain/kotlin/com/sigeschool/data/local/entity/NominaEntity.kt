package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "payroll_nominas")
data class NominaEntity(
    @PrimaryKey val id: String,
    val employeeId: String,
    val institutionId: String,
    val fechaEmision: Long,
    val periodoInicio: Long,
    val periodoFin: Long,
    val salarioBase: Double,
    val bonificaciones: Double,
    val deducciones: Double,
    val totalNeto: Double,
    val estado: String,
    val metodoPago: String,
    val syncStatus: Int = 1, // PENDING_INSERT
    val lastModified: Long = 0
)
