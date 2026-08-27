package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.AttendanceLocalDataSource
import com.sigeschool.data.local.dao.AttendanceDao
import com.sigeschool.data.local.entity.toEntity
import com.sigeschool.data.local.entity.AttendanceEntity
import com.sigeschool.domain.model.Attendance
import com.sigeschool.domain.model.EmployeeAttendance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AttendanceLocalDataSourceImpl(
    private val attendanceDao: AttendanceDao
) : AttendanceLocalDataSource {
    
    override fun getAttendanceByDate(fecha: String, institutionId: String): Flow<List<Attendance>> {
        return attendanceDao.getAttendanceByDate(fecha, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAttendanceByStudent(studentId: String, institutionId: String): Flow<List<Attendance>> {
        return attendanceDao.getAttendanceByStudent(studentId, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveAttendance(attendance: List<Attendance>) {
        attendanceDao.insertAll(attendance.map { it.toEntity() })
    }

    override suspend fun getUnsyncedAttendance(institutionId: String): List<Attendance> {
        return attendanceDao.getUnsyncedAttendance(institutionId).map { it.toDomain() }
    }

    override suspend fun deleteAll(institutionId: String) {
        attendanceDao.deleteAll(institutionId)
    }

    override suspend fun saveEmployeeAttendance(attendance: EmployeeAttendance) {
        attendanceDao.insertEmployeeAttendance(attendance.toEntity())
    }

    override suspend fun getEmployeeAttendance(employeeId: String, date: String, institutionId: String): EmployeeAttendance? {
        return attendanceDao.getEmployeeAttendance(employeeId, date, institutionId)?.toDomain()
    }

    override suspend fun getEmployeeAttendanceById(id: String, institutionId: String): EmployeeAttendance? {
        return attendanceDao.getEmployeeAttendanceById(id, institutionId)?.toDomain()
    }

    override fun getEmployeeAttendanceByDate(date: String, institutionId: String): Flow<List<EmployeeAttendance>> {
        return attendanceDao.getEmployeeAttendanceByDate(date, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getUnsyncedEmployeeAttendance(institutionId: String): List<EmployeeAttendance> {
        return attendanceDao.getUnsyncedEmployeeAttendance(institutionId).map { it.toDomain() }
    }
}
