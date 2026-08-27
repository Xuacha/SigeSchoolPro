package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Grade
import kotlinx.coroutines.flow.Flow

interface GradeLocalDataSource {
    fun getGradesByStudent(studentId: String, institutionId: String): Flow<List<Grade>>
    fun getGradesByStudentList(studentIds: List<String>, institutionId: String): Flow<List<Grade>>
    fun getGradesByInstitution(institutionId: String, periodId: String? = null): Flow<List<Grade>>
    suspend fun saveGrade(grade: Grade)
    suspend fun deleteGrade(gradeId: String, institutionId: String)
    suspend fun getUnsyncedGrades(institutionId: String): List<Grade>
}
