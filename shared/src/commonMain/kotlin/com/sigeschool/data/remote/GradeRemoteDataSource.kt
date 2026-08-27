package com.sigeschool.data.remote

import com.sigeschool.domain.model.Grade
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class GradeRemoteDataSource {
    private val client = SupabaseClientProvider.client

    suspend fun fetchGradesByInstitution(institutionId: String, period: String? = null): List<Grade> {
        return try {
            client.from("grades").select(Columns.raw("*")) {
                filter {
                    eq("institution_id", institutionId)
                    if (period != null) eq("period", period)
                }
            }.decodeList<Grade>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun syncGrade(grade: Grade): Boolean {
        return try {
            client.from("grades").upsert(grade)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteGrade(id: String, institutionId: String): Boolean {
        return try {
            client.from("grades").delete {
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
