package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Attendance
import com.sigeschool.domain.model.Student
import kotlinx.coroutines.flow.Flow
import com.sigeschool.domain.util.Resource

data class AttendanceWithStudent(
    val attendance: Attendance,
    val student: Student
)

data class GradeAttendance(
    val gradoNombre: String,
    val totalEstudiantes: Int,
    val totalPresentes: Int
)

interface AttendanceRepository {
    fun getAllAttendance(): Flow<List<Attendance>>
    fun getLatestMovements(limit: Int = 20): Flow<List<AttendanceWithStudent>>
    fun getPresentCount(startOfDay: Long, nextDayStart: Long): Flow<Int>
    fun getAbsentCount(startOfDay: Long, nextDayStart: Long): Flow<Int>
    fun getAttendanceByGrade(startOfDay: Long, nextDayStart: Long): Flow<List<GradeAttendance>>
    fun getAttendanceByRange(startOfDay: Long, nextDayStart: Long): Flow<List<AttendanceWithStudent>>

    suspend fun saveAttendance(attendance: Attendance)
    suspend fun saveAttendanceBatch(attendances: List<Attendance>)
    suspend fun getLastAttendanceForStudent(studentId: String): Attendance?
    suspend fun syncAttendance(): Resource<Unit>
    fun triggerImmediateSync()
}
