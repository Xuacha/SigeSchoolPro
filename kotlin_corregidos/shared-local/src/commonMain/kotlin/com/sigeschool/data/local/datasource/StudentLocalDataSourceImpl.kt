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

    override fun searchStudents(institutionId: String, query: String): Flow<List<Student>> {
        return studentDao.searchStudents(institutionId, query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertStudent(student: Student) {
        studentDao.insert(student.toEntity())
    }

    override suspend fun updateStudent(student: Student) {
        studentDao.update(student.toEntity())
    }

    override suspend fun deleteStudentById(id: Long) {
        studentDao.deleteById(id)
    }

    override suspend fun softDeleteStudentById(id: Long) {
        studentDao.softDeleteById(id)
    }

    override suspend fun getStudentById(id: Long): Student? {
        return studentDao.getStudentById(id)?.toDomain()
    }

    override suspend fun getStudentByDni(dni: String): Student? {
        return studentDao.getStudentByDni(dni)?.toDomain()
    }

    override suspend fun getUnsyncedStudents(): List<Student> {
        return studentDao.getUnsyncedStudents().map { it.toDomain() }
    }
}
