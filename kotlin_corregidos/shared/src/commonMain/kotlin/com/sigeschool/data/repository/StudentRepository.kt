package com.sigeschool.data.repository

import com.sigeschool.domain.model.Student
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getAllStudents(institutionId: String): Flow<Resource<List<Student>>>
    suspend fun addStudent(student: Student): Resource<Boolean>
    suspend fun updateStudent(student: Student): Resource<Boolean>
    suspend fun deleteStudent(id: Long): Resource<Boolean>
    suspend fun getStudentById(id: Long): Student?
    suspend fun syncWithSupabase(institutionId: String)
}
