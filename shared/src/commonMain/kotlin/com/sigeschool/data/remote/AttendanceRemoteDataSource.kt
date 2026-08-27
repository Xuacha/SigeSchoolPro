package com.sigeschool.data.remote

import com.sigeschool.domain.model.Attendance
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class AttendanceRemoteDataSource {
    private val client = SupabaseClientProvider.client

    suspend fun fetchAttendanceByInstitution(institutionId: String, date: String? = null): List<Attendance> {
        return try {
            client.from("attendance").select(Columns.raw("*")) {
                filter {
                    eq("institution_id", institutionId)
                    if (date != null) eq("fecha", date)
                }
            }.decodeList<Attendance>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun syncAttendance(attendance: Attendance): Boolean {
        return try {
            client.from("attendance").upsert(attendance)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteAttendance(id: String, institutionId: String): Boolean {
        return try {
            client.from("attendance").delete {
                filter {
                    eq("id", id)
                    eq("institution_id", institutionId)
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}
