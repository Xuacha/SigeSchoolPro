package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GradeRepository {
    fun getGradesByStudent(studentId: String, institutionId: String): Flow<Resource<List<Grade>>>
    fun getGradesByClase(claseId: Long): Flow<List<Grade>>
    suspend fun saveGrade(grade: Grade, institutionId: String): Resource<Boolean>
    suspend fun updateGrade(grade: Grade, institutionId: String): Resource<Boolean>
    suspend fun deleteGrade(gradeId: String, institutionId: String): Resource<Boolean>
    suspend fun syncGrades(): Resource<Unit>
}
