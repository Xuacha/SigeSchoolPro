package com.sigeschool.data.remote

import com.sigeschool.domain.model.Task
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class TaskRemoteDataSource(private val supabaseClient: SupabaseClient) {
    suspend fun getTasks(institutionId: String): List<Task> {
        return supabaseClient.postgrest["tasks"]
            .select(columns = Columns.ALL) {
                filter {
                    eq("institution_id", institutionId)
                }
            }.decodeList<Task>()
    }

    suspend fun getTasksByClass(classId: String): List<Task> {
        return supabaseClient.postgrest["tasks"]
            .select(columns = Columns.ALL) {
                filter {
                    eq("class_id", classId)
                }
            }.decodeList<Task>()
    }

    suspend fun upsertTask(task: Task): Boolean {
        return try {
            supabaseClient.postgrest["tasks"].upsert(task)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteTask(id: String): Boolean {
        return try {
            supabaseClient.postgrest["tasks"].delete {
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
