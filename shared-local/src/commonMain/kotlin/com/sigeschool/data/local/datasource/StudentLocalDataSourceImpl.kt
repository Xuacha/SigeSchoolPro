package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.StudentLocalDataSource
import com.sigeschool.data.local.dao.StudentDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StudentLocalDataSourceImpl(
    private val studentDao: StudentDao
) : StudentLocalDataSource {
    override fun getAllStudents(institutionId: String): Flow<List<Student>> {
        return studentDao.getAllStudents(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun searchStudents(query: String, institutionId: String): Flow<List<Student>> {
        return studentDao.searchStudents(query, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertStudent(student: Student) {
        studentDao.insert(student.toEntity())
    }

    override suspend fun updateStudent(student: Student) {
        studentDao.update(student.toEntity())
    }

    override suspend fun deleteStudentById(id: String, institutionId: String) {
        studentDao.deleteById(id, institutionId)
    }

    override suspend fun softDeleteStudentById(id: String, institutionId: String) {
        studentDao.softDeleteById(id, institutionId)
    }

    override suspend fun getStudentById(id: String, institutionId: String): Student? {
        return studentDao.getStudentById(id, institutionId)?.toDomain()
    }

    override suspend fun getStudentByDni(dni: String, institutionId: String): Student? {
        return studentDao.getStudentByDni(dni, institutionId)?.toDomain()
    }

    override suspend fun getUnsyncedStudents(): List<Student> {
        return studentDao.getUnsyncedStudents().map { it.toDomain() }
    }

    override suspend fun deleteAll(institutionId: String) {
        studentDao.deleteAll(institutionId)
    }
}
