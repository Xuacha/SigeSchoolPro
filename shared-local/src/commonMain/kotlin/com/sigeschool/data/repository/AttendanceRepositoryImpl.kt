package com.sigeschool.data.repository

import com.sigeschool.data.local.database.AppDatabase
import com.sigeschool.data.mapper.toDomain
import com.sigeschool.data.mapper.toEntity
import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.model.Attendance
import com.sigeschool.domain.repository.AttendanceRepository
import com.sigeschool.domain.repository.AttendanceWithStudent
import com.sigeschool.domain.repository.GradeAttendance
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AttendanceRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val sessionManager: SessionManager,
    private val database: AppDatabase,
    private val auditRepository: AuditRepository
) : AttendanceRepository {

    private val attendanceDao = database.attendanceDao()

    private fun getInstitutionId(): String {
        return sessionManager.getCurrentInstitutionId() ?: throw Exception("Sesión no encontrada")
    }

    override fun getAllAttendance(): Flow<List<Attendance>> {
        return attendanceDao.getAllAttendance(getInstitutionId()).map { list -> list.map { it.toDomain() } }
    }

    override fun getLatestMovements(limit: Int): Flow<List<AttendanceWithStudent>> {
        return attendanceDao.getLatestMovements(getInstitutionId(), limit).map { list ->
            list.map {
                AttendanceWithStudent(
                    attendance = it.attendance.toDomain(),
                    student = it.student.toDomain()
                )
            }
        }
    }

    override fun getPresentCount(startOfDay: Long, nextDayStart: Long): Flow<Int> {
        return attendanceDao.getPresentCount(getInstitutionId(), startOfDay, nextDayStart)
    }

    override fun getAbsentCount(startOfDay: Long, nextDayStart: Long): Flow<Int> {
        return attendanceDao.getAbsentCount(getInstitutionId(), startOfDay, nextDayStart)
    }

    override fun getAttendanceByGrade(startOfDay: Long, nextDayStart: Long): Flow<List<GradeAttendance>> {
        return attendanceDao.getAttendanceByGrade(getInstitutionId(), startOfDay, nextDayStart).map { list ->
            list.map {
                GradeAttendance(
                    gradoNombre = it.gradoNombre,
                    totalEstudiantes = it.totalEstudiantes,
                    totalPresentes = it.totalPresentes
                )
            }
        }
    }

    override fun getAttendanceByRange(startOfDay: Long, nextDayStart: Long): Flow<List<AttendanceWithStudent>> {
        return attendanceDao.getAttendanceByRange(getInstitutionId(), startOfDay, nextDayStart).map { list ->
            list.map {
                AttendanceWithStudent(
                    attendance = it.attendance.toDomain(),
                    student = it.student.toDomain()
                )
            }
        }
    }

    override suspend fun saveAttendance(attendance: Attendance) {
        attendanceDao.insertAttendance(attendance.toEntity())
        auditRepository.log(
            action = "SAVE_ATTENDANCE",
            resource = "attendance/${attendance.studentId}",
            payload = mapOf("type" to attendance.type.name, "timestamp" to attendance.timestamp)
        )
    }

    override suspend fun saveAttendanceBatch(attendances: List<Attendance>) {
        attendanceDao.insertAttendance(attendances.map { it.toEntity() })
        auditRepository.log(
            action = "SAVE_ATTENDANCE_BATCH",
            resource = "attendance/batch",
            payload = mapOf("count" to attendances.size)
        )
    }

    override suspend fun getLastAttendanceForStudent(studentId: String): Attendance? {
        return attendanceDao.getLastAttendanceForStudent(studentId, getInstitutionId())?.toDomain()
    }

    override suspend fun syncAttendance(): Resource<Unit> {
        return Resource.Success(Unit)
    }

    override fun triggerImmediateSync() {
        // Implementación multiplatform para disparar sync
    }
}
