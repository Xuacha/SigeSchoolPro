package com.sigeschool.data.repository

import com.sigeschool.data.local.dao.ParentDao
import com.sigeschool.data.local.dao.StudentDao
import com.sigeschool.data.local.mapper.*
import com.sigeschool.domain.model.*
import com.sigeschool.domain.repository.AcudienteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AcudienteRepositoryImpl(
    private val parentDao: ParentDao,
    private val studentDao: StudentDao
) : AcudienteRepository {

    override suspend fun saveAcudiente(acudiente: Acudiente): Result<Unit> = runCatching {
        parentDao.insertAcudiente(acudiente.toEntity())
    }

    override suspend fun getAcudienteById(id: String): Acudiente? {
        return parentDao.getAcudienteById(id)?.toDomain()
    }

    override suspend fun getAcudienteByDocument(numeroDocumento: String): Acudiente? {
        return parentDao.getAcudienteByDocument(numeroDocumento)?.toDomain()
    }

    override suspend fun getAcudienteByEmail(email: String): Acudiente? {
        return parentDao.getAcudienteByEmail(email)?.toDomain()
    }

    override suspend fun getAcudientesByStudent(studentId: String): List<Acudiente> {
        return parentDao.getAcudientesByEstudiante(studentId).map { it.toDomain() }
    }

    override suspend fun getStudentsByAcudiente(acudienteId: String): List<Student> {
        return parentDao.getEstudiantesByAcudiente(acudienteId).map { it.toDomain() }
    }

    override suspend fun linkStudent(relacion: EstudianteAcudienteRelacion): Result<Unit> = runCatching {
        parentDao.insertVinculacion(relacion.toEntity())
    }

    override suspend fun unlinkStudent(studentId: String, acudienteId: String): Result<Unit> = runCatching {
        parentDao.deleteVinculacion(studentId, acudienteId)
    }

    override suspend fun updatePreferences(preferences: PreferenciasNotificacion): Result<Unit> = runCatching {
        parentDao.insertPreferencias(preferences.toEntity())
    }

    override suspend fun getPreferences(acudienteId: String): PreferenciasNotificacion? {
        return parentDao.getPreferenciasByAcudiente(acudienteId)?.toDomain()
    }

    override suspend fun authenticate(email: String, passwordHash: String): Acudiente? {
        val entity = parentDao.getAcudienteByEmail(email)
        return if (entity?.passwordHash == passwordHash) {
            entity.toDomain()
        } else {
            null
        }
    }

    override suspend fun clearAllAcudientes() {
        parentDao.deleteAllAcudientes()
        parentDao.deleteAllVinculaciones()
    }
}
