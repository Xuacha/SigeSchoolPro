package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "employee_docente_cursos",
    foreignKeys = [
        ForeignKey(
            entity = DocenteEntity::class,
            parentColumns = ["id"],
            childColumns = ["docenteId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CursoEntity::class,
            parentColumns = ["id"],
            childColumns = ["cursoId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AsignaturaEntity::class,
            parentColumns = ["id"],
            childColumns = ["asignaturaId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DocenteCursoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institutionId: String,
    val docenteId: Long,
    val cursoId: Long,
    val asignaturaId: Long,
    val cargaHorariaSemanal: Int = 0,
    val esDirectorGrupo: Boolean = false,
    val activo: Boolean = true,
    val syncStatus: Int = 0,
    val lastModified: Long = 0
)
