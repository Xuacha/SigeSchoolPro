package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Acudiente
import com.sigeschool.domain.model.PreferenciasNotificacion
import com.sigeschool.domain.model.EstudianteAcudienteRelacion
import com.sigeschool.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface AcudienteRepository {
    suspend fun saveAcudiente(acudiente: Acudiente): Result<Unit>
    suspend fun getAcudienteById(id: String): Acudiente?
    suspend fun getAcudienteByDocument(numeroDocumento: String): Acudiente?
    suspend fun getAcudienteByEmail(email: String): Acudiente?
    suspend fun getAcudientesByStudent(studentId: String): List<Acudiente>
    suspend fun getStudentsByAcudiente(acudienteId: String): List<Student>
    suspend fun linkStudent(relacion: EstudianteAcudienteRelacion): Result<Unit>
    suspend fun unlinkStudent(studentId: String, acudienteId: String): Result<Unit>
    suspend fun updatePreferences(preferences: PreferenciasNotificacion): Result<Unit>
    suspend fun getPreferences(acudienteId: String): PreferenciasNotificacion?
    suspend fun authenticate(email: String, passwordHash: String): Acudiente?
    suspend fun clearAllAcudientes()
}
