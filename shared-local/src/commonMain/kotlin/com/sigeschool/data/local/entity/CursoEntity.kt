package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "academic_cursos")
data class CursoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val gradoId: Long,
    val sedeId: Long,
    val jornadaId: Long,
    val nombre: String,
    val codigo: String? = null,
    val capacidad: Int = 30,
    val estudiantesInscritos: Int = 0,
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
