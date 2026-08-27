package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "roles")
data class RoleEntity(
    @PrimaryKey val idRol: String,
    val nombre: String,
    val nivel: Int, // 1: Mayor privilegio, 10: Menor
    val descripcion: String?,
    val permisosJson: String, // JSON con permisos (ej. {"usuarios": "crud", "pagos": "r"})
    val esSistema: Boolean = false,
    val fechaCreacion: Long,
    val fechaActualizacion: Long
)

@Entity(tableName = "permisos")
data class PermisoEntity(
    @PrimaryKey val idPermiso: String,
    val nombre: String, // ej. "crear_usuario", "ver_pagos", "editar_curriculum"
    val recurso: String, // ej. "usuarios", "pagos", "curriculum"
    val accion: String, // ej. "create", "read", "update", "delete"
    val descripcion: String?
)

@Entity(
    tableName = "roles_permisos",
    primaryKeys = ["idRol", "idPermiso"],
    foreignKeys = [
        ForeignKey(
            entity = RoleEntity::class,
            parentColumns = ["idRol"],
            childColumns = ["idRol"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PermisoEntity::class,
            parentColumns = ["idPermiso"],
            childColumns = ["idPermiso"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RolePermisoCrossReference(
    val idRol: String,
    val idPermiso: String
)
