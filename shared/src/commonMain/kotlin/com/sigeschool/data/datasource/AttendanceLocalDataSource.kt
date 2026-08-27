package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Attendance
import com.sigeschool.domain.model.EmployeeAttendance
import kotlinx.coroutines.flow.Flow

interface AttendanceLocalDataSource {
    fun getAttendanceByDate(fecha: String, institutionId: String): Flow<List<Attendance>>
    fun getAttendanceByStudent(studentId: String, institutionId: String): Flow<List<Attendance>>
    suspend fun saveAttendance(attendance: List<Attendance>)
    suspend fun getUnsyncedAttendance(institutionId: String): List<Attendance>
    suspend fun deleteAll(institutionId: String)
    
    // Employee Attendance
    suspend fun saveEmployeeAttendance(attendance: EmployeeAttendance)
    suspend fun getEmployeeAttendance(employeeId: String, date: String, institutionId: String): EmployeeAttendance?
    suspend fun getEmployeeAttendanceById(id: String, institutionId: String): EmployeeAttendance?
    fun getEmployeeAttendanceByDate(date: String, institutionId: String): Flow<List<EmployeeAttendance>>
    suspend fun getUnsyncedEmployeeAttendance(institutionId: String): List<EmployeeAttendance>
}
