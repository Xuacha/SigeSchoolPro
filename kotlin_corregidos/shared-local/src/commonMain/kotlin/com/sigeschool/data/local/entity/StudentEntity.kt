package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val grado: String,
    val seccion: String,
    val dni: String,
    val telefono: String,
    val email: String,
    val direccion: String,
    val fechaRegistro: String,
    // FIX: este campo faltaba por completo. Sin él, institutionId se
    // perdía en cada guardado local y volvía siempre como "" al leer,
    // lo que rompía el filtrado multi-institución y hacía fallar la
    // sincronización remota (institution_id vacío no pasa RLS).
    val institutionId: String,
    val estadoMatricula: String, // MATRICULADO, RETIRADO, etc.
    val estadoAcademico: String,  // CURSANDO, PROMOVIDO, etc.
    val activo: Boolean,
    val sincronizado: Boolean
)
