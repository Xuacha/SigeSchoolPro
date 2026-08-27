package com.sigeschool.domain.usecase.billing

import com.sigeschool.domain.repository.StudentRepository
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.util.SessionManager
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.map

class SearchStudentUseCase(
    private val repository: StudentRepository,
    private val sessionManager: SessionManager
) {
    suspend fun execute(query: String): List<Student> {
        return repository.searchStudents(query)
    }

    operator fun invoke(query: String): kotlinx.coroutines.flow.Flow<List<Student>> {
        return repository.getAllStudents().map { students ->
            if (query.isBlank()) return@map emptyList<Student>()

            students.filter {
                it.nombre.contains(query, ignoreCase = true) ||
                it.apellido.contains(query, ignoreCase = true) ||
                it.dni.contains(query)
            }
        }
    }
}
