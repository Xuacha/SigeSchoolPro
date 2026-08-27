package com.sigeschool.data.remote

import com.sigeschool.domain.model.Employee
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class EmployeeRemoteDataSource(private val supabaseClient: SupabaseClient) {
    suspend fun getEmployees(institutionId: String): List<Employee> {
        return supabaseClient.postgrest["employees"]
            .select(columns = Columns.ALL) {
                filter {
                    eq("institution_id", institutionId)
                }
            }.decodeList<Employee>()
    }

    suspend fun upsertEmployee(employee: Employee): Boolean {
        return try {
            supabaseClient.postgrest["employees"].upsert(employee)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteEmployee(id: String): Boolean {
        return try {
            supabaseClient.postgrest["employees"].delete {
                filter {
                    eq("id", id)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
