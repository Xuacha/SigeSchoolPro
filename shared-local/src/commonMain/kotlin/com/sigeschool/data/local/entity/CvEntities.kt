package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "perfiles_personal",
    foreignKeys = [
        ForeignKey(
            entity = RoleEntity::class,
            parentColumns = ["idRol"],
            childColumns = ["idRol"]
        )
    ]
)
data class PerfilPersonalEntity(
    @PrimaryKey val idPerfil: String,
    val idUsuario: String, // Relación con usuarios (de Supabase u otro sistema)
    val idRol: String,
    val datosJson: String, // Todos los campos del CV
    val documentoOriginalPath: String?,
    val documentoOriginalHash: String?,
    val estado: String = "Pendiente",
    val fechaCarga: Long,
    val fechaActualizacion: Long,
    val version: Int = 1
)

@Entity(
    tableName = "historial_cv",
    foreignKeys = [
        ForeignKey(
            entity = PerfilPersonalEntity::class,
            parentColumns = ["idPerfil"],
            childColumns = ["idPerfil"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HistorialCvEntity(
    @PrimaryKey val idHistorial: String,
    val idPerfil: String,
    val version: Int,
    val datosJson: String,
    val fechaModificacion: Long,
    val idUsuarioModificador: String
)
